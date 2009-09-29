package it.uniroma3.gaia.test.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import it.uniroma3.gaia.hibernate.model.SchemaExchange;
import it.uniroma3.gaia.sama.DataExchange;
import it.uniroma3.gaia.sama.Parser;
import it.uniroma3.gaia.sama.exception.RelationNotFoundException;
import it.uniroma3.gaia.service.SchemaExchangeConverterService;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ChaseTest {

	private SchemaExchangeConverterService secs;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext factory = new ClassPathXmlApplicationContext("it/uniroma3/gaia/spring/gaia-spring.xml");
		secs = (SchemaExchangeConverterService)factory.getBean("schemaExchangeConverterService");
	}
	
	private void commonsOp(ApplicationContext schemaExchanges, Parser parser){
		SchemaExchange schemaExchangeToChase = (SchemaExchange)schemaExchanges.getBean("simpleSchemaExchange1");
		DataExchange result = secs.getDataExchange(schemaExchangeToChase);
		
		DataExchange resultExpected = null;
		try {
			resultExpected = parser.createDataExchange();
		} catch (RelationNotFoundException e) {
			fail("errore del parser");
		}
		DataExchange formula = secs.getFormula(schemaExchangeToChase);
		System.out.println();
		System.out.println("Expected logical formula: " + resultExpected.getLogicalFormula());
		System.out.println("Expected tgd: " + resultExpected.getTgd());
		System.out.println();
		System.out.println();
		assertEquals(resultExpected.getTgd(), result.getTgd());
	}

	@Test
	public void testGetDataExchangeSimple() {
		System.out.println("DataExchangeSimple");
		ApplicationContext schemaExchanges = new ClassPathXmlApplicationContext("it/uniroma3/gaia/test/junit/spring/chase/junit_chase-simple.xml");
		Parser parser = new Parser("src/xml/chase/chase-simple.xml");
		commonsOp(schemaExchanges, parser);
	}
	@Test
	public void testGetDataExchangeSimple1() {
		System.out.println("DataExchangeSimple1");
		ApplicationContext schemaExchanges = new ClassPathXmlApplicationContext("it/uniroma3/gaia/test/junit/spring/chase/junit_chase-simple1.xml");
		Parser parser = new Parser("src/xml/chase/chase-simple1.xml");
		commonsOp(schemaExchanges, parser);
	}
	
	
	
	@Test
	public void testGetDataExchangeSimpleFk() {
		System.out.println("DataExchangeSimpleFk");
		ApplicationContext schemaExchanges = new ClassPathXmlApplicationContext("it/uniroma3/gaia/test/junit/spring/chase/junit_chase-simple-fk.xml");
		Parser parser = new Parser("src/xml/chase/chase-simple-fk.xml");
		commonsOp(schemaExchanges, parser);
	}
	
	@Test
	public void testGetDataExchangeSimpleFk1() {
		System.out.println("DataExchangeSimpleFk1");
		ApplicationContext schemaExchanges = new ClassPathXmlApplicationContext("it/uniroma3/gaia/test/junit/spring/chase/junit_chase-simple-fk1.xml");
		Parser parser = new Parser("src/xml/chase/chase-simple-fk1.xml");
		commonsOp(schemaExchanges, parser);
	}
	
	@Test
	public void testGetDataExchangeSimpleFk2() {
		System.out.println("DataExchangeSimpleFk2");
		ApplicationContext schemaExchanges = new ClassPathXmlApplicationContext("it/uniroma3/gaia/test/junit/spring/chase/junit_chase-simple-fk2.xml");
		Parser parser = new Parser("src/xml/chase/chase-simple-fk2.xml");
		commonsOp(schemaExchanges, parser);
	}
	
	@Test
	public void testGetDataExchangen1fk() {
		System.out.println("DataExchange-n-1-fk");
		ApplicationContext schemaExchanges = new ClassPathXmlApplicationContext("it/uniroma3/gaia/test/junit/spring/chase/junit_chase-n-1-fk.xml");
		Parser parser = new Parser("src/xml/chase/chase-n-1-fk.xml");
		commonsOp(schemaExchanges, parser);
	}
	
	@Test
	public void testGetDataExchangen1fk1() {
		System.out.println("DataExchange-n-1-fk1");
		ApplicationContext schemaExchanges = new ClassPathXmlApplicationContext("it/uniroma3/gaia/test/junit/spring/chase/junit_chase-n-1-fk1.xml");
		Parser parser = new Parser("src/xml/chase/chase-n-1-fk1.xml");
		commonsOp(schemaExchanges, parser);
	}
	
	@Test
	public void testGetDataExchange1nfk() {
		System.out.println("DataExchange-1-n-fk");
		ApplicationContext schemaExchanges = new ClassPathXmlApplicationContext("it/uniroma3/gaia/test/junit/spring/chase/junit_chase-1-n-fk.xml");
		Parser parser = new Parser("src/xml/chase/chase-1-n-fk.xml");
		commonsOp(schemaExchanges, parser);
	}

}
