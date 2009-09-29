package it.uniroma3.gaia.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 
 * Questa classe rappresenta una relazione. <br/>
 * Ogni relazione � caratterizzata da: <br/>
 * <ul>
 * 	<li>un nome</li>
 * 	<li>un insieme di attributi</li>
 * 	<li>un insieme di attributi chiave</li>
 * 	<li>un insieme di attributi chiave esterne</li>
 *  <li>un insieme di funzioni</li>
 *  <li>un insieme di attributi derivati</li>
 * </ul>
 */

public class Relation {
	// Il nome della relazione
	private String name;
	// L'insieme degli attributi
	// Contiene coppie del tipo
	// <AttributeName, AttributeObject>
	private Map<String, Attribute> attributes;
	// L'insieme degli attributi chiave
	// Contiene coppie del tipo
	// <AttributeKeyName, AttributeKeyObject>
	private Map<String, AttributeKey> keys;
	// L'insieme degli attributi chiave esterna
	// Contiene coppie del tipo
	// <AttributeFKeyName, AttributeFKeyObject>
	private Map<String, AttributeFkey> fkeys;
	// L'insieme delle funzioni
	private List<Function> functions;
	// L'insieme dei DerivatedAttribute relativi a questa relazione
	// Contiene coppie del tipo
	// <DerivatedAttributeName, DerivatedAttributeObject> 
	private Map<String, DerivatedAttribute> derivatedAtt;
	
	private List<String> attsRealName = new ArrayList<String>();
	
	/**
	 * Crea un aggetto relazione
	 * @param relationName il nome della relazione
	 */
	public Relation(String relationName){
		// Imposta il nome
		this.name = relationName;

		// Crea la mappa degli attributi
		this.attributes = new HashMap<String, Attribute>();

		// Crea la mappa degli attributi chiave
		this.keys = new HashMap<String, AttributeKey>();
		
		// Crea la lista degli attributi chiave esterna
		this.fkeys = new HashMap<String, AttributeFkey>();
		
		// Crea la lista delle funzioni
		this.functions = new LinkedList<Function>();

		// Crea la lista degli attributi derivati
		this.derivatedAtt = new HashMap<String, DerivatedAttribute>();

	}
	
	/**
	 * Questo metodo imposta il nome di questa relazione
	 * @param relationName il nome di questa relazione
	 */
	public void setName(String relationName) {
		this.name = relationName;
		
	}
	
	/**
	 * Questo metodo restituisce il nome di questa relazione
	 * @return il nome di questa relazione
	 */
	public String getName(){
		return this.name; 
		
	}
	
	/**
	 * Questo metodo restituisce l'insieme degli attributi di questa
	 * relazione
	 * @return l'insieme degli attributi di questa relazione nel formato 
	 * (AttributeName, AttributeObject)
	 */
	public Map<String, Attribute> getAttribute() {
		return this.attributes;
	
	}
	
	/**
	 * Questo metodo restituisce l'insieme degli attributi chiave di questa
	 * relazione
	 * @return l'insieme degli attributi chiave di questa relazione nel 
	 * formato (AttributeKeyName, AttributeKeyObject)
	 */
	public Map<String, AttributeKey> getAttributeKey() {
		return this.keys;
	
	}
	
	/**
	 * Questo metodo restituisce l'insieme degli attributi di chiave esterna
	 * di questa relazione
	 * @return l'insieme degli attributi di chiave esterna di questa 
	 * relazione nel formato (AttributeFKeyName, AttributeFKeyObject) 
	 */
	public Map<String, AttributeFkey> getAttributeFkey() {
		return this.fkeys;
	
	}	
	
	/**
	 * Questo metodo restituisce la lista delle funzioni relative a 
	 * questa relazione
	 * @return la lista di funzioni relative a questa relazione
	 */
	public List<Function> getFunctions() {
		return this.functions;

	}

	/**
	 * Questo metodo restituisce l'insieme degli attributi derivati
	 * di questa relazione
	 * @return l'insieme degli attributi derivati di questa 
	 * relazione nel formato (DerivatedAttributeName, DervatedAttributeObject) 
	 */
	public Map<String, DerivatedAttribute> getDerivatedAttribute() {
		return this.derivatedAtt;
		
	}
	
	/**
	 * Questo metodo aggiunge un attributo alla lista degli attributi
	 * @param attributeName il nome dell'attributo da aggiungere
	 */
	public void addAttribute(String attributeName) {
		this.attributes.put(attributeName, new Attribute(attributeName));
		
	}
	
	/**
	 * Questo metodo aggiunge un attributo chiave all'insieme
	 * degli attributi chiave di questa relazione
	 * @param attributeName il nome dell'attributo da aggiungere 
	 */
	public void addAttributeKey(String attributeName) {
		this.keys.put(attributeName, new AttributeKey(attributeName));
		
	}
	
	/**
	 * Questo metodo aggiunge un attributo di chiave esterna all'insieme
	 * delle chiavi esterne di questa relazione
	 * @param attributeName il nome dell'attributo chiave
	 * @param relation la relazione referenziata da questo attributo di 
	 * chiave esterna
	 */
	public void addAttributeFKey(String attributeName, Relation relation) {
		this.fkeys.put(attributeName, new AttributeFkey(attributeName, relation));
		
	}
	
	/**
	 * Questo metodo aggiunge una condizione di uguaglianza ad uno specifico
	 * attributo di questa relazione
	 * @param attributeName il nome dell'attributo a cui aggiungere la 
	 * condizione
	 * @param value il valore di uguaglianza da assengare alla condizione
	 */
	public void addSamenessConditionsToAttributes(String attributeName, String value){
		this.attributes.get(attributeName).addSamenessConditions(value);
		
	}
	
	/**
	 * Questo metodo aggiunge una condizione di uguaglianza ad una specifica
	 * chiave di questa relazione
	 * @param keyName il nome della chiave a cui aggiungere la 
	 * condizione
	 * @param value il valore di uguaglianza da assengare alla condizione
	 */
	public void addSamenessConditionsToKey(String keyName, String value){
		this.keys.get(keyName).addSamenessConditions(value);
		
	}
	
	/**
	 * Questo metodo aggiunge una condizione di uguaglianza ad una specifica
	 * chiave esterna di questa relazione
	 * @param fkeyName il nome della chiave esterna a cui aggiungere la 
	 * condizione
	 * @param value il valore di uguaglianza da assengare alla condizione
	 */
	public void addSamenessConditionsToFkey(String fkeyName, String value){
		this.fkeys.get(fkeyName).addSamenessConditions(value);
		
	}
	
	/**
	 * Questo metodo aggiunge una condizione di disuguaglianza ad uno 
	 * specifico attributo di questa relazione
	 * @param attributeName il nome dell'attributo a cui aggiungere la 
	 * condizione
	 * @param value il valore di disuguaglianza da assengare alla condizione
	 */
	public void addInequalityConditionsToAttributes(String attributeName, String value){		
		this.attributes.get(attributeName).addInequalityConditions(value);
		
	}
	
	/**
	 * Questo metodo aggiunge una funzione a questa relazione
	 * @param attributeName il nome dell'attributo a cui applicare la 
	 * funzione
	 * @param functionName il nome della funzione
	 */
	public void addFunction(String attributeName, String functionName) {
		this.functions.add(new Function(functionName, attributeName));
		
	}
	
	/**
	 * Questo metodo aggiunge un attributo derivato allo schema exchange
	 * Da notare che questo metodo viene utilizzato solamente per la 
	 * creazione dello schema exchange
	 * Esempio: <br/>
	 * 		Att(na, nr), (na = 'month(date)')
	 * @param attributeName il nome dell'attributo che rappresenta una 
	 * funzione (na)
	 * @param functionName il nome della funzione (month)
	 * @param referredName il nome dell'attributo a cui si applica la 
	 * funzione (date)
	 */
	public void addDerivatedAttribute(String attributeName, String functionName, String referredName) {
		this.derivatedAtt.put(attributeName, new DerivatedAttribute(functionName,attributeName,referredName));
		
	}

	/**
	 * Ritorna una descrizione testuale di questa relazione
	 * @return una descrizione testuale di questa relazione
	 */
	public String toString(){
	
		return  this.name;
	}
	
	/**
	 * Questo metodo restituisce una descrizione testuale della tgd
	 * @return una descrizione testuale della tgd
	 */
	public String getTgd() {
		// La stringa da restituire
		StringBuffer descrizione = new StringBuffer("Rel(" + this.getName() + ")");
		
		List<AttributeKey> keys = new ArrayList<AttributeKey>(this.keys.values());
		Collections.sort(keys);
		List<AttributeFkey> fkeys = new ArrayList<AttributeFkey>(this.fkeys.values());
		Collections.sort(fkeys);
		List<Attribute> atts = new ArrayList<Attribute>(this.attributes.values());
		Collections.sort(atts);
		
		// L'teratore per scorrere gli attributi chiave
		Iterator<AttributeKey> itAttKey = keys.iterator();
		// Finch� ci sono attributi chiave...
		while(itAttKey.hasNext()) {
			// ...ne richiedo la descrzione della Tgd
			descrizione.append(itAttKey.next().getTgd(this.getName()));
			
		}
		
		// L'teratore per scorrere gli attributi chiave esterne
		Iterator<AttributeFkey> itAttFkey = fkeys.iterator();
		// Finch� ci sono attributi chiave...
		while(itAttFkey.hasNext()) {
			// ...ne richiedo la descrizione della Tgd
			descrizione.append(itAttFkey.next().getTgd(this.getName()));
			
		}
		
		// L'teratore per scorrere gli attributi
		Iterator<Attribute> itAtt = atts.iterator();
		// Finch� ci sono attributi... 
		while(itAtt.hasNext()) {
			// ...ne richiedo la descrizione della tgd
			descrizione.append(itAtt.next().getTgd(this.getName()));
			
		}

		// L'teratore per scorrere le funzioni
		//Iterator<Function> itFunction = this.function.values().iterator();
		Iterator<Function> itFunction = this.functions.iterator();
		// Finch� ci sono funzioni...
		while(itFunction.hasNext()) {
			// ...ne richiedo la descrizione della tgd
			descrizione.append(itFunction.next().getTgdForData(this.getName()));
			
		}
		
		// L'teratore per scorrere i DerivatedAttribute
		Iterator<DerivatedAttribute> itDerAtt = this.derivatedAtt.values().iterator();
		// Finch� ci sono attributi derivati...
		while(itDerAtt.hasNext()) {
			// ...ne richiedo la descrizione della tgd
			descrizione.append(itDerAtt.next().getTgd(this.getName()));
			
		}
		
		return descrizione.toString();
		
	}
	
	/**
	 * Questo metodo restituisce una stringa che rappresenta la formula
	 * logica di questa relazione
	 * @return la formula logica
	 */
	public String getLogicalFormula() {
		// La stringa da restituire
		StringBuffer formula = new StringBuffer(this.getName() + "(");
		
		List<AttributeKey> keys = new ArrayList<AttributeKey>(this.keys.values());
		Collections.sort(keys);
		List<AttributeFkey> fkeys = new ArrayList<AttributeFkey>(this.fkeys.values());
		Collections.sort(fkeys);
		List<Attribute> atts = new ArrayList<Attribute>(this.attributes.values());
		Collections.sort(atts);
		
		// L'iteratore per scorrere gli attributi chiave
		Iterator<AttributeKey> itAttKey = keys.iterator();
		// Finch� ci sono attributi chiave...
		while(itAttKey.hasNext()) {
			// ...ne richiedo la formula logica
			formula.append(itAttKey.next().getLogicalFormula());
			
			// se � una chiave composta, aggiunge la virgola
			if(itAttKey.hasNext())
				formula.append(" ,");
			
		}
		
		// L'iteratore per scorrere gli attributi chiave esterne
		Iterator<AttributeFkey> itAttFkey = fkeys.iterator();
		// Finch� ci sono attributi chiave...
		while(itAttFkey.hasNext()) {
			// ...ne richiedo la formula logica
			formula.append(itAttFkey.next().getLogicalFormula());
			
		}
				
		// L'iteratore per scorrere gli attributi
		Iterator<Attribute> itAtt = atts.iterator();
		// Finch� ci sono attributi... 
		while(itAtt.hasNext()) {
			// ...ne richiedo la formula logica
			formula.append(itAtt.next().getLogicalFormula());
				
		}

		// L'iteratore per scorrere le funzioni
		Iterator<Function> itFunction = this.functions.iterator();
		// Finch� ci sono funzioni...
		while(itFunction.hasNext()) {
			// ...ne richiedo la descrizione della formula logica
			formula.append(itFunction.next().getLogicalFormula());
				
		}

		// Aggiunge la parentesi di chiusura della relazione
		formula.append(")");
		
		return formula.toString();
	}
	
	/**
	 * Questo metodo ritorna true se obj � uguale a questa relazione. <br />
	 * Due relazioni sono uguali se hanno: <br />
	 * <ul>
	 * 		<li>stesso nome</li>
	 * 		<li>stessi attributi</li>
	 * 		<li>stessi attributi chiave</li>
	 * 		<li>stessi attributi di chiave esterna</li>
	 * 		<li>stesse funzioni</li>
	 * 		<li>stessi attributi derivati</li>
	 * </ul>
	 * 
	 * @return true se obj � uguale a questa relazione
	 */
	public boolean equals(Object obj) {
		// Effettuo il cast di obj a relation
		Relation relation = (Relation)obj;
		
		// Il valore da restituire
		// Controlla se i nomi delle due relazioni sono uguali
		boolean uguali = this.getName().equals(relation.getName());
		
		// Controlla se gli attributi delle due relazioni sono uguali
		uguali &= this.getAttribute().values().containsAll(relation.getAttribute().values());
		
		// Controlla se gli attributi chiave delle due relazioni sono uguali
		uguali &= this.getAttributeKey().values().containsAll(relation.getAttributeKey().values());
		
		// Controlla se gli attributi di chiave esterna delle due relazioni sono uguali
		uguali &= this.getAttributeFkey().values().containsAll(relation.getAttributeFkey().values());
		
		// Controlla se le funzioni delle due relazioni sono uguali
		uguali &= this.getFunctions().containsAll(relation.getFunctions());
		
		// Controlla se gli attributi detivati delle due relaizioni sono uguali
		uguali &= this.getDerivatedAttribute().values().containsAll(relation.getDerivatedAttribute().values());
		
		return uguali;
		
	}

	public List<String> getAttsRealName() {
		return attsRealName;
	}

	public void addAttRealName(String attRealName) {
		this.attsRealName.add(attRealName);
	}
	
}