package DAO;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Utente;

public class DAOUtente {
	private Connection connection;
	private PreparedStatement pstate;
	private ResultSet ris;
	
	public DAOUtente(Connection connessione) {
		this.connection = connessione;
	}
	
	public Utente checkLogin(String username, String password) {
		String query = "select * from webtech.utente where (username = ? or mail = ?) and password = ?";
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
	
	public int isManager(String utente) {
		String query = "select manager from webtech.utente where username=?";
		int risultato = -1;
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1, utente);
			ris = pstate.executeQuery();
			if(ris.next()) {
				risultato = ris.getInt("manager");
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
				return -1;
			}
		}
		return risultato;
	}

	public boolean modificaMail (Utente utente ) {
		
		String query= "update utente set mail=? where username=?";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1, utente.getMail());
			pstate.setString(2, utente.getNome());
			pstate.executeUpdate();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean modificaPassword (Utente utente ) {
		
		String query= "update utente set password=? where username=?";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1, utente.getPassword());
			pstate.setString(2, utente.getNome());
			pstate.executeUpdate();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	public boolean aggiungiManager(Utente utente) {
		String query = "insert into webtech.utente (username,mail,password,manager) values (?,?,?,?)";
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
	
	public boolean aggiungiLavoratore(Utente utente) {
		String query = "insert into webtech.utente (username,mail,password,manager,esperienza) values (?,?,?,?,?)";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1, utente.getNome());
			pstate.setString(2, utente.getMail());
			pstate.setString(3, utente.getPassword());
			pstate.setBoolean(4, false);
			pstate.setString(5, utente.getEsperienza());
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
	
	public void aggiornaImmagine(String username, String immagine) {

		String query = "update webtech.utente set immagine=? where username=?";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1, immagine);
			pstate.setString(2, username);
			pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				pstate.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void eliminaImmagine(String username) {
		String query = "update webtech.utente set immagine=null where username=?";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1, username);
			pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				pstate.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public Utente getInfo(String username){
		Utente info = new Utente();
		String query = "select * from webtech.utente where username = ?";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1, username);
			ris = pstate.executeQuery();
			while(ris.next()) {
				info.setNome(ris.getString("username"));
				info.setMail(ris.getString("mail"));
				info.setEsperienza(ris.getString("esperienza"));
				info.setManager(ris.getBoolean("manager"));
				info.setImmagine(ris.getString("immagine"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				pstate.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return info;
	}
}
