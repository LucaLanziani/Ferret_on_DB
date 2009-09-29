package it.uniroma3.gaia.model;



/**
 * Questa classe rappresenta una condizione di uguaglianza. <br/>
 * Ogni condizione di uguaglianza � caratterizzata da:<br/>
 * <ul>
 * 	<li>un valore di uguaglianza</li>
 * </ul>
 * <br/>
 * Implementa l'interfaccia Condition.
 * @see Condition
 *
 */
public class SamenessCondition implements Condition {
	// Il valore che deve essere assunto dall'attributo affinch� gli si
	// possa applicare la condizione
	private String value;
	
	/**
	 * Crea una condizione di uguaglianza
	 */
	public SamenessCondition() {}
	
	/**
	 * Crea una condizione di uguaglianza
	 * @param value il valore di uguaglianza
	 */
	public SamenessCondition(String value)
	{
		this.value = value;
		
	}
	
	/**
	 * Questo metodo ritorna il valore di verifica della condizione
	 * @return il valore di verifica
	 */
	public String getValue() {
		return this.value;
		
	}
	
	/**
	 * Questo metodo imposta il valore per la condizione
	 * @param value il valore di uguaglianza per la condizione
	 */
	public void setValue(String value) {
		this.value = value;
		
	}
	
	/**
	 * Questo metodo ritorna una descrizone testuale di questa condizione di
	 * uguaglianza.
	 * @return una descrizione testuale di questa condizione di uguaglianza
	 */
	public String toString() {
		return " = " + this.value;
		
	}	
	
	/**
	 * Questo metodo restituisce una descrizione testuale della Tgd
	 * @param attributeName il nome dell'attributo a cui si applica questa
	 * condizione
	 * @return una descrizione testuale della tgd
	 */
	public String getTgd(String attributeName) {
		// La stringa da restituire
		StringBuffer descrizione = new StringBuffer(", (" + attributeName + " = '" + this.getValue() + "')");
		
		return descrizione.toString();
		
	}
	
	/**
	 * Questo metodo ritorna true se obj � uguale a questa condizione di uguaglianza. <br />
	 * Due condizioni di uguaglianza sono uguali se hanno stesso valore
	 * 
	 * @return true se obj � uguale a questa condizione
	 */
	public boolean equals(Object obj) {
		SamenessCondition condition = (SamenessCondition)obj;
		return condition.getValue().equals(this.getValue());
		
	}
	
}