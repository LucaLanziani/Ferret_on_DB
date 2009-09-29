package it.uniroma3.gaia.service;

import it.uniroma3.gaia.hibernate.model.SchemaExchange;
import it.uniroma3.gaia.sama.Exchange;

public interface SchemaExchangeSaverService {

	/** Metodo che dato un exchange in modello di dominio, lo trasforma in un 
	 * exchange in modello di memorizzazione e lo salva. */
	public void saveSchemaExchange(Exchange exchange, String description);
	
	/** Metodo che dato un exchange in modello di memorizzazione lo salva. */
	public Boolean saveSchemaExchange(SchemaExchange se);
	
}