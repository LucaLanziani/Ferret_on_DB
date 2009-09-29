package it.uniroma3.gaia.hibernate.dao;

import it.uniroma3.gaia.hibernate.HibernateHelper;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Example;

public abstract class GaiaDao {
	private static final Logger log = Logger.getLogger(GaiaDao.class);

	public Session getSession() {
		return HibernateHelper.getSession();
	}
	
	protected void saveOrUpdate(Object o){
		log.debug("persisting " + o.getClass().getName() + " instance");
		try {
			getSession().persist(o);
			log.debug("persist successful");
			getSession().flush();
//			HibernateHelper.closeSession();
		}catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	protected Object getById(Class c, Integer id){
		Object o =  getSession().get(c, id);
		log.debug("Retrieved an instance of " + c.getName() + "with id " + id.toString());
		return o;
	}
	
	@SuppressWarnings("unchecked")
	protected List findByExample(Object exampleInstance, String[] excludeProperty) {
        log.debug("***Begin: findByExample***");
		Criteria crit = getSession().createCriteria(exampleInstance.getClass());
        Example example =  Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        return crit.list();
    }

}
