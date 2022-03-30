package itsar.selectmngr.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import itsar.selectmngr.varie.Constants;

@Entity(name = "utenti")
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	String user, pass;

	@OneToOne
	@JoinColumn(name = "ruolo_id", referencedColumnName = "id")
	Ruolo ruolo;

	public Utente() {
	}

	public Utente(String user, String pass, Ruolo ruolo) {
		super();
		this.user = user;
		this.pass = pass;
		this.ruolo = ruolo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	public boolean isEsaminatore() {
		return this.ruolo.getId() == Constants.ESAMINATORE;
	}

	public boolean isGestore() {
		return this.ruolo.getId() == Constants.GESTORE;
	}

	public boolean isSuper() {
		return this.ruolo.getId() == Constants.SUPER;
	}

	public boolean isCand() {
		return this.ruolo.getId() == Constants.CANDIDATO;
	}

}
