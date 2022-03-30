package itsar.selectmngr.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "calls")
public class Call {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	Integer numero;

	Date data;

	@OneToMany(mappedBy = "call")
	List<DataOrali> dateOrali;

	@Column(name = "mail_inviate")
	Integer mailInviate;
	@Column(name = "mail_orali")
	Integer mailOrali;

	public Call() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getMailInviate() {
		return mailInviate;
	}

	public void setMailInviate(Integer mailInviate) {
		this.mailInviate = mailInviate;
	}

	public List<DataOrali> getDateOrali() {
		return dateOrali;
	}

	public void setDateOrali(List<DataOrali> dateOrali) {
		this.dateOrali = dateOrali;
	}

	public Integer getMailOrali() {
		return mailOrali;
	}

	public void setMailOrali(Integer mailOrali) {
		this.mailOrali = mailOrali;
	}

}
