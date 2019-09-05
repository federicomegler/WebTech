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

import DAO.DAOCampagna;
import DAO.DAOImmagine;
import DAO.DAOLocalita;
import bean.Campagna;
import bean.Immagine;
import bean.Localita;

/**
 * Servlet implementation class GetDatiImmaginiWorker
 */
@WebServlet("/GetDatiImmaginiWorker")
public class GetDatiImmaginiWorker extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection connection;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDatiImmaginiWorker() {
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
    	else if((boolean)request.getSession().getAttribute("tipo") == true) {
    		response.sendRedirect("Home?errore=1");
    	}
    	else {
    		int idcampagna = 0, idlocalita = 0;
    		try {
    			idcampagna = Integer.parseInt(request.getParameter("idcampagna"));
        		idlocalita = Integer.parseInt(request.getParameter("idlocalita"));
    		}
    		catch (Exception e) {
    			e.printStackTrace();
    			response.sendRedirect("Home?errore=1");
    			return;
    		}
    		
    		DAOCampagna daocampagna = new DAOCampagna(connection);
    		DAOImmagine daoimmagine = new DAOImmagine(connection);
    		DAOLocalita daolocalita = new DAOLocalita(connection);
    		Campagna campagna = new Campagna();
    		campagna = daocampagna.getCampagnaAvviata(idcampagna);
    		Localita localita = new Localita();
    		localita = daolocalita.getLocalita(idcampagna, idlocalita);
    		if(campagna != null && localita != null && daocampagna.esisteCampagnaWorker(idcampagna, (String)request.getSession().getAttribute("UtenteConnesso"))) {
    			List<Immagine> listaimmagine = new ArrayList<Immagine>();
    			listaimmagine = daoimmagine.getImmaginiLocalita(idcampagna, idlocalita);
    			String res = new Gson().toJson(listaimmagine);    			
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
		doGet(request, response);
	}

}
