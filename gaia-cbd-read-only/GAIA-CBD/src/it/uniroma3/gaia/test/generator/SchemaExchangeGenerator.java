package it.uniroma3.gaia.test.generator;

import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import it.uniroma3.gaia.hibernate.dao.AtomTypeDao;
import it.uniroma3.gaia.hibernate.model.Atom;
import it.uniroma3.gaia.hibernate.model.AtomType;
import it.uniroma3.gaia.hibernate.model.AtomTypeEnum;
import it.uniroma3.gaia.hibernate.model.SchemaExchange;
import it.uniroma3.gaia.sama.DataExchange;
import it.uniroma3.gaia.sama.Exchange;
import it.uniroma3.gaia.sama.Parser;
import it.uniroma3.gaia.sama.exception.RelationNotFoundException;
import it.uniroma3.gaia.service.SchemaExchangeConverterService;
import it.uniroma3.gaia.service.SchemaExchangeRetrieverService;
import it.uniroma3.gaia.service.SchemaExchangeSaverService;
import it.uniroma3.gaia.service.SchemaExchangeSorterService;
import it.uniroma3.gaia.service.dto.SchemaExchangeSortable;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SchemaExchangeGenerator {
	
	public static void main(String[] args){
		Date d1 = new Date();
//		testInsert();
		testXmlMatching();
		Date d2 = new Date();
		System.out.println("Started at " + d1 + " " + d1.getTime());
		System.out.println("Finished at " + d2 + " " + d2.getTime());
		System.out.println("Execution time in milliseconds: " + (d2.getTime() - d1.getTime()));
	}
	
	private static void testInsert() {
		
		ApplicationContext schemaExchange = new ClassPathXmlApplicationContext("it/uniroma3/gaia/test/generator/BaseSchemaExchange.xml");
		File constants = new File("src/it/uniroma3/gaia/test/generator/constants.txt");
		
		ApplicationContext factory = new ClassPathXmlApplicationContext("it/uniroma3/gaia/spring/gaia-spring.xml");
		SchemaExchangeSaverService sess = (SchemaExchangeSaverService)factory.getBean("schemaExchangeSaverService");
		
		Map<Integer, AtomType> atomTypes = new HashMap<Integer, AtomType>();
		AtomTypeDao atomTypeDao = (AtomTypeDao)factory.getBean("atomTypeDao");
		atomTypes.put(AtomTypeEnum.ATTRIBUTE_KEY.getId(),atomTypeDao.getByTypeEnum(AtomTypeEnum.ATTRIBUTE_KEY));
		atomTypes.put(AtomTypeEnum.ATTRIBUTE_FKEY.getId(),atomTypeDao.getByTypeEnum(AtomTypeEnum.ATTRIBUTE_FKEY));
		atomTypes.put(AtomTypeEnum.ATTRIBUTE.getId(),atomTypeDao.getByTypeEnum(AtomTypeEnum.ATTRIBUTE));
		atomTypes.put(AtomTypeEnum.RELATION.getId(),atomTypeDao.getByTypeEnum(AtomTypeEnum.RELATION));
		
		int countInserted = 0;
		try {
			Scanner scanner = new Scanner(constants);
			scanner.useDelimiter(",");
			int constantsNumber = scanner.nextInt();
			List<String> constantsList = new ArrayList<String>();
			while(scanner.hasNext()){
				constantsList.add(scanner.next());
			}
			
			for(int i=0; i<8000; i++){
				SchemaExchange seBase = (SchemaExchange)schemaExchange.getBean("baseSchemaExchange");
				SchemaExchange se = new SchemaExchange();
				se.setDescription(seBase.getDescription() + i);
				se.setSchemaExchangeType(seBase.getSchemaExchangeType());
				
				List<Atom> atoms = new ArrayList<Atom>();
				atoms.addAll(seBase.getAtoms());
				atoms = cleanAtoms(atoms, atomTypes);
				
				int useConstants = (int)(Math.random()*(constantsNumber<5?constantsNumber:5));
				
				Collections.shuffle(constantsList);
				
				Set<String> constantsToUse = new HashSet<String>();
				
				int j = 0;
				while(j<useConstants){
					if(constantsToUse.add(constantsList.get((int)(Math.random()*constantsNumber)))){
						j++;
					}
				}
				
				
				Collections.shuffle(atoms);
				int k = 0;
				for(String constant: constantsToUse){
					if(StringUtils.equalsIgnoreCase(atoms.get(k).getSide(), "sn") && !atoms.get(k).getAtomType().getId().equals(new Integer(1))){
						atoms.get(k).setConstant(constant);
						k++;
					}
				}
				
				se.addAtoms(atoms);
				boolean inserted = sess.saveSchemaExchange(se);
				if(inserted){
					countInserted++;
				}
//				seBase.setAtoms(atoms);
//				sess.saveSchemaExchange(seBase);
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Inserted " + countInserted + " new schhema exchanges in Repository.");
		//testXmlMatching("src/it/uniroma3/gaia/test/generator/match.xml", factory);
	}
	
	private static List<Atom> cleanAtoms(List<Atom> list, Map<Integer, AtomType> atomTypes){
		List<Atom> res = new ArrayList<Atom>();
		Map<Atom, Atom> map = new HashMap<Atom, Atom>(); 
		for (Atom atom : list) {
			Atom a = new Atom();
			a.setAtomType(atomTypes.get(atom.getAtomType().getId()));
			a.setId(atom.getId());
//			a.setEquality(atom.getEquality());
//			a.setFkRefer(atom.getFkRefer());
//			a.setFather(atom.getFather());
			a.setSide(atom.getSide());
			map.put(atom, a);
		}
		for(Atom atom: map.keySet()){
			Atom a = map.get(atom);
			if(atom.getEquality()!=null){
				a.setEquality(map.get(atom.getEquality()));
			}
			if(atom.getFather()!=null){
				a.setFather(map.get(atom.getFather()));
			}
			if(atom.getFkRefer()!=null){
				a.setFkRefer(map.get(atom.getFkRefer()));
			}
		}
		res.addAll(map.values());
		return res;
	}
	
	public static void testXmlMatching(){
		ApplicationContext factory = new ClassPathXmlApplicationContext("it/uniroma3/gaia/spring/gaia-spring.xml");
		Parser parser = new Parser("src/it/uniroma3/gaia/test/generator/match.xml");
		DataExchange dataExchange;
		it.uniroma3.gaia.sama.SchemaExchange se = null;
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
}
