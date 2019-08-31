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
import javax.servlet.http.HttpSession;

import DAO.DAOCampagna;
import bean.Campagna;

/**
 * Servlet implementation class GetDettagli
 */
@WebServlet("/GetDettagli")
public class GetDettagli extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDettagli() {
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
			//va controllato che i dati siano reali e non modificati a lato client
			String id = (String)request.getParameter("idcampagna");
			request.setAttribute("errore",false);
			Campagna campagna = new Campagna();
			DAOCampagna daocampagna = new DAOCampagna(connection);
			campagna = daocampagna.getCampagna(Integer.parseInt(id), (String)request.getSession().getAttribute("UtenteConnesso"));
			if(campagna != null) {
				request.setAttribute("nomecampagna", campagna.getNome());
				request.setAttribute("committente", campagna.getCommittente());
				request.setAttribute("stato",campagna.getStato());
				request.setAttribute("idcampagna",campagna.getID_campagna());
				request.getRequestDispatcher("/WEB-INF/DettaglioCampagna.jsp").forward(request, response);
			}
			else {
				request.setAttribute("errore",true);
				request.getRequestDispatcher("/WEB-INF/HomePage.jsp").forward(request, response);
			}
			
		}
	}
}
