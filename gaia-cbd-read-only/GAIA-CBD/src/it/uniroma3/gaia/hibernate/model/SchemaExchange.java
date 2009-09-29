package it.uniroma3.gaia.hibernate.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SchemaExchange extends GaiaBo {
	
	private String description;
	private SchemaExchangeType schemaExchangeType;
	private Collection<Atom> atoms;
	
	public Collection<Atom> getAtoms() {
		return atoms;
	}
	public void setAtoms(Collection<Atom> atoms) {
		this.atoms = atoms;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public SchemaExchangeType getSchemaExchangeType() {
		return schemaExchangeType;
	}
	public void setSchemaExchangeType(SchemaExchangeType schemaExchangeType) {
		this.schemaExchangeType = schemaExchangeType;
	}
	public void addAtoms(List<Atom> list){
		if(atoms==null){
			atoms = new ArrayList<Atom>();
		}
		for (Atom atom : list) {
			atom.setSchemaExchange(this);
			atoms.add(atom);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder message = new StringBuilder();
		message.append("\n\t" + getDescription());
		message.append(" SchemaExchangeType: " );
		message.append(getSchemaExchangeType().getSnRelNum() + "-");
		message.append(getSchemaExchangeType().getDxRelNum() + "\n");
//		for(Atom atomo: getAtoms()){
//			message.append("\t\t");
//			if(!atomo.getAtomType().getId().equals(AtomTypeEnum.RELATION.getId())){
//				message.append("\t");
//			}
//			message.append(atomo.toString());
//			
//			message.append("\n");
//		}
		message.append("\n");
		return message.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		boolean isEqual = false;
		if(o instanceof SchemaExchange){
			SchemaExchange other = (SchemaExchange) o;
			if(this.getSchemaExchangeType().equals(other.getSchemaExchangeType())
					&& this.getAtoms().size()==other.getAtoms().size()){
				isEqual = true;
				for(Atom a:this.getAtoms()){
					if(!other.getAtoms().contains(a)){
						isEqual = false;
						break;
					}
				}
			}
		}
		return isEqual;
	}
}
