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
			String nome_utente = (String)request.getSession(true).getAttribute("UtenteConnesso");
			DAOUtente utente = new DAOUtente(connection);
			String tomcatBase = System.getProperty("catalina.base");
			String path = "\\webapps\\ImmaginiUtente\\";
			Utente user = utente.getInfo(nome_utente);
			System.out.println(request.getParameter("nuovaimmagine"));
			
				File saveDir = new File(tomcatBase);
				if(!saveDir.exists()) {
					saveDir.mkdirs();
				}
				Part part = request.getPart("nuovaimmagine");
				String estensione = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));
				utente.aggiornaImmagine(nome_utente, nome_utente + estensione);
				File immagine = new File(tomcatBase  + path + user.getImmagine());
				if(immagine.exists()) {
					immagine.delete();
				}
				part.write(tomcatBase  + path + nome_utente + estensione);
				getServletContext().getRequestDispatcher("/Profilo").forward(request, response);
		}
		
		
	}
}