package itsar.selectmngr.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

import itsar.selectmngr.daos.CallDao;
import itsar.selectmngr.daos.CandidatoDao;
import itsar.selectmngr.daos.CorsoDao;
import itsar.selectmngr.daos.DiplomaDao;
import itsar.selectmngr.daos.PunteggiTotaliDao;
import itsar.selectmngr.daos.RuoloDao;
import itsar.selectmngr.daos.UtenteDao;
import itsar.selectmngr.models.Candidato;
import itsar.selectmngr.models.Corso;
import itsar.selectmngr.models.Cv;
import itsar.selectmngr.models.Diploma;
import itsar.selectmngr.models.Utente;
import itsar.selectmngr.services.CvService;
import itsar.selectmngr.varie.Constants;

@Controller
public class CandidatoController {

	@Autowired
	CandidatoDao candidatoDao;
	@Autowired
	CorsoDao corsoDao;
	@Autowired
	CallDao callDao;
	@Autowired
	PunteggiTotaliDao ptDAO;
	@Autowired
	DiplomaDao diplomaDao;
	@Autowired
	UtenteDao uDao;
	@Autowired
	RuoloDao rDao;

	@Autowired
	CvService cvService;

	@GetMapping("/formCandidato")
	public String formCandidato(Model model) {

		List<Diploma> diplomi = diplomaDao.allDip();
		List<Corso> corsi = corsoDao.allCorsi();
		List<Corso> corsiPrimaScelta = corsoDao.corsiPrimaScelta();

		List<Corso> corsiOK = new ArrayList<Corso>();
		List<Corso> corsiPrimaOK = new ArrayList<Corso>();

		for (Corso c : corsi) {
			if (c.getMax() > ptDAO.ammessiPerCorso(c.getId())) {
				corsiOK.add(c);
			}
		}
		for (Corso c : corsiPrimaScelta) {
			if (c.getMax() > ptDAO.ammessiPerCorso(c.getId())) {
				corsiPrimaOK.add(c);
			}
		}
		model.addAttribute("diplomi", diplomi);
		model.addAttribute("avviso", "");
		model.addAttribute("candidato", new Candidato());
		model.addAttribute("corsi", corsiOK);
		model.addAttribute("primaScelta", corsiPrimaOK);

		return "formCandidato";
	}

	@PostMapping("/addCandidato")
	public String addCandidato(Model model, Candidato candidato, @RequestParam("cv") MultipartFile cv) {

		candidato.setCall(callDao.lastCall());
		candidato.setCallInviata(0);
		candidato.setDataCreazione(Date.valueOf(LocalDate.now()));
		candidatoDao.save(candidato);
		try {

			Cv nCv = cvService.saveCV(cv, candidato);
			Candidato oldC = candidatoDao.candidatoById(candidato.getId());
			oldC.setCurriculum(nCv);
			candidatoDao.save(oldC);

			uDao.save(new Utente(candidato.getMail(), candidato.getCf(), rDao.findById(Constants.CANDIDATO).get()));

		} catch (Exception e) {
			model.addAttribute("avviso", "Alcuni campi obbligatori non sono stati compilati!");
			model.addAttribute("candidato", new Candidato());
			model.addAttribute("corsi", corsoDao.allCorsi());
			model.addAttribute("primaScelta", corsoDao.corsiPrimaScelta());
			return "formCandidato";
		}

		return "candidatoAggiunto";
	}

	@GetMapping("/listCandidati")
	public String listCandidati(Model model) {
		model.addAttribute("cvs", cvService.allCVS());
		return "listCandidati";
	}

	@GetMapping("downloadFile/{id}")
	public ResponseEntity<ByteArrayResource> downloadCV(@PathVariable Integer id) {

		Cv cv = cvService.getCV(id).get();
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(cv.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline ; filename=\"" + cv.getName() + "\"")
				.body(new ByteArrayResource(cv.getFile()));

	}

}
