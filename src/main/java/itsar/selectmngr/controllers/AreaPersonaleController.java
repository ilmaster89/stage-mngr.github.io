package itsar.selectmngr.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import itsar.selectmngr.daos.CandidatoDao;
import itsar.selectmngr.daos.CorsoDao;
import itsar.selectmngr.daos.PunteggiTotaliDao;
import itsar.selectmngr.models.Candidato;
import itsar.selectmngr.models.Utente;

@Controller
public class AreaPersonaleController {

	@Autowired
	PunteggiTotaliDao ptDao;
	@Autowired
	CorsoDao cDao;
	@Autowired
	CandidatoDao canDao;

	@GetMapping("/mostraStato")
	public String mostraStato(Model model, HttpSession session) {

		Utente u = (Utente) session.getAttribute("loggedUser");
		Candidato c = canDao.candidatoByCF(u.getPass());
		model.addAttribute("stati", ptDao.ammissioniCandidato(c.getId()));
		model.addAttribute("candidato", c);

		return "mostraStato";

	}

}
