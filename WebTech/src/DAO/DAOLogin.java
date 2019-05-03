package DAO;
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
	
	public boolean checkLogin(String username, String password) {
		return checkManager(username, password);
	}
	
	private boolean checkManager(String username, String password) {
		String query = "select * from Manager where (username = ? or mail = ?) and password = ?";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1, username);
			pstate.setString(2, username);
			pstate.setString(3, password);
			ris = pstate.executeQuery();
			if(ris.next()) {
				Utente manager = new Utente();
				manager.setNome(ris.getString("username"));
				manager.setPassword(password);
				manager.setMail(ris.getString("mail"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
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
		return false;
	}
}
