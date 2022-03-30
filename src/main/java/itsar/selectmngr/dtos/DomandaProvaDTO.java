package itsar.selectmngr.dtos;

import java.util.List;

import itsar.selectmngr.models.DomandaProva;

public class DomandaProvaDTO {

	private List<DomandaProva> domandeProva;

	public DomandaProvaDTO() {
	}

	public void addDomandaProva(DomandaProva dp) {
		this.domandeProva.add(dp);
	}

	public List<DomandaProva> getDomandeProva() {
		return domandeProva;
	}

	public void setDomandeProva(List<DomandaProva> domandeProva) {
		this.domandeProva = domandeProva;
	}

}
