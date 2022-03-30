package itsar.selectmngr.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import itsar.selectmngr.daos.ArgomentoDao;
import itsar.selectmngr.daos.CandidatoDao;
import itsar.selectmngr.daos.DomandaDao;
import itsar.selectmngr.daos.DomandaProvaDao;
import itsar.selectmngr.daos.ProvaDao;
import itsar.selectmngr.dtos.DomandaProvaDTO;
import itsar.selectmngr.models.Candidato;
import itsar.selectmngr.models.Domanda;
import itsar.selectmngr.models.DomandaProva;
import itsar.selectmngr.models.Prova;
import itsar.selectmngr.varie.Constants;

@Controller
public class ProvaController {

	@Autowired
	DomandaDao domandaDao;
	@Autowired
	ProvaDao provaDao;
	@Autowired
	DomandaProvaDao domProvDao;
	@Autowired
	CandidatoDao candidatoDao;
	@Autowired
	ArgomentoDao argomentoDao;

	private List<Prova> genProve(Integer idCandidato) {

		System.out.print(idCandidato);
		List<Prova> prove = new ArrayList<Prova>();

		Candidato c = candidatoDao.candidatoById(idCandidato);

		boolean it = false;
		boolean grafica = false;

		if (c.getPref1().getIt() == 1 || (c.getPref2().getId() != 9 && c.getPref2().getIt() == 1)
				|| (c.getPref3().getId() != 9 && c.getPref3().getIt() == 1))
			it = true;

		if (c.getPref1().getGrafica() == 1 || (c.getPref2().getId() != 9 && c.getPref2().getGrafica() == 1)
				|| (c.getPref3().getId() != 9 && c.getPref3().getGrafica() == 1))
			grafica = true;

		int[] argomentiId = new int[] { Constants.LOGICA, Constants.INGLESE };

		for (int i : argomentiId) {

			Prova p = generaProva(i);
			p.setCandidato(c);
			p.setArgomento(argomentoDao.findById(i).get());

			provaDao.save(p);
			prove.add(p);

		}

		if (it) {
			Prova p = generaProva(Constants.IT);
			p.setCandidato(c);
			p.setArgomento(argomentoDao.findById(Constants.IT).get());
			provaDao.save(p);
			prove.add(p);
		}

		if (grafica) {
			Prova p = generaProva(Constants.GRAFICA);
			p.setCandidato(c);
			p.setArgomento(argomentoDao.findById(Constants.GRAFICA).get());

			provaDao.save(p);
			prove.add(p);
		}

		return prove;
	}

	private Prova generaProva(Integer argomentoId) {

		Prova p = new Prova();
		List<Domanda> domandeRandom = domandaDao.domandePerProva(argomentoId);

		for (Domanda d : domandeRandom) {
			DomandaProva dp = new DomandaProva();
			dp.setDomanda(d);
			dp.setProva(p);
			domProvDao.save(dp);
		}

		return p;
	}

	@GetMapping("/prove")
	public String prove(Model model) {
		model.addAttribute("avviso", "");
		return "loginProva";
	}

	/*@PostMapping("/login")
	public String login(Model model, HttpSession session, @RequestParam("mail") String mail,
			@RequestParam("cf") String cf) {
		Candidato c = candidatoDao.candidatoLogin(mail, cf);
		if (c == null) {
			model.addAttribute("avviso", "Utente non trovato!");
			return "loginProva";
		}

		session.setAttribute("utente", c);
		return "redirect:/proveCandidato/" + c.getId();
	}*/

	@GetMapping("/proveCandidato/{id}")
	public String proveCandidato(Model model, HttpSession session, @PathVariable Integer id) {

		List<Prova> proveCandidato = new ArrayList<Prova>();

		if (provaDao.proveCandidato(id).size() == 0) {
			proveCandidato = genProve(id);
		} else
			proveCandidato = provaDao.proveCandidato(id);

		model.addAttribute("proveCandidato", proveCandidato);

		return "elencoProve";
	}

	@GetMapping("prova/{idProva}")
	public String prova(Model model, HttpSession session, @PathVariable Integer idProva) {

		List<DomandaProva> domandeProva = domProvDao.domandeByProva(idProva);

		model.addAttribute("domande", domandeProva);
		model.addAttribute("prova", provaDao.findById(idProva).get());
		return "prova";
	}

}
