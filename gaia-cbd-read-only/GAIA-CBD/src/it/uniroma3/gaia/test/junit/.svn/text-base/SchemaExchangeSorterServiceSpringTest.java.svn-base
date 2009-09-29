package it.uniroma3.gaia.test.junit;

import static org.junit.Assert.assertEquals;
import it.uniroma3.gaia.hibernate.model.SchemaExchange;
import it.uniroma3.gaia.service.SchemaExchangeSorterService;
import it.uniroma3.gaia.service.dto.SchemaExchangeSortable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SchemaExchangeSorterServiceSpringTest {
	
	private SchemaExchangeSorterService sess;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext factory = new ClassPathXmlApplicationContext("it/uniroma3/gaia/spring/gaia-spring.xml");
		sess = (SchemaExchangeSorterService)factory.getBean("schemaExchangeSorterService");
	}

//	@Test
//	public void testSort() {
//		List<SchemaExchange> listToSort = new ArrayList<SchemaExchange>();
//		listToSort.add((SchemaExchange)schemaExchanges.getBean(""));
//		
//		List<SchemaExchangeSortable> listSortedExpected = new ArrayList<SchemaExchangeSortable>();
//		listToSort.add((SchemaExchange)schemaExchanges.getBean("fkSchemaExchange_1_n"));
//		listToSort.add((SchemaExchange)schemaExchanges.getBean("simpleSchemaExchange_1_n"));
//		listToSort.add((SchemaExchange)schemaExchanges.getBean("fkSchemaExchange_n_1"));
//		listToSort.add((SchemaExchange)schemaExchanges.getBean("simpleSchemaExchange_n_1"));
//		listToSort.add((SchemaExchange)schemaExchanges.getBean("simpleSchemaExchange_n_1"));
//		
//		List<SchemaExchangeSortable> listSorted = sess.sort(listToSort);
//		listToSort.add(sess.generateSchemaExchangeSortable((SchemaExchangeSortable)schemaExchanges.getBean("fkSchemaExchange_1_nS")));
//		listToSort.add(sess.generateSchemaExchangeSortable((SchemaExchangeSortable)schemaExchanges.getBean("simpleSchemaExchange_1_nS")));
//		listToSort.add(sess.generateSchemaExchangeSortable((SchemaExchangeSortable)schemaExchanges.getBean("fkSchemaExchange_n_1S")));
//		listToSort.add(sess.generateSchemaExchangeSortable((SchemaExchangeSortable)schemaExchanges.getBean("simpleSchemaExchange_n_1S")));
//		listToSort.add(sess.generateSchemaExchangeSortable((SchemaExchangeSortable)schemaExchanges.getBean("simpleSchemaExchange_n_1S")));
//		
//		Collections.sort(listSorted);
//		Collections.sort(listSortedExpected);
//		
//		for(int i=0; i<listSorted.size(); i++){
//			assertEquals(listSortedExpected.get(i), listSorted.get(i));
//		}
//	}
	
	@Test
	public void testSimpleSort(){
		System.out.println("testSimpleSort");
		ApplicationContext schemaExchanges = new ClassPathXmlApplicationContext("it/uniroma3/gaia/test/junit/spring/gaia-junit_simple.xml");
		commonsOp(schemaExchanges);
	}
	
	@Test
	public void testSimpleSortFkT(){
		System.out.println("testSimpleSortFkT");
		ApplicationContext schemaExchanges = new ClassPathXmlApplicationContext("it/uniroma3/gaia/test/junit/spring/gaia-junit_simple_fk_t.xml");
		commonsOp(schemaExchanges);
	}
	
	@Test
	public void testSimpleSortFkT2(){
		System.out.println("testSimpleSortFkT2");
		ApplicationContext schemaExchanges = new ClassPathXmlApplicationContext("it/uniroma3/gaia/test/junit/spring/gaia-junit_simple_fk_t_2.xml");
		commonsOp(schemaExchanges);
	}
	
	@Test
	public void testSimpleSortEqualssn(){
		System.out.println("testSimpleSortEqualssn");
		ApplicationContext schemaExchanges = new ClassPathXmlApplicationContext("it/uniroma3/gaia/test/junit/spring/gaia-junit_simple_equalssn.xml");
		commonsOp(schemaExchanges);
	}
	
	@Test
	public void testSimpleSortFkT2Equalitysn(){
		System.out.println("testSimpleSortFkT2Equalitysn");
		ApplicationContext schemaExchanges = new ClassPathXmlApplicationContext("it/uniroma3/gaia/test/junit/spring/gaia-junit_simple_fk_t_2_equalitysn.xml");
		commonsOp(schemaExchanges);
	}
	
	private void commonsOp(ApplicationContext schemaExchanges){
		List<SchemaExchange> listToSort = new ArrayList<SchemaExchange>();
		listToSort.add((SchemaExchange)schemaExchanges.getBean("simpleSchemaExchange1"));
		listToSort.add((SchemaExchange)schemaExchanges.getBean("simpleSchemaExchange2"));
		listToSort.add((SchemaExchange)schemaExchanges.getBean("simpleSchemaExchange3"));
		listToSort.add((SchemaExchange)schemaExchanges.getBean("simpleSchemaExchange4"));
		listToSort.add((SchemaExchange)schemaExchanges.getBean("simpleSchemaExchange5"));
		
		List<SchemaExchangeSortable> listSorted = sess.sort(listToSort);
		
		List<SchemaExchangeSortable> listSortedExpected = new ArrayList<SchemaExchangeSortable>();
		listSortedExpected.add(sess.generateSchemaExchangeSortable((SchemaExchangeSortable)schemaExchanges.getBean("simpleSchemaExchange1S")));
		listSortedExpected.add(sess.generateSchemaExchangeSortable((SchemaExchangeSortable)schemaExchanges.getBean("simpleSchemaExchange2S")));
		listSortedExpected.add(sess.generateSchemaExchangeSortable((SchemaExchangeSortable)schemaExchanges.getBean("simpleSchemaExchange3S")));
		listSortedExpected.add(sess.generateSchemaExchangeSortable((SchemaExchangeSortable)schemaExchanges.getBean("simpleSchemaExchange4S")));
		listSortedExpected.add(sess.generateSchemaExchangeSortable((SchemaExchangeSortable)schemaExchanges.getBean("simpleSchemaExchange5S")));
		

		Collections.sort(listSorted);
		Collections.sort(listSortedExpected);
		
		for(int i=0; i<listSorted.size(); i++){
			assertEquals(listSortedExpected.get(i), listSorted.get(i));
		}
	}

	public void setSess(SchemaExchangeSorterService sess) {
		this.sess = sess;
	}
}
