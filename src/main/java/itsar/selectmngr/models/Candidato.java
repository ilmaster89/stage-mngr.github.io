package itsar.selectmngr.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "candidati")
public class Candidato {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	String nome, cognome, sesso, mail, telefono, cf, note;

	Integer eta, dsa, univ, occupato;

	@Column(name = "data_creazione")
	Date dataCreazione;

	@Column(name = "scuola_provenienza")
	String scuolaProvenienza;

	@Column(name = "cap_espressive")
	Integer capEspressive;

	@OneToOne
	@JoinColumn(name = "pref1_id")
	Corso pref1;

	@OneToOne
	@JoinColumn(name = "pref2_id")
	Corso pref2;

	@OneToOne
	@JoinColumn(name = "pref3_id")
	Corso pref3;

	@OneToOne
	@JoinColumn(name = "call_id")
	Call call;

	@Column(name = "call_inviata")
	Integer callInviata;

	@OneToOne
	@JoinColumn(name = "scritti_id", referencedColumnName = "id")
	PunteggioScritti punteggioScritti;

	@OneToOne
	@JoinColumn(name = "foto_id", referencedColumnName = "id")
	Foto foto;

	@OneToMany(mappedBy = "candidato")
	List<PunteggioOrali> punteggioOrali;

	@OneToOne
	@JoinColumn(name = "diploma_id", referencedColumnName = "id")
	Diploma diploma;

	@OneToOne
	@JoinColumn(name = "cv_id", referencedColumnName = "id")
	Cv curriculum;

	Integer ritirato;

	public Candidato() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getEta() {
		return eta;
	}

	public void setEta(Integer eta) {
		this.eta = eta;
	}

	public Integer getDsa() {
		return dsa;
	}

	public void setDsa(Integer dsa) {
		this.dsa = dsa;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Corso getPref1() {
		return pref1;
	}

	public void setPref1(Corso pref1) {
		this.pref1 = pref1;
	}

	public Corso getPref2() {
		return pref2;
	}

	public void setPref2(Corso pref2) {
		this.pref2 = pref2;
	}

	public Corso getPref3() {
		return pref3;
	}

	public void setPref3(Corso pref3) {
		this.pref3 = pref3;
	}

	public Call getCall() {
		return call;
	}

	public void setCall(Call call) {
		this.call = call;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public Integer getUniv() {
		return univ;
	}

	public void setUniv(Integer univ) {
		this.univ = univ;
	}

	public Integer getCallInviata() {
		return callInviata;
	}

	public void setCallInviata(Integer callInviata) {
		this.callInviata = callInviata;
	}

	public String getScuolaProvenienza() {
		return scuolaProvenienza;
	}

	public void setScuolaProvenienza(String scuolaProvenienza) {
		this.scuolaProvenienza = scuolaProvenienza;
	}

	public Integer getCapEspressive() {
		return capEspressive;
	}

	public void setCapEspressive(Integer capEspressive) {
		this.capEspressive = capEspressive;
	}

	public Integer getOccupato() {
		return occupato;
	}

	public void setOccupato(Integer occupato) {
		this.occupato = occupato;
	}

	public PunteggioScritti getPunteggioScritti() {
		return punteggioScritti;
	}

	public void setPunteggioScritti(PunteggioScritti punteggioScritti) {
		this.punteggioScritti = punteggioScritti;
	}

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<PunteggioOrali> getPunteggioOrali() {
		return punteggioOrali;
	}

	public void setPunteggioOrali(List<PunteggioOrali> punteggioOrali) {
		this.punteggioOrali = punteggioOrali;
	}

	public Diploma getDiploma() {
		return diploma;
	}

	public void setDiploma(Diploma diploma) {
		this.diploma = diploma;
	}

	public Cv getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Cv curriculum) {
		this.curriculum = curriculum;
	}

	public Integer getRitirato() {
		return ritirato;
	}

	public void setRitirato(Integer ritirato) {
		this.ritirato = ritirato;
	}

	@Override
	public String toString() {
		return this.cognome + " " + this.nome;
	}

}
