package itsar.selectmngr.daos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import itsar.selectmngr.models.Call;

public interface CallDao extends CrudRepository<Call, Integer> {

	@Query(value = "select * from calls where mail_inviate = 0 order by id desc limit 1", nativeQuery = true)
	Call lastCall();

	@Query(value = "select id from calls where mail_inviate = 1 order by id desc limit 1", nativeQuery = true)
	Integer idPerScritti();

	@Query(value = "select * from calls", nativeQuery = true)
	List<Call> allCalls();

	@Query(value = "select * from calls", nativeQuery = true)
	List<Call> prossimeCall();

	@Query(value = "select numero from calls order by id desc limit 1", nativeQuery = true)
	Integer lastN();

}
