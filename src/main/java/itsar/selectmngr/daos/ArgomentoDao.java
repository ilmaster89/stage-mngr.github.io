package itsar.selectmngr.daos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import itsar.selectmngr.models.Argomento;

public interface ArgomentoDao extends CrudRepository<Argomento, Integer> {

	@Query(value = "select * from argomenti", nativeQuery = true)
	List<Argomento> allArgomenti();

}
