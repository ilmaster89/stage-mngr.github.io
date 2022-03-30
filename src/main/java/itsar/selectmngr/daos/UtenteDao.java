package itsar.selectmngr.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import itsar.selectmngr.models.Utente;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import itsar.selectmngr.models.Utente;

public interface UtenteDao extends CrudRepository<Utente, Integer> {

	@Query(value = "select * from utenti where user = :user and pass = :pass", nativeQuery = true)
	Utente utenteLogin(String user, String pass);

}
