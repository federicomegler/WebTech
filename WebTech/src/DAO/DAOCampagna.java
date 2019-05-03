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

	public boolean cambioStato(String id, String s) {
		
		String query = "INSERT INTO campagna (nome,committente,stato) VALUES (?,?,'creato')";
		if(DAOLocalita.checkimage(id)) {
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1,id);
			pstate.setString(2,s);
			ris = pstate.executeQuery();
			
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
		else return false;
		
	}
	
	
	
	public void addCampagna(String nome, String committente) {
		
		String query = "INSERT INTO campagna (nome,committente,stato) VALUES (?,?,'creato')";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1,nome);
			pstate.setString(2, committente);
			ris = pstate.executeQuery();
			
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
		String query = "select * from campagna where committente = ?";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1, username);
			ris = pstate.executeQuery();
			if(ris.next()) {
		
				Campagna c =new Campagna();
				c.setNome(ris.getString("nome"));
				c.setCommittente(ris.getString("committente"));
				c.setStato(ris.getString("stato"));
				c.setID_campagna(ris.getString("idcampagna"));
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
	
	public List<Campagna> getWorkerCampagnaSvolta (String username) {
		List <Campagna> campagnesvolte= new ArrayList<Campagna>();
		String query = "select * from campagne where id in ( select id from campagnesvolta where user = ?)";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1, username);
			ris = pstate.executeQuery();
			if(ris.next()) {
		
				Campagna c =new Campagna();
				c.setNome(ris.getString("nome"));
				c.setCommittente(ris.getString("committente"));
				c.setStato(ris.getString("stato"));
				c.setID_campagna(ris.getString("idcampagna"));
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
		String query = "select * from campagne where id not in ( select id from campagnesvolta where user = ?)";
		try {
			pstate = connection.prepareStatement(query);
			pstate.setString(1, username);
			ris = pstate.executeQuery();
			if(ris.next()) {
				
				Campagna c =new Campagna();
				c.setNome(ris.getString("nome"));
				c.setCommittente(ris.getString("committente"));
				c.setStato(ris.getString("stato"));
				c.setID_campagna(ris.getString("idcampagna"));
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
