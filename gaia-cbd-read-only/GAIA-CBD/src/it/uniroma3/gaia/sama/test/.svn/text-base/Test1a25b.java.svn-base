package it.uniroma3.gaia.sama.test;

import it.uniroma3.gaia.sama.DataExchange;
import it.uniroma3.gaia.sama.SchemaExchange;
/**
 * Questa classe testa i passi 1a,2,5b dell'algoritmo . <br />
 * DEPT(<i>did</i>,cid,num,desc)-> EMP(<i>did</i>,num,desc)
 */


public class Test1a25b {
	
	// Il dataExchange (input)
	private static DataExchange dataExchange;
	
	// Lo schemaExchange (output)
	private static SchemaExchange schemaExchange;

	
	
	public static void main(String[] args) {
		Passo1a25b();
		
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
	
	public static void Passo1a25b () {
		dataExchange = new DataExchange("Dept");
		
		//Creazione del data exchange source
		// Aggiunge una relazione
		dataExchange.addRelationToSource("DEPT");
		
		// Aggiunge un attributo chiave a Dept
		dataExchange.addAttributeKeyToSource("DEPT", "did");
				
		// Aggiunge un attributo chiave a Dept
		dataExchange.addAttributeToSource("DEPT", "cid");
		
		// Aggiunge un attributo chiave a Dept
		dataExchange.addAttributeToSource("DEPT", "num");
				
		// Aggiunge un attributo chiave a Dept
		dataExchange.addAttributeToSource("DEPT", "desc");
								
		//Creazione del data exchange target
		// Aggiunge una relazione
		dataExchange.addRelationToTarget("EMP");
		
		// Aggiunge un attributo chiave a Emp
		dataExchange.addAttributeKeyToTarget("EMP", "did");
		
		// Aggiunge l'attributo a Emp
		dataExchange.addAttributeToTarget("EMP", "num");
		
		// Aggiunge l'attributo a Emp
		dataExchange.addAttributeToTarget("EMP", "desc");
		
		
		}
}

