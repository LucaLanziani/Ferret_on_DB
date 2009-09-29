package it.uniroma3.gaia.test.scenario;

import static org.junit.Assert.fail;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import it.uniroma3.gaia.hibernate.model.SchemaExchange;
import it.uniroma3.gaia.sama.DataExchange;
import it.uniroma3.gaia.sama.Parser;
import it.uniroma3.gaia.sama.exception.AttributeNotFoundException;
import it.uniroma3.gaia.sama.exception.RelationNotFoundException;
import it.uniroma3.gaia.service.SchemaExchangeConverterService;
import it.uniroma3.gaia.service.SchemaExchangeRetrieverService;
import it.uniroma3.gaia.service.SchemaExchangeSorterService;
import it.uniroma3.gaia.service.dto.SchemaExchangeSortable;

public class B_EsseMatching {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Parser parser = new Parser("src/xml/scenario/scenario-esse.xml");
		ApplicationContext factory = new ClassPathXmlApplicationContext("it/uniroma3/gaia/spring/gaia-spring.xml");
		SchemaExchangeRetrieverService sers = (SchemaExchangeRetrieverService) factory.getBean("schemaExchangeRetrieverService");
		SchemaExchangeConverterService secs = (SchemaExchangeConverterService) factory.getBean("schemaExchangeConverterService");
		SchemaExchangeSorterService sess = (SchemaExchangeSorterService) factory.getBean("schemaExchangeSorterService");
		
		DataExchange dataExchangeToMatch = null;
		it.uniroma3.gaia.sama.SchemaExchange exchangeToMatch = null;
		try {
			dataExchangeToMatch = parser.createDataExchange();
			System.out.println(dataExchangeToMatch.getLogicalFormula());
			exchangeToMatch = dataExchangeToMatch.createSchemaExchange("esse");
			System.out.println(exchangeToMatch.getTgd());
		} catch (RelationNotFoundException e) {
			fail("errore del parser");
		} catch (AttributeNotFoundException e) {
			fail("errore del parser");
		}
		
		List<SchemaExchange> matchList = sers.getListBySourceRepo(exchangeToMatch);
		List<SchemaExchangeSortable> matchListOrdered = sess.sort(matchList);
		for(SchemaExchangeSortable se: matchListOrdered){
			secs.getDataExchange(se);
			secs.getFormula(se);
		}
	}

}
