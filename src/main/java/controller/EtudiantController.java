package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Etudiant;
import model.EtudiantHome;
import model.Inscription;
import model.InscriptionHome;
import model.Utilisateur;
//import model.UtilisateurHome;
import service.UtilisateurService;

import java.io.IOException;
import java.util.Date;
import java.util.List;



public class EtudiantController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final EtudiantHome etudiantHome;
    private final UtilisateurService userService;
    private final InscriptionHome inscriptionHome;

    public EtudiantController() {
        super();
        etudiantHome = new EtudiantHome();
        userService = new 	UtilisateurService();
        inscriptionHome = new InscriptionHome();
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";
 
        try {
            switch (action) {
                case "delete":
                    deleteEtudiant(request, response);
                    break;
                case "inscriptionEtudiant" :
                	listInscriptionEtu(request, response);
                	break;
                case "detail" :
                	detailEtudiant(request, response);
                	break;
                default:
                    listEtudiants(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("EtudiantController?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String contact = request.getParameter("contact");
            String moyenneParam = request.getParameter("moyenne");

            // Logs pour vérifier les données reçues
            System.out.println("Nom: " + nom + ", Prénom: " + prenom + ", Contact: " + contact + ", Moyenne: " + moyenneParam);

            if (nom == null || prenom == null || contact == null || moyenneParam == null) {
                throw new IllegalArgumentException("Tous les champs sont obligatoires.");
            }

            float moyenne = Float.parseFloat(moyenneParam);
            int id = Integer.parseInt(idParam);
            
           Utilisateur user = userService.getUserById(id);
           Etudiant etudiant = new Etudiant(user, nom, prenom, new Date(), contact, moyenne);
           System.out.println(etudiant.getNom());
           etudiantHome.saveOrUpdate(etudiant);
           
           request.getRequestDispatcher("WEB-INF/views/index_etudiant.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors du traitement : " + e.getMessage());
            
        }
    }


    
    private void listEtudiants(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Etudiant> etudiantList = etudiantHome.findAll();
        request.setAttribute("etudiantList", etudiantList);
        request.getRequestDispatcher("WEB-INF/views/etudiant-list.jsp").forward(request, response);
    }
 
    private void deleteEtudiant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Etudiant etudiant = etudiantHome.findById(id);
            if (etudiant != null) {
                etudiantHome.delete(etudiant);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        response.sendRedirect("EtudiantController?action=list");
    }
    
    private void listInscriptionEtu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String idCours = request.getParameter("id");
    	List<Etudiant> etudiantList = etudiantHome.findAll();
    	request.setAttribute("idCours", idCours);
        request.setAttribute("etudiantList", etudiantList);
        request.getRequestDispatcher("WEB-INF/views/inscription-etudiant.jsp").forward(request, response);
    	
    }
    
    
    private void detailEtudiant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            System.out.println("Etudiant ID: " + id); // Log pour vérifier l'ID
            Etudiant selection = etudiantHome.findById(id);
            
            if (selection == null) {
                System.out.println("Etudiant non trouvé pour ID: " + id); // Log si l'étudiant n'est pas trouvé
                response.sendRedirect("EtudiantController?action=list");
                return;
            }
 
            List<Inscription> inscription = inscriptionHome.findByEtudiant(selection);
            request.setAttribute("etudiant", selection);
            request.setAttribute("inscriptionList", inscription);
 
            request.getRequestDispatcher("WEB-INF/views/etudiantDetail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("EtudiantController?action=list");
        }
    }
    
   /* private void generatePdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Etudiant etudiant = etudiantHome.findById(id);
        List<Inscription> inscriptions = inscriptionHome.findByEtudiant(etudiant);
 
        String dest = "etudiant_" + id + ".pdf";
        PdfGenerator pdfGenerator = new PdfGenerator();
        pdfGenerator.createPdf(dest, etudiant, inscriptions);
 
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + dest);
        java.nio.file.Files.copy(java.nio.file.Paths.get(dest), response.getOutputStream());
    }*/
    
 
    
}

