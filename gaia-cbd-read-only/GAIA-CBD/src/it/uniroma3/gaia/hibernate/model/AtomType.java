package it.uniroma3.gaia.hibernate.model;

public class AtomType extends GaiaBo {
	
	private String description;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		if(obj!=null && obj instanceof AtomType){
			AtomType at = (AtomType) obj;
			if(at.getId().equals(this.getId())){
				isEqual = true;
			}
		}
		return isEqual;
	}
}
