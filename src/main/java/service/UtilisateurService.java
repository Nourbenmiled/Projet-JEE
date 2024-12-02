package service;

import java.util.List;

import model.Utilisateur;
import model.UtilisateurHome;

public class UtilisateurService {

    private UtilisateurHome utilisateurHome;
    private Utilisateur user ;

    public UtilisateurService() {
        utilisateurHome = new UtilisateurHome(); // Initialise l'instance de UtilisateurHome
        user = new Utilisateur();
    }

    public Utilisateur getUtilisateurByUsername(String username) {
        try {
            return utilisateurHome.findByUsername(username);
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
            return null;
        }
    }

    public void addUtilisateur(Utilisateur user) {
    	
    	try {
    		utilisateurHome.persist(user);
    		System.out.println("Utilisateur creer avec succees! ");
    	}catch(Exception e) {
    		System.err.println("Erreur lors de la création de l'utilisateur: "+ e.getMessage());
    	}
    }
    
   public List<Utilisateur> utilisateurNonValide(){
	   
	   return utilisateurHome.findNoValidatedUsers();
	      
   }
   
   public boolean validerInscription(int id) {
	   return utilisateurHome.updateValidationStatus(id);
   }
   
   public Utilisateur getUserById(int id) {
	   return utilisateurHome.findById(id);
   }

}