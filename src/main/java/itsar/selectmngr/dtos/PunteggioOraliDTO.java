package itsar.selectmngr.dtos;

import java.util.ArrayList;
import java.util.List;

import itsar.selectmngr.models.PunteggioOrali;

public class PunteggioOraliDTO {

	private List<PunteggioOrali> punteggi;

	public PunteggioOraliDTO() {
		this.punteggi = new ArrayList<PunteggioOrali>();
	}

	public void addPunteggio(PunteggioOrali po) {
		this.punteggi.add(po);
	}

	public List<PunteggioOrali> getPunteggi() {
		return this.punteggi;
	}

	public void setPunteggi(List<PunteggioOrali> punteggi) {
		this.punteggi = punteggi;
	}

}
