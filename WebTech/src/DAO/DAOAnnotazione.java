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

	public void addDescription (int idimmagine,String username, boolean validita, String fiducia, String note) {
		
		String query = "INSERT INTO annotazione (idimmagine,user,datacreazione,validita,fiduica,note) "
				+ "VALUES (?,?,SYSDATE(),?,?,?)";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setInt(1,idimmagine);
			pstate.setString(2, username);
			pstate.setBoolean(3,validita);
			pstate.setString(4,fiducia);
			pstate.setString(5, note);
			pstate.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		finally {
			try {
				pstate.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
				
	}

	public List<Annotazione> getDescription(int idcampagna,String username) {
		List<Annotazione> annotazioni = new ArrayList<Annotazione>();
		String query = "select * from annotazione where user=? and idimmagine in (select idimmagine from mappacampagna where idcampagna=?)";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1, username);
			pstate.setInt(2, idcampagna);
			ris = pstate.executeQuery();
			if(ris.next()) {
				Annotazione a= new Annotazione();
				a.setData_creazione(ris.getDate("datacreazione"));
				a.setFiducia(ris.getString("fiducia"));
				a.setNote(ris.getString("note"));
				a.setProprietario(username);
				a.setValidita(ris.getBoolean("validita"));
				annotazioni.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
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
		
			
		return annotazioni;
		
	}
  
}
