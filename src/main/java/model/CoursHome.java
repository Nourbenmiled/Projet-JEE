package model;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * DAO pour la gestion des entités Cours.
 */
public class CoursHome {

    private static final Logger logger = Logger.getLogger(CoursHome.class.getName());
    private static final SessionFactory sessionFactory = buildSessionFactory();

    // Initialisation de la SessionFactory
    private static SessionFactory buildSessionFactory() {
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

    // Ajouter ou mettre à jour un Cours
    public void saveOrUpdate(Cours instance) {
        logger.log(Level.INFO, "Saving or updating Cours instance");
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(instance);
            transaction.commit();
            logger.log(Level.INFO, "Save or update successful");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Save or update failed", e);
        }
    }

    // Supprimer un Cours
    public void delete(Cours instance) {
        logger.log(Level.INFO, "Deleting Cours instance");
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(instance);
            transaction.commit();
            logger.log(Level.INFO, "Delete successful");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Delete failed", e);
        }
    }

    // Trouver un Cours par ID
    public Cours findById(Integer id) {
        logger.log(Level.INFO, "Finding Cours instance with id: " + id);
        try (Session session = sessionFactory.openSession()) {
        	//Transaction transaction = session.beginTransaction();
            Cours instance = session.get(Cours.class, id);
            
            if (instance != null) {
                logger.log(Level.INFO, "Cours instance found");
            } else {
                logger.log(Level.WARNING, "No Cours instance found with id: " + id);
            }
            return instance;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Find by ID failed", e);
            return null;
        }
    }

    // Obtenir tous les Cours
    public List<Cours> findAll() {
        Logger.getLogger(CoursHome.class.getName()).info("Fetching all Cours instances");
        try (Session session = sessionFactory.openSession()) {
            Query<Cours> query = session.createQuery("from Cours", Cours.class); // Récupération de tous les enregistrements
            List<Cours> results = query.list();
            Logger.getLogger(CoursHome.class.getName()).info("Number of Cours instances fetched: " + results.size());
            return results;
        } catch (RuntimeException re) {
            Logger.getLogger(CoursHome.class.getName()).severe("Find all failed");
            throw re;
        }
    }
}
