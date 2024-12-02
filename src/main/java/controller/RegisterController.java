package controller;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Utilisateur;
import service.UtilisateurService;

import java.io.IOException;

/**
 * Servlet implementation class RegisterController
 */
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int role = Integer.parseInt(request.getParameter("role"));
		
		UtilisateurService userService = new UtilisateurService();
		Utilisateur user = new Utilisateur(username,password,role,0);
		try {
			userService.addUtilisateur(user);
			request.setAttribute("succes","Vous êtes bien inscris.Veuillez attendre la validation pour vous connectez.");
			request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request, response);
		}catch(Exception e) {
			request.setAttribute("error","Une erreur est survenue. Veuillez reesayer ulterieurement!");
			request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request, response);
		}
		
				
	}

}
