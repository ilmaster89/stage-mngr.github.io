package itsar.selectmngr.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import itsar.selectmngr.daos.CvDao;
import itsar.selectmngr.models.Candidato;
import itsar.selectmngr.models.Cv;

@Service
public class CvService {

	@Autowired
	private CvDao cvDao;

	public Cv saveCV(MultipartFile file, Candidato c) {

		try {
			Cv cv = new Cv(file.getContentType(), file.getOriginalFilename(), file.getBytes(), c);
			cvDao.save(cv);
			return cv;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Optional<Cv> getCV(Integer id) {
		return cvDao.findById(id);
	}

	public List<Cv> allCVS() {
		return cvDao.findAll();
	}

}
