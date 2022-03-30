package itsar.selectmngr.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "date_orali")
public class DataOrali {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@ManyToOne
	@JoinColumn(name = "call_id", referencedColumnName = "id")
	Call call;

	@ManyToOne
	@JoinColumn(name = "corso_id", referencedColumnName = "id")
	Corso corso;

	Date data;

	Integer mail = 0;

	public DataOrali() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Call getCall() {
		return call;
	}

	public void setCall(Call call) {
		this.call = call;
	}

	public Corso getCorso() {
		return corso;
	}

	public void setCorso(Corso corso) {
		this.corso = corso;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getMail() {
		return mail;
	}

	public void setMail(Integer mail) {
		this.mail = mail;
	}

}
