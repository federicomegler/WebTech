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
import bean.Annotazione;
import bean.Campagna;
import bean.Localita;

/**
 * Servlet implementation class GetInfoStatistiche
 */
@WebServlet("/GetInfoStatistiche")
public class GetInfoStatistiche extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection connection;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetInfoStatistiche() {
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
			int idcampagna = Integer.parseInt(request.getParameter("idcampagna"));
			int idimmagine = Integer.parseInt(request.getParameter("idimmagine"));
			DAOCampagna daocampagna = new DAOCampagna(connection);
			DAOImmagine daoimmagine = new DAOImmagine(connection);
			DAOLocalita daolocalita = new DAOLocalita(connection);
			Campagna campagna = new Campagna();
			campagna = daocampagna.getCampagna(idcampagna, (String)request.getSession().getAttribute("UtenteConnesso"));
			if(campagna != null) {
				DAOAnnotazione daoannotazione = new DAOAnnotazione(connection);
				List<Annotazione> listaannotazione = new ArrayList<Annotazione>();
				Localita localita = daolocalita.getLocalita(idimmagine);
				listaannotazione = daoannotazione.getAnnotazioni(idimmagine,idcampagna,localita.getID_localita());
				String res1 = new Gson().toJson(listaannotazione);
				String res2 = new Gson().toJson(localita);  
				String res1_2 = "["+res1+","+res2+"]";
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(res1_2);
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
