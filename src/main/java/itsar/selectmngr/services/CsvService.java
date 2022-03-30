package itsar.selectmngr.services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import itsar.selectmngr.daos.CsvDao;
import itsar.selectmngr.models.Csv;

@Service
public class CsvService {

	@Autowired
	private CsvDao csvDao;

	public Csv saveCSV(MultipartFile file) {
		try {
			Csv csv = new Csv(file.getContentType(), file.getOriginalFilename(), file.getBytes());
			return csv;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Optional<Csv> getCSV(Integer id) {
		return csvDao.findById(id);
	}

}
