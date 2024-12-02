package model;
// Generated 6 nov. 2024, 13:56:39 by Hibernate Tools 5.5.9.Final

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Enseignant.
 * @see model.Enseignant
 * @author Hibernate Tools
 */
public class EnseignantHome {

	private static final Logger logger = Logger.getLogger(EnseignantHome.class.getName());

	private final SessionFactory sessionFactory = getSessionFactory();

	public SessionFactory getSessionFactory() {
		try {
			return  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Enseignant instance) {
		Transaction transaction = null;
		Session session = null;
		logger.log(Level.INFO, "persisting Enseignant instance");
		try {
			 session = sessionFactory.openSession();
			 transaction = session.beginTransaction();
			 session.persist(instance);
			//sessionFactory.getCurrentSession().persist(transientInstance);
			 transaction.commit(); // Valide la transaction
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "persist failed", re);
			if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Rollback seulement si la transaction est active
            }
           
			throw re;
		}finally {
            if (session != null && session.isOpen()) {
                session.close(); // Ferme la session dans tous les cas
            }
        }
	}

	public void attachDirty(Enseignant instance) {
		logger.log(Level.INFO, "attaching dirty Enseignant instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void attachClean(Enseignant instance) {
		logger.log(Level.INFO, "attaching clean Enseignant instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void delete(Enseignant persistentInstance) {
		logger.log(Level.INFO, "deleting Enseignant instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			throw re;
		}
	}

	public Enseignant merge(Enseignant detachedInstance) {
		logger.log(Level.INFO, "merging Enseignant instance");
		try {
			Enseignant result = (Enseignant) sessionFactory.getCurrentSession().merge(detachedInstance);
			logger.log(Level.INFO, "merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "merge failed", re);
			throw re;
		}
	}

	public Enseignant findById(Integer id) {
	    Transaction transaction = null;
	    Enseignant enseignant = null;

	    try (Session session = sessionFactory.openSession()) {
	        transaction = session.beginTransaction();
	        enseignant = session.get(Enseignant.class, id);
	        transaction.commit();
	    } catch (RuntimeException e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        throw e;
	    }
	    return enseignant;
	}


	public List findByExample(Enseignant instance) {
		logger.log(Level.INFO, "finding Enseignant instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("model.Enseignant")
					.add(Example.create(instance)).list();
			logger.log(Level.INFO, "find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find by example failed", re);
			throw re;
		}
	}
	
	
	public List<Enseignant> findAll() {
		List<Enseignant> enseignants = null;

	    try (Session session = sessionFactory.openSession()) {
	        Transaction transaction = session.beginTransaction();
	        enseignants = session.createQuery("FROM Enseignant", Enseignant.class).list();

	        // Charger explicitement les relations lazy
	        for (Enseignant enseignant : enseignants) {
	            if (enseignant.getUtilisateur() != null) {
	                Hibernate.initialize(enseignant.getUtilisateur());
	            }
	        }

	        transaction.commit();
	    } catch (RuntimeException e) {
	        e.printStackTrace();
	    }
	    return enseignants;
	}

}
