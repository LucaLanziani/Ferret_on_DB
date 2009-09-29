package it.uniroma3.gaia.model;



import java.util.Iterator;
/**
 * 
 * Questa classe rappresenta un attributo chiave.<br/>
 * Ogni attributo chiave � caratterizzato da: <br/>
 * <ul>
 * 	<li>un nome</li>
 * </ul>
 * <br/>
 * Estende la classe Attribute.
 * @see Attribute
 */
public class AttributeKey extends Attribute{
	
	/**
	 * Crea un attributo chiave
	 * @param attributeName il nome di questo attributo
	 */
	public AttributeKey(String attributeName){
		// Crea l'attributo
	     super(attributeName);

	}
	
	/**
	 * Questo metodo restituisce una descrizione testuale della tgd
	 * @param relationName il nome della relazione di cui questo attributo � 
	 * chiave
	 * @return una descrizione testuale della tgd
	 */
	public String getTgd(String relationName) {
		// La stringa da restituire
		StringBuffer descrizione = new StringBuffer(", AttKey(" + this.getName() + ", " + relationName + ")");
		
		// L'iteratore per scorrere le condizioni
		Iterator<Condition> itCond = super.getConditions().iterator();
		// Finch� ci sono condizioni...
		while(itCond.hasNext()) {
			// ...gli richiede la descrizione della Tgd
			descrizione.append(itCond.next().getTgd(this.getName()));
		}
		
		return descrizione.toString();
		
	}
	
	/**
	 * Questo metodo restituisce una stringa che rappresenta la formula
	 * logica di questo attributo chiave
	 * @return la formula logica
	 */
	public String getLogicalFormula() {

		return this.getName();
		
	}

}