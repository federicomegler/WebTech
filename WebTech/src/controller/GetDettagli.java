package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DAOCampagna;

/**
 * Servlet implementation class GetDettagli
 */
@WebServlet("/GetDettagli")
public class GetDettagli extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDettagli() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("UtenteConnesso") == null) {
			response.sendRedirect("Login");
		}
		else {
			//va controllato che i dati siano reali e non modificati a lato client
			String nomecampagna =(String)request.getParameter("nome");
			String committente = (String)request.getParameter("committente");
			String stato =(String)request.getParameter("stato");
			String id = (String)request.getParameter("idcampagna");
			request.setAttribute("nomecampagna", nomecampagna);
			request.setAttribute("committente", committente);
			request.setAttribute("stato",stato);
			request.setAttribute("idcampagna",id);
			request.setAttribute("errore",false);
			request.getRequestDispatcher("/WEB-INF/DettaglioCampagna.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}