package it.uniroma3.gaia.hibernate.dao.impl;

import it.uniroma3.gaia.hibernate.dao.AtomTypeDao;
import it.uniroma3.gaia.hibernate.dao.GaiaDao;
import it.uniroma3.gaia.hibernate.model.AtomType;
import it.uniroma3.gaia.hibernate.model.AtomTypeEnum;

public class AtomTypeDaoImpl extends GaiaDao implements
		AtomTypeDao {

	@Override
	public AtomType getByTypeEnum(AtomTypeEnum en) {
		return (AtomType)getSession().get(AtomType.class, en.getId());
	}

}
