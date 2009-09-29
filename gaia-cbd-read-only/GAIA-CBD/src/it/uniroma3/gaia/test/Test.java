package it.uniroma3.gaia.test;

import it.uniroma3.gaia.sama.DataExchange;
import it.uniroma3.gaia.sama.Exchange;
import it.uniroma3.gaia.sama.Parser;
import it.uniroma3.gaia.sama.SchemaExchange;
import it.uniroma3.gaia.sama.exception.RelationNotFoundException;
import it.uniroma3.gaia.service.SchemaExchangeConverterService;
import it.uniroma3.gaia.service.SchemaExchangeRetrieverService;
import it.uniroma3.gaia.service.SchemaExchangeSaverService;
import it.uniroma3.gaia.service.SchemaExchangeSorterService;
import it.uniroma3.gaia.service.dto.SchemaExchangeSortable;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Test {
	
	public static void testXmlMatching(String xmlPath){
		ApplicationContext factory = new ClassPathXmlApplicationContext("it/uniroma3/gaia/spring/gaia-spring.xml");
		Parser parser = new Parser(xmlPath);
		DataExchange dataExchange;
		SchemaExchange se = null;
		try {
			dataExchange = parser.createDataExchange();
	
			// Stampa la formula logica
			System.out.println(dataExchange.getLogicalFormula());
			
			System.out.println(dataExchange.getTgd());
			se = dataExchange.createSchemaExchange("course");
			System.out.println(se.getTgd());
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		SchemaExchangeRetrieverService sers = (SchemaExchangeRetrieverService)factory.getBean("schemaExchangeRetrieverService");
		SchemaExchangeSorterService sess = (SchemaExchangeSorterService)factory.getBean("schemaExchangeSorterService");
		SchemaExchangeConverterService secs = (SchemaExchangeConverterService)factory.getBean("schemaExchangeConverterService");
		List<it.uniroma3.gaia.hibernate.model.SchemaExchange> seList = sers.getListBySourceRepo(se);
		List<SchemaExchangeSortable> sortedList = sess.sort(seList);
		System.out.println(sortedList.size() + " Matching trovati:");
		for (SchemaExchangeSortable schemaExchangeSortable : sortedList) {
			Exchange e;
			try {
				e = secs.convertFromRepository(schemaExchangeSortable);
				System.out.println(e.getTgd());
				secs.getDataExchange(schemaExchangeSortable);
			} catch (RelationNotFoundException e1) {
				e1.printStackTrace();
			}
			System.out.println(schemaExchangeSortable.toString());
		}
	}
	
	public static void testXmlInsertion(String xmlPath){
		ApplicationContext factory = new ClassPathXmlApplicationContext("it/uniroma3/gaia/spring/gaia-spring.xml");
		Parser parser = new Parser(xmlPath);
		DataExchange dataExchange;
		SchemaExchange se = null;
		try {
			dataExchange = parser.createDataExchange();
	
			// Stampa la formula logica
			System.out.println(dataExchange.getLogicalFormula());
			
			System.out.println(dataExchange.getTgd());
			se = dataExchange.createSchemaExchange("course");
			System.out.println(se.getTgd());
		} catch(Exception e) {
			e.printStackTrace();
		}
		SchemaExchangeSaverService sess = (SchemaExchangeSaverService)factory.getBean("schemaExchangeSaverService");
		sess.saveSchemaExchange(se, null);
	}

}
