package itsar.selectmngr.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "punteggio_orali")
public class PunteggioOrali {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@ManyToOne
	@JoinColumn(name = "candidato_id")
	Candidato candidato;

	@ManyToOne
	@JoinColumn(name = "corso_id")
	Corso corso;

	@Column(name = "p_motivazione")
	Integer pMotivazione;

	@Column(name = "p_conoscenza")
	Integer pConoscenza;

	@Column(name = "p_coerenza")
	Integer pCoerenza;

	@Column(name = "p_diploma")
	Integer pDiploma;

	public PunteggioOrali() {
	}

	public PunteggioOrali(Candidato candidato, Corso corso, Integer pDiploma) {
		this.candidato = candidato;
		this.corso = corso;
		this.pDiploma = pDiploma;
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

	public Corso getCorso() {
		return corso;
	}

	public void setCorso(Corso corso) {
		this.corso = corso;
	}

	public Integer getpMotivazione() {
		return pMotivazione;
	}

	public void setpMotivazione(Integer pMotivazione) {
		this.pMotivazione = pMotivazione;
	}

	public Integer getpConoscenza() {
		return pConoscenza;
	}

	public void setpConoscenza(Integer pConoscenza) {
		this.pConoscenza = pConoscenza;
	}

	public Integer getpCoerenza() {
		return pCoerenza;
	}

	public void setpCoerenza(Integer pCoerenza) {
		this.pCoerenza = pCoerenza;
	}

	public Integer getpDiploma() {
		return pDiploma;
	}

	public void setpDiploma(Integer pDiploma) {
		this.pDiploma = pDiploma;
	}

}
