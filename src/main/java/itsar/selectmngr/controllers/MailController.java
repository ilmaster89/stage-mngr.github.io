package itsar.selectmngr.controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import itsar.selectmngr.daos.CallDao;
import itsar.selectmngr.daos.CandidatoDao;
import itsar.selectmngr.models.Call;
import itsar.selectmngr.models.Candidato;

@Controller
public class MailController {

	@Autowired
	private CandidatoDao candidatoDao;
	@Autowired
	private CallDao callDao;

	private static void sendMail(Address[] recipients, String subject, String text) {

		String user = "a.ramponi@itsrizzoli.it";
		String pass = "pgtntggjwisfbcah";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.setRecipients(Message.RecipientType.TO, recipients);

			message.setSubject(subject);
			message.setText(text);

			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	@GetMapping("/testMail")
	public String testMail() {
		return "testMail";
	}

	public static String textForCall(Candidato candidato, java.util.Date date) {

		String name = candidato.toString();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		String dateStr = sdf.format(date);
		String mail = candidato.getMail();

		String url = "http://localhost:8080/prove";

		String text = "Gentile " + name
				+ "\nA breve parteciperai alle selezioni scritte per la Fondazione ITS Angelo Rizzoli.\n\nLe selezioni si svolgeranno il "
				+ dateStr + ".\nPer parteciparvi, clicca sul seguente link: " + url
				+ "\n\nAlle prove scritte potrai accedere utilizzando la mail che hai usato per candidarti come username ed il tuo codice fiscale come password.\n\n Ti aspettiamo!\nFondazione ITS Rizzoli";

		return text;

	}

	public void sendTestMail(Integer callId, java.util.Date date) {

		List<Candidato> candidati = candidatoDao.candidatiDaAvvisarePerCall(1);
		Call lastCall = callDao.findById(callId).get();

		if (lastCall != null) {
			StringBuilder sb = new StringBuilder();

			for (Candidato c : candidati) {

				String text = textForCall(c, date);
				String subject = "Convocazione selezioni scritte ITS Angelo Rizzoli";

				try {

					Address[] recipient = InternetAddress.parse(c.getMail());
					sendMail(recipient, subject, text);
					c.setCallInviata(1);
					candidatoDao.save(c);

				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			lastCall.setMailInviate(1);
			callDao.save(lastCall);

		}
	}

	@Scheduled(cron = "0 0 9 * * ?")
	public void checkCallsForMail() {

		List<Call> calls = callDao.allCalls();
		LocalDate now = LocalDate.now();

		for (Call c : calls) {
			Date date = c.getData();
			LocalDate convertedDate = date.toLocalDate();
			Long today = now.getLong(ChronoField.DAY_OF_YEAR);
			Long callDay = convertedDate.getLong(ChronoField.DAY_OF_YEAR);

			java.util.Date callDate = new java.util.Date(date.getTime());

			if ((callDay - today) == 2) {
				sendTestMail(c.getId(), callDate);
			}
		}
	}
}
