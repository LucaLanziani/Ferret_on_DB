package it.uniroma3.gaia.sama.test;

import it.uniroma3.gaia.sama.DataExchange;
import it.uniroma3.gaia.sama.SchemaExchange;
/**
 * Questa classe testa i passi 1a,2a,4,5a dell'algoritmo . <br />
 * DEPT(<i>did</i>,address)-> EMP(<i>emp</i>,name,did,address)
 */

public class Test1a2a45a {
	
	// Il dataExchange (input)
	private static DataExchange dataExchange;
	
	// Lo schemaExchange (output)
	private static SchemaExchange schemaExchange;

	
	
	public static void main(String[] args) {
		Passo1a2a45a();
		
		try {
			// Calcola lo schema exchange
			schemaExchange = dataExchange.createSchemaExchange("Dept");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		// Stampa la formula logica
		System.out.println(dataExchange.getLogicalFormula());
		
		// Stampa la descrizione del data exchange
		System.out.println("Tgd per " + dataExchange.getName());
		System.out.println(dataExchange.getTgd());
		// Stampa la descrizione dello schema exchange
		System.out.println("Tgd per " + schemaExchange.getName());
		System.out.println(schemaExchange.getTgd());
	}
	
	public static void Passo1a2a45a() {
		dataExchange = new DataExchange("Dept");
		
		//Creazione del data exchange source
		// Aggiunge una relazione
		dataExchange.addRelationToSource("DEPT");
		
		// Aggiunge un attributo chiave a Dept
		dataExchange.addAttributeKeyToSource("DEPT", "did");
		// Aggiunge l'attributo a Dept
		dataExchange.addAttributeToSource("DEPT", "address");
		
				
		//Creazione del data exchange target
		// Aggiunge una relazione
		dataExchange.addRelationToTarget("EMP");
		
		// Aggiunge un attributo chiave a Emp
		dataExchange.addAttributeKeyToTarget("EMP", "emp");
		
		// Aggiunge l'attributo a Emp
		dataExchange.addAttributeToTarget("EMP", "name");
		
		// Aggiunge l'attributo a Emp
		dataExchange.addAttributeToTarget("EMP", "did");
		
		// Aggiunge l'attributo a Emp
		dataExchange.addAttributeToTarget("EMP", "address");
		
	}
}
