package itsar.selectmngr.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import itsar.selectmngr.models.Soglia;

public interface SogliaDao extends CrudRepository<Soglia, Integer> {

	@Query(value = "select ammITS from soglie", nativeQuery = true)
	Integer ammITS();

	@Query(value = "select ammIFTS from soglie", nativeQuery = true)
	Integer ammIFTS();

	@Query(value = "select idITS from soglie", nativeQuery = true)
	Integer idITS();

	@Query(value = "select idIFTS from soglie", nativeQuery = true)
	Integer idIFTS();

	@Query(value = "select * from soglie limit 1", nativeQuery = true)
	Soglia unicaSoglia();

}
