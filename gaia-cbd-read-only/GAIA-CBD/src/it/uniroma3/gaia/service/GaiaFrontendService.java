package it.uniroma3.gaia.service;

import it.uniroma3.gaia.sama.DataExchange;
import it.uniroma3.gaia.hibernate.model.SchemaExchange;

import java.io.File;
import java.util.List;

public interface GaiaFrontendService {
	
	public List<DataExchange> getDataExchangeMatchings(File file);
	
	public List<DataExchange> getFormulaMatchings(File file);
	
	public List<DataExchange> getSchemaExchangeMatchings(DataExchange dataExchangeToMatch);
	
	/** Metodo che dato un file contenente l'xml dello schema exchange lo inserisce nel repository */
	public Boolean insertSchemaExchange(File file);
}
