package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Annotazione;
import bean.Campagna;
import DAO.DAOLocalita;

public class DAOAnnotazione {
	private Connection connection;
	private PreparedStatement pstate;
	private ResultSet ris;
	
	public DAOAnnotazione(Connection connessione) {
		this.connection = connessione;
	}
	
	public boolean checkAnnotazione(String user, int idimmagine) {
		String query = "select * from webtech.annotazione where idimmagine=? and user=?";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setInt(1, idimmagine);
			pstate.setString(2, user);
			ris = pstate.executeQuery();
			if(ris.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				ris.close();
				pstate.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public int getNumAnnotazioniCampagna(int idcampagna) {
		String query = "select count(*) as tot from webtech.annotazione as a join (select * from webtech.mappacampagna where idcampagna = ?) as mc on a.idimmagine = mc.idimmagine";
		int tot = -1;
		try {
			pstate = connection.prepareStatement(query);
			pstate.setInt(1, idcampagna);
			ris = pstate.executeQuery();
			if(ris.next()) {
				tot = ris.getInt("tot");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		finally {
			try {
				pstate.close();
				ris.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return tot;
	}
	
	public boolean setColore(int idimmagine,boolean validita) {
		
		String query1 = "select * from webtech.annotazione where idimmagine=? and validita <> ?";
		String query2 = "update webtech.mappacampagna set colore=?, priorita=? where idimmagine=?";
		String colore = "green";
		int priorita = 1;
		try {
			pstate = connection.prepareStatement(query1);
			pstate.setInt(1,idimmagine);
			pstate.setBoolean(2,validita);
			ris=pstate.executeQuery();
			if(ris.next()) {
				colore="red";
				priorita=2;
			}
			pstate = connection.prepareStatement(query2);
			pstate.setString(1, colore);
			pstate.setInt(2, priorita);
			pstate.setInt(3, idimmagine);
			pstate.executeUpdate();
			
			return true;
			
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
	}

	public boolean addAnnotazione (int idimmagine,String username, boolean validita, String fiducia, String note) {
		
		String query = "INSERT INTO webtech.annotazione (idimmagine,user,datacreazione,validita,fiducia,note) "
				+ "VALUES (?,?,SYSDATE(),?,?,?)";
		if(setColore(idimmagine, validita)) {
		
		try {
			pstate = connection.prepareStatement(query);
			pstate.setInt(1,idimmagine);
			pstate.setString(2, username);
			pstate.setBoolean(3,validita);
			pstate.setString(4,fiducia);
			pstate.setString(5, note);
			pstate.executeUpdate();
			return true;
			
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
	}
		else return false;
	}
	public List<Annotazione> getAnnotazioni(int idimmagine, int idcampagna, int idlocalita){
		List<Annotazione> lista = null;
		String query = "select * from webtech.annotazione as a join webtech.mappacampagna as mc on a.idimmagine = mc.idimmagine where mc.idimmagine = ? and mc.idcampagna = ? and mc.idlocalita = ? order by datacreazione";
		
		try {
			pstate = connection.prepareStatement(query);
			pstate.setInt(1, idimmagine);
			pstate.setInt(2, idcampagna);
			pstate.setInt(3, idlocalita);
			ris = pstate.executeQuery();
			lista = new ArrayList<Annotazione>();
			while(ris.next()) {
				Annotazione a = new Annotazione();
				a.setProprietario(ris.getString("user"));
				a.setData_creazione(ris.getDate("datacreazione"));
				a.setValidita(ris.getBoolean("validita"));
				a.setFiducia(ris.getString("fiducia"));
				a.setNote(ris.getString("note"));
				lista.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				pstate.close();
				ris.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		return lista;
	}
	
}
