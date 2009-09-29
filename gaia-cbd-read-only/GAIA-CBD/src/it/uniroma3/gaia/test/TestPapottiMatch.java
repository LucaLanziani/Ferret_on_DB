package it.uniroma3.gaia.test;

import it.uniroma3.gaia.hibernate.model.SchemaExchange;
import it.uniroma3.gaia.sama.DataExchange;
import it.uniroma3.gaia.sama.Parser;
import it.uniroma3.gaia.sama.exception.AttributeNotFoundException;
import it.uniroma3.gaia.sama.exception.RelationNotFoundException;
import it.uniroma3.gaia.service.SchemaExchangeConverterService;
import it.uniroma3.gaia.service.SchemaExchangeRetrieverService;
import it.uniroma3.gaia.service.SchemaExchangeSorterService;
import it.uniroma3.gaia.service.dto.SchemaExchangeSortable;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPapottiMatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		match("src/xml/testPapotti/matchEsempio1.xml");
//		match("src/xml/testPapotti/S1.xml");
		match("src/xml/testPapotti/S2.xml");
//		match("src/xml/testPapotti/S3.xml");
//		match("src/xml/testPapotti/S4.xml");
//		match("src/xml/testPapotti/S5.xml");
//		match("src/xml/testPapotti/S6.xml");
	}
	
	private static void match(String filePath){
		ApplicationContext factory = new ClassPathXmlApplicationContext("it/uniroma3/gaia/spring/gaia-spring.xml");
		SchemaExchangeRetrieverService sers = (SchemaExchangeRetrieverService) factory.getBean("schemaExchangeRetrieverService");
		SchemaExchangeConverterService secs = (SchemaExchangeConverterService) factory.getBean("schemaExchangeConverterService");
		SchemaExchangeSorterService sesos = (SchemaExchangeSorterService) factory.getBean("schemaExchangeSorterService");
		
		Parser parser = new Parser(filePath);
		DataExchange dataExchangeToMatch = null;
		it.uniroma3.gaia.sama.SchemaExchange exchangeToMatch = null;
		try {
			dataExchangeToMatch = parser.createDataExchange();
			System.out.println(dataExchangeToMatch.getLogicalFormula());
			exchangeToMatch = dataExchangeToMatch.createSchemaExchange(dataExchangeToMatch.getName());
			System.out.println(exchangeToMatch.getTgd());
		} catch (RelationNotFoundException e) {
			e.printStackTrace();
		} catch (AttributeNotFoundException e) {
			e.printStackTrace();
		}
		
		List<SchemaExchange> matchList = sers.getListBySourceRepo(exchangeToMatch);
		List<SchemaExchangeSortable> matchListOrdered = sesos.sort(matchList);
		for(SchemaExchangeSortable se: matchListOrdered){
			secs.getDataExchange(se);
			secs.getFormula(se);
		}
	}

}
