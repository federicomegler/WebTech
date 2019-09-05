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
import DAO.DAOUtente;
import bean.Campagna;

/**
 * Servlet implementation class IscrizioneCampagna
 */
@WebServlet("/IscrizioneCampagna")
public class IscrizioneCampagna extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection connection;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IscrizioneCampagna() {
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
<<<<<<< HEAD
		response.sendRedirect("Home?errore=1");
=======
		response.sendRedirect("Home");
>>>>>>> branch 'master' of https://gitlab.com/FedeMeg/web-tech.git
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("UtenteConnesso") == null) {
			response.sendRedirect("Login");
		}
		else if((boolean)request.getSession().getAttribute("tipo") == true) {
			response.sendRedirect("Home?errore=1");
		}
		else {
			int idcampagna = 0;
			try {
				idcampagna = Integer.parseInt(request.getParameter("idcampagna"));
			}
			catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("Home?errore=1");
				return;
			}
			
			String user = (String)request.getSession().getAttribute("UtenteConnesso");
			DAOCampagna daocampagna = new DAOCampagna(connection);
			Campagna campagna = daocampagna.getCampagnaAvviata(idcampagna);
			if(campagna != null) {
				daocampagna.addsubscription(idcampagna, user);
				DAOUtente daoutente = new DAOUtente(connection);
				daoutente.incrementaEsperienza(user);
				request.setAttribute("idcampagna", idcampagna);
				getServletContext().getRequestDispatcher("/GetDettagliWorker").forward(request, response);
			}
			else {
				response.sendRedirect("Home?errore=1");
			}
		}
	}
}