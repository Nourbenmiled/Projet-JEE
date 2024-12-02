package model;
// Generated 6 nov. 2024, 13:56:39 by Hibernate Tools 5.5.9.Final

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Resultat.
 * @see model.Resultat
 * @author Hibernate Tools
 */
public class ResultatHome {

	private static final Logger logger = Logger.getLogger(ResultatHome.class.getName());

	private static final SessionFactory sessionFactory = buildSessionFactory();

	public static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}
	
	
	// Obtenir la SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

/*	public void persist(Resultat transientInstance) {
		logger.log(Level.INFO, "persisting Resultat instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "persist failed", re);
			throw re;
		}
	}
*/
	
	
	
	
	  public void saveOrUpdate(Resultat instance) {
	        logger.log(Level.INFO, "Saving or updating Resultat instance");
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
	
	
	

	public void attachDirty(Resultat instance) {
		logger.log(Level.INFO, "attaching dirty Resultat instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void attachClean(Resultat instance) {
		logger.log(Level.INFO, "attaching clean Resultat instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void delete(Resultat persistentInstance) {
		logger.log(Level.INFO, "deleting Resultat instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			throw re;
		}
	}

	public Resultat merge(Resultat detachedInstance) {
		logger.log(Level.INFO, "merging Resultat instance");
		try {
			Resultat result = (Resultat) sessionFactory.getCurrentSession().merge(detachedInstance);
			logger.log(Level.INFO, "merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "merge failed", re);
			throw re;
		}
	}

	public Resultat findById(java.lang.Integer id) {
		logger.log(Level.INFO, "getting Resultat instance with id: " + id);
		try {
			Resultat instance = (Resultat) sessionFactory.getCurrentSession().get("model.Resultat", id);
			if (instance == null) {
				logger.log(Level.INFO, "get successful, no instance found");
			} else {
				logger.log(Level.INFO, "get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "get failed", re);
			throw re;
		}
	}

	public List findByExample(Resultat instance) {
		logger.log(Level.INFO, "finding Resultat instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("model.Resultat")
					.add(Example.create(instance)).list();
			logger.log(Level.INFO, "find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find by example failed", re);
			throw re;
		}
	}
}
