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
import DAO.DAOUtente;
import bean.Annotazione;
import bean.Campagna;
import bean.Localita;
import bean.Utente;

/**
 * Servlet implementation class CreaCommento
 */
@WebServlet("/CreaCommento")
public class CreaCommento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;   
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreaCommento() {
		super();
		// TODO Auto-generated constructor stub
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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("UtenteConnesso") == null) {
			response.sendRedirect("Login");

		}
		else {
			String nota = request.getParameter("nota");
			String validita = request.getParameter("validita");
			String user = (String)request.getSession().getAttribute("UtenteConnesso");

			int idcampagna = Integer.parseInt(request.getParameter("idcampagna"));
			int idimmagine = Integer.parseInt(request.getParameter("idimmagine"));
			int idlocalita = Integer.parseInt(request.getParameter("idlocalita"));
			DAOCampagna daocampagna = new DAOCampagna(connection);
			DAOImmagine daoimmagine = new DAOImmagine(connection);
			DAOLocalita daolocalita = new DAOLocalita(connection);
			DAOUtente daoutente = new DAOUtente(connection);
			Localita localita = new Localita();
			Campagna campagna = new Campagna();
			Utente utente = daoutente.getInfo(user);
			localita = daolocalita.getLocalita(idcampagna, idlocalita);
			campagna = daocampagna.getCampagnaAvviata(idcampagna);
			boolean immagine = daoimmagine.checkImmagine(idcampagna, idimmagine, idlocalita);
			if(localita != null && campagna != null && immagine && daocampagna.esisteCampagnaWorker(idcampagna, (String)request.getSession().getAttribute("UtenteConnesso"))) {
				DAOAnnotazione daoannotazione = new DAOAnnotazione(connection);
				if(!daoannotazione.checkAnnotazione(user, idimmagine)) {
					String esito = new Gson().toJson(daoannotazione.addAnnotazione(idimmagine, user, validita.equals("vero"), utente.getEsperienza(), nota));
					PrintWriter out = response.getWriter();
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					out.print(esito);
					out.flush();
				}
				else {
					String esito = new Gson().toJson(false);
					PrintWriter out = response.getWriter();
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					out.print(esito);
					out.flush();
				}
			}
			else {
				request.setAttribute("errore", true);
				getServletContext().getRequestDispatcher("/WEB-INF/HomePageWorker.jsp").forward(request, response);

			}
		}

	}
}
