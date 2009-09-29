package it.uniroma3.gaia.model;


/**
 * 
 * Questa classe rappresenta una funzione. <br/>
 * Ogni funzione � caratterizzato da:<br/>
 * <ul>
 * 	<li>un nome</li>
 * 	<li>l'attributo a cui si applica</li>
 * </ul>
 */


public class Function {
	// Il nome della funzione
	private String name;
	// L'attributo a cui fa riferimento
	private Attribute attribute;
	
	/**
	 * Questo metodo crea una nuova funzione
	 * @param functionName il nome della funzione
	 * @param attributeName il nome dell'attributo a cui si applica la 
	 * funzione
	 */
	public Function(String functionName, String attributeName) {
		// Crea l'attributo
		this.attribute = new Attribute(attributeName);
		// Imposta il nome
		this.name = functionName;
		
	}
		
	/**
	 * Questo metodo imposta il nome di questa funzione
	 * @param functionName il nome della funzione
	 */
	public void setFunctionName(String functionName){
		this.name = functionName;
	}
	
	/**
	 * Questo metodo restituisce il nome di questa funzione
	 * @return il nome di questa funzione
	 */
	public String getFunctionName() {
		return this.name; 
	}
	
	/**
	 * Questo metodo restituisce il nome dell'attributo a cui si applica
	 * questa funzione
	 * @return il nome dell'attributo
	 */
	public String getAttributeName() {
		return this.attribute.getName();
		
	}
	
	/**
	 * Questo metodo restituisce l'attributo a cui si applica questo 
	 * attributo
	 * @return l'attributo
	 */
	public Attribute getAttribute() {
		return this.attribute;
		
	}
	
	/**
	 * Ritorna una descrizione testuale di questo attributo
	 * @return una descrizione testuale di questa funzione
	 */
	public String toString(){	
		return this.name;
		
	}
	
	/**
	 * Questo metodo verifica se due funzioni sono uguali. <br />
	 * Due funzioni sono uguali se hanno stesso nome e se
	 * si applicano allo stesso attributo.
	 * @return true se obj � uguale a questa funzione
	 */
	public boolean equals(Object obj) {
		Function funzione = (Function)obj;
		return funzione.getFunctionName().equals(this.getFunctionName()) &&
			funzione.getAttribute().equals(this.getAttribute());
		
	}
	
	/**
	 * Questo metodo restituisce una descrizione testuale della Tgd
	 * relativa ad uno schema exchange
	 * @return una descrizione testuale della Tgd
	 */
	public String getTgdForSchema() {
		return this.name + "(" + this.attribute.getName() + ")";
		
	}
	
	/**
	 * Questo metodo restituisce una descrizione testuale della Tgd
	 * @param relationName il nome della relazione a cui appartiene 
	 * l'attributo a cui � stata applicata la funzione
	 * @return la descrizione testuale della Tgd
	 */
	public String getTgdForData(String relationName) {
		return this.attribute.getTgdForSchema(this.name + 
				"(" + this.attribute.getName() + ")", relationName);
		
	}
	
	/**
	 * Questo metodo restituisce una stringa che rappresenta la formula
	 * logica di questa relazione
	 * @return la formula logica
	 */
	public String getLogicalFormula() {
		// La stringa da restituire
		StringBuffer formula = new StringBuffer(", " + this.name + "(");
		
		formula.append(this.attribute.getName());
		
		formula.append(")");
		
		return formula.toString();
	}
		
}