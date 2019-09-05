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
import DAO.DAOLocalita;
import bean.Campagna;
import bean.Localita;

/**
 * Servlet implementation class GetLocalita
 */
@WebServlet("/GetLocalita")
public class GetLocalita extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

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
	 * @see HttpServlet#HttpServlet()
	 */
	public GetLocalita() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("UtenteConnesso") == null) {
			response.sendRedirect("Login");
		}
		else if((boolean)request.getSession().getAttribute("tipo") == false) {
			response.sendRedirect("Home?errore=1");
		}
		else {
			DAOLocalita l = new DAOLocalita (connection);
			DAOCampagna dcampagna = new DAOCampagna(connection);
			String nome_utente = (String)request.getSession(true).getAttribute("UtenteConnesso");
			int idcamp = 0;
			try {
				idcamp = Integer.parseInt((String)request.getParameter("idcampagna"));
			}
			catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("Home?errore=1");
				return;
			}
			Campagna c= new Campagna();
			c = dcampagna.getCampagna(idcamp, nome_utente);
			if(c!=null){

				List<Localita> loc= new ArrayList<Localita>();
				loc=l.getPlaces(c.getID_campagna());
				String res = new Gson().toJson(loc);
				PrintWriter out= response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(res);
				out.flush();

			}else {
				request.setAttribute("errore",true);
				getServletContext().getRequestDispatcher("/GetDettagli").forward(request, response);
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