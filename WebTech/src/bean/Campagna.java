package bean;

public class Campagna {
	private int ID_campagna;
	private String nome;
	private String committente;
	private String stato;
	public int getID_campagna() {
		return ID_campagna;
	}
	public void setID_campagna(int iD_campagna) {
		ID_campagna = iD_campagna;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCommittente() {
		return committente;
	}
	public void setCommittente(String committente) {
		this.committente = committente;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
}
