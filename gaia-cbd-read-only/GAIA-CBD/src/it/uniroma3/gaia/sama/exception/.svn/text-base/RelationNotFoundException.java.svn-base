package it.uniroma3.gaia.sama.exception;

/**
 * Questa classe rappresenta l'eccezione dovuta alla mancanza di una
 * relazione
 */
@SuppressWarnings("serial")
public class RelationNotFoundException extends Exception {
	
	// Il nome della relazione
	String relation;
	// Il lato della tgd
	String st;
	
	/**
	 * Crea un oggetto eccezione
	 * @param relationName il nome della relazione non trovata
	 * @param st della tgd nel quale si verifica l'eccezione (source | target)
	 */
	public RelationNotFoundException(String relationName, String st) {
		this.relation = relationName;
		this.st = st;
		
	}
	
	/**
	 * Ritorna una descrizione testuale di questa eccezione
	 * @return la descrizione dell'errore
	 */
	public String toString() {
		return "Relation " + this.relation + " on " + this.st + " not found";
		
	}

}
