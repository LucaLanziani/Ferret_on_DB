package it.uniroma3.gaia.hibernate.dao;

import it.uniroma3.gaia.hibernate.model.SchemaExchange;

import java.util.List;

public interface SchemaExchangeDao {

	public void save(SchemaExchange se);
	public List<SchemaExchange> getListBySourceRelNumber(Integer num);
	public List<SchemaExchange> getListBySourceTargetRelNumber(Integer numSource, Integer numTarget);
	public List<SchemaExchange> getListByExample(SchemaExchange example);
	
}
