package it.uniroma3.gaia.service.dto;

import it.uniroma3.gaia.hibernate.model.AtomType;

public class EqualityArcType implements Comparable<EqualityArcType> {
	AtomType tipoSource;
	AtomType tipoTarget;
	
	public EqualityArcType(AtomType tipoSource, AtomType tipoTarget){
		this.tipoSource = tipoSource;
		this.tipoTarget = tipoTarget;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean result = false;
		if(o instanceof EqualityArcType){
			EqualityArcType e = (EqualityArcType)o;
			if(this.tipoSource.equals(e.tipoSource) && this.tipoTarget.equals(e.tipoTarget)){
				result = true;
			}
		}
		return result;
	}
	
	@Override
	public int compareTo(EqualityArcType o){
		int result;
		
		if(this.equals(o)){
			result = 0;
		}else if(this.tipoSource.getId().compareTo(o.tipoSource.getId())>0
				|| (this.tipoSource.getId().equals(o.tipoSource.getId()) &&
						this.tipoTarget.getId().compareTo(o.tipoTarget.getId())>0)
			){
			result = 1;
		}else{
			result = -1;
		}
		
		return result;
	}
}
