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

	public int totaleLocalita(int idcampagna) {
		String query = "select count(distinct idlocalita) as totlocalita from webtech.mappacampagna where idcampagna = ?";
		int tot = -1;
		try {
			pstate = connection.prepareStatement(query);
			pstate.setInt(1, idcampagna);
			ris = pstate.executeQuery();
			if(ris.next()) {
				tot = ris.getInt("totlocalita");
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
	

    public int addLocalita(double latitudine, double longitudine, String nome,
    	                           String comune, String regione, String stato) {
    	int idloc=-1;
    	String query1 = "INSERT INTO webtech.localita (latitudine,longitudine,nome,comune,regione,stato) "
    			+ "VALUES (?,?,?,?,?,?)";
    	String query2="SELECT LAST_INSERT_ID() as last_id;";
    	
    	Localita loc = new Localita(); 
        loc=getLocalita (-1 , nome, comune, regione, stato);          
    	if(loc!=null) {
    		System.out.println("localita gia esistente");
    		return loc.getID_localita();
    		}
    	try {
    		System.out.println("localita non esistente");
			pstate = connection.prepareStatement(query1);
			pstate.setDouble(1,latitudine);
			pstate.setDouble(2,longitudine);
			pstate.setString(3, nome);
			pstate.setString(4, comune);
			pstate.setString(5, regione);
			pstate.setString(6, stato);
			pstate.executeUpdate();
			pstate = connection.prepareStatement(query2);
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
		String query = "select * from webtech.localita as loc join (\r\n" + 
				"select idcampagna,idlocalita,colore,max(priorita) from webtech.mappacampagna where idcampagna = ? group by idlocalita\r\n" + 
				") as mc on loc.id = mc.idlocalita ";
		
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
				l.setStato(ris.getString("stato"));
				l.setColore(ris.getString("colore"));
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
    
    
    
    public Localita getLocalita(int idimmagine){
    	String query = "select * from webtech.mappacampagna as mc join webtech.localita as l on mc.idlocalita = l.id where mc.idimmagine = ?";
    	Localita l = null;
    	try {
			pstate = connection.prepareStatement(query);
			pstate.setInt(1, idimmagine);
			ris = pstate.executeQuery();
			
			if(ris.next()) {
				l =new Localita();
    			l.setID_localita(ris.getInt("id"));
    			l.setNome(ris.getString("nome"));
    			l.setComune(ris.getString("comune"));
    			l.setRegione(ris.getString("regione"));
    			l.setStato(ris.getString("stato"));
    			l.setLatitudine(ris.getDouble("latitudine"));
    			l.setLongitudine(ris.getDouble("longitudine"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		}
    	return l;
    }
    
    
    public Localita getLocalita(int idcampagna, int idlocalita){
    	String query = "select * from webtech.mappacampagna as mc join webtech.localita as l on mc.idlocalita = l.id where mc.idcampagna = ? and l.id = ?";
    	Localita l = null;
    	try {
			pstate = connection.prepareStatement(query);
			pstate.setInt(1, idcampagna);
			pstate.setInt(2, idlocalita);
			ris = pstate.executeQuery();
			
			if(ris.next()) {
				l =new Localita();
    			l.setID_localita(ris.getInt("id"));
    			l.setNome(ris.getString("nome"));
    			l.setComune(ris.getString("comune"));
    			l.setRegione(ris.getString("regione"));
    			l.setStato(ris.getString("stato"));
    			l.setLatitudine(ris.getDouble("latitudine"));
    			l.setLongitudine(ris.getDouble("longitudine"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		}
    	return l;
    }
  
    public Localita getLocalita (int id,String nome,
            String comune, String regione, String stato) {
    	
    	Localita l=null;
    	String query = "select * from webtech.localita where id =? or (nome=? and comune=? and regione=? and stato=?)";
    	try {
    		pstate = connection.prepareStatement(query);
    		pstate.setInt(1, id);
    	    pstate.setString(2, nome);
			pstate.setString(3, comune);
			pstate.setString(4, regione);
			pstate.setString(5, stato);
    		ris = pstate.executeQuery();
    		if(ris.next()) {
    			l =new Localita();
    			l.setID_localita(ris.getInt("id"));
    			l.setNome(ris.getString("nome"));
    			l.setComune(ris.getString("comune"));
    			l.setRegione(ris.getString("regione"));
    			l.setStato(ris.getString("stato"));
    			l.setLatitudine(ris.getDouble("latitudine"));
    			l.setLongitudine(ris.getDouble("longitudine"));    			
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
    	
    	return l;
    }	

	public boolean checkLoc(int idcampagna) { 
		String query = "select * from webtech.mappacampagna where idcampagna=?";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setInt(1, idcampagna);
			ris = pstate.executeQuery();
			if(ris.next()) {
			
				return true;
				
			}
			else {
				return false;
				
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}