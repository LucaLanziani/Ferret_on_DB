package it.uniroma3.gaia.hibernate.dao.impl;

import it.uniroma3.gaia.hibernate.HibernateHelper;
import it.uniroma3.gaia.hibernate.HibernateHelperException;
import it.uniroma3.gaia.hibernate.dao.GaiaDao;
import it.uniroma3.gaia.hibernate.dao.SchemaExchangeTypeDao;
import it.uniroma3.gaia.hibernate.model.SchemaExchangeType;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class SchemaExchangeTypeDaoImpl extends GaiaDao implements
		SchemaExchangeTypeDao {

	@Override
	public SchemaExchangeType getByRelNumSnDx(Integer snRelNum, Integer dxRelNum) {
		Criteria cr = getSession().createCriteria(SchemaExchangeType.class)
			.add(Restrictions.eq("snRelNum", snRelNum))
			.add(Restrictions.eq("dxRelNum", dxRelNum));
		SchemaExchangeType result = (SchemaExchangeType)cr.uniqueResult();
		if(result==null){
			result = new SchemaExchangeType();
			result.setSnRelNum(snRelNum);
			result.setDxRelNum(dxRelNum);
//			try {
//				HibernateHelper.beginTransaction();
			persist(result);
//				HibernateHelper.endTransaction(true);
//			} catch (HibernateHelperException e) {
//				HibernateHelper.endTransaction(false);
//				e.printStackTrace();
//			}
			
			
		}
		return result;
	}

	@Override
	public void persist(SchemaExchangeType exchangeType) {
		saveOrUpdate(exchangeType);
	}

}
