package model;
// Generated 6 nov. 2024, 13:56:39 by Hibernate Tools 5.5.9.Final

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.naming.InitialContext;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class InscriptionEnseignant.
 * @see model.InscriptionEnseignant
 * @author Hibernate Tools
 */
public class InscriptionEnseignantHome {

	private static final Logger logger = Logger.getLogger(InscriptionEnseignantHome.class.getName());

	private final SessionFactory sessionFactory = getSessionFactory();

	public SessionFactory getSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(InscriptionEnseignant transientInstance) {
		logger.log(Level.INFO, "persisting InscriptionEnseignant instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "persist failed", re);
			throw re;
		}
	}

	public void attachDirty(InscriptionEnseignant instance) {
		logger.log(Level.INFO, "attaching dirty InscriptionEnseignant instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void attachClean(InscriptionEnseignant instance) {
		logger.log(Level.INFO, "attaching clean InscriptionEnseignant instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void delete(InscriptionEnseignant persistentInstance) {
		logger.log(Level.INFO, "deleting InscriptionEnseignant instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			throw re;
		}
	}

	public InscriptionEnseignant merge(InscriptionEnseignant detachedInstance) {
		logger.log(Level.INFO, "merging InscriptionEnseignant instance");
		try {
			InscriptionEnseignant result = (InscriptionEnseignant) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			logger.log(Level.INFO, "merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "merge failed", re);
			throw re;
		}
	}

	public InscriptionEnseignant findById(Integer id) {
	    Transaction transaction = null;
	    InscriptionEnseignant inscriptionEnseignant = null;

	    try (Session session = sessionFactory.openSession()) {
	        transaction = session.beginTransaction(); // Démarrer une transaction

	        // Requête HQL avec fetch join pour récupérer l'inscription et le cours associé
	        String hql = "SELECT ie FROM InscriptionEnseignant ie " +
	                     "JOIN FETCH ie.cours " +
	                     "WHERE ie.id = :id";
	        inscriptionEnseignant = session.createQuery(hql, InscriptionEnseignant.class)
	                                       .setParameter("id", id)
	                                       .uniqueResult();

	        transaction.commit(); // Valider la transaction
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback(); // Annuler en cas d'erreur
	        }
	        e.printStackTrace();
	    }
	    return inscriptionEnseignant;
	}



	public List findByExample(InscriptionEnseignant instance) {
		logger.log(Level.INFO, "finding InscriptionEnseignant instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("model.InscriptionEnseignant")
					.add(Example.create(instance)).list();
			logger.log(Level.INFO, "find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find by example failed", re);
			throw re;
		}
	}
	
	 public void saveOrUpdate(InscriptionEnseignant instance) {
	        logger.log(Level.INFO, "Saving or updating InscriptionEnseignant instance");
	        try (Session session = sessionFactory.openSession()) {
	            Transaction transaction = session.beginTransaction();
	            session.saveOrUpdate(instance);
	            transaction.commit();
	            logger.log(Level.INFO, "Save or update successful");
	        } catch (Exception e) {
	            logger.log(Level.SEVERE, "Save or update failed", e);
	        }
	    }
	 
	 
	 public List<InscriptionEnseignant> findByEnseignant(Enseignant enseignant) {
		    logger.log(Level.INFO, "Finding Inscription instances by Enseignant");
		    try (Session session = sessionFactory.openSession()) {
		        List<InscriptionEnseignant> results = session.createQuery(
		                "from InscriptionEnseignant i join fetch i.cours where i.enseignant = :enseignant", InscriptionEnseignant.class)
		                .setParameter("enseignant", enseignant)
		                .list();
		        logger.log(Level.INFO, "Find by Enseignant successful, result size: " + results.size());
		        return results;
		    } catch (RuntimeException re) {
		        logger.log(Level.SEVERE, "Find by Enseignant failed", re);
		        throw re;
		    }
		}
}
