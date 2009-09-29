package it.uniroma3.gaia.service;

import java.io.File;

import it.uniroma3.gaia.sama.SchemaExchange;
import it.uniroma3.gaia.sama.exception.RelationNotFoundException;

public interface SchemaExchangeParserService {

	public SchemaExchange createSchemaExchange(String fileName) throws RelationNotFoundException;
	
	public it.uniroma3.gaia.hibernate.model.SchemaExchange createSchemaExchangeRepo(String fileName);
	
	public it.uniroma3.gaia.hibernate.model.SchemaExchange createSchemaExchangeRepo(File file);
	
	public SchemaExchange createSchemaExchange(File file) throws RelationNotFoundException;
	
}
