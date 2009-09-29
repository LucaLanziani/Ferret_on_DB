package it.uniroma3.gaia.test;

import it.uniroma3.gaia.hibernate.model.SchemaExchange;
import it.uniroma3.gaia.service.SchemaExchangeParserService;
import it.uniroma3.gaia.service.SchemaExchangeSaverService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPapottiInserimento {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("it/uniroma3/gaia/spring/gaia-spring.xml");
		SchemaExchangeSaverService sess = (SchemaExchangeSaverService)factory.getBean("schemaExchangeSaverService");
		SchemaExchangeParserService seps = (SchemaExchangeParserService)factory.getBean("schemaExchangeParserService");
		
		SchemaExchange general1 = seps.createSchemaExchangeRepo("src/xml/testPapotti/General1.xml");
		SchemaExchange general2 = seps.createSchemaExchangeRepo("src/xml/testPapotti/General2.xml");
		SchemaExchange general3 = seps.createSchemaExchangeRepo("src/xml/testPapotti/General3.xml");
		SchemaExchange general4 = seps.createSchemaExchangeRepo("src/xml/testPapotti/General4.xml");
		SchemaExchange general5 = seps.createSchemaExchangeRepo("src/xml/testPapotti/General5.xml");
		SchemaExchange general6 = seps.createSchemaExchangeRepo("src/xml/testPapotti/General6.xml");
		SchemaExchange general7 = seps.createSchemaExchangeRepo("src/xml/testPapotti/General7.xml");
		SchemaExchange general8 = seps.createSchemaExchangeRepo("src/xml/testPapotti/General8.xml");
		SchemaExchange general9 = seps.createSchemaExchangeRepo("src/xml/testPapotti/General9.xml");
		SchemaExchange general10 = seps.createSchemaExchangeRepo("src/xml/testPapotti/General10.xml");
		
		SchemaExchange uni1 = seps.createSchemaExchangeRepo("src/xml/testPapotti/Uni1.xml");
		SchemaExchange uni2 = seps.createSchemaExchangeRepo("src/xml/testPapotti/Uni2.xml");
		SchemaExchange uni3 = seps.createSchemaExchangeRepo("src/xml/testPapotti/Uni3.xml");
		SchemaExchange uni4 = seps.createSchemaExchangeRepo("src/xml/testPapotti/Uni4.xml");
		SchemaExchange uni5 = seps.createSchemaExchangeRepo("src/xml/testPapotti/Uni5.xml");
		SchemaExchange uni6 = seps.createSchemaExchangeRepo("src/xml/testPapotti/Uni6.xml");
		SchemaExchange uni7 = seps.createSchemaExchangeRepo("src/xml/testPapotti/Uni7.xml");
		SchemaExchange uni8 = seps.createSchemaExchangeRepo("src/xml/testPapotti/Uni8.xml");
		SchemaExchange uni9 = seps.createSchemaExchangeRepo("src/xml/testPapotti/Uni9.xml");
		SchemaExchange uni10 = seps.createSchemaExchangeRepo("src/xml/testPapotti/Uni10.xml");
		
		SchemaExchange company1 = seps.createSchemaExchangeRepo("src/xml/testPapotti/company1.xml");
		SchemaExchange company2 = seps.createSchemaExchangeRepo("src/xml/testPapotti/company2.xml");
		SchemaExchange company3 = seps.createSchemaExchangeRepo("src/xml/testPapotti/company3.xml");
		SchemaExchange company4 = seps.createSchemaExchangeRepo("src/xml/testPapotti/company4.xml");
		SchemaExchange company5 = seps.createSchemaExchangeRepo("src/xml/testPapotti/company5.xml");
		SchemaExchange company6 = seps.createSchemaExchangeRepo("src/xml/testPapotti/company6.xml");
		SchemaExchange company7 = seps.createSchemaExchangeRepo("src/xml/testPapotti/company7.xml");
		SchemaExchange company8 = seps.createSchemaExchangeRepo("src/xml/testPapotti/company8.xml");
		SchemaExchange company9 = seps.createSchemaExchangeRepo("src/xml/testPapotti/company9.xml");
		SchemaExchange company10 = seps.createSchemaExchangeRepo("src/xml/testPapotti/company10.xml");
		
		sess.saveSchemaExchange(uni1);
		sess.saveSchemaExchange(uni2);
		sess.saveSchemaExchange(uni3);
		sess.saveSchemaExchange(uni4);
		sess.saveSchemaExchange(uni5);
		sess.saveSchemaExchange(uni6);
		sess.saveSchemaExchange(uni7);
		sess.saveSchemaExchange(uni8);
		sess.saveSchemaExchange(uni9);
		sess.saveSchemaExchange(uni10);
		
		sess.saveSchemaExchange(company1);
		sess.saveSchemaExchange(company2);
		sess.saveSchemaExchange(company3);
		sess.saveSchemaExchange(company4);
		sess.saveSchemaExchange(company5);
		sess.saveSchemaExchange(company6);
		sess.saveSchemaExchange(company7);
		sess.saveSchemaExchange(company8);
		sess.saveSchemaExchange(company9);
		sess.saveSchemaExchange(company10);
		
		sess.saveSchemaExchange(general1);
		sess.saveSchemaExchange(general2);
		sess.saveSchemaExchange(general3);
		sess.saveSchemaExchange(general4);
		sess.saveSchemaExchange(general5);
		sess.saveSchemaExchange(general6);
		sess.saveSchemaExchange(general7);
		sess.saveSchemaExchange(general8);
		sess.saveSchemaExchange(general9);
		sess.saveSchemaExchange(general10);
	}

}
