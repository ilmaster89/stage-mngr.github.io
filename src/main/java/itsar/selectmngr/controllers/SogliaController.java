package itsar.selectmngr.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import itsar.selectmngr.daos.SogliaDao;
import itsar.selectmngr.models.Soglia;

@Controller
public class SogliaController {

	@Autowired
	private SogliaDao sDao;

	@GetMapping("/modSoglia")
	public String modSoglia(Model model, HttpSession session) {

		model.addAttribute("soglia", sDao.unicaSoglia());
		return "modSoglia";

	}

	@PostMapping("/sogliaModded")
	public String sogliaModded(Model model, HttpSession session, Soglia soglia) {
		sDao.save(soglia);
		return "mainPage";
	}
}
