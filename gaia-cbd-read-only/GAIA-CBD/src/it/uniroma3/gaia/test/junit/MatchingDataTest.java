package it.uniroma3.gaia.test.junit;

import it.uniroma3.gaia.hibernate.model.AtomType;
import it.uniroma3.gaia.service.dto.EqualityArcType;
import it.uniroma3.gaia.service.dto.MatchingData;
import junit.framework.TestCase;

public class MatchingDataTest extends TestCase{
	
	private AtomType relation;
	private AtomType attribute;
	private AtomType key;
	private AtomType fkey;
	
	/* 1 chiave, 1 chiave esterna, 1 attributo, 1 chiave esterna puntante
	 * lato sinistro, 1 uguaglianza incoming (attributo-attributo),
	 * 1 uguaglianza outgoing (chiave-attributo) */
	private MatchingData md1;
	
	/* 1 chiave, 1 chiave esterna, 1 attributo, 1 chiave esterna puntante
	 * lato sinistro, 1 uguaglianza incoming (attributo-attributo),
	 * 1 uguaglianza outgoing (chiave-attributo) */
	private MatchingData md2;
	
	/* 1 chiave, 1 chiave esterna, 1 attributo, 1 chiave esterna puntante
	 * lato sinistro, 1 uguaglianza incoming (attributo-attributo),
	 * 1 uguaglianza outgoing (chiave-chiave) */
	private MatchingData md3;
	
	/* 1 chiave, 1 chiave esterna, 1 attributo, 1 chiave esterna puntante
	 * lato sinistro, 1 uguaglianza incoming (attributo-chiaveEsterna),
	 * 1 uguaglianza outgoing (chiave-attributo) */
	private MatchingData md4;
	
	/* 1 chiave, 1 chiave esterna, 1 attributo, 1 chiave esterna puntante
	 * lato destro, 1 uguaglianza incoming (attributo-attributo),
	 * 1 uguaglianza outgoing (chiave-attributo) */
	private MatchingData md5;
	
	/* 1 chiave, 1 chiave esterna, 2 attributi, 1 chiave esterna puntante
	 * lato sinistro, 1 uguaglianza incoming (attributo-attributo),
	 * 1 uguaglianza outgoing (chiave-attributo) */
	private MatchingData md6;
	
	/* 1 chiave, 2 chiavi esterne, 1 attributi, 1 chiave esterna puntante
	 * lato sinistro, 1 uguaglianza incoming (attributo-attributo),
	 * 1 uguaglianza outgoing (chiave-attributo) */
	private MatchingData md7;
	
	
	@Override
	protected void setUp() throws Exception {
		relation = new AtomType();
		relation.setId(1);
		relation.setDescription("Relation");
		
		attribute = new AtomType();
		attribute.setId(2);
		attribute.setDescription("Attribute");
		
		key = new AtomType();
		key.setId(3);
		key.setDescription("AttributeKey");
		
		fkey = new AtomType();
		fkey.setId(4);
		fkey.setDescription("AttributeFKey");
		
		md1 = new MatchingData();
		md1.addKey();
		md1.addFkey();
		md1.addAttribute();
		md1.addIncomingFkey();
		md1.setSide("sn");
		md1.addIncomingEqualsNum(new EqualityArcType(attribute, attribute));
		md1.addOutgoingEqualsNum(new EqualityArcType(key, attribute));
		
		md2 = new MatchingData();
		md2.addKey();
		md2.addFkey();
		md2.addIncomingFkey();
		md2.addAttribute();
		md2.setSide("sn");
		md2.addIncomingEqualsNum(new EqualityArcType(attribute, attribute));
		md2.addOutgoingEqualsNum(new EqualityArcType(key, attribute));
		
		md3 = new MatchingData();
		md3.addKey();
		md3.addFkey();
		md3.addIncomingFkey();
		md3.addAttribute();
		md3.setSide("sn");
		md3.addIncomingEqualsNum(new EqualityArcType(attribute, attribute));
		md3.addOutgoingEqualsNum(new EqualityArcType(key, key));
		
		md4 = new MatchingData();
		md4.addKey();
		md4.addFkey();
		md4.addAttribute();
		md4.addIncomingFkey();
		md4.setSide("sn");
		md4.addIncomingEqualsNum(new EqualityArcType(attribute, fkey));
		md4.addOutgoingEqualsNum(new EqualityArcType(key, attribute));
		
		md5 = new MatchingData();
		md5.addKey();
		md5.addFkey();
		md5.addAttribute();
		md5.addIncomingFkey();
		md5.setSide("dx");
		md5.addIncomingEqualsNum(new EqualityArcType(attribute, attribute));
		md5.addOutgoingEqualsNum(new EqualityArcType(key, attribute));
		
		md6 = new MatchingData();
		md6.addKey();
		md6.addFkey();
		md6.addAttribute();
		md6.addAttribute();
		md6.addIncomingFkey();
		md6.setSide("sn");
		md6.addIncomingEqualsNum(new EqualityArcType(attribute, attribute));
		md6.addOutgoingEqualsNum(new EqualityArcType(key, attribute));
		
		md7 = new MatchingData();
		md7.addKey();
		md7.addFkey();
		md7.addFkey();
		md7.addAttribute();
		md7.addAttribute();
		md7.addIncomingFkey();
		md7.setSide("sn");
		md7.addIncomingEqualsNum(new EqualityArcType(attribute, attribute));
		md7.addOutgoingEqualsNum(new EqualityArcType(key, attribute));
	}
	
	/* *********************
	 * TEST SULL'UGUAGLIANZA
	 * ********************* */
	
	public void testEqualsSelf(){
		assertEquals(true, md1.equals(md1));
	}
	public void testEqualsDifferentEquals(){
		assertEquals(true, md1.equals(md2));
	}
	public void testEqualsDifferentEqualsInverse(){
		assertEquals(true, md2.equals(md1));
	}
	
	public void testEqualOutEqNumNotEq(){
		assertEquals(false, md1.equals(md3));
	}
	
	public void testEqualInEqNumNotEq(){
		assertEquals(false, md1.equals(md4));
	}
	
	public void testEqualSideNotEq(){
		assertEquals(false, md1.equals(md5));
	}
	
	public void testEqualDifferentAttsNum(){
		assertEquals(false, md1.equals(md6));
		assertEquals(false, md6.equals(md1));
	}
	
	/* *****************
	 * TEST SUL MATCHING
	 * ***************** */
	
	/* Numero di attributi differenti devono matchare solo in un verso */
	public void testMatchDifferentAttsNum(){
		/* Matchano solo se, a parità di tutto il resto, il primo ha più 
		 * attributi del secondo.Naturalmente non è simmetrico */
		assertEquals(false, md1.match(md6));
		assertEquals(true, md6.match(md1));
	}
	
	/* Deve matchare con sé stesso */
	public void testMatchSelf(){
		assertEquals(true, md1.match(md1));
	}
	
	/* devono matchare se sono uguali, in entrambi i versi */
	public void testMatchDifferentEquals(){
		assertEquals(true, md1.match(md2));
		assertEquals(true, md2.match(md1));
	}
	
	/* Non matchano alla minima differenza */
	public void testMatchOutEqNumNotEq(){
		assertEquals(false, md1.match(md3));
	}
	
	/* *********************
	 * TEST SULL'ORDINAMENTO
	 * ********************* */
	
	/* Uguale a sé stesso */
	public void testCompareToSelf(){
		assertEquals(0, md1.compareTo(md1));
	}
	
	/* Uguali */
	public void testCompareToDifferentEqual(){
		assertEquals(0, md1.compareTo(md2));
		assertEquals(0, md2.compareTo(md1));
	}
	
	/* Lato sinistro maggiore di lato destro */
	public void testCompareToSnDx(){
		assertEquals(true, md1.compareTo(md5)>0);
		assertEquals(true, md5.compareTo(md1)<0);
	}
	
	/* più attributi maggiore */
	public void testCompareToDiffNumAtts(){
		assertEquals(true, md1.compareTo(md6)<0);
		assertEquals(true, md6.compareTo(md1)>0);
	}
	
	/* più chiavi esterne maggiore anche se l'altro ha più attributi */
	public void testCompareToDiffNumKeys(){
		assertEquals(true, md6.compareTo(md7)<0);
		assertEquals(true, md7.compareTo(md6)>0);
	}
}
