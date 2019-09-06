package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileUploadBase.InvalidContentTypeException;

import DAO.DAOUtente;
import bean.Utente;

/**
 * Servlet implementation class CambiaImmagineProfilo
 */
@MultipartConfig
@WebServlet("/CambiaImmagineProfilo")
public class CambiaImmagineProfilo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CambiaImmagineProfilo() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("UtenteConnesso") == null) {
			response.sendRedirect("Login");
		}
		else {
			boolean elimina = false;
			Part part = null;
			try {
				elimina = ((String)request.getParameter("eliminafoto")).equals("true");
				part = request.getPart("nuovaimmagine");
			}
			catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("Profilo");
				return;
			}
			String estensione = null;
			String nome_utente = (String)request.getSession(true).getAttribute("UtenteConnesso");
			DAOUtente utente = new DAOUtente(connection);
			String tomcatBase = System.getProperty("catalina.base");
			String path = "/webapps/ImmaginiUtente/";
			Utente user = utente.getInfo(nome_utente);
			File saveDir = new File(tomcatBase);
			if(!saveDir.exists()) {
				saveDir.mkdirs();
			}
			
			if(elimina) {
				if(!user.getImmagine().equals("default.png")) {
					File immagine = new File(tomcatBase  + path + user.getImmagine());
					if(immagine.exists()) {
						immagine.delete();
					}
					utente.aggiornaImmagine(nome_utente, "default.png");
				}
			}
			else {
				File immagine = new File(tomcatBase  + path + user.getImmagine());
				if(immagine.exists()) {
					immagine.delete();
				}
				if(part != null) {
				estensione = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));
				part.write(tomcatBase  + path + nome_utente + estensione);
				
			}
			utente.aggiornaImmagine(nome_utente, nome_utente + estensione);} 
			getServletContext().getRequestDispatcher("/Profilo").forward(request, response);
		}
	}
}