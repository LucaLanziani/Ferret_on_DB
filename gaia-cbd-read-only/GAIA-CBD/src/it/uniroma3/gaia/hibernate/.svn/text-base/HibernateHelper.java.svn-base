package it.uniroma3.gaia.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class HibernateHelper {
	private static SessionFactory sessionFactory = null;
	private static Logger log = Logger.getLogger(HibernateHelper.class);
	private static ThreadLocal<Session> sharedSession;
	private static ThreadLocal<Transaction> transaction;
	
	static {
		try {
			sessionFactory = new Configuration()
				.configure("/it/uniroma3/gaia/hibernate/hibernate.cfg.xml")
				.buildSessionFactory();
			sharedSession = new ThreadLocal<Session>();
			transaction = new ThreadLocal<Transaction>();
		} catch (Throwable ex) {
			log.error("SessionFactory creation failed.", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;

	}
	
	public static Session getSession() {
		Session session = (Session)sharedSession.get();
		if (session == null) {
			session = sessionFactory.openSession();
			log.debug("new session opened");
			sharedSession.set(session);
		}
		return session;
	}
	
	public static void closeSession() {
		Session shSession = (Session)sharedSession.get();
		if (shSession != null ) {
			shSession.flush();
			shSession.close();
			sharedSession.set(null);
			log.debug("session closed");
		}
	}
	
	public static void beginTransaction() throws HibernateHelperException{
//		Session shSession = (Session)sharedSession.get();
		Session shSession = (Session)getSession();
		Transaction tr = (Transaction)transaction.get();
//		if (shSession == null) {
//			log.error("Session is null.");
//			throw new HibernateHelperException("Session is null.");
//		} else 
		if (!shSession.isOpen()) {
			log.error("SSession is closed.");
			throw new HibernateHelperException("Session is closed.");
		}
		if(tr == null){
			tr = shSession.beginTransaction();
			transaction.set(tr);
			log.debug("new transaction beginned");
		}
	}
	
	public static void endTransaction(boolean commit) {
		Transaction tr = (Transaction)transaction.get();
		if (tr != null) {
			if (commit) {
				tr.commit();
//				getSession().flush();
				log.debug("transaction committed");
			} else {
				tr.rollback();
				log.debug("transaction rolled back");
			}
			transaction.set(null);
		}
	}
	
//	public static Session getSession() throws HibernateHelperException{
//		Session shSession = (Session)sharedSession.get();
//		if (shSession == null) {
//			log.error("Session is null.");
//			throw new HibernateHelperException("Session is null.");
//		}
//		return shSession;
//	}
}
	

	
//	public static Session getSession() {
//		Session s = null;
//		if (ManagedSessionContext.hasBind(sessionFactory)) {
//			s = sessionFactory.getCurrentSession();
//			log.info("get current hibernate session");
//		} else {
//			s = sessionFactory.openSession();
//			log.info("binding hibernate session factory");
//			ManagedSessionContext.bind(s);
//			log.info("new hibernate session");
//			s.setFlushMode(FlushMode.MANUAL);
//			s.beginTransaction();
//		}
//		return s;
//	}
//	
//
//	public static void closeSession() throws Throwable {
//		if (ManagedSessionContext.hasBind(sessionFactory)) {
//			Session currentSession = sessionFactory.getCurrentSession();
//			if (currentSession != null && currentSession.isOpen()) {
//				try {
//					Transaction tx = currentSession.getTransaction();
//					if(tx.isActive()){
//						flush();
//						commit();
//					}
//				} finally {
//					log.info("hibernate close session");
//					currentSession.close();
//				}
//			}
//		}
//	}
//	
//	public static void commit() throws Throwable {
//		Session currentSession = sessionFactory.getCurrentSession();
//		if (currentSession != null && currentSession.isOpen()) {
//			try {
//				currentSession.getTransaction().commit();
//				log.info("hibernate commit");
//			} catch (Throwable e) {
//				rollback();
//				throw e;
//			}
//		}
//	}
//
//	public static void rollback() throws Throwable {
//		Session currentSession = sessionFactory.getCurrentSession();
//		if (currentSession != null && currentSession.isOpen()) {
//			try {
//				currentSession.getTransaction().rollback();
//				log.info("hibernate rollback");
//			} catch (Throwable e) {
//				//throw e;
//			}
//		}
//	}
//	public static void flush() throws Throwable {
//		if (ManagedSessionContext.hasBind(sessionFactory)) {
//			Session currentSession = sessionFactory.getCurrentSession();
//			if (currentSession != null && currentSession.isOpen()) {
//				Transaction tx = currentSession.getTransaction();
//				if(tx.isActive()){
//					try {
//						currentSession.flush();
//						log.info("hibernate flush");
//						commit();
//					} catch (Throwable e) {
//						rollback();
//						throw e;
//					}
//				}
//			}
//		}
//	}
//}
