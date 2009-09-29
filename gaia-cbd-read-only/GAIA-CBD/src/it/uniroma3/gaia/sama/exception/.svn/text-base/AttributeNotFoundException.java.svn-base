package it.uniroma3.gaia.sama.exception;

/**
 * Questa classe rappresenta l'eccezione dovuta alla mancanza di un
 * attributo
 */
@SuppressWarnings("serial")
public class AttributeNotFoundException extends Exception {
	// Il nome dell'attributo
	private String name;
	private String relation;
	
	/**
	 * Crea un oggetto eccezione
	 * @param relationName il nome della relazione in cui si � verificata 
	 * l'eccezione
	 * @param attributeName il nome dell'attributo non trovato
	 */
	public AttributeNotFoundException(String relationName, String attributeName) {
		this.name = attributeName;
		this.relation = relationName;
		
	}

	/**
	 * Ritorna una descrizione testuale di questa eccezione
	 * @return la descrizione dell'errore
	 */
	public String toString() {
		return "Attribute " + this.name + " of relation " + this.relation + " not found in source";
	}

}
