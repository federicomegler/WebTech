package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAOUtente;
import bean.Utente;

/**
 * Servlet implementation class Profilo
 */
@WebServlet("/Profilo")
public class Profilo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profilo() {
        super();
    }
    
    public void init(){
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
		if(request.getSession().getAttribute("UtenteConnesso") == null) {
			response.sendRedirect("Login");
		}
		else {
			String utente = (String)request.getSession().getAttribute("UtenteConnesso");
			DAOUtente dati_utente = new DAOUtente(connection);
			Utente info = dati_utente.getInfo(utente);
			request.setAttribute("nickname", info.getNome());
			request.setAttribute("mail", info.getMail());
			request.setAttribute("esperienza", info.getEsperienza());
			if(info.getImmagine() == null) {
				request.setAttribute("immagine","default.png");
			}
			else {
				request.setAttribute("immagine",info.getImmagine());
			}
			
			if(info.isManager()) {
				request.setAttribute("tipo", "Manager");
			}
			else {
				request.setAttribute("tipo", "Lavoratore");
			}
			getServletContext().getRequestDispatcher("/WEB-INF/PaginaProfilo.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
