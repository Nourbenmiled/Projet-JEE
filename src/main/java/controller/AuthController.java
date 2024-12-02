package controller;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Enseignant;
import model.Etudiant;
import model.Utilisateur;
//import model.UtilisateurHome;
import service.EnseignantService;
import service.EtudiantService;
import service.UtilisateurService;

//import org.hibernate.Session;

import java.io.IOException;

/**
 * Servlet implementation class AuthController
 */
public class AuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UtilisateurService userService = new UtilisateurService();
		Utilisateur user = userService.getUtilisateurByUsername(username);
		
		EtudiantService etuService = new EtudiantService();
		Etudiant etu = new Etudiant();
		
		EnseignantService enseignantService = new EnseignantService();
		Enseignant enseignant = new Enseignant();
		
		if(user != null) {
			System.out.println("Utilisateur trouvé : " + user.getUsername());
			
			if(user.getPassword().equals(password)) {
				
				if(user.getValidation()==1) {
				
					if(user.getRole()==0) {
						 HttpSession sessionAuth = request.getSession();
				         sessionAuth.setAttribute("username", username);
						request.getRequestDispatcher("WEB-INF/views/index_admin.jsp").forward(request, response);
					}else if(user.getRole()==1) {
						
						 HttpSession sessionAuth = request.getSession();
						 enseignant = enseignantService.getEnseignantById(user.getIdUser());
						 if(enseignant != null) {
							 sessionAuth.setAttribute("enseignant", enseignant);
							 request.getRequestDispatcher("WEB-INF/views/index_enseignant.jsp").forward(request, response);
						 } else {
							 sessionAuth.setAttribute("user", user.getIdUser());
							 request.getRequestDispatcher("WEB-INF/views/enseignant-form.jsp").forward(request, response);
						 }
					}else if(user.getRole()==2) {
						 HttpSession sessionAuth = request.getSession();
						 etu = etuService.getEtudiantById(user.getIdUser());
						 if(etu != null) {
							 sessionAuth.setAttribute("etudiant", etu);
							 request.getRequestDispatcher("WEB-INF/views/index_etudiant.jsp").forward(request, response);
						 } else {
							 sessionAuth.setAttribute("user", user.getIdUser());
							 request.getRequestDispatcher("WEB-INF/views/etudiant-form.jsp").forward(request, response);
						 }
				        
					
					}				
				} else {
					request.setAttribute("username", username);
					request.setAttribute("error", "Inscription en attente de validation.");
					request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
				}
					
			} else {
				request.setAttribute("username", username);
				request.setAttribute("error","Mot de passe incorrect pour cet utilisateur.");
				request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
			}
			
			
			
		} else {
			
			System.out.println("Utilisateur inexistant");
			request.setAttribute("username", username);
			request.setAttribute("error","Nom d'utilisateur incorrect ou inexistant.");
			request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
			
		}
		
		
		
		
	}

}
