package itsar.selectmngr.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "diplomi")
public class Diploma {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	String tipo;

	Integer itsGC, itsICT, itsM, iftsGC, iftsICT;

	public Diploma() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getItsGC() {
		return itsGC;
	}

	public void setItsGC(Integer itsGC) {
		this.itsGC = itsGC;
	}

	public Integer getItsICT() {
		return itsICT;
	}

	public void setItsICT(Integer itsICT) {
		this.itsICT = itsICT;
	}

	public Integer getItsM() {
		return itsM;
	}

	public void setItsM(Integer itsM) {
		this.itsM = itsM;
	}

	public Integer getIftsGC() {
		return iftsGC;
	}

	public void setIftsGC(Integer iftsGC) {
		this.iftsGC = iftsGC;
	}

	public Integer getIftsICT() {
		return iftsICT;
	}

	public void setIftsICT(Integer iftsICT) {
		this.iftsICT = iftsICT;
	}

}
