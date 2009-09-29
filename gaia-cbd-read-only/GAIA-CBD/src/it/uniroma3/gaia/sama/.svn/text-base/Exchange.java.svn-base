package it.uniroma3.gaia.sama;
import it.uniroma3.gaia.model.Attribute;
import it.uniroma3.gaia.model.AttributeKey;
import it.uniroma3.gaia.model.Relation;
import it.uniroma3.gaia.sama.exception.RelationNotFoundException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Questa classe rappresenta un generico exchange.<br/>
 * Ogni exchange � caratterizzato da:<br/>
 * <ul>
 * 	<li>un nome</li>
 * 	<li>un insieme di relazioni source</li>
 * 	<li>un insieme di relazioni target</li>
 * </ul>
 *
 */
public abstract class Exchange {
	// Il nome del exchange
	private String name;
	
	// L'insieme delle relazioni lato source
	// Contiene coppie del tipo
	//		<RelationName, RelationObject>
	protected Map<String, Relation> source;
	
	// L'insieme delle relazioni lato target
	// Contiene coppie del tipo
	// 		<RelationName, RelationObject>
	protected Map<String, Relation> target;

	/**
	 * Crea un nuovo exchange
	 * @param name il nome dell'exchange
	 */
	public Exchange(String name) {
		// Imposta il nome
		this.name = name;
		// Crea l'insieme delle relazioni lato source
		this.source = new HashMap<String, Relation>();
		// Crea l'insieme delle relazioni lato target
		this.target = new HashMap<String, Relation>();
		
	}
	
	/**
	 * Questo metodo imposta il nome di questo exchange
	 * @param name il nome dell'exchange
	 */
	public void setName(String name){
		// Imposta il nome dell'exchange
		this.name = name;
		
	}
	
	/**
	 * Questo metodo restituisce il nome di questo exchange
	 * @return il nome dell'exchange
	 */
	public String getName(){
		// Restituisce il nome dell'exchange
		return this.name;
		
	}
	
	/**
	 * Questo metodo aggiunge una relazione all'insieme delle relazioni del 
	 * source
	 * @param relationName il nome della relazione da aggiungere
	 */
	public void addRelationToSource(String relationName) {
		this.source.put(relationName, new Relation(relationName));
		
	}
	
	/**
	 * Questo metodo aggiunge un attributo ad una specifica relazione del 
	 * source
	 * @param relationName il nome della relazione a cui aggiungere 
	 * l'attributo
	 * @param attributeName il nome dell'attributo da aggiungere
	 */
	public void addAttributeToSource(String relationName, String attributeName) {
			this.source.get(relationName).addAttribute(attributeName);
		
	}
	
	/**
	 * Questo metodo aggiunge un attributo chiave ad una specifica relazione
	 * del source
	 * @param relationName il nome della relazione  a cui aggiungere 
	 * l'attributo chiave
	 * @param attributeName il nome dell'attributo chiave da aggiungere 
	 */
	public void addAttributeKeyToSource(String relationName, String attributeName) {
		this.source.get(relationName).addAttributeKey(attributeName);
		
	}
	
	/**
	 * Questo metodo aggiunge un attributo di chiave esterna ad una specifica
	 * relazione lato source
	 * @param relationName1 il nome della relazione in cui � definita la 
	 * chiave esterna
	 * @param relationName2 il nome della relazione referenziata dalla 
	 * chiave esterna da inserire
	 * @param attributeName il nome dell'attributo di chiave esterna da 
	 * aggiungere
	 * @throws RelationNotFoundException
	 */
	public void addAttributeFkeyToSource(String relationName1, String relationName2, String attributeName) throws RelationNotFoundException {
		if(this.source.get(relationName2) == null)
			throw new RelationNotFoundException(relationName2, "source");

		this.source.get(relationName1).addAttributeFKey(attributeName, this.source.get(relationName2));
		
	}
	
	/**
	 * Questo metodo aggiunge una condizione di uguaglianza ad uno specifico
	 * attributo di una specifica relazione del source
	 * @param relationName il nome della relazione
	 * @param attributeName il nome dell'attributo a cui aggiungere la 
	 * condizione di uguaglianza
	 * @param value il valore da applicare all'uguaglianza
	 */
	public void addSamenessConditionsToAttributeInSource(String relationName, String attributeName, String value) {
		this.source.get(relationName).addSamenessConditionsToAttributes(attributeName, value);
		
	}
	
	public void setSource(Map<String, Relation> source) {
		this.source = source;
	}

	public void setTarget(Map<String, Relation> target) {
		this.target = target;
	}

	/**
	 * Questo metodo aggiunge una condizione di uguaglianza ad una specifica
	 * chiave di una specifica relazione del source
	 * @param relationName il nome della relazione
	 * @param keyName il nome della chiave a cui aggiungere la 
	 * condizione di uguaglianza
	 * @param value il valore da applicare all'uguaglianza
	 */
	public void addSamenessConditionsToKeyInSource(String relationName, String keyName, String value) {
		this.source.get(relationName).addSamenessConditionsToKey(keyName, value);
		
	}
	
	/**
	 * Questo metodo aggiunge una condizione di uguaglianza ad una specifica
	 * chiave esterna di una specifica relazione del source
	 * @param relationName il nome della relazione
	 * @param fkeyName il nome della chiave esterna a cui aggiungere la 
	 * condizione di uguaglianza
	 * @param value il valore da applicare all'uguaglianza
	 */
	public void addSamenessConditionsToFkeyInSource(String relationName, String fkeyName, String value) {
		this.source.get(relationName).addSamenessConditionsToFkey(fkeyName, value);
		
	}

	/**
	 * Questo metodo aggiunge una condizione di disuguaglianza ad uno 
	 * specifico attributo di una specifica relazione del target
	 * @param relationName il nome della relazione
	 * @param attributeName il nome dell'attributo a cui aggiungere la 
	 * condizione di disuguaglianza
	 * @param value il valore da applicare alla disuguaglianza
	 */
	public void addInequalityConditionsToAttributeInSource(String relationName, String attributeName, String value) {
		this.source.get(relationName).addInequalityConditionsToAttributes(attributeName, value);
		
	}
	
	/**
	 * Questo metodo aggiunge una relazione all'insieme delle relazioni del 
	 * target
	 * @param relationName il nome della relazione da aggiungere
	 */
	public void addRelationToTarget(String relationName) {
		this.target.put(relationName, new Relation(relationName));
		
	}
	
	/**
	 * Questo metodo aggiunge un attributo ad una specifica relazione del 
	 * target
	 * @param relationName il nome della relazione a cui aggiungere 
	 * l'attributo
	 * @param attributeName il nome dell'attributo da aggiungere
	 */
	public void addAttributeToTarget(String relationName, String attributeName) {
		this.target.get(relationName).addAttribute(attributeName);
		
	}
	
	/**
	 * Questo metodo aggiunge un attributo chiave ad una specifica relazione
	 * del target
	 * @param relationName il nome della relazione  a cui aggiungere 
	 * l'attributo chiave
	 * @param attributeName il nome dell'attributo chiave da aggiungere 
	 */
	public void addAttributeKeyToTarget(String relationName, String attributeName) {
		this.target.get(relationName).addAttributeKey(attributeName);
		
	}
	
	/**
	 * Questo metodo aggiunge un attributo di chiave esterna ad una specifica
	 * relazione lato target
	 * @param relationName1 il nome della relazione in cui � definita la 
	 * chiave esterna
	 * @param relationName2 il nome della relazione referenziata dalla chiave
	 * esterna da inserire
	 * @param attributeName il nome dell'attributo di chiave esterna da 
	 * aggiungere
	 * @throws RelationNotFoundException
	 */
	public void addAttributeFkeyToTarget(String relationName1, String relationName2, String attributeName) throws RelationNotFoundException {
		if(this.target.get(relationName2) == null)
			throw new RelationNotFoundException(relationName2, "target");	
		//TODO controllare probabile errore
//		this.target.get(relationName1).addAttributeFKey(attributeName, this.source.get(relationName2));
		this.target.get(relationName1).addAttributeFKey(attributeName, this.target.get(relationName2));
		
	}
	
	/**
	 * Questo metodo aggiunge una funzione ad uno specifico attributo di una
	 * specifica relazione del target
	 * @param relationName il nome della relazione
	 * @param functionName il nome della funzione da aggiungere
	 * @param attributeName il nome dell'attributo a cui applicare la funzione
	 */
	public void addFunctionToTarget(String relationName, String functionName, String attributeName) {
		this.target.get(relationName).addFunction(attributeName, functionName);
		
	}
	
	/**
	 * Questo metodo restituisce una mappa contenente tutti 
	 * gli attributi delle relazioni nel source.<br/>
	 * Tale mappa � organizzata nel seguente modo<br/>
	 * 		(AttributeName, RelationName) <br/>
	 * in cui RelationName � il nome della relazione a cui appartiene 
	 * l'attributo corrispondente.
	 * @return la mappa con la corrispondenza tra attributo e relazione a cui
	 * appartiene 
	 */
	public Map<String,String> getAllAttributesOfSource() {
		// La mappa da restituire
		Map<String, String> attributes = new HashMap<String, String>();
		
		// Itera sul source
		Iterator<Relation> itSource = this.source.values().iterator();
		// Finch� ci sono relazioni...
		while(itSource.hasNext()) {
			// ...aggiunge gli attributi della relazione corrente alla mappa
			// degli attributi
			// Un riferimento alla relazione corrente
			Relation relCorrente = itSource.next();
			// Itera sugli attributi
			Iterator<Attribute> attributi = relCorrente.getAttribute().values().iterator();
			// Finch� ci sono attributi...
			while(attributi.hasNext()) {
				// ...copia un riferimento all'attributo corrente 
				Attribute att = attributi.next();
				// Aggiungo alla mappa da restituire il nome dell'attributo e
				// il nome della relazione
				attributes.put(att.getName(), relCorrente.getName());
			}			
		}
		
		return attributes;
		
	}
	
	/**
	 * Questo metodo restituisce una mappa contenente tutti 
	 * gli attributi delle relazioni nel target. <br/>
	 * Tale mappa � organizzata nel seguente modo <br/>
	 * 			(AttributeName, RelationName)  <br/>
	 * 	in cui RelationName � il nome della relazione a cui appartiene 
	 * l'attributo corrispondente
	 * @return la mappa con la corrispondenza tra attributo e relazione a cui appartiene 
	 */
	public Map<String,String> getAllAttributesOfTarget() {
		// La mappa da restituire
		Map<String, String> attributes = new HashMap<String, String>();
		
		// Itera sul source
		Iterator<Relation> itTarget = this.target.values().iterator();
		// Finch� ci sono relazioni...
		while(itTarget.hasNext()) {
			// ...aggiunge gli attributi della relazione corrente alla mappa
			// degli attributi
			// Un riferimento alla relazione corrente
			Relation relCorrente = itTarget.next();
			// Itera sugli attributi
			Iterator<Attribute> attributi = relCorrente.getAttribute().values().iterator();
			// Finch� ci sono attributi...
			while(attributi.hasNext()) {
				// ...copia un riferimento all'attributo corrente 
				Attribute att = attributi.next();
				attributes.put(att.getName(), relCorrente.getName());
			}			
		}
		
		return attributes;
	}
	
	/**
	 * Questo metodo restituisce una mappa contenente tutti 
	 * gli attributi chiave delle relazioni nel source. <br/>
	 * Tale mappa � organizzata nel seguente modo <br/>
	 * 			(AttributeKeyName, RelationName) <br/>
	 * 	in cui RelationName � il nome della relazione a cui appartiene 
	 * l'attributo chiave corrispondente
	 * @return la mappa con la corrispondenza tra attributo chiave e
	 * relazione a cui appartiene 
	 */
	public Map<String,String> getAllKeyOfSource() {
		// La mappa da restituire
		Map<String,String> keys = new HashMap<String, String>();
		
		// Itera sul source
		Iterator<Relation> itSource= this.source.values().iterator();
		// Finch� ci sono relazioni...
		while(itSource.hasNext()) {
			// Un riferimento alla relazine corrente
			Relation relCorrente = itSource.next();
			// Itera sulle chiavi
			Iterator<AttributeKey> chiavi = relCorrente.getAttributeKey().values().iterator();
			// Finch� ci sono chiavi...
			while(chiavi.hasNext()) {
				// L'attributo chiave corrente
				AttributeKey att = chiavi.next();
				// ...aggiunge gli attributi chiave della relazione corrente alla 
				// mappa delle chiavi
				keys.put(att.getName(), relCorrente.getName());
				
			}
			
		}
		
		return keys;
		
	}
	
	/**
	 * Questo metodo restituisce una mappa contenente tutti 
	 * gli attributi chiave delle relazioni nel target. <br/>
	 * Tale mappa � organizzata nel seguente modo <br/>
	 * 			(AttributeKeyName, RelationName) <br/>
	 * in cui RelationName � il nome della relazione a cui appartiene 
	 * l'attributo chiave corrispondente.
	 * @return la mappa con la corrispondenza tra attributo chiave e 
	 * relazione a cui appartiene 
	 */
	public Map<String,String> getAllKeyOfTarget() {
		// La mappa da restituire
		Map<String,String> keys = new HashMap<String, String>();
		
		// Itera sul source
		Iterator<Relation> itTarget= this.target.values().iterator();
		// Finch� ci sono relazioni...
		while(itTarget.hasNext()) {
			// Un riferimento alla relazine corrente
			Relation relCorrente = itTarget.next();
			// Itera sulle chiavi
			Iterator<AttributeKey> chiavi = relCorrente.getAttributeKey().values().iterator();
			// Finch� ci sono chiavi...
			while(chiavi.hasNext()) {
				// L'attributo chiave corrente
				AttributeKey att = chiavi.next();
				// ...aggiunge gli attributi chiave della relazione corrente alla 
				// mappa delle chiavi
				keys.put(att.getName(), relCorrente.getName());
			}
			
		}
		
		return keys;
		
	}
	
	/**
	 * Questo metodo restituisce la mappa delle relazioni del source. <br/>
	 * Tale mappa � organizzata nel seguente modo <br/>
	 * 			(RelationName, RelationObject)
	 * @return la mappa delle relazioni del source
	 */
	public Map<String, Relation> getRelationOfSource() {
		return this.source;
		
	}
	
	/**
	 * Questo metodo restituisce la mappa delle relazioni del target. <br/>
	 * Tale mappa � organizzata nel seguente modo <br/>
	 * 			(RelationName, RelationObject)
	 * @return la mappa delle relazioni del target
	 */
	public Map<String, Relation> getRelationOfTarget() {
		return this.target;
		
	}
	
	/**
	 * Questo metodo restituisce la tgd dell'exchange
	 * @return una descrizione testuale della tgd
	 */
	public String getTgd() {
		// La stringa da restituire
		StringBuffer descrizione = new StringBuffer();
		// L'iteratore per scorrere nelle relazioni
		Iterator<Relation> itRel;
		
		// Itera sulle relazioni del source
		itRel = this.source.values().iterator();
		// Finch� ci sono relazioni...
		while (itRel.hasNext()) {
			// ...richiede la descrizione della Tgd alla relazione corrente...
			descrizione.append(itRel.next().getTgd());
			
			// ...se ci sono altre relazioni, aggiunge una virgola
			if(itRel.hasNext())
				descrizione.append(", ");
		}
		
		// Aggiunge la freccia
		descrizione.append(" -> ");
		
		// Itera sulle relazioni del target
		itRel = this.target.values().iterator();
		
		// Finch� ci sono relazioni...
		while (itRel.hasNext()) {
			// ...richiede la descrizione della Tgd alla relazione corrente...
			descrizione.append(itRel.next().getTgd());

			// ...se ci sono altre relazioni, aggiunge una virgola
			if(itRel.hasNext())
				descrizione.append(", ");
		}
		
		// Restituisco la descrizione testuale della tgd
		return descrizione.toString();
		  
	}
	
	/**
	 * Questo metodo ritorna true se obj � uguale a questo exchange. <br />
	 * Due exchange sono uguali se hanno stesso relazioni lato source e stesse
	 * relazioni lato target
	 * 
	 * @return true se obj � uguale a questa condizione
	 */
	public boolean equals(Object obj) {
		// Effettua un cast di obj ad exchange
		Exchange ex = (Exchange) obj;
		
		// Il valore da restituire
		boolean uguali;
		
		// Verifica l'uguaglianza delle relazioni del source
		uguali = this.getRelationOfSource().values().containsAll(ex.getRelationOfSource().values());
		
		// verifica l'uguaglianza delle relazioni del target
		uguali &= this.getRelationOfTarget().values().containsAll(ex.getRelationOfTarget().values());
		
		return uguali;		 
		
	}
	
}