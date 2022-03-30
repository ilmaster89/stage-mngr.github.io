package itsar.selectmngr.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import itsar.selectmngr.daos.FotoDao;
import itsar.selectmngr.models.Candidato;
import itsar.selectmngr.models.Foto;

@Service
public class FotoService {

	@Autowired
	private FotoDao fotoDao;

	public Foto saveFoto(MultipartFile file, Candidato c) {

		try {
			Foto f = new Foto(file.getContentType(), file.getOriginalFilename(), file.getBytes(), c);
			fotoDao.save(f);
			return f;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	public Optional<Foto> getFoto(Integer id) {
		return fotoDao.findById(id);
	}

	public List<Foto> allFotos() {
		return fotoDao.findAll();
	}

}
