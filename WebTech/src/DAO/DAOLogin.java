package DAO;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Utente;

public class DAOLogin {
	private Connection connection;
	private PreparedStatement pstate;
	private ResultSet ris;
	
	public DAOLogin(Connection connessione) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			utente.setValid(false);
			return utente;
		}
		finally {
			try {
				ris.close();
				pstate.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return utente;
	}
}
