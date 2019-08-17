package controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.google.gson.Gson;

import DAO.DAOUtente;
import bean.Utente;

/**
 * Servlet implementation class ModificaProfilo
 */
@WebServlet("/ModificaProfiloMail")
public class ModificaProfiloMail extends HttpServlet {
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
    public ModificaProfiloMail() {
        super();
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
			HttpSession s = request.getSession(true);
			String mail =(String)request.getParameter("nuovaemail");
			String nome_utente = (String)s.getAttribute("UtenteConnesso");
			DAOUtente utente = new DAOUtente(connection);
			Utente user = new Utente();
			user.setMail(mail); 
			if(utente.esisteUtente(user)==0) {
				user.setNome(nome_utente);
				utente.modificaMail(user);
				String res=new Gson().toJson(mail);
				PrintWriter out= response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(res);
				out.flush();
				
			}
			
		}
		
	}
	
}