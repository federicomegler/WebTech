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
 * Servlet implementation class GetMap
 */
@WebServlet("/GetMap")
public class GetMap extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection connection;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMap() {
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
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("UtenteConnesso") == null) {
			response.sendRedirect("Login");
		}
		else {
			int id = 0;
			try {
				id = Integer.parseInt(request.getParameter("idcampagna"));
			}
			catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("Home?errore=1");
				return;
			}
			Campagna campagna = new Campagna();
			DAOCampagna daocampagna = new DAOCampagna(connection);
			campagna = daocampagna.getCampagna(id,(String)request.getSession().getAttribute("UtenteConnesso"));
			if(campagna != null) {
				request.setAttribute("idcampagna", id);
				getServletContext().getRequestDispatcher("/WEB-INF/MapPage.jsp").forward(request, response);
			}
			else {
				request.setAttribute("errore", true);
				getServletContext().getRequestDispatcher("/WEB-INF/DettaglioCampagna.jsp").forward(request, response);
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
