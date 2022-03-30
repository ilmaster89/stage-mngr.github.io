package itsar.selectmngr.controllers;

import java.io.FileReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;

import itsar.selectmngr.daos.CsvDao;
import itsar.selectmngr.models.Csv;
import itsar.selectmngr.services.CsvService;

@Controller
public class DevController {

	@Autowired
	CsvService cService;
	@Autowired
	CsvDao cDao;

	@GetMapping("/upCSV")
	public String upCSV() {
		return "upCSV";
	}

	@PostMapping("/readCSV")
	public String readCSV(@RequestParam("file") MultipartFile file, Model model) {

		try {
			Csv csv = cService.saveCSV(file);
			cDao.save(csv);

			Csv toRead = cService.getCSV(csv.getId()).get();
			CSVReader cReader = new CSVReader(new FileReader(toRead.getName()));
			List<String[]> list = cReader.readAll();
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "readCSV";
	}

}
