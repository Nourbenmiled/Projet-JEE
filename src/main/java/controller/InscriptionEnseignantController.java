package controller;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cours;
import model.CoursHome;
import model.Enseignant;
import model.InscriptionEnseignant;
import model.InscriptionEnseignantHome;
import service.EnseignantService;

import java.io.IOException;

/**
 * Servlet implementation class InscriptionEnseignantController
 */
public class InscriptionEnseignantController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EnseignantService eService ;
	private static CoursHome cHome;
	private static InscriptionEnseignantHome ieHome;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionEnseignantController() {
        super();
        eService = new EnseignantService();
        cHome = new CoursHome();
        ieHome = new InscriptionEnseignantHome();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/views/inscription-enseignant.jsp").forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] selectedEnseignants = request.getParameterValues("selectedEnseignants");
		String idCoursStr = request.getParameter("idCours");
		if (selectedEnseignants != null) {
	        for (String id : selectedEnseignants) {
	            try {
	                // Exemple de traitement
	                int enseignantId = Integer.parseInt(id);
	                int idCours = Integer.parseInt(idCoursStr);
	                Enseignant enseignant = eService.getEnseignantById(enseignantId);
	                Cours cours = cHome.findById(idCours);
	                if(cours != null && enseignant != null) {
	                	
	                	InscriptionEnseignant insEns = new InscriptionEnseignant(cours,enseignant);
	                	ieHome.saveOrUpdate(insEns);
	                	request.setAttribute("message", "Les Cours ont bien été assignés aux Enseignants.");
	                	System.out.println("Traitement de l'enseignant avec ID : " + enseignantId);
	                } else {
	                	System.out.println("Cours ou Enseignant est null");
	                }
	               
	            } catch (NumberFormatException e) {
	                System.err.println("ID invalide : " + id);
	            }
	        }
	    } else {
	        System.out.println("Aucun enseignant sélectionné.");
	    }

		request.getRequestDispatcher("WEB-INF/views/inscription-enseignant.jsp").forward(request, response);
	}

}
