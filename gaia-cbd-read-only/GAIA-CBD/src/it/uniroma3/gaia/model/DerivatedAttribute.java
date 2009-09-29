package it.uniroma3.gaia.model;


/**
 * Questa classe rappresenta le condizioni di uguaglianza sugli
 * attributi derivati (lato target). <br/>
 * Esempio: <br/>
 * 		(nm = 'f(d)')
 * <br/>
 * Estende la classe Attribute.
 * @see Attribute
 *
 */

public class DerivatedAttribute extends Attribute {
	
	// La funzione
	Function function;
	
	/**
	 * Crea un attributo derivato
	 * @param functionName il nome della funzione applicata all'attributo
	 * @param attributeName il nome simbolico dell'attributo che rappresenta 
	 * l'attributo derivato
	 * @param referredAttName il nome reale dell'attributo a cui si applica 
	 * la funzione
	 */
	public DerivatedAttribute(String functionName, String attributeName, String referredAttName) {
		// Crea l'attributo
		super(attributeName);
		
		// Crea la funzione
		this.function = new Function(functionName, referredAttName);
		
	}
	
	/**
	 * Questo metodo restituisce una descrizione testuale della Tgd
	 * @param relationName il nome della relazione a cui appartiene questo 
	 * attributo derivato
	 * @return una descrizione testuale della tgd
	 */
	public String getTgd(String relationName) {
		return super.getTgd(relationName) + ", (" + this.getName() + 
			" = '" + this.function.getTgdForSchema() + "')";
		
	}
	
	/**
	 * Questo metodo ritorna true se obj � uguale a questo attributo derivato. <br />
	 * Due attributi derivati sono uguali se si riferiscono allo stesso attributo e se
	 * applicano la stessa funzione
	 * 
	 * @return true se obj � uguale a questo attributo derivato
	 */
	public boolean equals(Object obj) {
		DerivatedAttribute attribute = (DerivatedAttribute)obj;
		return super.equals(attribute) && this.getFunction().equals(attribute.getFunction());
		
	}
	
	/**
	 * Questo metodo restituisce la funzione applicata all'attributo
	 * @return la funzione applicata all'attributo
	 */
	public Function getFunction() {
		return this.function;
		
	}

}