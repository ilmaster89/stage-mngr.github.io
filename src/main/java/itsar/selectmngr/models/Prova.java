package itsar.selectmngr.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "prove")
public class Prova {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@OneToOne
	@JoinColumn(name = "candidato_id")
	Candidato candidato;

	@OneToOne
	@JoinColumn(name = "argomento_id")
	Argomento argomento;

	@OneToMany(mappedBy = "prova")
	List<DomandaProva> domandeProva;

	public Prova() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public List<DomandaProva> getDomandeProva() {
		return domandeProva;
	}

	public void setDomandeProva(List<DomandaProva> domandeProva) {
		this.domandeProva = domandeProva;
	}

	public Argomento getArgomento() {
		return argomento;
	}

	public void setArgomento(Argomento argomento) {
		this.argomento = argomento;
	}

}
