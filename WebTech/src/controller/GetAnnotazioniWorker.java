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
 * Servlet implementation class GetAnnotazioniWorker
 */
@WebServlet("/GetAnnotazioniWorker")
public class GetAnnotazioniWorker extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection connection;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAnnotazioniWorker() {
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
		if(request.getSession().getAttribute("UtenteConnesso") == null) {
			response.sendRedirect("Login");
		}
		else{
			System.out.println("sono nella servlet get annotazioni");
			int idcampagna = Integer.parseInt(request.getParameter("idcampagna"));
			int idlocalita = Integer.parseInt(request.getParameter("idlocalita"));
			int idimmagine = Integer.parseInt(request.getParameter("idimmagine"));
			DAOCampagna daocampagna = new DAOCampagna(connection);
			DAOLocalita daolocalita = new DAOLocalita(connection);
			Campagna campagna = new Campagna();
			campagna = daocampagna.getCampagnaAvviata(idcampagna);
			Localita localita = new Localita();
			localita = daolocalita.getLocalita(idcampagna, idlocalita);
			System.out.println(campagna.getNome()+localita.getNome()+daocampagna.esisteCampagnaWorker(idcampagna, (String)request.getSession().getAttribute("UtenteConnesso")));
			if(campagna != null && localita != null && daocampagna.esisteCampagnaWorker(idcampagna, (String)request.getSession().getAttribute("UtenteConnesso"))) {
				DAOAnnotazione daoannotazione = new DAOAnnotazione(connection);
				List<Annotazione> listaannotazione = new ArrayList<Annotazione>();
				listaannotazione = daoannotazione.getAnnotazioni(idimmagine,idcampagna,idlocalita);
				String res1 = new Gson().toJson(listaannotazione);
				String res2 = new Gson().toJson(daoannotazione.checkAnnotazione((String)request.getSession().getAttribute("UtenteConnesso"),idimmagine));
				String res = "[" + res1 + "," + res2 +"]";
				System.out.println(res);
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(res);
				out.flush();
			}
			else {
				request.setAttribute("errore", true);
				getServletContext().getRequestDispatcher("/WEB-INF/HomePageWorker.jsp").forward(request, response);
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
