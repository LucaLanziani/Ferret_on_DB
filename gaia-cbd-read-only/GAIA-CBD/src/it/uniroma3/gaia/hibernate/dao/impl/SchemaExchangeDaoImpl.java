package it.uniroma3.gaia.hibernate.dao.impl;

import it.uniroma3.gaia.hibernate.dao.GaiaDao;
import it.uniroma3.gaia.hibernate.dao.SchemaExchangeDao;
import it.uniroma3.gaia.hibernate.model.Atom;
import it.uniroma3.gaia.hibernate.model.SchemaExchange;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

@SuppressWarnings("unchecked")
public class SchemaExchangeDaoImpl extends GaiaDao implements SchemaExchangeDao{
	
	
	public void save(SchemaExchange se) {
		for (Atom atom : se.getAtoms()) {
			atom.setId(null);
		}
		saveOrUpdate(se);
	}
	
	public List<SchemaExchange> getListBySourceRelNumber(Integer num){
		Criteria cr = getSession().createCriteria(SchemaExchange.class, "se");
		cr.createAlias("se.schemaExchangeType","type")
			.add(Restrictions.eq("type.snRelNum", num));
		return (List<SchemaExchange>)cr.list();
	}
	
	public List<SchemaExchange> getListBySourceTargetRelNumber(Integer numSource, Integer numTarget){
		Criteria cr = getSession().createCriteria(SchemaExchange.class, "se");
		cr.createAlias("se.schemaExchangeType","type")
			.add(Restrictions.eq("type.snRelNum", numSource))
			.add(Restrictions.eq("type.dxRelNum", numTarget));
		return (List<SchemaExchange>)cr.list();
	}
	
	public List<SchemaExchange> getListByExample(SchemaExchange example){
		return (List<SchemaExchange>)findByExample(example,(new String[] {"id"}));
	}

}
