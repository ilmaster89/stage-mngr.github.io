package itsar.selectmngr.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import itsar.selectmngr.daos.CategoriaCorsoDao;
import itsar.selectmngr.daos.CorsoDao;
import itsar.selectmngr.models.Corso;

@Controller
public class CorsoController {

	@Autowired
	private CorsoDao cDao;
	@Autowired
	private CategoriaCorsoDao catDao;

	@GetMapping("/listaCorsi")
	public String listaCorsi(Model model, HttpSession session) {
		model.addAttribute("corsi", cDao.allCorsi());
		return "listaCorsi";
	}

	@GetMapping("/nuovoCorso")
	public String nuovoCorso(Model model, HttpSession session) {

		model.addAttribute("corso", new Corso());
		model.addAttribute("cats", catDao.allCats());

		return "nuovoCorso";
	}

	@PostMapping("/corsoAggiunto")
	public String corsoAggiunto(Model model, HttpSession session, Corso corso) {
		cDao.save(corso);
		return "redirect:/listaCorsi";
	}

	@GetMapping("/modCorso/{id}")
	public String modCorso(Model model, HttpSession session, @PathVariable Integer id) {

		model.addAttribute("corso", cDao.findById(id).get());
		model.addAttribute("cats", catDao.allCats());
		return "modCorso";
	}

	@PostMapping("/corsoModded")
	public String corsoModded(Model model, HttpSession session, Corso corso) {
		cDao.save(corso);
		return "redirect:/listaCorsi";
	}
}
