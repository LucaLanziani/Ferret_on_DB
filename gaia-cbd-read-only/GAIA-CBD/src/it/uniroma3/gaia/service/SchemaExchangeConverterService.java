package it.uniroma3.gaia.service;

import it.uniroma3.gaia.hibernate.model.Atom;
import it.uniroma3.gaia.hibernate.model.SchemaExchange;
import it.uniroma3.gaia.sama.DataExchange;
import it.uniroma3.gaia.sama.Exchange;
import it.uniroma3.gaia.sama.exception.RelationNotFoundException;

import java.util.List;

public interface SchemaExchangeConverterService {

	/* Dato uno schema exchange preso dal repository lo ritrasforma in uno
	 * schema exchange secondo il modello canonico */
	public Exchange convertFromRepository(SchemaExchange schemaExchange)
			throws RelationNotFoundException;

	public SchemaExchange convertToRepository(Exchange exchange,
			String description);

	public List<Atom> convertToRepositoryForCompareHalf(it.uniroma3.gaia.sama.SchemaExchange se);
	
	public DataExchange getDataExchange(SchemaExchange se);
	
	public DataExchange getFormula(SchemaExchange se);

}