package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Campagna;
import bean.Localita;
import bean.Utente;

public class DAOLocalita {
	private Connection connection;
	private PreparedStatement pstate;
	private ResultSet ris;
	
	public DAOLocalita(Connection connessione) {
		this.connection = connessione;
	}
	
	public boolean checkLocImage(int idcampagna) { 
		String query = "select * from mappacampagna where idcampagna=? and idimmagine=null ";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setInt(1, idcampagna);
			ris = pstate.executeQuery();
			if(ris.next()) {
			
				return false;
				
			}
			else {
				
				return true;
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
	}

    public int addLocalita(double latitudine, double longitudine, String nome,
    	                           String comune, String regione, String stato) {
    	int idloc=0;
    	String query = "INSERT INTO localita (idcampagna,latitudine,longitudine,nome,comune,regione,stato) "
    			+ "VALUES (?,?,?,?,?,?,?); SELECT LAST_INSERT_ID() as last_id;";
    	 //fare controllo della localita se esiste quindi nome localita uguale stesso comune steesa regione stesso stato
		try {
			pstate = connection.prepareStatement(query);
			pstate.setDouble(1,latitudine);
			pstate.setDouble(2,longitudine);
			pstate.setString(3, nome);
			pstate.setString(4, comune);
			pstate.setString(5, regione);
			pstate.setString(6, stato);
			ris=pstate.executeQuery();
			if(ris.next())
				idloc = ris.getInt("last_id");
			
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
		
		return idloc;
    	
    	
    }

    public List<Localita> getPlaces(int idcampagna){
    	List<Localita> places= new ArrayList<Localita>();
		String query = "select * from localita where id in (select idlocalita from mappacampagna where idcampagna=?)";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setInt(1, idcampagna);
			ris = pstate.executeQuery();
			while(ris.next()) {
				Localita l=new Localita();
				l.setComune(ris.getString("comune"));
				l.setID_localita(ris.getInt("id"));
				l.setLatitudine(ris.getDouble("latitudine"));
				l.setLongitudine(ris.getDouble("longitudine"));
				l.setNome(ris.getString("nome"));
				l.setRegione(ris.getString("regione"));
				l.setStato("stato");
				places.add(l);
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
    	return places;
    }
}