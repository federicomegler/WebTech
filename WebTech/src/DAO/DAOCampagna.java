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
		if(d.checkLocImage(id)) {
			
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
				// TODO Auto-generated catch block
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

    public void addCampagna(String nome, String committente) {
		
		String query = "INSERT INTO campagna (nome,committente,stato) VALUES (?,?,'creata')";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1,nome);
			pstate.setString(2, committente);
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
	
	public List<Campagna> getManagerCampagna (String username) {
		List <Campagna> campagne= new ArrayList<Campagna>();
		String query = "select * from campagna where committente = ? order by stato desc";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1, username);
			ris = pstate.executeQuery();
			if(ris.next()) {
		
				Campagna c =new Campagna();
				c.setNome(ris.getString("nome"));
				c.setCommittente(ris.getString("committente"));
				c.setStato(ris.getString("stato"));
				c.setID_campagna(ris.getInt("idcampagna"));
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
		String query = "select * from campagne where stato='avviata' and id in ( select idcampagna from iscrizione where user = ?)";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1, username);
			ris = pstate.executeQuery();
			if(ris.next()) {
		
				Campagna c =new Campagna();
				c.setNome(ris.getString("nome"));
				c.setCommittente(ris.getString("committente"));
				c.setStato(ris.getString("stato"));
				c.setID_campagna(ris.getInt("idcampagna"));
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
		String query = "select * from campagne where stato='avviata' and id not in ( select id from iscrizione where user = ?)";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1, username);
			ris = pstate.executeQuery();
			if(ris.next()) {
				Campagna c =new Campagna();
				c.setNome(ris.getString("nome"));
				c.setCommittente(ris.getString("committente"));
				c.setStato(ris.getString("stato"));
				c.setID_campagna(ris.getInt("idcampagna"));
				campagnenonsvolte.add(c);
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
		
		return campagnenonsvolte;
	}

}
