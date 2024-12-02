package model;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class EtudiantHome {

    private static final Logger logger = Logger.getLogger(EtudiantHome.class.getName());
    private static final SessionFactory sessionFactory = buildSessionFactory();

    // Initialisation de la SessionFactory
    public static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            logger.log(Level.SEVERE, "La création de la SessionFactory a échoué.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Obtenir la SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Ajouter ou mettre à jour un Etudiant
    public void saveOrUpdate(Etudiant instance) {
        logger.log(Level.INFO, "Saving or updating Etudiant instance");
        Transaction transaction = null;

        // Ouverture de la session manuellement (pas dans un try-with-resources)
        Session session = null;
        try {
            session = sessionFactory.openSession(); // Ouvre une session
            transaction = session.beginTransaction(); // Démarre une transaction

            session.persist(instance); // Enregistrement ou mise à jour

            transaction.commit(); // Valide la transaction
            logger.log(Level.INFO, "Save or update successful");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Rollback seulement si la transaction est active
            }
            logger.log(Level.SEVERE, "Save or update failed", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close(); // Ferme la session dans tous les cas
            }
        }
    }




    // Supprimer un Etudiant
    public void delete(Etudiant instance) {
        logger.log(Level.INFO, "Deleting Etudiant instance");
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(instance);
            transaction.commit();
            logger.log(Level.INFO, "Delete successful");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Delete failed", e);
        }
    }

    // Trouver un Etudiant par ID
    public Etudiant findById(Integer id) {
    	
    	 Transaction transaction = null;
 	    Etudiant instance = null;
        logger.log(Level.INFO, "Finding Etudiant instance with id: " + id);
        try (Session session = sessionFactory.openSession()) {
        	transaction = session.beginTransaction();
             instance = session.get(Etudiant.class, id);
            if (instance != null) {
                logger.log(Level.INFO, "Etudiant instance found");
            } else {
                logger.log(Level.WARNING, "No Etudiant instance found with id: " + id);
            }
            transaction.commit();
         
            
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Find by ID failed", e);
            if (transaction != null) {
	            transaction.rollback();
	        }
        }
        return instance;
    }

    // Obtenir tous les Etudiants
    public List<Etudiant> findAll() {
    	
    		List<Etudiant> etudiants = null;

    	    try (Session session = sessionFactory.openSession()) {
    	        Transaction transaction = session.beginTransaction();
    	        etudiants = session.createQuery("FROM Etudiant", Etudiant.class).list();

    	        // Charger explicitement les relations lazy
    	        for (Etudiant etudiant : etudiants) {
    	            if (etudiant.getUtilisateur() != null) {
    	                Hibernate.initialize(etudiant.getUtilisateur());
    	            }
    	        }

    	        transaction.commit();
    	    } catch (RuntimeException e) {
    	        e.printStackTrace();
    	    }
    	    return etudiants;
    	
    }
}
