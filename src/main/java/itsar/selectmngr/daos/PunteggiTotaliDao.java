package itsar.selectmngr.daos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import itsar.selectmngr.models.PunteggiTotali;

public interface PunteggiTotaliDao extends CrudRepository<PunteggiTotali, Integer> {

	@Query(value = "select count(id) from punteggi_totali where ammissione_id = 2 and corso_id = :idCorso", nativeQuery = true)
	Integer ammessiPerCorso(Integer idCorso);

	@Query(value = "select punteggio from punteggi_totali where candidato_id = :idCandidato and corso_id = :idCorso", nativeQuery = true)
	Integer punteggioCandidato(Integer idCandidato, Integer idCorso);

	@Query(value = "select count(id) from punteggi_totali where candidato_id = :idCandidato and ammissione_id = 2", nativeQuery = true)
	Integer giaAmmesso(Integer idCandidato);

	@Query(value = "select * from punteggi_totali where candidato_id = :idCandidato and ammissione_id != 5", nativeQuery = true)
	List<PunteggiTotali> ammissioniCandidato(Integer idCandidato);

	@Query(value = "select * from punteggi_totali where candidato_id = :idCandidato and ammissione_id = 2", nativeQuery = true)
	PunteggiTotali ammissioneSingola(Integer idCandidato);

	@Query(value = "select * from punteggi_totali where corso_id = :idCorso and ammissione_id = 3 order by punteggio desc", nativeQuery = true)
	List<PunteggiTotali> listaAttesaCorso(Integer idCorso);

}
