package itsar.selectmngr.daos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import itsar.selectmngr.models.Corso;

public interface CorsoDao extends CrudRepository<Corso, Integer> {

	@Query(value = "select * from corsi order by id desc", nativeQuery = true)
	List<Corso> allCorsi();

	@Query(value = "select * from corsi where id != 9", nativeQuery = true)
	List<Corso> corsiPrimaScelta();

}
