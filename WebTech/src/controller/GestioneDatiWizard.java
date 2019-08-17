package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.DAOCampagna;
import DAO.DAOImmagine;
import DAO.DAOLocalita;
import DAO.DAOUtente;
import bean.Utente;

/**
 * Servlet implementation class GestinoeDatiWizard
 */
@WebServlet("/GestioneDatiWizard")
public class GestioneDatiWizard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestioneDatiWizard() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() {
    	ServletContext context = getServletContext();
		String driver = context.getInitParameter("dbDriver");
		String url = context.getInitParameter("dbUrl") + "?useLegacyDatetimeCode=false&serverTimezone=Europe/Rome";
		String user = context.getInitParameter("dbUser");
		String password = context.getInitParameter("dbPassword");
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getSession().getAttribute("UtenteConnesso") == null) {
			response.sendRedirect("Login");
			
		}
		else {
			
			int idloc,idimm,idcamp;
			String estensione =null;
			String nome_utente = (String)request.getSession(true).getAttribute("UtenteConnesso");
		    idcamp = Integer.parseInt((String)request.getParameter("idcampagna"));
		    
		    double lat = Double.parseDouble((String)request.getParameter("latitudine"));
		    double lon =Double.parseDouble((String)request.getParameter("longitudine"));
		    String localita = (String)request.getParameter("localita");
		    String comune = (String)request.getParameter("comune");
		    String stato = (String)request.getParameter("Stato");
		    String regione = (String)request.getParameter("regione");
          
		    
		    
		    String provenienza = (String)request.getParameter("provenienza");
			Date data; //= Date.parse((String)request.getParameter("datarecupero")); conversione string to date
//            String string = "January 2, 2010";
//            DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
//            Date date = format.parse(string);
            String risoluzione = (String)request.getParameter("risoluzione");
			
            DAOCampagna dcampagna = new DAOCampagna(connection);
		    DAOImmagine dimm = new DAOImmagine(connection);
		    DAOLocalita dloc = new DAOLocalita(connection);
		   
		    idloc= dloc.addLocalita( lat, lon, localita, comune, regione, stato);
		    
		    String tomcatBase = System.getProperty("catalina.base");
			String path = "/webapps/ImmaginiCampagna/";
			File saveDir = new File(tomcatBase);
			Part part = request.getPart("nuovaimmagine");
			estensione = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));
			
			idimm=dimm.addImmagine(data, provenienza, risoluzione, estensione);
			
			dcampagna.addMappacampagna(idimm, idcamp, idloc);
			
			File immagine = new File(tomcatBase  + path + Integer.toString(idcamp)+ Integer.toString(idloc) + Integer.toString(idimm));
			if(!saveDir.exists()) {
					saveDir.mkdirs();
				}
				estensione = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));
				part.write(tomcatBase  + path + nome_utente + estensione);
			getServletContext().getRequestDispatcher("/").forward(request, response);
			
		   }	    
		    	   

		}

}




/*sistemare do post 
 * prelevare id campagna e inviare i parametri della stessa alla servlet dei dettagli
 * valutare se introdurre il daomappacampagna per le funzioni di set e get e cehck situati nei dao
 * controllare il salvataggio delle immagini riguardo al nome assegnato idcamp + idloc + idimm
 */










