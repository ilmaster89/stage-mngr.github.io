package itsar.selectmngr.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import itsar.selectmngr.models.PunteggioScritti;

public interface PunteggioScrittiDao extends CrudRepository<PunteggioScritti, Integer> {

	@Query(value = "select p_inglese + p_logica + p_grafica + p_ict from punteggio_scritti where candidato_id = :idCandidato", nativeQuery = true)
	Integer totScrittiCandidato(Integer idCandidato);

}
