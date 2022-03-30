package itsar.selectmngr.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import itsar.selectmngr.daos.CandidatoDao;
import itsar.selectmngr.daos.CorsoDao;
import itsar.selectmngr.daos.PunteggiTotaliDao;
import itsar.selectmngr.models.Candidato;
import itsar.selectmngr.models.Corso;

@Controller
public class AmmessiController {

	@Autowired
	CorsoDao corsoDao;
	@Autowired
	PunteggiTotaliDao ptDAO;
	@Autowired
	CandidatoDao candidatoDao;

	@GetMapping("/ammessiPerCorso")
	public String ammessiPerCorso(Model model, HttpSession session) {
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		Map<Object, Object> map = new LinkedHashMap<Object, Object>();

		List<Corso> corsi = corsoDao.corsiPrimaScelta();

		for (Corso c : corsi) {
			map.put("corso", c);
			map.put("numero", ptDAO.ammessiPerCorso(c.getId()));
			list.add(map);
			map = new LinkedHashMap<Object, Object>();
		}

		model.addAttribute("list", list);
		return "ammessiPerCorso";

	}

	@GetMapping("/dettaglioAmmessi/{id}")
	public String dettaglioAmmessi(Model model, HttpSession session, @PathVariable Integer id) {

		List<Candidato> ammessi = candidatoDao.ammessiPerCorso(id);
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		Map<Object, Object> mappa = new LinkedHashMap<Object, Object>();

		for (Candidato c : ammessi) {
			mappa.put("candidato", c);
			mappa.put("punteggio", ptDAO.punteggioCandidato(c.getId(), id));
			list.add(mappa);
			mappa = new LinkedHashMap<Object, Object>();
		}
		model.addAttribute("ammessi", list);
		model.addAttribute("corso", corsoDao.findById(id).get());
		return "dettaglioAmmessi";
	}

}
