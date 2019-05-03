package bean;

import java.io.File;
import java.sql.Date;

public class Immagine {
	private String provenienza;
	private Date data_recupero;
	private String risoluzione;
	private File immagine;
	private int ID_localita;
	
	public String getProvenienza() {
		return provenienza;
	}
	public void setProvenienza(String provenienza) {
		this.provenienza = provenienza;
	}
	public Date getData_recupero() {
		return data_recupero;
	}
	public void setData_recupero(Date data_recupero) {
		this.data_recupero = data_recupero;
	}
	public String getRisoluzione() {
		return risoluzione;
	}
	public void setRisoluzione(String risoluzione) {
		this.risoluzione = risoluzione;
	}
	public File getImmagine() {
		return immagine;
	}
	public void setImmagine(File immagine) {
		this.immagine = immagine;
	}
	public int getID_localita() {
		return ID_localita;
	}
	public void setID_localita(int iD_localita) {
		ID_localita = iD_localita;
	}
}
