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

import DAO.DAOCampagna;
import bean.Campagna;

/**
 * Servlet implementation class GetDettagliWorker
 */
@WebServlet("/GetDettagliWorker")
public class GetDettagliWorker extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection connection;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDettagliWorker() {
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
    	else {
    		String id = (String)request.getParameter("idcampagna");
    		request.setAttribute("errore",false);
    		Campagna campagna = null;
    		DAOCampagna daocampagna = new DAOCampagna(connection);
    		campagna = daocampagna.getCampagnaAvviata(Integer.parseInt(id));
    		if(campagna != null) {
    			if(daocampagna.esisteCampagnaWorker(Integer.parseInt(id), (String)request.getSession().getAttribute("UtenteConnesso"))){
    				request.setAttribute("iscritto", true);
    			}
    			else {
    				request.setAttribute("iscritto", false);
    			}
    			request.setAttribute("nomecampagna", campagna.getNome());
    			request.setAttribute("committente", campagna.getCommittente());
    			request.setAttribute("stato",campagna.getStato());
    			request.setAttribute("idcampagna",campagna.getID_campagna());
    			request.getRequestDispatcher("/WEB-INF/DettaglioCampagnaWorker.jsp").forward(request, response);

    		}
    		else {
    			request.setAttribute("errore",true);
    			request.getRequestDispatcher("/WEB-INF/HomePageWorker.jsp").forward(request, response);
    		}

    	}
	}

}
