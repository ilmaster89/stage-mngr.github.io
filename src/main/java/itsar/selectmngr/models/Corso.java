package itsar.selectmngr.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "corsi")
public class Corso {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	String codice;

	Integer it, grafica, max;

	@OneToMany(mappedBy = "corso")
	List<PunteggioOrali> punteggioOrali;

	@OneToMany(mappedBy = "corso")
	List<DataOrali> dateOrali;

	@OneToOne
	@JoinColumn(name = "cat_id", referencedColumnName = "id")
	CategoriaCorso categoriaCorso;

	public Corso() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Integer getIt() {
		return it;
	}

	public void setIt(Integer it) {
		this.it = it;
	}

	public Integer getGrafica() {
		return grafica;
	}

	public void setGrafica(Integer grafica) {
		this.grafica = grafica;
	}

	public List<PunteggioOrali> getPunteggioOrali() {
		return punteggioOrali;
	}

	public void setPunteggioOrali(List<PunteggioOrali> punteggioOrali) {
		this.punteggioOrali = punteggioOrali;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public CategoriaCorso getCategoriaCorso() {
		return categoriaCorso;
	}

	public void setCategoriaCorso(CategoriaCorso categoriaCorso) {
		this.categoriaCorso = categoriaCorso;
	}

	public List<DataOrali> getDateOrali() {
		return dateOrali;
	}

	public void setDateOrali(List<DataOrali> dateOrali) {
		this.dateOrali = dateOrali;
	}

	@Override
	public String toString() {
		return this.codice;
	}

}
