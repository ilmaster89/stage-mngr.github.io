package itsar.selectmngr.daos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import itsar.selectmngr.models.Diploma;

public interface DiplomaDao extends CrudRepository<Diploma, Integer> {

	@Query(value = "select * from diplomi", nativeQuery = true)
	List<Diploma> allDip();

}
