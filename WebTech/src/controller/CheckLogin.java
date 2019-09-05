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
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import DAO.DAOUtente;
import bean.Utente;

/**
 * Servlet implementation class CheckLogin
 */
@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckLogin() {
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
		if(request.getSession().getAttribute("UtenteConnesso") == null){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			DAOUtente daologin = new DAOUtente(connection);
			Utente utente = new Utente();
			utente = daologin.checkLogin(username, password);
			if(utente.isValid()) {
				HttpSession session = request.getSession(true);
				session.setAttribute("UtenteConnesso", utente.getNome());
				session.setAttribute("tipo", utente.isManager());
				response.sendRedirect("Home");
			}
			else {
				request.setAttribute("errore", 1);
				getServletContext().getRequestDispatcher("/Login").forward(request, response);
			}
		}
		else {
			response.sendRedirect("Home");
		}
	}
}