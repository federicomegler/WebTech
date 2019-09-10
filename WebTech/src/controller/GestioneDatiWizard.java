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
		response.sendRedirect("Home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("UtenteConnesso") == null) {
			response.sendRedirect("Login");

		}
		else if((boolean)request.getSession().getAttribute("tipo") == false) {
			response.sendRedirect("Home");
		}
		else {			
			DAOCampagna dcampagna = new DAOCampagna(connection);
			DAOImmagine dimm = new DAOImmagine(connection);
			DAOLocalita dloc = new DAOLocalita(connection);

			int idloc = 0,idimm = 0,idcamp = 0;
			String estensione =null;
			String nome_utente = (String)request.getSession(true).getAttribute("UtenteConnesso");
			try {
				idcamp = Integer.parseInt((String)request.getParameter("idcampagna"));
			}
			catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("Home");
				return;
			}

			Campagna c = new Campagna();
			c = dcampagna.getCampagna(idcamp, nome_utente);

			if(c != null && c.getStato().equals("creata")) {
				String localita = (String)request.getParameter("localita");
				String comune = (String)request.getParameter("comune");
				String stato = (String)request.getParameter("Stato");
				String regione = (String)request.getParameter("regione");
				String provenienza = (String)request.getParameter("provenienza");
				String data =((String)request.getParameter("datarecupero"));
				String risoluzione = (String)request.getParameter("risoluzione");
				if(localita == null || comune == null || stato == null || regione == null || provenienza == null || data == null || risoluzione == null) {
					response.sendRedirect("Home?errore=1");
					return;
				}
				double lat = 0, lon = 0;
				try {
					lat = Double.parseDouble((String)request.getParameter("lat"));
					lon = Double.parseDouble((String)request.getParameter("lon"));
				}
				catch (Exception e) {
					e.printStackTrace();
					response.sendRedirect("Home?errore=1");
					return;
				}
				
				if(Math.abs(lat)>90 || Math.abs(lon)>180){
					response.sendRedirect("Home?errore=1");
					return;
				}

				Date date = Date.valueOf(data);

				idloc= dloc.addLocalita( lat, lon, localita, comune, regione, stato);

				String tomcatBase = System.getProperty("catalina.base");
				String path = "/webapps/ImmaginiCampagna/";
				File saveDir = new File(tomcatBase);
				Part part = null;

				try {
					part = request.getPart("file");
				}
				catch (Exception e) {
					e.printStackTrace();
					response.sendRedirect("Home?errore=1");
					return;
				}

				estensione = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));

				idimm = dimm.addImmagine(date, provenienza, risoluzione, estensione);

				dcampagna.addMappacampagna(idimm, idcamp, idloc);

				File immagine = new File(tomcatBase  + path + Integer.toString(idimm));
				if(!saveDir.exists()) {
					saveDir.mkdirs();
				}
				estensione = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));
				part.write(tomcatBase + path + idimm + estensione);
				request.setAttribute("idcampagna",idcamp);
				request.setAttribute("errore",false);
				response.sendRedirect("GetDettagli?idcampagna=" + idcamp);

			}
			else { 
				request.setAttribute("errore",true);
				response.sendRedirect("GetDettagli?idcampagna=" + idcamp);
				}  	 
		}
	}
}