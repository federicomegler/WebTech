package bean;

import java.io.File;

public class Utente {
	private String nome;
	private String password;
	private String mail;
	private String esperienza;
	private File immagine;
	private boolean manager;//true se � un manager
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getEsperienza() {
		return esperienza;
	}
	public void setEsperienza(String esperienza) {
		this.esperienza = esperienza;
	}
	public File getImmagine() {
		return immagine;
	}
	public void setImmagine(File immagine) {
		this.immagine = immagine;
	}
	public boolean isManager() {
		return manager;
	}
	public void setManager(boolean manager) {
		this.manager = manager;
	}
}
