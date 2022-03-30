package itsar.selectmngr.daos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import itsar.selectmngr.models.Candidato;

public interface CandidatoDao extends CrudRepository<Candidato, Integer> {

	@Query(value = "select * from candidati", nativeQuery = true)
	List<Candidato> allCandidati();

	@Query(value = "select * from candidati where id = :id", nativeQuery = true)
	Candidato candidatoById(Integer id);

	@Query(value = "select * from candidati where dsa = 1 and ritirato = 0", nativeQuery = true)
	List<Candidato> candidatiDSA();

	@Query(value = "select * from candidati where dsa = 0 and ritirato = 0", nativeQuery = true)
	List<Candidato> candidatiNonDSA();

	@Query(value = "select * from candidati where cf = :cf", nativeQuery = true)
	Candidato candidatoByCF(String cf);

	@Query(value = "select * from candidati where mail = :mail and cf = :cf", nativeQuery = true)
	Candidato candidatoLogin(String mail, String cf);

	@Query(value = "select * from candidati where call_id = :idCall and call_inviata = 0 and ritirato = 0", nativeQuery = true)
	List<Candidato> candidatiDaAvvisarePerCall(Integer idCall);

	@Query(value = "select * from candidati join punteggio_scritti on candidati.id = punteggio_scritti.candidato_id"
			+ " left join punteggio_orali on punteggio_orali.candidato_id = candidati.id"
			+ " where call_id = :idCall and ammesso_orale = 1 and punteggio_orali.candidato_id is null order by ora_orale and candidati.ritirato = 0", nativeQuery = true)
	List<Candidato> ammessiOraleInCorso(Integer idCall);

	@Query(value = "select candidati.* from candidati join punteggi_totali on punteggi_totali.candidato_id = candidati.id"
			+ " where ammissione_id = 2 and corso_id = :idCorso order by punteggio desc", nativeQuery = true)
	List<Candidato> ammessiPerCorso(Integer idCorso);

	@Query(value = "select candidati.* from candidati join punteggi_totali on punteggi_totali.candidato_id = candidati.id"
			+ " where ammissione_id = 2 order by cognome", nativeQuery = true)
	List<Candidato> candidatiAmmessiInGenerale();

	@Query(value = "select * from candidati where call_id = :idCall and call_inviata = 1 and ritirato = 0", nativeQuery = true)
	List<Candidato> candidatiCall(Integer idCall);

}
