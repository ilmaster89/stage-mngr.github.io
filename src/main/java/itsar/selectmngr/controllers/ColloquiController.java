package itsar.selectmngr.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import itsar.selectmngr.daos.AmmissioneDao;
import itsar.selectmngr.daos.CallDao;
import itsar.selectmngr.daos.CandidatoDao;
import itsar.selectmngr.daos.PunteggiTotaliDao;
import itsar.selectmngr.daos.PunteggioOraliDao;
import itsar.selectmngr.daos.PunteggioScrittiDao;
import itsar.selectmngr.daos.SogliaDao;
import itsar.selectmngr.dtos.PunteggioOraliDTO;
import itsar.selectmngr.models.Candidato;
import itsar.selectmngr.models.Corso;
import itsar.selectmngr.models.Foto;
import itsar.selectmngr.models.PunteggiTotali;
import itsar.selectmngr.models.PunteggioOrali;
import itsar.selectmngr.services.FotoService;
import itsar.selectmngr.varie.Constants;

@Controller
public class ColloquiController {

	@Autowired
	CandidatoDao candidatoDao;
	@Autowired
	CallDao callDao;
	@Autowired
	PunteggioOraliDao poDAO;
	@Autowired
	PunteggiTotaliDao ptDao;
	@Autowired
	PunteggioScrittiDao psDao;
	@Autowired
	AmmissioneDao amDao;
	@Autowired
	SogliaDao sDao;

	@Autowired
	FotoService fService;

	@GetMapping("/colloqui")
	public String colloqui(Model model, HttpSession session) {

		List<Candidato> daEsaminare = candidatoDao.ammessiOraleInCorso(callDao.lastCall().getId());
		for (Candidato c : daEsaminare) {
			System.out.println(c);
		}
		String[] header = new String[] { "Candidato", "Diploma", "CV", "Inglese", "Logica", "Grafica", "ICT",
				"Prima Preferenza", "Seconda Preferenza", "Terza Preferenza", "Note", "Foto" };
		model.addAttribute("candidati", daEsaminare);
		model.addAttribute("header", header);
		return "mainColloqui";

	}

	@GetMapping("/aggiungiFoto/{id}")
	public String aggiungiFoto(Model model, HttpSession session, @PathVariable Integer id) {
		model.addAttribute("candidato", candidatoDao.candidatoById(id));
		return "aggiungiFoto";
	}

	@PostMapping("/fotoAggiunta")
	public String fotoAggiunta(Model model, HttpSession session, @RequestParam("foto") MultipartFile foto,
			@RequestParam("id") Integer id) {

		Candidato c = candidatoDao.candidatoById(id);
		try {
			Foto f = fService.saveFoto(foto, c);
			c.setFoto(f);
			candidatoDao.save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/colloqui";
	}

	@GetMapping("/visualizzaFoto/{id}")
	public ResponseEntity<ByteArrayResource> visualizzaFoto(@PathVariable Integer id) {

		Foto f = fService.getFoto(candidatoDao.candidatoById(id).getFoto().getId()).get();
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(f.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + f.getName() + "\"")
				.body(new ByteArrayResource(f.getFile()));
	}

	@GetMapping("/aggiungiNote/{id}")
	public String aggiungiNote(HttpSession session, Model model, @PathVariable Integer id) {
		model.addAttribute("candidato", candidatoDao.candidatoById(id));
		return "aggiungiNote";
	}

	@PostMapping("/noteAggiunte")
	public String noteAggiunte(HttpSession session, Model model, @RequestParam("id") Integer id,
			@RequestParam("note") String note) {
		Candidato c = candidatoDao.candidatoById(id);
		c.setNote(note);
		candidatoDao.save(c);

		return "redirect:/colloqui";
	}

	@GetMapping("datiColloquio/{id}")
	public String datiColloquio(HttpSession session, Model model, @PathVariable Integer id) {

		Candidato c = candidatoDao.candidatoById(id);
		List<Corso> preferenze = new ArrayList<Corso>();

		preferenze.add(c.getPref1());

		if (c.getPref2().getId() != 9999)
			preferenze.add(c.getPref2());
		if (c.getPref3().getId() != 9999)
			preferenze.add(c.getPref3());

		PunteggioOraliDTO poDTO = new PunteggioOraliDTO();

		List<Integer> punteggiDip = new ArrayList<Integer>();

		for (Corso corso : preferenze) {

			Integer pDip = (corso.getCategoriaCorso().getId() == Constants.ITSGC) ? c.getDiploma().getItsGC()
					: (corso.getCategoriaCorso().getId() == Constants.ITSICT) ? c.getDiploma().getItsICT()
							: (corso.getCategoriaCorso().getId() == Constants.ITSM) ? c.getDiploma().getItsM()
									: (corso.getCategoriaCorso().getId() == Constants.IFTSGC)
											? c.getDiploma().getIftsGC()
											: c.getDiploma().getIftsICT();

			poDTO.addPunteggio(new PunteggioOrali(c, corso, pDip));
			punteggiDip.add(pDip);
		}

		model.addAttribute("preferenze", poDTO);
		model.addAttribute("candidato", c);
		session.setAttribute("corsi", preferenze);
		session.setAttribute("punteggiDip", punteggiDip);

		return "datiColloquio";

	}

	@PostMapping("/caricaRisultati")
	public String caricaRisultati(Model model, HttpSession session, PunteggioOraliDTO poDTO,
			@RequestParam("capEspr") Integer capEspr, @RequestParam("id") Integer id) {

		List<Corso> corsi = (List<Corso>) session.getAttribute("corsi");
		List<Integer> punteggiDip = (List<Integer>) session.getAttribute("punteggiDip");

		Candidato c = candidatoDao.candidatoById(id);
		c.setCapEspressive(capEspr);

		candidatoDao.save(c);
		Integer scritti = psDao.totScrittiCandidato(c.getId());

		for (Integer i = 0; i < poDTO.getPunteggi().size(); i++) {
			poDTO.getPunteggi().get(i).setCandidato(c);
			poDTO.getPunteggi().get(i).setpDiploma(punteggiDip.get(i));
			poDTO.getPunteggi().get(i).setCorso(corsi.get(i));
			poDAO.save(poDTO.getPunteggi().get(i));

			Integer orali = poDAO.totOraliCandidatoCorso(c.getId(), corsi.get(i).getId());
			Integer tot = scritti + orali;

			PunteggiTotali pt = new PunteggiTotali();
			pt.setCandidato(c);
			pt.setCorso(corsi.get(i));
			pt.setPunteggio(tot);
			pt.setPagato(0);
			pt.setData(Date.valueOf(LocalDate.now()));

			Integer ammissione = (tot >= sDao.ammITS()) ? 2 : (tot < sDao.idITS()) ? 4 : 1;

			if (ammissione == 2 && ptDao.giaAmmesso(c.getId()) == 1)
				ammissione = 1;
			if (ammissione == 2 && ptDao.ammessiPerCorso(corsi.get(i).getId()) == corsi.get(i).getMax())
				ammissione = 3;

			pt.setAmmissione(amDao.findById(ammissione).get());

			ptDao.save(pt);
		}

		session.setAttribute("corsi", null);
		session.setAttribute("punteggiDip", null);
		return "redirect:/colloqui";

	}

}
