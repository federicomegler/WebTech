package DAO;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Annotazione;
import bean.Campagna;
import bean.Immagine;
import DAO.DAOLocalita;

public class DAOImmagine {
	private Connection connection;
	private PreparedStatement pstate;
	private ResultSet ris;
	
	public DAOImmagine(Connection connessione) {
		this.connection = connessione;
	}
	
	public int getTotaleImmaginiCampagna(int idcampagna) {
		String query = "select count(*) as totale from webtech.mappacampagna where idcampagna=?";
		int tot = -1;
		try {
			pstate = connection.prepareStatement(query);
			pstate.setInt(1, idcampagna);
			ris = pstate.executeQuery();
			if(ris.next()) {
				tot = ris.getInt("totale");
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
		return tot;
	}
   
public List<Immagine> getImmaginiLocalitaConflitto(int idcampagna){
		
		List <Immagine> immaginiconflitto = new ArrayList<Immagine>();
		String query = "select * from webtech.immagine as i join webtech.mappacampagna as mc on (i.id=mc.idimmagine) where mc.idcampagna=? and mc.colore='red'";
		
		try {
			pstate = connection.prepareStatement(query);
			pstate.setInt(1, idcampagna);
			ris = pstate.executeQuery();
			while(ris.next()) {
			Immagine i = new Immagine();
			i.setId(ris.getInt("id"));
			i.setData_recupero(ris.getDate("datarecupero"));
			i.setFormato(ris.getString("formato"));
			i.setProvenienza(ris.getString("provenienza"));
			i.setRisoluzione(ris.getString("risoluzione"));
			immaginiconflitto.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		finally {
			try {
				ris.close();
				pstate.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return immaginiconflitto;
	}
	
	public List<Immagine> getImmaginiLocalita(int idcampagna, int idlocalita){
		
		List <Immagine> immaginicampagna = new ArrayList<Immagine>();
		String query = "select * from webtech.immagine as i join webtech.mappacampagna as mc on (i.id=mc.idimmagine) where mc.idcampagna=? and mc.idlocalita = ?";
		
		try {
			pstate = connection.prepareStatement(query);
			pstate.setInt(1, idcampagna);
			pstate.setInt(2, idlocalita);
			ris = pstate.executeQuery();
			while(ris.next()) {
			Immagine i = new Immagine();
			i.setId(ris.getInt("id"));
			i.setData_recupero(ris.getDate("datarecupero"));
			i.setFormato(ris.getString("formato"));
			i.setProvenienza(ris.getString("provenienza"));
			i.setRisoluzione(ris.getString("risoluzione"));
			immaginicampagna.add(i);
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
		
		return immaginicampagna;
		
	}

	public int addImmagine (Date date, String provenienza, String risoluzione, String formato) {
		int idimm=0;
		String query1 = "INSERT INTO webtech.immagine (provenienza,datarecupero,risoluzione,formato) "
				+ "VALUES (?,?,?,?);";
	 	String query2="SELECT LAST_INSERT_ID() as last_id;";
    	
		try {
			pstate = connection.prepareStatement(query1);
			pstate.setString(1,provenienza);
			pstate.setDate(2, date);
			pstate.setString(3,risoluzione);
			pstate.setString(4,formato);
			pstate.executeUpdate();
			pstate = connection.prepareStatement(query2);
			ris = pstate.executeQuery();
			if(ris.next())
			idimm = ris.getInt("last_id");
			
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
		
		return idimm;
				
	}
	

}
