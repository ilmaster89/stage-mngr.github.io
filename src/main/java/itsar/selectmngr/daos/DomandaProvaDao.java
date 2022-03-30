package itsar.selectmngr.daos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import itsar.selectmngr.models.DomandaProva;

public interface DomandaProvaDao extends CrudRepository<DomandaProva, Integer> {

	@Query(value = "select * from domande_prova where prova_id = :provaId", nativeQuery = true)
	List<DomandaProva> domandeByProva(Integer provaId);

}
