package itsar.selectmngr.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import itsar.selectmngr.daos.CallDao;
import itsar.selectmngr.daos.CorsoDao;
import itsar.selectmngr.daos.DataOraliDao;
import itsar.selectmngr.models.Call;
import itsar.selectmngr.models.DataOrali;

@Controller
public class CallController {

	@Autowired
	private CallDao callDao;
	@Autowired
	private CorsoDao corsoDao;
	@Autowired
	private DataOraliDao doDao;

	@GetMapping("/listaCall")
	public String listaCall(Model model, HttpSession session) {
		model.addAttribute("calls", callDao.prossimeCall());
		Integer lastNumber = (callDao.lastN() == null) ? 0 : callDao.lastN();
		model.addAttribute("lastN", lastNumber);
		return "listaCall";
	}

	@GetMapping("/addCall/{number}")
	public String addCall(Model model, HttpSession session, @PathVariable Integer number) {

		model.addAttribute("call", new Call());
		model.addAttribute("newN", number + 1);

		return "addCall";
	}

	@PostMapping("/callAdded")
	public String callAdded(Model model, HttpSession session, Call call) {

		Integer lastNumber = (callDao.lastN() == null) ? 0 : callDao.lastN();
		call.setNumero(lastNumber + 1);
		call.setMailInviate(0);
		call.setMailOrali(0);
		callDao.save(call);
		return "redirect:/listaCall";
	}

	@GetMapping("modCall/{id}")
	public String modCall(Model model, HttpSession session, @PathVariable Integer id) {

		Call c = callDao.findById(id).get();
		model.addAttribute("call", c);

		return "modCall";
	}

	@GetMapping("/dateOrali/{id}")
	public String dateOrali(Model model, HttpSession session, @PathVariable Integer id) {

		Call c = callDao.findById(id).get();
		model.addAttribute("call", c);

		return "dateOrali";
	}

	@GetMapping("/addOrale/{id}")
	public String addOrale(Model model, HttpSession session, @PathVariable Integer id) {

		model.addAttribute("dataOrali", new DataOrali());
		model.addAttribute("call", callDao.findById(id).get());
		model.addAttribute("corsi", corsoDao.allCorsi());

		return "addOrale";
	}

	@PostMapping("/oraleAdded")
	public String oraleAdded(Model model, HttpSession session, DataOrali dataOrali,
			@RequestParam("callId") Integer callId) {

		dataOrali.setCall(callDao.findById(callId).get());
		doDao.save(dataOrali);
		return "redirect:/dateOrali/" + dataOrali.getCall().getId();

	}

	@GetMapping("/eraseCall/{id}")
	public String eraseCall(Model model, HttpSession session, @PathVariable Integer id) {

		callDao.delete(callDao.findById(id).get());
		return "redirect:/listaCall";
	}

	@GetMapping("/eraseOrale/{id}/{callId}")
	public String eraseOrale(Model model, HttpSession session, @PathVariable Integer id, @PathVariable Integer callId) {

		doDao.delete(doDao.findById(id).get());

		return "redirect:/dateOrali/" + callId;
	}

	@GetMapping("/modOrali/{id}")
	public String modOrali(Model model, HttpSession session, @PathVariable Integer id) {

		model.addAttribute("dataOrali", doDao.findById(id).get());
		return "modOrali";
	}

	@PostMapping("/callModded")
	public String callModded(Model model, HttpSession session, Call call) {
		callDao.save(call);
		return "redirect:/listaCall";
	}

	@PostMapping("/oraleModded")
	public String oraleModded(Model model, HttpSession session, DataOrali dataOrali) {
		doDao.save(dataOrali);
		return "redirect:/dateOrali/" + dataOrali.getCall().getId();
	}

}
