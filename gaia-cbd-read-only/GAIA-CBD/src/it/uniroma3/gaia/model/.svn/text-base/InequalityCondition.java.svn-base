package it.uniroma3.gaia.model;



/**
 * Questa classe rappresenta una condizione di disuguaglianza.<br/>
 * Ogni condizione di disuguaglianza � caratterizzata:<br/>
 * <ul>
 * 	<li>da un valore di disuguaglianza</li>
 * </ul><br/>
 * Implementa l'interfaccia Condition.
 * @see Condition
 *
 */
public class InequalityCondition implements Condition {	
	// Il valore che deve essere assunto dall'attributo affinch� gli si
	// possa applicare la condizione
	private String value;
	
	/**
	 * Crea un oggetto InequalityCondition
	 * @param value il valore di disuguaglianza per questa condizione 
	 */
	public InequalityCondition(String value)
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
	 * @param value il valore per la condizione
	 */
	public void setValue(String value) {
		this.value = value;
		
	}
	
	/**
	 * Questo metodo restituisce una rappresentazione testuale di questa
	 * condizione
	 * @return una descrizione testuale di questa condizione di disuguaglianza 
	 */
	public String toString() {
		return "<> " + this.value;
		
	}	
	
	/**
	 * Questo metodo restituisce una descrizione testuale della Tgd
	 * @param attributeName il nome dell'attributo a cui si applica questa 
	 * condizione
	 * @return una descrizione testuale della tgd
	 */
	public String getTgd(String attributeName) {
		// La stringa da restituire
		StringBuffer descrizione = new StringBuffer(", (" + attributeName + " <> '" + this.getValue() + "')");
		
		return descrizione.toString();
		
	}
	
	/**
	 * Questo metodo ritorna true se obj � uguale a questa condizione di disuguaglianza. <br />
	 * Due condizioni di diuguaglianza sono uguali se hanno stesso valore
	 * 
	 * @return true se obj � uguale a questa condizione
	 */
	public boolean equals(Object obj) {
		InequalityCondition condition = (InequalityCondition)obj;
		return condition.getValue().equals(this.getValue());
		
	}
	
}