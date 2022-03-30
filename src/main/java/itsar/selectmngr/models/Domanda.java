package itsar.selectmngr.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "domande")
public class Domanda {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	String testo, risp1, risp2, risp3, risp4;

	@OneToOne
	@JoinColumn(name = "arg_id")
	Argomento argomento;

	@Column(name = "risp_corretta")
	Integer risposta;

	@OneToMany(mappedBy = "domanda")
	List<DomandaProva> domandeProva;

	public Domanda() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public Argomento getArgomento() {
		return argomento;
	}

	public void setArgomento(Argomento argomento) {
		this.argomento = argomento;
	}

	public Integer getRisposta() {
		return risposta;
	}

	public void setRisposta(Integer risposta) {
		this.risposta = risposta;
	}

	public String getRisp1() {
		return risp1;
	}

	public void setRisp1(String risp1) {
		this.risp1 = risp1;
	}

	public String getRisp2() {
		return risp2;
	}

	public void setRisp2(String risp2) {
		this.risp2 = risp2;
	}

	public String getRisp3() {
		return risp3;
	}

	public void setRisp3(String risp3) {
		this.risp3 = risp3;
	}

	public String getRisp4() {
		return risp4;
	}

	public void setRisp4(String risp4) {
		this.risp4 = risp4;
	}

	public List<DomandaProva> getDomandeProva() {
		return domandeProva;
	}

	public void setDomandeProva(List<DomandaProva> domandeProva) {
		this.domandeProva = domandeProva;
	}

	@Override
	public String toString() {
		return this.testo;
	}

}
