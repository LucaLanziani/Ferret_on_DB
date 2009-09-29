package it.uniroma3.gaia.test;

import it.uniroma3.gaia.sama.SchemaExchange;
import it.uniroma3.gaia.sama.exception.RelationNotFoundException;
import it.uniroma3.gaia.service.SchemaExchangeConverterService;
import it.uniroma3.gaia.service.SchemaExchangeParserService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSEParser {

	/**
	 * @param args
	 * @throws RelationNotFoundException 
	 */
	public static void main(String[] args) throws RelationNotFoundException {
		ApplicationContext factory = new ClassPathXmlApplicationContext("it/uniroma3/gaia/spring/gaia-spring.xml");
		SchemaExchangeParserService seps = (SchemaExchangeParserService)factory.getBean("schemaExchangeParserService");
		SchemaExchange se = seps.createSchemaExchange("src/xml/exchange/SEcourse.xml");
		System.out.println(se.getTgd());
		SchemaExchangeConverterService secs = (SchemaExchangeConverterService)factory.getBean("schemaExchangeConverterService");
		secs.convertToRepository(se, se.getName());
	}

}
