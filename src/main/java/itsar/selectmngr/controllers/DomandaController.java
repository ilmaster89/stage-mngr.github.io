package itsar.selectmngr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import itsar.selectmngr.daos.ArgomentoDao;
import itsar.selectmngr.daos.DomandaDao;
import itsar.selectmngr.models.Domanda;

@Controller
public class DomandaController {

	@Autowired
	private DomandaDao domandaDao;
	@Autowired
	private ArgomentoDao argomentoDao;

	@GetMapping("/formDomanda")
	public String formDomanda(Model model) {
		model.addAttribute("domanda", new Domanda());
		model.addAttribute("argomenti", argomentoDao.allArgomenti());
		return "formDomanda";
	}

	@PostMapping("/addDomanda")
	public String addDomanda(Model model, Domanda domanda) {
		domandaDao.save(domanda);
		return "addOK";
	}
}
