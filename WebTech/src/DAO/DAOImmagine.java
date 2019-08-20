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
   
	public List<Immagine> getAlbum(int idcampagna ){
		
		List <Immagine> immaginicampagna = new ArrayList<Immagine>();
		String query = "select * from immagine as i join mappacampagna as m "
				+ "on (i.id=m.idimmagine) where m.idcampagna=?";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setInt(1, idcampagna);
			ris = pstate.executeQuery();
			if(ris.next()) {
			Immagine i = new Immagine();
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
