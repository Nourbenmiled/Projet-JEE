package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Utilisateur;
import service.UtilisateurService;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class ValidationController
 */
public class ValidationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupère la liste des utilisateurs non validés
        List<Utilisateur> nonValidatedUsers = null;
        try {
        	
            // Appel au service ou à la méthode DAO
        	UtilisateurService userService = new UtilisateurService();
            nonValidatedUsers = userService.utilisateurNonValide();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Ajoute la liste comme attribut de requête
        request.setAttribute("userList", nonValidatedUsers);

        // Redirige vers la page JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/validation_admin.jsp");
        dispatcher.forward(request, response);
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
