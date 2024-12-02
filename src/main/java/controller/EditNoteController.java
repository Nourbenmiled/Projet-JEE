package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cours;
import model.CoursHome;
import model.Etudiant;
import model.EtudiantHome;
import model.Resultat;
import model.ResultatHome;

import java.io.IOException;

/**
 * Servlet implementation class EditNoteController
 */
public class EditNoteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EtudiantHome eHome;
	private static CoursHome cHome;
	private static ResultatHome rHome;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditNoteController() {
        super();
        eHome = new EtudiantHome();
        cHome = new CoursHome();
        rHome = new ResultatHome();
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
		// TODO Auto-generated method stub
		//doGet(request, response);
		String idEtuParam = request.getParameter("etudiant");
		String idCoursParam = request.getParameter("cours");
		String noteParam = request.getParameter("note");
		int idEtu = Integer.parseInt(idEtuParam);
		int idCours = Integer.parseInt(idCoursParam);
		float note = Float.parseFloat(noteParam);
		
		Cours cours = cHome.findById(idCours);
		Etudiant etu = eHome.findById(idEtu);
		Resultat res = new Resultat(cours,etu,note);
		
		try {
			rHome.saveOrUpdate(res);
			request.setAttribute("message", "Note Attribuée.");
			request.setAttribute("inscription", request.getParameter("idIns") );
			request.getRequestDispatcher("/WEB-INF/views/edit-note.jsp").forward(request, response);

		}catch(Exception e) {
			request.setAttribute("message", "Erreur.");
			request.setAttribute("inscription", request.getParameter("idIns") );
			request.getRequestDispatcher("/WEB-INF/views/edit-note.jsp").forward(request, response);
		}
		
	}

}
