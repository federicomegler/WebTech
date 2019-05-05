package DAO;
import java.io.File;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
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
			i.setID_localita(ris.getInt("idlocalita"));
			i.setImmagine((File)ris.getBlob("immagine"));
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

	public void addElementMappacampagna(int idimmagine,int idcampagna,int idlocalita) {
		
		String query=" INSERT INTO mappacampagna (idcampagna,idlocalita,idimmagine) VALUES(?,?,?)";
		
		try {
			pstate = connection.prepareStatement(query);
			pstate.setInt(1,idcampagna);
			pstate.setInt(1,idlocalita);
			pstate.setInt(1,idimmagine);
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
	
	public void addImmagine (int idimmagine,int idcampagna,int idlocalita,Date d,File immagine, String provenienza, String risoluzione) {
		String query = "INSERT INTO immagine (idimmagine,provenienza,datarecupero,risoluzione,immagine) "
				+ "VALUES (?,?,?,?,?)";
		
		if(addImmagineMappacampagna(idimmagine,idcampagna,idlocalita)==0) {
			
			addElementMappacampagna(idimmagine,idcampagna,idlocalita);
			
		}
		try {
			pstate = connection.prepareStatement(query);
			pstate.setInt(1,idimmagine);
			pstate.setString(2,provenienza);
			pstate.setDate(3, d);
			pstate.setString(4,risoluzione);
			pstate.setBlob(5,(Blob) immagine);
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
	
	public int addImmagineMappacampagna(int idimmagine,int idcampagna,int idlocalita) {
		int i=0;
		String query = "UPDATE mappacampagna SET (idimmagine=?)"
				+ "WHERE idlocalita=? and idcampagna=? and idimmagine=null ";
		
		try {
			pstate = connection.prepareStatement(query);
			pstate.setInt(1,idimmagine);
			pstate.setInt(2,idlocalita);
			pstate.setInt(3,idcampagna);
			i=pstate.executeUpdate(); // 0 se non ha fatto cambiamenti
			
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
		
		return i;
				
	}

}
