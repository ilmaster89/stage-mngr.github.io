package itsar.selectmngr.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity(name = "csvs")
public class Csv {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	String type, name;

	@Lob
	byte[] file;

	public Csv() {
	}

	public Csv(String type, String name, byte[] file) {
		super();
		this.type = type;
		this.name = name;
		this.file = file;
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

}
