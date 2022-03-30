package itsar.selectmngr.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "argomenti")
public class Argomento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	String argomento;

	public Argomento() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArgomento() {
		return argomento;
	}

	public void setArgomento(String argomento) {
		this.argomento = argomento;
	}

	@Override
	public String toString() {
		return this.argomento;
	}

}
