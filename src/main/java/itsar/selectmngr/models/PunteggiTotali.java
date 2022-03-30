package itsar.selectmngr.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "punteggi_totali")
public class PunteggiTotali {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	Integer punteggio, pagato;

	@OneToOne
	@JoinColumn(name = "candidato_id", referencedColumnName = "id")
	Candidato candidato;

	@OneToOne
	@JoinColumn(name = "corso_id", referencedColumnName = "id")
	Corso corso;

	@OneToOne
	@JoinColumn(name = "ammissione_id", referencedColumnName = "id")
	Ammissione ammissione;

	Date data;
	
	@Column(name="data_pagamento")
	Date dataPagamento;

	public PunteggiTotali() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(Integer punteggio) {
		this.punteggio = punteggio;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public Corso getCorso() {
		return corso;
	}

	public void setCorso(Corso corso) {
		this.corso = corso;
	}

	public Ammissione getAmmissione() {
		return ammissione;
	}

	public void setAmmissione(Ammissione ammissione) {
		this.ammissione = ammissione;
	}

	public Integer getPagato() {
		return pagato;
	}

	public void setPagato(Integer pagato) {
		this.pagato = pagato;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	

}
