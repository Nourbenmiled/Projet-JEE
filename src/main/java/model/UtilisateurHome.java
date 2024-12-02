package model;
// Generated 6 nov. 2024, 13:56:39 by Hibernate Tools 5.5.9.Final

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;


/**
 * Home object for domain model class Utilisateur.
 * @see model.Utilisateur
 * @author Hibernate Tools
 */
public class UtilisateurHome {

	private static final Logger logger = Logger.getLogger(UtilisateurHome.class.getName());

	private final SessionFactory sessionFactory = getSessionFactory();

	public SessionFactory getSessionFactory() {
		try {
			//return (SessionFactory) new InitialContext().lookup("SessionFactory");
			return new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Utilisateur transientInstance) {
		logger.log(Level.INFO, "persisting Utilisateur instance");
		Transaction transaction = null;
		
		try {
			Session session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			session.persist(transientInstance);
			logger.log(Level.INFO, "persist successful");
			transaction.commit();
		} catch (RuntimeException re) {
			if(transaction!=null) {
				transaction.rollback();
			}
			logger.log(Level.SEVERE, "persist failed", re);
			System.out.println("Erreur lors de l'enregistrement");
			throw re;
			
		}
	}

	public void attachDirty(Utilisateur instance) {
		logger.log(Level.INFO, "attaching dirty Utilisateur instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void attachClean(Utilisateur instance) {
		logger.log(Level.INFO, "attaching clean Utilisateur instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void delete(Utilisateur persistentInstance) {
		logger.log(Level.INFO, "deleting Utilisateur instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			throw re;
		}
	}

	public Utilisateur merge(Utilisateur detachedInstance) {
		logger.log(Level.INFO, "merging Utilisateur instance");
		try {
			Utilisateur result = (Utilisateur) sessionFactory.getCurrentSession().merge(detachedInstance);
			logger.log(Level.INFO, "merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "merge failed", re);
			throw re;
		}
	}

	public Utilisateur findById(java.lang.Integer id) {
	    Transaction transaction = null;
	    Utilisateur instance = null;
	    logger.log(Level.INFO, "getting Utilisateur instance with id: " + id);
	    
	    try (Session session = sessionFactory.openSession()) {
	        transaction = session.beginTransaction(); // Démarre la transaction
	        instance = (Utilisateur) session.get("model.Utilisateur", id); // Utilise la session ouverte
	        if (instance == null) {
	            logger.log(Level.INFO, "get successful, no instance found");
	        } else {
	            logger.log(Level.INFO, "get successful, instance found");
	        }
	        
	        transaction.commit(); // Validation de la transaction
	    } catch (RuntimeException re) {
	        logger.log(Level.SEVERE, "get failed", re);
	        if (transaction != null) {
	            transaction.rollback(); // Annulation en cas d'erreur
	        }
	        throw re; // Relance l'exception après rollback
	    }
	    
	    return instance;
	}


	public List findByExample(Utilisateur instance) {
		logger.log(Level.INFO, "finding Utilisateur instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("model.Utilisateur")
					.add(Example.create(instance)).list();
			logger.log(Level.INFO, "find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find by example failed", re);
			throw re;
		}
	}
	
	public Utilisateur findByUsername(String username) {
	    Transaction transaction = null;
	    Utilisateur utilisateur = null;
	    
	    try (Session session = sessionFactory.openSession()) {
	        // Démarre la transaction
	        transaction = session.beginTransaction();

	        // Exécute la requête
	        Query<Utilisateur> query = session.createQuery("from Utilisateur where username = :username", Utilisateur.class);
	        query.setParameter("username", username);
	        utilisateur = query.uniqueResult();

	        // Commit la transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        System.err.println("Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
	    }
	    
	    return utilisateur;
	}
	
	public List<Utilisateur> findNoValidatedUsers() {
	    Transaction transaction = null;
	    List<Utilisateur> utilisateurs = null;

	    try (Session session = sessionFactory.openSession()) {
	        // Démarre la transaction
	        transaction = session.beginTransaction();

	        // Exécute la requête
	        Query<Utilisateur> query = session.createQuery("from Utilisateur where validation = 0", Utilisateur.class);
	        utilisateurs = query.list();

	        // Commit la transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        System.err.println("Erreur lors de la récupération des utilisateurs non validés : " + e.getMessage());
	    }

	    return utilisateurs;
	}
	
	public boolean updateValidationStatus(int idUser) {
	    Transaction transaction = null;
	    boolean isUpdated = false;

	    try (Session session = sessionFactory.openSession()) {
	        // Démarre la transaction
	        transaction = session.beginTransaction();

	        // Récupère l'utilisateur ciblé
	        Utilisateur utilisateur = session.get(Utilisateur.class, idUser);

	        if (utilisateur != null && utilisateur.getValidation() == 0) {
	            // Modifie le statut de validation
	            utilisateur.setValidation(1);

	            // Sauvegarde les modifications
	            session.update(utilisateur);

	            // Marque comme mis à jour
	            isUpdated = true;
	        }

	        // Commit la transaction
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        System.err.println("Erreur lors de la mise à jour du statut de validation : " + e.getMessage());
	    }

	    return isUpdated;
	}



}
