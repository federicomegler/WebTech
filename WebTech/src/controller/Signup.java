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
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
       
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
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/SignupPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		String tipo = request.getParameter("tipo_account");
		
		if(tipo.equals("manager")) {
			Utente utente = new Utente();
			utente.setNome(username);
			utente.setMail(mail);
			utente.setPassword(password);
			utente.setManager(true);
			DAOUtente daoutente = new DAOUtente(connection);
			if(daoutente.esisteUtente(utente) == 0) {
				if(daoutente.aggiungiManager(utente)) {
					response.sendRedirect(request.getContextPath()+"/Login");
				}
				else {
					request.setAttribute("errore", 1);
					doGet(request, response);
				}
			}
			else {
				request.setAttribute("errore", 1);
				doGet(request, response);
			}
		}
		else {
			Utente utente = new Utente();
			utente.setNome(username);
			utente.setMail(mail);
			utente.setPassword(password);
			utente.setManager(false);
			utente.setEsperienza("bassa");
			DAOUtente daoutente = new DAOUtente(connection);
			if(daoutente.esisteUtente(utente) == 0) {
				//--------------------------------------------------------------------------
				if(daoutente.aggiungiManager(utente)) {
					response.sendRedirect(request.getContextPath()+"/Login");
				}
				else {
					request.setAttribute("errore", 1);
					doGet(request, response);
				}
			}
			else {
				request.setAttribute("errore", 1);
				doGet(request, response);
			}
		}
	}
}