package controller;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Enseignant;
import model.EnseignantHome;
import model.Utilisateur;
import service.EnseignantService;
import service.UtilisateurService;

import java.io.IOException;
import java.util.List;


/**
 * Servlet implementation class EnseignantController
 */
public class EnseignantController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final EnseignantService enseignantService ;
	private final UtilisateurService userService;
	private final EnseignantHome enseignantHome;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnseignantController() {
        super();
        enseignantService = new EnseignantService(); 
        userService = new UtilisateurService();
        enseignantHome = new EnseignantHome();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";
 
        try {
            switch (action) {
                case "delete":
                    deleteEnseignant(request, response);
                    break;
                case "inscriptionEnseignant" :
                	listAttributionEns(request, response);
                	break;
                default:
                    listEnseignants(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("EnseignantController?action=list");
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try {
	            String idParam = request.getParameter("id");
	            String nom = request.getParameter("nom");
	            String prenom = request.getParameter("prenom");
	            String contact = request.getParameter("contact");

	            // Logs pour vérifier les données reçues
	            System.out.println("Nom: " + nom + ", Prénom: " + prenom + ", Contact: " + contact);

	            if (nom == null || prenom == null || contact == null) {
	                throw new IllegalArgumentException("Tous les champs sont obligatoires.");
	            }

	            int id = Integer.parseInt(idParam);
	            
	           Utilisateur user = userService.getUserById(id);
	           Enseignant enseignant = new Enseignant(user, nom, prenom, contact);
	           //System.out.println(enseigant.getNom());
	           enseignantService.addEnseignant(enseignant);
	           
	           HttpSession sessionAuth = request.getSession();
		       sessionAuth.setAttribute("enseignant",enseignant);
	           
	           request.getRequestDispatcher("WEB-INF/views/index_enseignant.jsp").forward(request, response);

	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("error", "Erreur lors du traitement : " + e.getMessage());
	            //request.getRequestDispatcher("WEB-INF/views/index_enseignant.jsp").forward(request, response);
	        }
	}
	
	
	
	private void listEnseignants(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Enseignant> enseignantList = enseignantHome.findAll();
        request.setAttribute("enseignantList", enseignantList);
        request.getRequestDispatcher("WEB-INF/views/enseignant-list.jsp").forward(request, response);
    }
 
    private void deleteEnseignant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Enseignant enseignant = enseignantHome.findById(id);
            if (enseignant != null) {
                enseignantHome.delete(enseignant);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        response.sendRedirect("EnseignantController?action=list");
    }
    
    private void listAttributionEns(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String idCours = request.getParameter("id");
    	List<Enseignant> enseignantList = enseignantHome.findAll();
    	request.setAttribute("idCours", idCours);
        request.setAttribute("enseignantList", enseignantList);
        request.getRequestDispatcher("WEB-INF/views/inscription-enseignant.jsp").forward(request, response);
    	
    }

}
