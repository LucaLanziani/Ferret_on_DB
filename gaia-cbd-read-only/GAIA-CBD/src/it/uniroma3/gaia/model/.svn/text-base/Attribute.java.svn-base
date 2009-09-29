package it.uniroma3.gaia.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 * 
 * Questa classe rappresenta un attributo.<br/>
 * Ogni attributo è caratterizzato da:<br/>
 * <ul>
 * 	<li>un nome</li>
 * 	<li>una lista di condizioni</li>
 * </ul>
 *
 */
public class Attribute implements Comparable<Attribute>{
	
	// Il nome dell' attributo
	private String name;
	
	// L'insieme delle condizioni per questo attributo
	private List<Condition> conditions;
	
	/**
	 * Crea un oggetto Attribute
	 * @param attributeName il nome dell'attributo
	 */
	public Attribute(String attributeName){
		// Imposta il nome
		this.name = attributeName;
		
		// Crea la lista delle condizioni
		this.conditions = new LinkedList<Condition>();
		
	}
	
	/**
	 * Questo metodo imposta il nome di questo attributo
	 * @param attributeName il nome dell'attributo
	 */
	public void setName(String attributeName){
		this.name = attributeName;
		
	}
	
	/**
	 * Questo metodo ritorna il nome di questo attributo
	 * @return il nome di questo attributo
	 */
	public String getName() {
		return this.name;
		
	}
	
	/**
	 * Questo metodo restituisce una descrizione testuale di questo attributo
	 * @return una descrizione testuale di questo attributo
	 */
	public String toString() {
		return this.name;
		
	}
	
	/**
	 * Questo metodo ritorna la lista delle condizioni relative a questo
	 * attributo
	 * @return la lista delle condizioni
	 */
	public List<Condition> getConditions() {
		return this.conditions;
		
	}
	
	/**
	 *  Questo metodo aggiunge una condizione di uguaglianza
	 *  alla lista delle condizioni per questo attributo
	 *  @param value il valore di uguaglianza della condizione da aggiungere
	 */
	public void addSamenessConditions(String value){
		this.conditions.add(new SamenessCondition(value));
		
	}
	
	/**
	 *  Questo metodo aggiunge una condizione di disuguaglianza
	 *  alla lista delle condizioni per questo attributo
	 *  @param value il valore di disuguaglianza della condizione da 
	 *  aggiungere
	 */
	public void addInequalityConditions(String value){
		this.conditions.add(new InequalityCondition(value));
		
	}
	
	/**
	 * Questo metodo restituisce una descrizione testuale della Tgd
	 * @param relationName il nome della relazione a cui appartiene questo 
	 * attributo
	 * @return una descrizione testuale della tgd
	 */
	public String getTgd(String relationName) {
		// La stringa da restituire
		StringBuffer descrizione = new StringBuffer(", Att(" + this.getName() + ", " + relationName + ")");
		
		// L'iteratore per scorrere le condizioni
		Iterator<Condition> itCond = this.conditions.iterator();
		
		// Funch� ci sono condizioni...
		while(itCond.hasNext()) {
			// ...richiede la Tgd all'attributo corrente
			descrizione.append(itCond.next().getTgd(this.getName()));
		}
		
		return descrizione.toString();
		
	}
	
	/**
	 * Questo metodo restituisce la descrizione testuale per la Tgd nel caso
	 * in cui si stia richiedendo la Tgd testuale di uno SchemaExchange
	 * @param functionDesc la descrizione della funzione applicata a questo 
	 * attributo
	 * @param relationName il nome della relazione a cui appartiene questo 
	 * attributo
	 * @return una descrizione testuale della tgd
	 */
	public String getTgdForSchema(String functionDesc, String relationName) {
		return ", Att(" + functionDesc + ", " + relationName + ")";
		
	}

	/**
	 * Questo metodo restituisce un valore che indica se due attributi sono
	 * uguali.
	 * Due attributi sono uguali se hanno stesso nome e stesse condizioni
	 * @return true se obj è uguale a questo attributo
	 */
	public boolean equals(Object obj) {
		// Effettuo un cast da Object ad Attribute
		Attribute attribute = (Attribute)obj;
		
		return attribute.getName().equals(this.getName()) &&
			(this.conditions.size() == attribute.getConditions().size()) &&
			this.getConditions().containsAll(attribute.getConditions());
		
	}

	
	/**
	 * Questo metodo restituisce una stringa che rappresenta la formula
	 * logica di questo attributo
	 * @return la formula logica
	 */
	public String getLogicalFormula() {
		// La stringa da restituire
		String formula = ", " + this.getName();

		return formula;
		
	}

	@Override
	public int compareTo(Attribute o) {
		return this.getName().compareTo(o.getName());
	}
	
}