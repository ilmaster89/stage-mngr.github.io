package itsar.selectmngr.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "soglie")
public class Soglia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	Integer ammITS, ammIFTS, idITS, idIFTS;

	public Soglia() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAmmITS() {
		return ammITS;
	}

	public void setAmmITS(Integer ammITS) {
		this.ammITS = ammITS;
	}

	public Integer getAmmIFTS() {
		return ammIFTS;
	}

	public void setAmmIFTS(Integer ammIFTS) {
		this.ammIFTS = ammIFTS;
	}

	public Integer getIdITS() {
		return idITS;
	}

	public void setIdITS(Integer idITS) {
		this.idITS = idITS;
	}

	public Integer getIdIFTS() {
		return idIFTS;
	}

	public void setIdIFTS(Integer idIFTS) {
		this.idIFTS = idIFTS;
	}

}
