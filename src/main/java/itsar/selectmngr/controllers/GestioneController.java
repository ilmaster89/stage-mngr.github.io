package itsar.selectmngr.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import itsar.selectmngr.daos.AmmissioneDao;
import itsar.selectmngr.daos.CallDao;
import itsar.selectmngr.daos.CandidatoDao;
import itsar.selectmngr.daos.PunteggiTotaliDao;
import itsar.selectmngr.daos.PunteggioScrittiDao;
import itsar.selectmngr.daos.SogliaDao;
import itsar.selectmngr.models.Candidato;
import itsar.selectmngr.models.PunteggiTotali;
import itsar.selectmngr.models.PunteggioScritti;

@Controller
public class GestioneController {

	@Autowired
	private CandidatoDao cDao;
	@Autowired
	private PunteggiTotaliDao ptDao;
	@Autowired
	private AmmissioneDao aDao;
	@Autowired
	private CallDao callDao;
	@Autowired
	private PunteggioScrittiDao psDao;
	@Autowired
	private SogliaDao sDao;

	@GetMapping("/studenteRitirato/{id}/{idCorso}")
	public String studenteRitirato(Model model, HttpSession session, @PathVariable Integer id,
			@PathVariable Integer idCorso) {

		Candidato c = cDao.candidatoById(id);
		List<PunteggiTotali> ammissioniDaCancellare = ptDao.ammissioniCandidato(id);

		for (PunteggiTotali pt : ammissioniDaCancellare) {
			pt.setAmmissione(aDao.findById(5).get());
			ptDao.save(pt);
		}

		List<PunteggiTotali> q = ptDao.listaAttesaCorso(idCorso);

		for (int i = 0; i < q.size(); i++) {
			if (ptDao.giaAmmesso(q.get(i).getCandidato().getId()) != 1) {
				q.get(i).setAmmissione(aDao.findById(2).get());
				ptDao.save(q.get(i));
				break;
			}
		}

		return "redirect:/dettaglioAmmessi/" + idCorso;
	}

	@GetMapping("/modPagamenti")
	public String modPagamenti(Model model, HttpSession session) {

		List<Candidato> ammessi = cDao.candidatiAmmessiInGenerale();
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		Map<Object, Object> map = new LinkedHashMap<Object, Object>();

		for (Candidato c : ammessi) {

			PunteggiTotali pt = ptDao.ammissioneSingola(c.getId());
			map.put("candidato", c);
			map.put("punteggio", pt);
			list.add(map);

			map = new LinkedHashMap<Object, Object>();
		}

		model.addAttribute("list", list);

		return "modPagamenti";
	}

	@GetMapping("/confermaPagamento/{id}")
	public String confermaPagamento(Model model, HttpSession session, @PathVariable Integer id) {
		PunteggiTotali pt = ptDao.findById(id).get();
		pt.setPagato(1);
		pt.setDataPagamento(Date.valueOf(LocalDate.now()));
		ptDao.save(pt);
		return "redirect:/modPagamenti";
	}

	@GetMapping("/caricaScritti")
	public String caricaScritti(HttpSession session, Model model) {
		model.addAttribute("candidati", cDao.candidatiCall(callDao.idPerScritti()));
		return "caricaScritti";
	}

	@GetMapping("/ritirato/{id}")
	public String ritirato(HttpSession session, Model model, @PathVariable Integer id) {
		Candidato c = cDao.candidatoById(id);
		c.setRitirato(1);
		cDao.save(c);
		return "redirect:/caricaScritti";
	}

	@GetMapping("/assegnaPunteggio/{id}")
	public String assegnaPunteggio(HttpSession session, Model model, @PathVariable Integer id) {
		Candidato c = cDao.candidatoById(id);
		model.addAttribute("candidato", c);

		if (c.getPunteggioScritti() != null) {
			model.addAttribute("punteggi", c.getPunteggioScritti());
		} else {
			PunteggioScritti p = new PunteggioScritti();
			p.setpInglese(0);
			p.setpLogica(0);
			p.setpGrafica(0);
			p.setpICT(0);
			model.addAttribute("punteggi", p);
		}

		return "assegnaPunteggio";
	}

	@PostMapping("/punteggiAssegnati")
	public String punteggiAssegnati(HttpSession session, Model model, PunteggioScritti punteggi,
			@RequestParam("candidatoId") Integer candidatoId) {

		int tot = (punteggi.getpInglese() + punteggi.getpLogica() + punteggi.getpGrafica() + punteggi.getpICT());

		if (tot >= 12)
			punteggi.setAmmessoOrale(1);
		else
			punteggi.setAmmessoOrale(0);
		punteggi.setCandidato(cDao.candidatoById(candidatoId));
		psDao.save(punteggi);
		Candidato c = cDao.candidatoById(candidatoId);

		c.setPunteggioScritti(punteggi);
		cDao.save(c);

		return "redirect:/caricaScritti";

	}

	@GetMapping("/caricaCSV")
	public String caricaCSV(HttpSession session, Model model) {
		return "caricaCSV";
	}

	@PostMapping("/CSVcaricato")
	public String CSVcaricato(HttpSession session, Model model, MultipartFile csv) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(csv.getOriginalFilename())));
			while (br.readLine() != null) {
				System.out.println(br.readLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/caricaScritti";
	}

}
