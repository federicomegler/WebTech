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
 * Servlet implementation class ModificaProfiloPassword
 */
@WebServlet("/ModificaProfiloPassword")
public class ModificaProfiloPassword extends HttpServlet {
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
    public ModificaProfiloPassword() {
        super();
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
		else {	
			HttpSession s=request.getSession(true);
			String np1 =(String)request.getParameter("np1");
			String np2 =(String)request.getParameter("np2");
			String p =(String)request.getParameter("p");
			String nome_utente = (String)s.getAttribute("UtenteConnesso");
			DAOUtente utente = new DAOUtente(connection);
			Utente user = new Utente();
			user = utente.checkLogin(nome_utente, p);
			boolean [] arr= {true,true}; //primo password errata secondo np1 e np2 non corrispondono
			
			if(np1.equals(np2)) {
				arr[1]=false;
			}
			if(user.isValid()) {
				arr[0]=false;
				if(!arr[1]) {
			user.setPassword(np1);
			utente.modificaPassword(user);
				}
			}
				String res=new Gson().toJson(arr);
				PrintWriter out= response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(res);
				out.flush();
			
		}
		
	}

}
