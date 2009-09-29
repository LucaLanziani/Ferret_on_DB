package it.uniroma3.gaia.sama.test;

import it.uniroma3.gaia.sama.DataExchange;
import it.uniroma3.gaia.sama.SchemaExchange;
/**
 * Questa classe testa i passi 1a,2a,3,4,5,6 dell'algoritmo . <br />
 * DEPT(<i>cod</i>,date)-> EMP(<i>id</i>,cod,date,name,month(date),day(date))
 */

public class Test1a2a3456 {
	
	// Il dataExchange (input)
	private static DataExchange dataExchange;
	
	// Lo schemaExchange (output)
	private static SchemaExchange schemaExchange;

	
	
	public static void main(String[] args) {
		Passo1a2a3456();
		
		try {
			// Calcola lo schema exchange
			schemaExchange = dataExchange.createSchemaExchange("Course");
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		// Stampa la formula logica
		System.out.println(dataExchange.getLogicalFormula());
		
		// Stampa la descrizione del data exchange
		System.out.println("Tgd per " + dataExchange.getName());
		System.out.println(dataExchange.getTgd());
		// Stampa la descrizione del schema exchange
		System.out.println("Tgd per " + schemaExchange.getName());
		System.out.println(schemaExchange.getTgd());
	}
	
	public static void Passo1a2a3456() {
		dataExchange = new DataExchange("Dept");
		
		//Creazione del data exchange source
		// Aggiunge una relazione
		dataExchange.addRelationToSource("DEPT");	
		
		// Aggiunge un attributo chiave a DEPT
		dataExchange.addAttributeKeyToSource("DEPT", "cod");
		
		// Aggiunge l'attributo a DEPT
		dataExchange.addAttributeToSource("DEPT", "date");

		//Creazione del data exchange target
		// Aggiunge una relazione
		dataExchange.addRelationToTarget("EMP");
		
		// Aggiunge un attributo chiave a EMP
		dataExchange.addAttributeKeyToTarget("EMP", "id");
		
		// Aggiunge un attributo a EMP
		dataExchange.addAttributeToTarget("EMP", "cod");
		
		// Aggiunge l'attributo a EMP
		dataExchange.addFunctionToTarget("EMP", "month", "date");
		
		// Aggiunge l'attributo a EMP
		dataExchange.addAttributeToTarget("EMP", "name");
		
		// Aggiunge l'attributo a EMP
		dataExchange.addFunctionToTarget("EMP", "day", "date");
		
		// Aggiunge l'attributo a EMP
		dataExchange.addAttributeToTarget("EMP", "date");
		
	}
}
