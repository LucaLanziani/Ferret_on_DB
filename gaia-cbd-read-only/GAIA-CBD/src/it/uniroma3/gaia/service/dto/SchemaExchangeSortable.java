package it.uniroma3.gaia.service.dto;

import it.uniroma3.gaia.hibernate.model.SchemaExchange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class SchemaExchangeSortable extends SchemaExchange implements Comparable<SchemaExchangeSortable>{
	protected static final Logger log = Logger.getLogger(SchemaExchangeSortable.class);
	private List<SchemaExchangeSortable> sons;
	private Map<Integer, MatchingData> matchingDatas;
	private boolean isRoot;
	
	public SchemaExchangeSortable(SchemaExchange se){
		this.setAtoms(se.getAtoms());
		this.setDescription(se.getDescription());
		this.setId(se.getId());
		this.setSchemaExchangeType(se.getSchemaExchangeType());
		this.sons = new ArrayList<SchemaExchangeSortable>();
		this.setIsRoot(true);
	}

	public boolean isRoot() {
		return isRoot;
	}

	public void setIsRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public Map<Integer, MatchingData> getMatchingDatas() {
		return matchingDatas;
	}

	public void setMatchingDatas(Map<Integer, MatchingData> matchingDatas) {
		this.matchingDatas = matchingDatas;
	}

	public List<SchemaExchangeSortable> getSons() {
		return sons;
	}

	public void setSons(List<SchemaExchangeSortable> sons) {
		this.sons = sons;
	}
	
	public void addSon(SchemaExchangeSortable son){
		this.sons.add(son);
	}
	
	@Override
	public String toString() {
		StringBuilder message = new StringBuilder(super.toString());
		if(isRoot){
			message.append("\t root;\n");
		}
		if(!sons.isEmpty()){
			message.append("\t Sons: ");
			for(SchemaExchangeSortable ses: sons){
				message.append(ses.getId() + "-");
			}
			message.append("\n");
		}
		return message.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		boolean isEqual = false;
		if(o instanceof SchemaExchangeSortable){
			SchemaExchangeSortable other = (SchemaExchangeSortable)o;
			if(this.isRoot()==other.isRoot()){
				
				List<MatchingData> listaThis = new ArrayList<MatchingData>(this.getMatchingDatas().values());
				List<MatchingData> listaOther = new ArrayList<MatchingData>(other.getMatchingDatas().values());
				Collections.sort(listaThis);
				Collections.sort(listaOther);
				if(listaThis.size()==listaOther.size()){
					for(int i=0; i<listaThis.size(); i++){
						if(listaThis.get(i).equals(listaOther.get(i))){
							isEqual = true;
						}else{
							isEqual = false;
							log.debug(this.getDescription() + "-" + other.getDescription() + ": " + "Uno dei matching data non è uguale nei due schema exchange");
							break;
						}
					}
					if(isEqual && this.getSons().size()==other.getSons().size()){
						Collections.sort(this.getSons());
						Collections.sort(other.getSons());
						for(int index=0; index<this.getSons().size(); index++){
							if(!this.getSons().get(index).equals(other.getSons().get(index))){
								isEqual = false;
								log.debug(this.getDescription() + "-" + other.getDescription() + ": " + "Uno dei figli non è uguale nei due schema exchange");
								break;
							}
						}
					}else{
						isEqual = false;
						log.debug(this.getDescription() + "-" + other.getDescription() + ": " + "I due schema exchange hanno liste di figli di cardinalità diversa");
					}
				}
			}else{
				log.debug(this.getDescription() + "-" + other.getDescription() + ": " + "I due schema exchange non sono entrambi root");
			}
		}
		return isEqual;
	}
	
	@Override
	public int compareTo(SchemaExchangeSortable o) {
		int result = this.getSchemaExchangeType().compareTo(o.getSchemaExchangeType());
		if(result==0){
			List<MatchingData> listaThis = new ArrayList<MatchingData>(this.getMatchingDatas().values());
			List<MatchingData> listaOther = new ArrayList<MatchingData>(o.getMatchingDatas().values());
			Collections.sort(listaThis);
			Collections.sort(listaOther);
			for(int index=0; index<listaThis.size(); index++){
				result += listaThis.get(index).compareTo(listaOther.get(index));
			}
		}
		return result;
	}
}
