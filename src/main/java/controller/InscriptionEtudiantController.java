package controller;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cours;
import model.CoursHome;
import model.Etudiant;
import model.Inscription;
import model.InscriptionHome;
import service.EtudiantService;

import java.io.IOException;
import java.util.Date;

/**
 * Servlet implementation class InscriptionEtudiantController
 */
public class InscriptionEtudiantController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EtudiantService eService;
	private static CoursHome cHome;
	private static InscriptionHome iHome;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionEtudiantController() {
        super();
        eService = new EtudiantService();
        cHome = new CoursHome();
        iHome = new InscriptionHome();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/views/inscription-etudiant.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] selectedEtudiants = request.getParameterValues("selectedEtudiants");
		String idCoursStr = request.getParameter("idCours");
		if (selectedEtudiants != null) {
	        for (String id : selectedEtudiants) {
	            try {
	                // Exemple de traitement
	                int etudiantId = Integer.parseInt(id);
	                int idCours = Integer.parseInt(idCoursStr);
	                Etudiant etudiant = eService.getEtudiantById(etudiantId);
	                Cours cours = cHome.findById(idCours);
	                if(cours != null && etudiant != null) {
	                	
	                	Inscription insEns = new Inscription(cours,etudiant, new Date());
	                	iHome.saveOrUpdate(insEns);
	                	request.setAttribute("message", "Les Etudiants ont bien été inscrits à ce cours.");
	                	System.out.println("Traitement de l'etudiant avec ID : " + etudiantId);
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
