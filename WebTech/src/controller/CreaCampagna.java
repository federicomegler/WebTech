package controller;

import java.io.File;
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
import javax.servlet.http.Part;

import DAO.DAOCampagna;

/**
 * Servlet implementation class CreaCampagna
 */
@WebServlet("/CreaCampagna")
public class CreaCampagna extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreaCampagna() {
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
		response.sendRedirect("Home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("UtenteConnesso") == null) {
			response.sendRedirect("Login");
		}
		else if((boolean)request.getSession().getAttribute("tipo") == false) {
			response.sendRedirect("Home");
		}
		else {
			int id = 0;
			HttpSession session = request.getSession();
			String username =(String)session.getAttribute("UtenteConnesso");
			String nomecampagna = request.getParameter("nome");
			String committente = request.getParameter("committente");
			if(nomecampagna == null || committente == null) {
				request.setAttribute("errore", true);
				response.sendRedirect("Home?errore=1");
			}
			else {
				DAOCampagna daocampagna= new DAOCampagna(connection);
				id = daocampagna.addCampagna(nomecampagna, committente,username);
				request.setAttribute("idcampagna", id);
				request.setAttribute("nomecampagna", nomecampagna);
				request.setAttribute("committente", committente);
				request.setAttribute("stato","creata");
				request.getRequestDispatcher("/WEB-INF/DettaglioCampagna.jsp").forward(request, response);
			}
			
		}
	}
}
