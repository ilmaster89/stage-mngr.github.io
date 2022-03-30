package itsar.selectmngr.daos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import itsar.selectmngr.models.Domanda;

public interface DomandaDao extends CrudRepository<Domanda, Integer> {

	@Query(value = "select * from domande", nativeQuery = true)
	List<Domanda> allDomande();

	@Query(value = "select * from domande where argomento_id = :argId", nativeQuery = true)
	List<Domanda> domandePerArgomento(Integer argId);

	@Query(value = "select * from domande where argomento_id = :argId order by rand() limit 20", nativeQuery = true)
	List<Domanda> domandePerProva(Integer argId);
}
