package itsar.selectmngr.daos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import itsar.selectmngr.models.CategoriaCorso;

public interface CategoriaCorsoDao extends CrudRepository<CategoriaCorso, Integer> {

	@Query(value = "select * from cat_corsi", nativeQuery = true)
	List<CategoriaCorso> allCats();

}
