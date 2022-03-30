package itsar.selectmngr.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import itsar.selectmngr.daos.UtenteDao;
import itsar.selectmngr.models.Utente;

@Controller
public class MainController {

	@Autowired
	private UtenteDao utenteDao;

	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute("avviso", "");
		return "login";
	}

	@PostMapping("/login")
	public String logged(Model model, HttpSession session, @RequestParam("user") String user,
			@RequestParam("pass") String pass) {

		Utente u = utenteDao.utenteLogin(user, pass);

		if (u == null) {
			model.addAttribute("avviso", "Utente non trovato!");
			return "login";
		}

		session.setAttribute("loggedUser", u);
		return "mainPage";

	}

	@GetMapping("/mainMenu")
	public String mainMenu(Model model, HttpSession session) {
		return "mainPage";
	}

	@GetMapping("/cambiaUtente")
	public String logout(Model model, HttpSession session) {
		session.setAttribute("loggedUser", null);
		return "redirect:/";
	}
	
	

}
