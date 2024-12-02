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
 * Home object for domain model class Inscription.
 * @see model.Inscription
 * @author Hibernate Tools
 */
public class InscriptionHome {

	private static final Logger logger = Logger.getLogger(InscriptionHome.class.getName());

	private final SessionFactory sessionFactory = getSessionFactory();

	public SessionFactory getSessionFactory() {
		try {
			return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Inscription transientInstance) {
		logger.log(Level.INFO, "persisting Inscription instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Inscription instance) {
		logger.log(Level.INFO, "attaching dirty Inscription instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void attachClean(Inscription instance) {
		logger.log(Level.INFO, "attaching clean Inscription instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void delete(Inscription persistentInstance) {
		logger.log(Level.INFO, "deleting Inscription instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			throw re;
		}
	}

	public Inscription merge(Inscription detachedInstance) {
		logger.log(Level.INFO, "merging Inscription instance");
		try {
			Inscription result = (Inscription) sessionFactory.getCurrentSession().merge(detachedInstance);
			logger.log(Level.INFO, "merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "merge failed", re);
			throw re;
		}
	}

	public Inscription findById(java.lang.Integer id) {
		logger.log(Level.INFO, "getting Inscription instance with id: " + id);
		try {
			Inscription instance = (Inscription) sessionFactory.getCurrentSession().get("model.Inscription", id);
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

	public List findByExample(Inscription instance) {
		logger.log(Level.INFO, "finding Inscription instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("model.Inscription")
					.add(Example.create(instance)).list();
			logger.log(Level.INFO, "find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find by example failed", re);
			throw re;
		}
	}
	
	 public void saveOrUpdate(Inscription instance) {
	        logger.log(Level.INFO, "Saving or updating Inscription instance");
	        try (Session session = sessionFactory.openSession()) {
	            Transaction transaction = session.beginTransaction();
	            session.saveOrUpdate(instance);
	            transaction.commit();
	            logger.log(Level.INFO, "Save or update successful");
	        } catch (Exception e) {
	            logger.log(Level.SEVERE, "Save or update failed", e);
	        }
	    }
	 
	 
	 public List<Inscription> findByEtudiant(Etudiant etudiant) {
	        logger.log(Level.INFO, "Finding Inscription instances by Etudiant");
	        try (Session session = sessionFactory.openSession()) {
	            List<Inscription> results = session.createQuery(
	                    "select distinct i from Inscription i " +
	                    "join fetch i.cours c " +
	                    "left join fetch c.resultats r " +
	                    "where i.etudiant = :etudiant", Inscription.class)
	                .setParameter("etudiant", etudiant)
	                .list();
	            logger.log(Level.INFO, "Find by Etudiant successful, result size: " + results.size());
	            return results;
	        } catch (RuntimeException re) {
	            logger.log(Level.SEVERE, "Find by Etudiant failed", re);
	            throw re;
	        }
	    }


		public List<Inscription> findByCours(Cours cours) {
			logger.log(Level.INFO, "Finding Inscription instances Cours");
		    try (Session session = sessionFactory.openSession()) {
		        List<Inscription> results = session.createQuery(
		                "from Inscription i join fetch i.etudiant where i.cours = :cours", Inscription.class)
		                .setParameter("cours", cours)
		                .list();
		        logger.log(Level.INFO, "Find by Cours successful, result size: " + results.size());
		        return results;
		    } catch (RuntimeException re) {
		        logger.log(Level.SEVERE, "Find by Cours failed", re);
		        throw re;
		    }
		}

	 
	 
}
