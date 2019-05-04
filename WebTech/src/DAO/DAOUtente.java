package DAO;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Utente;

public class DAOUtente {
	private Connection connection;
	private PreparedStatement pstate;
	private ResultSet ris;
	
	public DAOUtente(Connection connessione) {
		this.connection = connessione;
	}
	
	public Utente checkLogin(String username, String password) {
		String query = "select * from Manager where (username = ? or mail = ?) and password = ?";
		Utente utente = new Utente();
		utente.setValid(false);
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1, username);
			pstate.setString(2, username);
			pstate.setString(3, password);
			ris = pstate.executeQuery();
			if(ris.next()) {
				utente.setNome(ris.getString("username"));
				utente.setPassword(password);
				utente.setMail(ris.getString("mail"));
				utente.setEsperienza(ris.getString("esperienza"));
				utente.setImmagine((File)ris.getBlob("immagine"));
				utente.setManager(ris.getBoolean("manager"));
				utente.setValid(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			utente.setValid(false);
			return utente;
		}
		finally {
			try {
				ris.close();
				pstate.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return utente;
	}
	
	public int esisteUtente(Utente utente) {
		String query = "select * from webtech.utente where username=? or mail=?";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1, utente.getNome());
			pstate.setString(2, utente.getMail());
			ris = pstate.executeQuery();
			if(ris.next()) {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		finally {
			try {
				ris.close();
				pstate.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public boolean aggiungiManager(Utente utente) {
		String query = "insert into webtech.utente (username,mail,password,ruolo) values (?,?,?,?)";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1, utente.getNome());
			pstate.setString(2, utente.getMail());
			pstate.setString(3, utente.getPassword());
			pstate.setBoolean(4, true);
			pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				pstate.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
