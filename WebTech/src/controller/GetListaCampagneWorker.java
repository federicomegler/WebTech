package controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.google.gson.Gson;

import DAO.DAOCampagna;
import bean.Campagna;

/**
 * Servlet implementation class GetListaCampagneWorker
 */
@WebServlet("/GetListaCampagneWorker")
public class GetListaCampagneWorker extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection connection;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetListaCampagneWorker() {
        super();
        // TODO Auto-generated constructor stub
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
			System.out.println("ciao sono nella servlet worker");
			String tipo_richiesta = request.getParameter("richiesta"); //la campagna può essere optata oppure non optata
			if(tipo_richiesta.equals("optata")) {
				HttpSession session = request.getSession(true);
				String nome_utente = (String)session.getAttribute("UtenteConnesso");
				DAOCampagna camp = new DAOCampagna(connection);
				List<Campagna> arr = new ArrayList<Campagna>();		
				arr = camp.getWorkerCampagnaOptata(nome_utente);
				String res = new Gson().toJson(arr);
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				System.out.println("optata" + arr.size());
				out.print(res);
				out.flush();
			}
			else if(tipo_richiesta.equals("nonoptata")){
				HttpSession session = request.getSession(true);
				String nome_utente = (String)session.getAttribute("UtenteConnesso");
				DAOCampagna camp = new DAOCampagna(connection);
				List<Campagna> arr = new ArrayList<Campagna>();		
				arr = camp.getWorkerCampagnaNonSvolta(nome_utente);
				String res = new Gson().toJson(arr);
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				System.out.println("nonoptata" + arr.size());
				out.print(res);
				out.flush();
			}
			else {
				PrintWriter out = response.getWriter();
				out.print("errore");
				out.flush();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
