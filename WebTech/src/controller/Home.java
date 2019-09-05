package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DAOUtente;

/**
 * Servlet implementation class HomePage
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection connection;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
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
		if(request.getSession().getAttribute("UtenteConnesso") == null) {
			response.sendRedirect("Login");
		}
		else {
			boolean errore = false;
			if(request.getAttribute("errore") != null) {
				errore = (boolean)request.getAttribute("errore");
			}
			request.setAttribute("nomeutente", (String)request.getSession(true).getAttribute("UtenteConnesso"));
			request.setAttribute("errore", errore);
			DAOUtente daoutente = new DAOUtente(connection);
			if(daoutente.isManager((String)request.getSession(true).getAttribute("UtenteConnesso")) == 1 ) {
				request.getRequestDispatcher("/WEB-INF/HomePage.jsp").forward(request, response);
			}
			else if(daoutente.isManager((String)request.getSession(true).getAttribute("UtenteConnesso")) == 0) {
				request.getRequestDispatcher("/WEB-INF/HomePageWorker.jsp").forward(request, response);
			}
			else {
				//se c'è qualche problema torno alla pagina di login
				request.getRequestDispatcher("/Login").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
