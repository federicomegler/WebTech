package bean;

import java.sql.Date;

public class Immagine {
	private String id;
	private String provenienza;
	private Date data_recupero;
	private String risoluzione;
	private String formato;
	
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
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
