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
import javax.servlet.http.Part;

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
		// TODO Auto-generated method stub
		String lon = request.getParameter("");
		String lat = request.getParameter("");
		String stato = request.getParameter("");
		String regione = request.getParameter("");
		String comune = request.getParameter("");
		String localita = request.getParameter("");
		String provenienza = request.getParameter("");
		String data_recupero = request.getParameter("");
		String risoluzione = request.getParameter("");
		
		
		String tomcatBase = System.getProperty("catalina.base");
		String path = "\\webapps\\Images\\";
		File saveDir = new File(tomcatBase);
		if(!saveDir.exists()) {
			saveDir.mkdirs();
		}
		Part part = request.getPart("file");
		String estensione = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));
		System.out.println(estensione);
		//inserisco nel database i dati relativi all'immagine e all'estensione.
		//il database mi restituisce il nuovo nome(unico), salvo il file con il nuovo nome
		part.write(tomcatBase  + path + "file3.jpg");
		
	}

}
