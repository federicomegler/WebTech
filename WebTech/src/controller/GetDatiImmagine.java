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

import com.google.gson.Gson;

import DAO.DAOAnnotazione;
import DAO.DAOCampagna;
import DAO.DAOImmagine;
import DAO.DAOLocalita;
import bean.Campagna;
import bean.Immagine;
import bean.Localita;

/**
 * Servlet implementation class GetDatiLocalita
 */
@WebServlet("/GetDatiImmagine")
public class GetDatiImmagine extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection connection;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDatiImmagine() {
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
		else if((boolean)request.getSession().getAttribute("tipo") == false) {
			response.sendRedirect("Home");
		}
		else {
			int idcampagna = 0, idlocalita = 0;
			try {
				idcampagna = Integer.parseInt(request.getParameter("idcampagna"));
				idlocalita = Integer.parseInt(request.getParameter("idlocalita"));
			}
			catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("Home");
				return;
			}
			
			DAOCampagna daocampagna = new DAOCampagna(connection);
			DAOImmagine daoimmagine = new DAOImmagine(connection);
			DAOLocalita daolocalita = new DAOLocalita(connection);
			Campagna campagna = null;
			campagna = daocampagna.getCampagna(idcampagna, (String)request.getSession().getAttribute("UtenteConnesso"));
			Localita localita = null;
			localita = daolocalita.getLocalita(idcampagna, idlocalita);
			if(campagna != null && localita != null) {
				List<Immagine> listaimmagine = new ArrayList<Immagine>();
				listaimmagine = daoimmagine.getImmaginiLocalita(idcampagna, idlocalita);
				String res1 = new Gson().toJson(listaimmagine);
				String errore = new Gson().toJson(false);
				String res = "[" + res1 + "," + errore + "]";
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(res);
				out.flush();
			}
			else {
				String res1 = "";
				String errore = new Gson().toJson(false);
				String res = "[" + res1 + "," + errore + "]";
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(res);
				out.flush();
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
