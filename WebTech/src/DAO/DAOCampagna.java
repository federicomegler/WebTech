package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bean.Campagna;
import DAO.DAOLocalita;

public class DAOCampagna {
	private Connection connection;
	private PreparedStatement pstate;
	private ResultSet ris;
	
	public DAOCampagna(Connection connessione) {
		this.connection = connessione;
	}

	public boolean cambioStato(int id, String s) {

		String query = "UPDATE campagna SET stato =? WHERE id=?";
		DAOLocalita d= new DAOLocalita(connection);
		if(d.checkLoc(id)) {

			try {
				pstate = connection.prepareStatement(query);
				pstate.setInt(2,id);
				pstate.setString(1,s);
				pstate.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

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
		else return false;

	}

	public void addsubscription (int id, String username) {
		
		String query = "INSERT INTO iscrizione (idcampagna,user) VALUES (?,?)";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setInt(1,id);
			pstate.setString(2, username);
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

    public int addCampagna(String nome, String committente,String creatore) {
		int id=0;
		String query = "INSERT INTO webtech.campagna (nome,committente,stato,creatore) VALUES (?,?,'creata',?)";
		
		String query2 = "SELECT LAST_INSERT_ID() as last_id";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1,nome);
			pstate.setString(2, committente);
			pstate.setString(3,creatore);
			pstate.executeUpdate();
			
			pstate = connection.prepareStatement(query2);
			ris = pstate.executeQuery();
			
			if(ris.next()) {
				id = ris.getInt("last_id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		finally {
			try {
				pstate.close();
				ris.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return id;
	}
	
	public List<Campagna> getManagerCampagna (String username , String stato) {
		List <Campagna> campagne= new ArrayList<Campagna>();
		String query = "select * from campagna where creatore = ? and stato= ? order by nome";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1, username);
			pstate.setString(2, stato);
			ris = pstate.executeQuery();
			while(ris.next()) {
				Campagna c =new Campagna();
				c.setNome(ris.getString("nome"));
				c.setCommittente(ris.getString("committente"));
				c.setStato(ris.getString("stato"));
				c.setID_campagna(ris.getInt("id"));
				campagne.add(c);
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
		
		return campagne;
	}
		
	public List<Campagna> getWorkerCampagnaOptata (String username) {
		List <Campagna> campagnesvolte= new ArrayList<Campagna>();
		String query = "select * from campagna where stato='avviata' and id in ( select idcampagna from iscrizione where user = ?)";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1, username);
			ris = pstate.executeQuery();
			while(ris.next()) {
		
				Campagna c =new Campagna();
				c.setNome(ris.getString("nome"));
				c.setCommittente(ris.getString("committente"));
				c.setStato(ris.getString("stato"));
				c.setID_campagna(ris.getInt("id"));
				campagnesvolte.add(c);
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
		
		return campagnesvolte;
	}
	
	public List<Campagna> getWorkerCampagnaNonSvolta (String username) {
		List <Campagna> campagnenonsvolte=new ArrayList<Campagna>();
		String query = "select * from webtech.campagna where stato='avviata' and id not in ( select id from webtech.iscrizione where user = ?)";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1, username);
			ris = pstate.executeQuery();
			while(ris.next()) {
				Campagna c =new Campagna();
				c.setNome(ris.getString("nome"));
				c.setCommittente(ris.getString("committente"));
				c.setStato(ris.getString("stato"));
				c.setID_campagna(ris.getInt("id"));
				campagnenonsvolte.add(c);
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
		
		return campagnenonsvolte;
	}
	
	
	public boolean esisteCampagna(int id, String creatore) {
		String query = "select * from campagna where id =? and creatore=?";
		boolean risultato = false;
		try {
			pstate = connection.prepareStatement(query);
			pstate.setInt(1, id);
			pstate.setString(2,creatore);
			ris = pstate.executeQuery();
			if(ris.next()) {
				risultato = true;
			}
			else {
				risultato = false;
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
		return risultato;
	}
	
	
    public Campagna getCampagna (int id, String creatore) {
	
    Campagna c = null;
	String query = "select * from campagna where id =? and creatore=?";
	try {
		pstate = connection.prepareStatement(query);
		pstate.setInt(1, id);
		pstate.setString(2,creatore);
		ris = pstate.executeQuery();
		if(ris.next()) {
			c =new Campagna();
			c.setNome(ris.getString("nome"));
			c.setCommittente(ris.getString("committente"));
			c.setStato(ris.getString("stato"));
			c.setID_campagna(ris.getInt("id"));
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
	
	return c;
	
	
}	
	
    public void addMappacampagna(int idimmagine,int idcampagna,int idlocalita) {
		
		String query=" INSERT INTO mappacampagna (idcampagna,idlocalita,idimmagine,colore) VALUES(?,?,?,yellow)";
		
		try {
			pstate = connection.prepareStatement(query);
			pstate.setInt(1,idcampagna);
			pstate.setInt(2,idlocalita);
			pstate.setInt(3,idimmagine);
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
	
}



