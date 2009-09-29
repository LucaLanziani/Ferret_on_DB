package it.uniroma3.gaia.sama.test;

import it.uniroma3.gaia.sama.DataExchange;
import it.uniroma3.gaia.sama.SchemaExchange;
/**
 * Questa classe testa i passi 1,2,3,6 dell'algoritmo . <br />
 * COURSE(<i>cod</i>,date,exameDate)-> COURSE(<i>cod</i>,name,month(date),day(date),month(exameDate))
 */
public class Test1236 {
	
	// Il dataExchange (input)
	private static DataExchange dataExchange;
	
	// Lo schemaExchange (output)
	private static SchemaExchange schemaExchange;

	
	
	public static void main(String[] args) {
		Passo1236();

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
		// Stampa la descrizione dello schema exchange
		System.out.println("Tgd per " + schemaExchange.getName());
		System.out.println(schemaExchange.getTgd());
	}
	
	public static void Passo1236() {
		dataExchange = new DataExchange("Course");
		
		//Creazione del data exchange source
		// Aggiunge una relazione
		dataExchange.addRelationToSource("COURSE");	
		
		// Aggiunge un attributo chiave a COURSE
		dataExchange.addAttributeKeyToSource("COURSE", "cod");
		
		// Aggiunge l'attributo a COURSE
		dataExchange.addAttributeToSource("COURSE", "date");
		
		// Aggiunge l'attributo a COURSE
		dataExchange.addAttributeToSource("COURSE", "exameDate");
		
		//Creazione del data exchange target
		// Aggiunge una relazione
		dataExchange.addRelationToTarget("COURSE");
		
		// Aggiunge un attributo chiave a COURSE
		dataExchange.addAttributeKeyToTarget("COURSE", "cod");
		
		// Aggiunge l'attributo a COURSE
		dataExchange.addFunctionToTarget("COURSE", "month", "date");
	
		// Aggiunge l'attributo a COURSE
		dataExchange.addFunctionToTarget("COURSE", "day", "date");
	
		// Aggiunge l'attributo a COURSE
		dataExchange.addFunctionToTarget("COURSE", "month", "exameDate");
		
		
		
	}
}
