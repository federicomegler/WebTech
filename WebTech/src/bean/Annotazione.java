package bean;

import java.sql.Date;

public class Annotazione {
	private Date data_creazione;
	private boolean validita;
	private String fiducia;
	private String note;
	public Date getData_creazione() {
		return data_creazione;
	}
	public void setData_creazione(Date data_creazione) {
		this.data_creazione = data_creazione;
	}
	public boolean isValidita() {
		return validita;
	}
	public void setValidita(boolean validita) {
		this.validita = validita;
	}
	public String getFiducia() {
		return fiducia;
	}
	public void setFiducia(String fiducia) {
		this.fiducia = fiducia;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
