package itsar.selectmngr.daos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import itsar.selectmngr.models.Prova;

public interface ProvaDao extends CrudRepository<Prova, Integer> {
	
	@Query(value="select * from prove", nativeQuery=true)
	List<Prova> allProve();
	
	@Query(value="select * from prove where id = :id", nativeQuery=true)
	Prova provaById(Integer id);
	
	@Query(value="select * from prove where candidato_id = :candidatoId", nativeQuery=true)
	List<Prova> proveCandidato(Integer candidatoId);

}
