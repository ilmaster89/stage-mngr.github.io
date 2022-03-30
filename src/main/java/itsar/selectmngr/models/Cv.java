package itsar.selectmngr.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity(name = "cvs")
public class Cv {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	String type, name;

	@Lob
	byte[] file;

	@OneToOne
	@JoinColumn(name = "candidato_id", referencedColumnName = "id")
	Candidato candidato;

	public Cv() {
	}

	public Cv(String type, String name, byte[] file, Candidato candidato) {
		super();
		this.type = type;
		this.name = name;
		this.file = file;
		this.candidato = candidato;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

}
