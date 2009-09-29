package it.uniroma3.gaia.sama;

import java.util.HashMap;
import java.util.Map;



/**
 * Questa classe rappresenta uno Schema Exchange. <br/>
 * Estende la classe Exchange.
 * @see Exchange
 *
 */
public class SchemaExchange extends Exchange {
	
	// Una mappa in cui teniamo le corrispondenze fra 
	// nomi simbolici (n'r) e nome reali delle relazioni (course)
	private Map<String, String> corrispondenzaNomi = new HashMap<String, String>();
	
	/**
	 * Crea uno SchemaExchange
	 * @param name il nome dello schema exchange
	 */
	public SchemaExchange(String name) {
		super(name);
		
	}
	
	/**
	 * Questo metodo aggiunge a questo schemaExchange un attributo derivato
	 * @param relationName il nome della relazione che contiene l'attributo 
	 * derivato
	 * @param attributeName il nome dell'attributo derivato
	 * @param functionName il nome della funzione applicata all'attributo 
	 * del data exchange
	 * @param referredName il nome dell'attributo del data exchange a cui 
	 * viene applicata la funzione
	 */
	public void addDerivatedAttribute(String relationName, String attributeName, String functionName, String referredName) {
			this.getRelationOfTarget().get(relationName).addDerivatedAttribute(attributeName, functionName, referredName);
			
		
	}

	public Map<String, String> getCorrispondenzaNomi() {
		return corrispondenzaNomi;
	}
	
	public void addCorrispondenzaNome(String realName, String simbolicName){
		corrispondenzaNomi.put(simbolicName, realName);
	}

}