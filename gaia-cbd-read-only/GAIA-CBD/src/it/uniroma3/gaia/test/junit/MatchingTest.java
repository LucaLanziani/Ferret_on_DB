package it.uniroma3.gaia.test.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import it.uniroma3.gaia.hibernate.model.Atom;
import it.uniroma3.gaia.hibernate.model.SchemaExchange;
import it.uniroma3.gaia.sama.DataExchange;
import it.uniroma3.gaia.sama.Exchange;
import it.uniroma3.gaia.sama.Parser;
import it.uniroma3.gaia.sama.exception.AttributeNotFoundException;
import it.uniroma3.gaia.sama.exception.RelationNotFoundException;
import it.uniroma3.gaia.service.SchemaExchangeConverterService;
import it.uniroma3.gaia.service.SchemaExchangeRetrieverService;
import it.uniroma3.gaia.service.dto.SchemaExchangeSortable;

import org.apache.commons.lang.StringUtils;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MatchingTest {
	
	private SchemaExchangeRetrieverService sers;
	private SchemaExchangeConverterService secs;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext factory = new ClassPathXmlApplicationContext("it/uniroma3/gaia/spring/gaia-spring.xml");
		sers = (SchemaExchangeRetrieverService) factory.getBean("schemaExchangeRetrieverService");
		secs = (SchemaExchangeConverterService) factory.getBean("schemaExchangeConverterService");
	}
	
	@Test
	public void testMatchingSimple() {
		ApplicationContext schemaExchanges = new ClassPathXmlApplicationContext("it/uniroma3/gaia/test/junit/spring/match/junit_match-n-1-fk.xml");
		Parser parser = new Parser("src/xml/match/match-n-1-fk.xml");
		List<SchemaExchange> listToVerify = new ArrayList<SchemaExchange>();
		listToVerify.add((SchemaExchange)schemaExchanges.getBean("simpleSchemaExchange1"));
		List<Integer> matchingsPresunti = new ArrayList<Integer>();
		matchingsPresunti.add(1);
		commonsOp(listToVerify, matchingsPresunti, parser);
	}
	
	private void commonsOp(List<SchemaExchange> listToVerify, List<Integer> matchingsPresunti, Parser parser){
		DataExchange givenDe = null;
		it.uniroma3.gaia.sama.SchemaExchange e = null;
		try {
			givenDe = parser.createDataExchange();
			System.out.println(givenDe.getLogicalFormula());
			System.out.println(givenDe.getTgd());
			e = givenDe.createSchemaExchange(null);
			System.out.println(e.getTgd());
		} catch (RelationNotFoundException ex) {
			ex.printStackTrace();
		} catch (AttributeNotFoundException ex) {
			ex.printStackTrace();
		}
		
		SchemaExchange given = secs.convertToRepository(e, null);
		
		List<Atom> sourceGiven = new ArrayList<Atom>();
		List<Atom> targetGiven = new ArrayList<Atom>();
		for(Atom a: given.getAtoms()){
			if(StringUtils.equals(a.getSide(), "sn")){
				sourceGiven.add(a);
			}else{
				targetGiven.add(a);
			}
		}
		List<SchemaExchange> matchings = sers.findMatching(sourceGiven, targetGiven, listToVerify, true);
		assertEquals(matchings.size(), matchingsPresunti.size());
		for(SchemaExchange se: matchings){
			assertEquals(matchingsPresunti.contains(se.getId()), true);
		}
	}
}
