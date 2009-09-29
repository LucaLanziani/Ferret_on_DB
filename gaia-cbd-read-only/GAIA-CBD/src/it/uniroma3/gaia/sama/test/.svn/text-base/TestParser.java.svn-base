package it.uniroma3.gaia.sama.test;

import it.uniroma3.gaia.sama.DataExchange;
import it.uniroma3.gaia.sama.Parser;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


/**
 * Questa classe testa la classe parser. <br />
 * Passa in input il file XML C:\course.xml 
 * Notare che java tratta i path di file come se fossero path html cio�
 * con il carattere '/' al posto del carattere '\' <br />
 * STUDENT(<i>emp</i>,<b>course</b>,name,surname), COURSE(<i>cod</i>,date) -> COURSE(<i>cod</i>,univ,name,day(date),month(date))
 */
public class TestParser {
	
	public static void main(String argv[]) throws ParserConfigurationException, SAXException, IOException {

		Parser parser = new Parser("C:/course.xml");
		DataExchange dataExchange;
		
		try {
			dataExchange = parser.createDataExchange();
	
			// Stampa la formula logica
			System.out.println(dataExchange.getLogicalFormula());
			
			System.out.println(dataExchange.getTgd());		
			System.out.println(dataExchange.createSchemaExchange("course").getTgd());
		} catch(Exception e) {
			System.out.println(e.toString());
		}

	}

}