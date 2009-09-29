package it.uniroma3.gaia.test.scenario;

import it.uniroma3.gaia.hibernate.model.SchemaExchange;
import it.uniroma3.gaia.service.SchemaExchangeParserService;
import it.uniroma3.gaia.service.SchemaExchangeSaverService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class A_DbInitialSetter {
	
	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("it/uniroma3/gaia/spring/gaia-spring.xml");
		SchemaExchangeSaverService sess = (SchemaExchangeSaverService)factory.getBean("schemaExchangeSaverService");
		SchemaExchangeParserService seps = (SchemaExchangeParserService)factory.getBean("schemaExchangeParserService");
		SchemaExchange t1 = seps.createSchemaExchangeRepo("src/xml/scenario/t1.xml");
		SchemaExchange t2 = seps.createSchemaExchangeRepo("src/xml/scenario/t2.xml");
		SchemaExchange t3 = seps.createSchemaExchangeRepo("src/xml/scenario/t3.xml");
//		SchemaExchange t4 = seps.createSchemaExchangeRepo("src/xml/scenario/t4.xml");
//		SchemaExchange t5 = seps.createSchemaExchangeRepo("src/xml/scenario/t5.xml");
		
		sess.saveSchemaExchange(t1);
		sess.saveSchemaExchange(t2);
		sess.saveSchemaExchange(t3);
//		sess.saveSchemaExchange(t4);
//		sess.saveSchemaExchange(t5);
	}
	
}
