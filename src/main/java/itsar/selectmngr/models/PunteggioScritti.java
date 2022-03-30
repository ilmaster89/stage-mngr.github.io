package itsar.selectmngr.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "punteggio_scritti")
public class PunteggioScritti {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@OneToOne
	@JoinColumn(name = "candidato_id", referencedColumnName = "id")
	Candidato candidato;

	@Column(name = "p_inglese")
	Integer pInglese;
	@Column(name = "p_logica")
	Integer pLogica;
	@Column(name = "p_grafica")
	Integer pGrafica;
	@Column(name = "p_ict")
	Integer pICT;

	@Column(name = "ammesso_orale")
	Integer ammessoOrale;

	@Column(name = "ora_orale")
	Date oraOrale;

	public PunteggioScritti() {
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

	public Integer getpInglese() {
		return pInglese;
	}

	public void setpInglese(Integer pInglese) {
		this.pInglese = pInglese;
	}

	public Integer getpLogica() {
		return pLogica;
	}

	public void setpLogica(Integer pLogica) {
		this.pLogica = pLogica;
	}

	public Integer getpGrafica() {
		return pGrafica;
	}

	public void setpGrafica(Integer pGrafica) {
		this.pGrafica = pGrafica;
	}

	public Integer getpICT() {
		return pICT;
	}

	public void setpICT(Integer pICT) {
		this.pICT = pICT;
	}

	public Integer getAmmessoOrale() {
		return ammessoOrale;
	}

	public void setAmmessoOrale(Integer ammessoOrale) {
		this.ammessoOrale = ammessoOrale;
	}

	public Date getOraOrale() {
		return oraOrale;
	}

	public void setOraOrale(Date oraOrale) {
		this.oraOrale = oraOrale;
	}

}
