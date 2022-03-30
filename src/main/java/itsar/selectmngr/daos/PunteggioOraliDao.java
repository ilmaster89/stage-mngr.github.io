package itsar.selectmngr.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import itsar.selectmngr.models.PunteggioOrali;

public interface PunteggioOraliDao extends CrudRepository<PunteggioOrali, Integer> {

	@Query(value = "select p_motivazione + p_conoscenza + p_coerenza"
			+ " from punteggio_orali where candidato_id = :idCandidato"
			+ " and corso_id = :idCorso", nativeQuery = true)
	Integer totOraliCandidatoCorso(Integer idCandidato, Integer idCorso);
}
