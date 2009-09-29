package it.uniroma3.gaia.model;



import java.util.Iterator;

/**
 * 
 * Questa classe rappresenta un attributo di chiave esterna.<br/>
 * Ogni attributo di chiave esterna � caratterizzato:<br/>
 * <ul>
 * 	<li>da un nome</li>
 * 	<li>da relazione esterna</li>
 * </ul>
 * 
 * Estende la classe Attribute
 * @see Attribute
  */
public class AttributeFkey extends Attribute{
	// La relazione esterna
	private Relation relation;
	
	/**
	 * Crea un oggetto AttributeFkey
	 * @param attributeName il nome dell'attributo
	 * @param relation la relazione esterna a cui fa riferimento questa 
	 * chiave esterna
	 */
	public AttributeFkey(String attributeName, Relation relation) {
		// Crea l'oggetto attributo
		 super(attributeName);
		
		// Imposta la relazione
		this.relation = relation;
		
	}
	
	/**
	 * Questo metodo imposta la relazione a cui fa riferimento questa chiave
	 * @param relation la relazione esterna
	 */
	public void setRelation(Relation relation) {
			 // Imposta la relazione
			 this.relation = relation;
		
	}
	
	/**
	 * Questo metodo restituisce la relazione esterna cui fa riferimento questa
	 * chiave
	 * @return la relazione esterna
	 */
	public Relation getRelation() {
		return this.relation; 
		
	}
	
	/**
	 * Questo metodo restituisce una descrizione testuale della Tgd
	 * @param relationName il nome della relazione esterna
	 * @return una descrizione testuale della tgd 
	 */
	public String getTgd(String relationName) {
		// La stringa da restituire
		StringBuffer descrizione = new StringBuffer(", AttFkey(" + 
				this.getName() + ", " + relationName + ", " + 
				this.relation.getName() + ")");
		
		// L'iteratore per scorrere le condizioni
		Iterator<Condition> itCond = super.getConditions().iterator();

		// Finch� ci sono condizioni...
		while(itCond.hasNext()) {
			// ...gli richiedo la descrizione
			descrizione.append(itCond.next().getTgd(this.getName()));
		}
		
		return descrizione.toString();
		
	}
	
	/**
	 * Questo metodo ritorna true se obj � uguale a questo attributo di chiave esterna. <br />
	 * Due chiavi esterne sono uguali se hanno stesso nome e referenziano la stessa relazione
	 * 
	 * @return true se obj � uguale a questo attributo di chiave esterna
	 */
	public boolean equals(Object obj) {
		// Effettuo un cast di obj ad AttributeFKey
		AttributeFkey attribute = (AttributeFkey)obj;
		
		
		return attribute.getName().equals(this.getName()) && 
			this.getRelation().equals(attribute.getRelation());
		
	}
	
}