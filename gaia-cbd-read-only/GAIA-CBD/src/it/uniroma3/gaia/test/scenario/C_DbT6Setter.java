package it.uniroma3.gaia.test.scenario;

import it.uniroma3.gaia.hibernate.model.SchemaExchange;
import it.uniroma3.gaia.service.SchemaExchangeParserService;
import it.uniroma3.gaia.service.SchemaExchangeSaverService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class C_DbT6Setter {
	
	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("it/uniroma3/gaia/spring/gaia-spring.xml");
		SchemaExchangeSaverService sess = (SchemaExchangeSaverService)factory.getBean("schemaExchangeSaverService");
		SchemaExchangeParserService seps = (SchemaExchangeParserService)factory.getBean("schemaExchangeParserService");
		SchemaExchange t6 = seps.createSchemaExchangeRepo("src/xml/scenario/t6.xml");
		sess.saveSchemaExchange(t6);
	}
	
}
