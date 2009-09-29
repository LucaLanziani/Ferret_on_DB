package it.uniroma3.gaia.sama.test;

import it.uniroma3.gaia.sama.CommonSchemaExchange;
import it.uniroma3.gaia.sama.exception.AttributeNotFoundException;
import it.uniroma3.gaia.sama.exception.RelationNotFoundException;

/**
 * Questa classe testa la classe CommonSchemaExchange. <br />
 */
public class TestCommonSchema {
	
	public static void main(String argv[]) {
		
		try {
			CommonSchemaExchange common = new CommonSchemaExchange("C:/course.xml", 
					"C:/recipe.xml");
			
			System.out.println("Formula logica relativa al primo data exchange:");
			// Stampa la formula logica del primo data exchange
			System.out.println(common.getFirstDataExchange().getLogicalFormula());
			System.out.println("Tgd per il primo data exchange:");
			// Stampa il primo data exchange
			System.out.println(common.getFirstDataExchange().getTgd());
			System.out.println("Schema exchange del primo data exchange:");
			// Stampa lo schema exchange corrispondente al primo data exchange
			System.out.println(common.getFirstDataExchange().createSchemaExchange("course").getTgd());
			
			System.out.println("Formula logica relativa al secondo data exchange:");
			// Stampa la formula logica del secondo data exchange
			System.out.println(common.getSecondDataExchange().getLogicalFormula());
			System.out.println("Tgd per il secondo data exchange:");
			// Stampa il secondo data exchange
			System.out.println(common.getSecondDataExchange().getTgd());
			System.out.println("Schema exchange del secondo data exchange:");
			// Stampa lo schema exchange corrispondente al secondo data exchange
			System.out.println(common.getSecondDataExchange().createSchemaExchange("recipe").getTgd());
			
			common.getCommonSchemaExchange("common");
			
		} catch (RelationNotFoundException e) {
			System.out.println(e.toString());
			
		} catch (AttributeNotFoundException e) {
			System.out.println(e.toString());
			
		}

	}

}
