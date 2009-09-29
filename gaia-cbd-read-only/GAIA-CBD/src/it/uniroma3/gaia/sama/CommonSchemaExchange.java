package it.uniroma3.gaia.sama;

import java.io.File;

import it.uniroma3.gaia.sama.exception.AttributeNotFoundException;
import it.uniroma3.gaia.sama.exception.RelationNotFoundException;

/**
 * Questa classe si occupa di trovare uno schema exchange comune a
 * due data exchange.
 *
 */
public class CommonSchemaExchange {
	// Il primo data exchange
	private DataExchange dataExchange1;
	// Il secondo data exchange
	private DataExchange dataExchange2;
	
	/**
	 * Crea un nuovo oggetto CommonSchemaExchange
	 * @param dataExchange1 il path del file XML contenente la descrizione 
	 * del primo data exchange
	 * @param dataExchange2 il path del file XML contenente la descrizione 
	 * del secondo data exchange
	 * @throws RelationNotFoundException 
	 */
	public CommonSchemaExchange(String dataExchange1, String dataExchange2) throws RelationNotFoundException {
		// Il parser per il caricamento dei due data exchange
		// Lo prepara alla lettura del primo data exchange.
		Parser parser = new Parser(dataExchange1);

		// Legge e decodifica il primo data exchange
		this.dataExchange1 = parser.createDataExchange();
		
		// Prepara il parser al caricamento del secondo data exchange
		parser.setFile(new File(dataExchange2));
		
		// Legge e decodifica il secondo data exchange
		this.dataExchange2 = parser.createDataExchange();
		
	}
	
	/**
	 * Questo metodo calcola uno schema exchange comune a due data exchange
	 * @param schemaName il nome da assegnare allo schema comune
	 * @return uno schema exchange comune tra i due data exchange
	 * @throws AttributeNotFoundException 
	 * @throws RelationNotFoundException 
	 */
	public SchemaExchange getCommonSchemaExchange(String schemaName) throws RelationNotFoundException, AttributeNotFoundException {
		// I due schema exchange relativi ai due data exchange passati in input
		SchemaExchange se1, se2;
		
		// Inizializzazione
		// Passo 1. Calcola gli schema exchange relativi ai due data exchange
		se1 = this.getFirstDataExchange().createSchemaExchange(this.getFirstDataExchange().getName());
		se2 = this.getSecondDataExchange().createSchemaExchange(this.getSecondDataExchange().getName());		
		
		// Algoritmo di calcolo dello schema comune
		// Verifica se i due schema exchange sono uguali
		boolean seUguali;
		try {
			seUguali = se1.equals(se2);
		} catch(Exception e) {
			seUguali = false;
		}
		
		System.out.println(seUguali);
		
		
		return null;
	}
	
	/**
	 * Questo metodo restituisce il primo data exchange
	 * @return il primo data exchange
	 */
	public DataExchange getFirstDataExchange() {
		return this.dataExchange1;
		
	}
	
	/**
	 * Questo metodo restituisce il secondo data exchange
	 * @return il secondo data exchange
	 */
	public DataExchange getSecondDataExchange() {
		return this.dataExchange2;
		
	}
		
}
