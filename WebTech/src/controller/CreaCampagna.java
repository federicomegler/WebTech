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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("UtenteConnesso") == null) {
			response.sendRedirect("Login");
		}
		else {
			HttpSession session = request.getSession();
			String username =(String)session.getAttribute("UtenteConnesso");
			String nomecampagna = request.getParameter("nome");
			String committente = request.getParameter("committente");
			DAOCampagna daocampagna= new DAOCampagna(connection);
			daocampagna.addCampagna(nomecampagna, committente,username);
			session.setAttribute("nomecampagna", nomecampagna);
			session.setAttribute("committente", committente);
			session.setAttribute("stato","creata");
			request.getRequestDispatcher("/WEB-INF/DettaglioCampagna.jsp").forward(request, response);
		}
		
		
	}

}
