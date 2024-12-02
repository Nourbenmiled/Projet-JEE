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
 * Servlet implementation class ValiderInscriptionController
 */
public class ValiderInscriptionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValiderInscriptionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Récupérer l'ID de l'utilisateur depuis les paramètres de la requête
	    String userIdParam = request.getParameter("userId");
	    String message;

	    if (userIdParam != null) {
	        try {
	            int userId = Integer.parseInt(userIdParam);

	            // Appeler la méthode de mise à jour
	            UtilisateurService utilisateurDAO = new UtilisateurService();
	            boolean isUpdated = utilisateurDAO.validerInscription(userId);

	            if (isUpdated) {
	                // Message de succès
	                message = "L'inscription de l'utilisateur a été validée avec succès.";
	            } else {
	                // Message d'échec (exemple : utilisateur introuvable ou déjà validé)
	                message = "Erreur : L'utilisateur est introuvable ou son inscription a déjà été validée.";
	            }
	        } catch (NumberFormatException e) {
	            // Message d'erreur si l'ID utilisateur n'est pas valide
	            message = "Erreur : ID utilisateur invalide.";
	        }
	    } else {
	        // Message d'erreur si aucun ID utilisateur n'est fourni
	        message = "Erreur : Aucun utilisateur spécifié.";
	    }

	    // Ajouter le message en tant qu'attribut de requête
	    request.setAttribute("message", message);

	    // Récupérer à nouveau la liste des utilisateurs non validés
	    UtilisateurService utilisateurDAO = new UtilisateurService();
	    List<Utilisateur> userList = utilisateurDAO.utilisateurNonValide();
	    request.setAttribute("userList", userList);

	    // Rediriger vers la page de validation
	    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/validation_admin.jsp");
	    dispatcher.forward(request, response);
	}

}
