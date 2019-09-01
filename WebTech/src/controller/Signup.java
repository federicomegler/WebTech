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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/SignupPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String mail = request.getParameter("mail");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");

		String tipo = request.getParameter("tipo_account");
		
		
		if(password1==null || !password1.equals(password2)) {
			request.setAttribute("errorepassword", true);
			
		}
		if(tipo.equals("manager")) {
			Utente utente = new Utente();
			utente.setNome(username);
			utente.setMail(mail);
			utente.setPassword(password1);
			utente.setManager(true);
			DAOUtente daoutente = new DAOUtente(connection);
			if(daoutente.esisteUtente(utente) == 0) {
				if(daoutente.aggiungiManager(utente)) {
					response.sendRedirect(request.getContextPath()+"/Login");
				}
				else {
					request.setAttribute("errore", true);
				}
			}
			else {
				request.setAttribute("erroreesisteutente", true);
			}
		}
		else if(tipo.equals("lavoratore")){
			Utente utente = new Utente();
			utente.setNome(username);
			utente.setMail(mail);
			utente.setPassword(password1);
			utente.setManager(false);
			utente.setEsperienza("bassa");
			DAOUtente daoutente = new DAOUtente(connection);
			if(daoutente.esisteUtente(utente) == 0) {
				if(daoutente.aggiungiLavoratore(utente)) {
					response.sendRedirect(request.getContextPath()+"/Login");
				}
				else {
					request.setAttribute("errore", true);
				}
			}
			else {
				request.setAttribute("erroreesisteutente", true);
			}
		}
		else {
			request.setAttribute("errore", true);
		}
		getServletContext().getRequestDispatcher("/WEB-INF/SignupPage.jsp").forward(request, response);

	}
}