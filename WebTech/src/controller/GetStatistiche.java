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

/**
 * Servlet implementation class GetStatistiche
 */
@WebServlet("/GetStatistiche")
public class GetStatistiche extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection connection;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStatistiche() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("UtenteConnesso") == null) {
			response.sendRedirect("Login");
		}
		else if((boolean)request.getSession().getAttribute("tipo") == false) {
			response.sendRedirect("Home?errore=1");
		}
		else {
			int idcampagna = 0;
			try {
				idcampagna = Integer.parseInt(request.getParameter("idcampagna"));
			}
			catch(Exception e){
				e.printStackTrace();
				response.sendRedirect("Home?errore=1");
				return;
			}
			String creatore = (String)request.getSession().getAttribute("UtenteConnesso");
			DAOCampagna daocampagna = new DAOCampagna(connection);
			DAOImmagine daoimmagine = new DAOImmagine(connection);
			DAOAnnotazione daoannotazione = new DAOAnnotazione(connection);
			DAOLocalita daolocalita = new DAOLocalita(connection);
			Campagna campagna = new Campagna();
			campagna = daocampagna.getCampagna(idcampagna, creatore);
			if(campagna != null) {
				int numerofoto = daoimmagine.getTotaleImmaginiCampagna(idcampagna);
				int numeroann = daoannotazione.getNumAnnotazioniCampagna(idcampagna);
				int numeroloc = daolocalita.totaleLocalita(idcampagna);
				List<Immagine> listaimmaginiconfl = new ArrayList<Immagine>();
				listaimmaginiconfl = daoimmagine.getImmaginiLocalitaConflitto(idcampagna);
				request.setAttribute("idcampagna", idcampagna);
				request.setAttribute("totLocalita", numeroloc);
				request.setAttribute("totImmagini", numerofoto);
				request.setAttribute("totAnnotazioni", numeroann);
				request.setAttribute("listaImmagini", listaimmaginiconfl);
				request.setAttribute("nome", campagna.getNome());
				getServletContext().getRequestDispatcher("/WEB-INF/PaginaStatistiche.jsp").forward(request, response);
			}
			else {
				getServletContext().getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
			}

		}
	}
}
