package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Date;  

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.DAOCampagna;
import DAO.DAOImmagine;
import DAO.DAOLocalita;
import DAO.DAOUtente;
import bean.Campagna;
import bean.Utente;

/**
 * Servlet implementation class GestinoeDatiWizard
 */
@MultipartConfig
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

		
		System.out.println("gestione dati wizard");
		if(request.getSession().getAttribute("UtenteConnesso") == null) {
			response.sendRedirect("Login");

		}
		else {

			System.out.println("processo");
			
			DAOCampagna dcampagna = new DAOCampagna(connection);
			DAOImmagine dimm = new DAOImmagine(connection);
			DAOLocalita dloc = new DAOLocalita(connection);

			int idloc,idimm,idcamp;
			String estensione =null;
			String nome_utente = (String)request.getSession(true).getAttribute("UtenteConnesso");
			idcamp = Integer.parseInt((String)request.getParameter("idcampagna"));

			//controllo
			Campagna c = new Campagna();
			c = dcampagna.getCampagna(idcamp, nome_utente);

			if(c!=null && c.getStato().equals("creata")) {
				String localita = (String)request.getParameter("localita");
				String comune = (String)request.getParameter("comune");
				String stato = (String)request.getParameter("Stato");
				String regione = (String)request.getParameter("regione");
				double lat = Double.parseDouble((String)request.getParameter("lat"));
				double lon = Double.parseDouble((String)request.getParameter("lon"));

				String provenienza = (String)request.getParameter("provenienza");
				String data =((String)request.getParameter("datarecupero"));
				Date date = Date.valueOf(data);
				String risoluzione = (String)request.getParameter("risoluzione");

				idloc= dloc.addLocalita( lat, lon, localita, comune, regione, stato);

				String tomcatBase = System.getProperty("catalina.base");
				String path = "/webapps/ImmaginiCampagna/";
				File saveDir = new File(tomcatBase);
				Part part = request.getPart("file");
				estensione = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));

				idimm = dimm.addImmagine(date, provenienza, risoluzione, estensione);

				dcampagna.addMappacampagna(idimm, idcamp, idloc);

				File immagine = new File(tomcatBase  + path + Integer.toString(idimm));
				if(!saveDir.exists()) {
					saveDir.mkdirs();
					System.out.println("la directory non esiste");
				}
				estensione = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));
				part.write(tomcatBase + path + idimm + estensione);
				System.out.println("dispatcher");
				request.setAttribute("idcampagna",idcamp);
				request.setAttribute("errore",false);
				getServletContext().getRequestDispatcher("/GetDettagli").forward(request, response);

			}
			else { 
				request.setAttribute("errore",true);
				getServletContext().getRequestDispatcher("/GetDettagli").forward(request, response);}  	 
		}

}

}




/*fare controlli sulla campagna
 * sistemare do post 
 * prelevare id campagna e inviare i parametri della stessa alla servlet dei dettagli
 * valutare se introdurre il daomappacampagna per le funzioni di set e get e cehck situati nei dao
 * controllare il salvataggio delle immagini riguardo al nome assegnato idcamp + idloc + idimm
 */










