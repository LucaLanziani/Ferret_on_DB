package it.uniroma3.gaia.service.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;



public class MatchingData implements Comparable<MatchingData>{
	private List<Integer> fkRefIds;
	private Integer numAtts;
	private Integer numKeys;
	private Integer numFkeys;
	private Integer incomingFkeyNum;
	private Map<EqualityArcType, Integer> incomingEqualsNumMap;
	private Map<EqualityArcType, Integer> outgoingEqualsNumMap;
	private Integer incomingEqualsNum;
	private Integer outgoingEqualsNum;
	private String side;
	private List<String> attributeConditions;
	private List<String> attributeInequalityConditions;
	private List<String> fkeyConditions;
	private List<String> fkeyInequalityConditions;
	private List<String> keyConditions;
	private List<String> keyInequalityConditions;
	
	private List<String> attributeRealNames;
	private List<String> keyRealNames;
	private List<String> fkeyRealNames;
	private Integer relId;

	public MatchingData(){
		this.numAtts = 0;
		this.numKeys = 0;
		this.numFkeys = 0;
		this.incomingFkeyNum = 0;
		this.incomingEqualsNum = 0;
		this.outgoingEqualsNum = 0;
		this.incomingEqualsNumMap = new HashMap<EqualityArcType, Integer>();
		this.outgoingEqualsNumMap = new HashMap<EqualityArcType, Integer>();
		this.fkRefIds = new ArrayList<Integer>();
		this.attributeConditions = new ArrayList<String>();
		this.attributeInequalityConditions = new ArrayList<String>();
		this.fkeyConditions = new ArrayList<String>();
		this.fkeyInequalityConditions = new ArrayList<String>();
		this.keyConditions = new ArrayList<String>();
		this.keyInequalityConditions = new ArrayList<String>();
		this.attributeRealNames = new ArrayList<String>();
		this.keyRealNames = new ArrayList<String>();
		this.fkeyRealNames = new ArrayList<String>();
	}
	
	public List<String> getAttributeRealNames() {
		return attributeRealNames;
	}

	public void setAttributeRealNames(List<String> attributeRealNames) {
		this.attributeRealNames = attributeRealNames;
	}

	public List<String> getKeyRealNames() {
		return keyRealNames;
	}

	public void setKeyRealNames(List<String> keyRealNames) {
		this.keyRealNames = keyRealNames;
	}

	public List<String> getFkeyRealNames() {
		return fkeyRealNames;
	}

	public void setFkeyRealNames(List<String> fkeyRealNames) {
		this.fkeyRealNames = fkeyRealNames;
	}

	public List<String> getAttributeInequalityConditions() {
		return attributeInequalityConditions;
	}

	public void setAttributeInequalityConditions(
			List<String> attributeInequalityConditions) {
		this.attributeInequalityConditions = attributeInequalityConditions;
	}
	public List<String> getFkeyInequalityConditions() {
		return fkeyInequalityConditions;
	}

	public void setFkeyInequalityConditions(
			List<String> fkeyInequalityConditions) {
		this.fkeyInequalityConditions = fkeyInequalityConditions;
	}
	public List<String> getKeyInequalityConditions() {
		return keyInequalityConditions;
	}

	public void setKeyInequalityConditions(
			List<String> keyInequalityConditions) {
		this.keyInequalityConditions = keyInequalityConditions;
	}

	public List<String> getAttributeConditions() {
		return attributeConditions;
	}

	public void setAttributeConditions(List<String> attributeConditions) {
		this.attributeConditions = attributeConditions;
	}

	public List<String> getFkeyConditions() {
		return fkeyConditions;
	}

	public void setFkeyConditions(List<String> fkeyConditions) {
		this.fkeyConditions = fkeyConditions;
	}

	public List<String> getKeyConditions() {
		return keyConditions;
	}

	public void setKeyConditions(List<String> keyConditions) {
		this.keyConditions = keyConditions;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public Integer getNumAtts() {
		return numAtts;
	}
	public Integer getNumKeys() {
		return numKeys;
	}
	public Integer getIncomingFkeyNum() {
		return incomingFkeyNum;
	}
	public Integer getNumFkeys() {
		return numFkeys;
	}
	public void addAttribute(){
		this.numAtts++;
	}
	public void addKey(){
		this.numKeys++;
	}
	public void addFkey(){
		this.numFkeys++;
	}
	public void addIncomingFkey(){
		this.incomingFkeyNum++;
	}
	public void addFkRefId(Integer id){
		this.fkRefIds.add(id);
	}
	public List<Integer> getFkRefIds(){
		return this.fkRefIds;
	}
	public void addIncomingEqualsNum(EqualityArcType type){
		Integer num = incomingEqualsNumMap.get(type);
		if(num!=null){
			num++;
		}else{
			num = new Integer(1);
		}
		incomingEqualsNumMap.put(type, num);
		incomingEqualsNum++;
	}
	public void addOutgoingEqualsNum(EqualityArcType type){
		Integer num = outgoingEqualsNumMap.get(type);
		if(num!=null){
			num++;
		}else{
			num = new Integer(1);
		}
		outgoingEqualsNumMap.put(type, num);
		outgoingEqualsNum++;
	}
	public Integer getIncomingEqualsNum(EqualityArcType type) {
		return incomingEqualsNumMap.get(type);
	}

	public Integer getOutgoingEqualsNum(EqualityArcType type) {
		return outgoingEqualsNumMap.get(type);
	}

	@Override
	public int compareTo(MatchingData o) {
		int result;
		if(this.getSide().equals(o.getSide())){
			if(
					(this.getNumKeys().compareTo(o.getNumKeys())>0)
					||	(this.getNumKeys().equals(o.getNumKeys())
							&& this.getNumFkeys().compareTo(o.getNumFkeys())>0
						)
					|| (this.getNumKeys().equals(o.getNumKeys())
							&& this.getNumFkeys().equals(o.getNumFkeys())
							&& this.getIncomingFkeyNum().compareTo(o.getIncomingFkeyNum())>0
						)
					|| (this.getNumKeys().equals(o.getNumKeys())
							&& this.getNumFkeys().equals(o.getNumFkeys()) 
							&& this.getIncomingFkeyNum().equals(o.getIncomingFkeyNum())
							&& this.getNumAtts().compareTo(o.getNumAtts())>0
						)
	//				|| (this.getNumKeys().equals(o.getNumKeys())
	//						&& this.getNumFkeys().equals(o.getNumFkeys()) 
	//						&& this.getNumAtts().equals(o.getNumAtts())
	//						&& this.getIncomingFkeyNum().equals(o.getIncomingFkeyNum())
	//						&& this.incomingEqualsNum.compareTo(o.incomingEqualsNum)>0
	//					)
	//				|| (this.getNumKeys().equals(o.getNumKeys())
	//						&& this.getNumFkeys().equals(o.getNumFkeys()) 
	//						&& this.getNumAtts().equals(o.getNumAtts())
	//						&& this.getIncomingFkeyNum().equals(o.getIncomingFkeyNum())
	//						&& this.incomingEqualsNum.equals(o.incomingEqualsNum)
	//						&& this.outgoingEqualsNum.compareTo(o.outgoingEqualsNum)>0
	//					)
			){
				result = 1;
			}else if(this.getNumKeys().equals(o.getNumKeys())
					&& this.getNumFkeys().equals(o.getNumFkeys()) 
					&& this.getNumAtts().equals(o.getNumAtts())
					&& this.getIncomingFkeyNum().equals(o.getIncomingFkeyNum())
				){
				if(compareEquality(this.incomingEqualsNumMap, o.incomingEqualsNumMap, this.incomingEqualsNum, o.incomingEqualsNum)==0){
					result = compareEquality(this.outgoingEqualsNumMap, o.outgoingEqualsNumMap, this.outgoingEqualsNum, o.outgoingEqualsNum);
				}else{
					result = compareEquality(this.incomingEqualsNumMap, o.incomingEqualsNumMap, this.incomingEqualsNum, o.incomingEqualsNum);
				}
			}
			else{
				result = -1;
			}
		}else if(this.getSide().equals("sn") && o.getSide().equals("dx")){
			result = 1;
		}else{
			result = -1;
		}
		/* Se ancora risultano uguali passo a controllare prima le condition equal poi quelle unequal */
		if(result==0){
			result = compareConditions(this.attributeConditions, o.attributeConditions);
			if(result==0){
				result = compareConditions(this.keyConditions, o.keyConditions);
				if(result==0){
					result = compareConditions(this.fkeyConditions, o.fkeyConditions);
					if(result==0){
						result = compareConditions(this.attributeInequalityConditions, o.attributeInequalityConditions);
						if(result==0){
							result = compareConditions(this.keyInequalityConditions, o.keyInequalityConditions);
							if(result==0){
								result = compareConditions(this.fkeyInequalityConditions, o.fkeyInequalityConditions);
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	private int compareEquality(Map<EqualityArcType, Integer> source, Map<EqualityArcType, Integer> target, Integer sourceNum, Integer targetNum){
		int result = 0;
		/* Se il source ha più archi del target è maggiore il source */
		if(sourceNum.compareTo(targetNum)>0){
			result = 1;
		}else if(sourceNum.compareTo(targetNum)<0){	// se il target ha più archi del source è maggiore il target
			result = -1;
		}else{	//se hanno lo stesso numero di archi passo a controllare più a fondo
			List<EqualityArcType> lSource = new ArrayList<EqualityArcType>();
			lSource.addAll(source.keySet());
			List<EqualityArcType> lTarget = new ArrayList<EqualityArcType>();
			lTarget.addAll(target.keySet());
			if(lSource.size()==lTarget.size()){ // se hanno lo stesso numero di tipi diversi di archi passo a controllare più a fondo
				/* ordino le liste di tipi di archi e confronto uno ad uno */
				Collections.sort(lSource);
				Collections.sort(lTarget);
				for(int i=0; i<lSource.size(); i++){
					if(lSource.get(i).equals(lTarget.get(i))){	// se l'i-esimotipo di arco è lo stesso in source e target passo a controllare più a fondo
						/* Se per l'i-esimo tipo di arco ho lo stesso numero di archi, passo a controllare l'i+1-esimo
						 * tipo di arco, altrimenti è maggiore quello che ha più archi per l'i-esimo tipo di arco e fermo il controllo */
						if(!source.get(lSource.get(i)).equals(target.get(lTarget.get(i)))){
							result = source.get(lSource.get(i)).compareTo(target.get(lTarget.get(i)));
							break;
						}
					}else{	// altrimenti è maggiore quello che ha nell'i-esima posizione il tipo di arco maggiore e fermo il controllo
						result = lSource.get(i).compareTo(lTarget.get(i));
						break;
					}
				}
			}else if(lSource.size()>lTarget.size()){	// se il source ha un maggior numero di tipi diversi di archi è maggiore il source
				result = 1;
			}else{	// se il target ha un maggior numero di tipi diversi di archi è maggiore il target
				result = -1;
			}
		}
		
		return result;
	}
	
	private int compareConditions(List<String> sourceCond, List<String> destCond){
		int result = 0;
		if(sourceCond.size()!=destCond.size()){
			result = (int)Math.signum(sourceCond.size()-destCond.size());
		}else{
			Collections.sort(sourceCond);
			Collections.sort(destCond);
			for(int i=0; i<sourceCond.size(); i++){
				if(sourceCond.get(i).compareTo(destCond.get(i))!=0){
					result = sourceCond.get(i).compareTo(destCond.get(i));
					break;
				}
			}
		}
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof MatchingData){
			MatchingData other = (MatchingData) obj;
			if(this.getNumKeys().equals(other.getNumKeys())
					&& this.getNumFkeys().equals(other.getNumFkeys())
					&& this.getNumAtts().equals(other.getNumAtts())
					&& this.getIncomingFkeyNum().equals(other.getIncomingFkeyNum())
					&& StringUtils.equals(this.getSide(), other.getSide())
					&& compareEquality(this.incomingEqualsNumMap, other.incomingEqualsNumMap, this.incomingEqualsNum, other.incomingEqualsNum)==0
					&& compareEquality(this.outgoingEqualsNumMap, other.outgoingEqualsNumMap, this.outgoingEqualsNum, other.outgoingEqualsNum)==0
					){
				if(equalsConditions(this.getAttributeConditions(), other.getAttributeConditions())
//						|| (other.getAttributeConditions().isEmpty() || this.getAttributeRealNames().isEmpty() || matchConditions(this.getAttributeRealNames(), other.getAttributeConditions())))
						&& equalsConditions(this.getKeyConditions(), other.getKeyConditions())
//								|| (other.getKeyConditions().isEmpty() || this.getKeyRealNames().isEmpty() || matchConditions(this.getKeyRealNames(), other.getKeyConditions())))
						&& equalsConditions(this.getFkeyConditions(), other.getFkeyConditions())
//								|| (other.getFkeyConditions().isEmpty() || this.getFkeyRealNames().isEmpty() || matchConditions(this.getFkeyRealNames(), other.getFkeyConditions())))
						&& equalsConditions(this.getAttributeInequalityConditions(), other.getAttributeInequalityConditions())
						&& equalsConditions(this.getKeyInequalityConditions(), other.getKeyInequalityConditions())
						&& equalsConditions(this.getFkeyInequalityConditions(), other.getFkeyInequalityConditions())
				){
					result = true;
				}
			}
		}
		return result;
	}
	
	public boolean equalsWOEqualities(Object obj){
		boolean result = false;
		if(obj instanceof MatchingData){
			MatchingData other = (MatchingData) obj;
			if(this.getNumKeys().equals(other.getNumKeys())
					&& this.getNumFkeys().equals(other.getNumFkeys())
					&& this.getNumAtts().equals(other.getNumAtts())
					&& this.getIncomingFkeyNum().equals(other.getIncomingFkeyNum())
					&& StringUtils.equals(this.getSide(), other.getSide())
					
					){
				if(equalsConditions(this.getAttributeConditions(), other.getAttributeConditions())
						&& equalsConditions(this.getKeyConditions(), other.getKeyConditions())
						&& equalsConditions(this.getFkeyConditions(), other.getFkeyConditions())
						&& equalsConditions(this.getAttributeInequalityConditions(), other.getAttributeInequalityConditions())
						&& equalsConditions(this.getKeyInequalityConditions(), other.getKeyInequalityConditions())
						&& equalsConditions(this.getFkeyInequalityConditions(), other.getFkeyInequalityConditions())
				){
					result = true;
				}
			}
		}
		return result;
	}
	
	public boolean match(MatchingData other){
		boolean result = false;
		if(this.getNumKeys().equals(other.getNumKeys())
				&& this.getNumFkeys().equals(other.getNumFkeys())
				&& this.getNumAtts().compareTo(other.getNumAtts())>=0
				&& this.getIncomingFkeyNum().equals(other.getIncomingFkeyNum())
				&& this.getSide().equals(other.getSide())
				&& compareEquality(this.incomingEqualsNumMap, other.incomingEqualsNumMap, this.incomingEqualsNum, other.incomingEqualsNum)==0
				&& compareEquality(this.outgoingEqualsNumMap, other.outgoingEqualsNumMap, this.outgoingEqualsNum, other.outgoingEqualsNum)==0
				&& matchConditions(this.getAttributeConditions(), other.getAttributeConditions())
				&& matchConditions(this.getKeyConditions(), other.getKeyConditions())
				&& matchConditions(this.getFkeyConditions(), other.getFkeyConditions())
				&& matchConditions(this.getAttributeInequalityConditions(), other.getAttributeInequalityConditions())
				&& matchConditions(this.getKeyInequalityConditions(), other.getKeyInequalityConditions())
				&& matchConditions(this.getFkeyInequalityConditions(), other.getFkeyInequalityConditions())
				){
			result = true;
		}
		return result;
	}
	
	public boolean matchWOEqualities(MatchingData other){
		boolean result = false;
		if(this.getNumKeys().equals(other.getNumKeys())
				&& this.getNumFkeys().equals(other.getNumFkeys())
				&& this.getNumAtts().compareTo(other.getNumAtts())>=0
				&& this.getIncomingFkeyNum().equals(other.getIncomingFkeyNum())
				&& this.getSide().equals(other.getSide())
//				&& compareEquality(this.incomingEqualsNumMap, other.incomingEqualsNumMap, this.incomingEqualsNum, other.incomingEqualsNum)==0
//				&& compareEquality(this.outgoingEqualsNumMap, other.outgoingEqualsNumMap, this.outgoingEqualsNum, other.outgoingEqualsNum)==0
				&& (matchConditions(this.getAttributeConditions(), other.getAttributeConditions())
						|| (other.getAttributeConditions().isEmpty() || matchConditions(this.getAttributeRealNames(), other.getAttributeConditions())))
				&& (matchConditions(this.getKeyConditions(), other.getKeyConditions())
						|| (other.getKeyConditions().isEmpty() || matchConditions(this.getKeyRealNames(), other.getKeyConditions())))
				&& (matchConditions(this.getFkeyConditions(), other.getFkeyConditions())
						|| (other.getFkeyConditions().isEmpty() || matchConditions(this.getFkeyRealNames(), other.getFkeyConditions())))
				&& matchConditions(this.getAttributeInequalityConditions(), other.getAttributeInequalityConditions())
				&& matchConditions(this.getKeyInequalityConditions(), other.getKeyInequalityConditions())
				&& matchConditions(this.getFkeyInequalityConditions(), other.getFkeyInequalityConditions())
				){
			result = true;
		}
		return result;
	}
	
	/* Metodo per il match di due MatchingData per quanto riguarda le condizioni;
	 * le due relazioni rappresentate da questi matching data matchano se la prima ha tutte
	 * le condizioni della seconda (può anche averne altre, ma l'importante è che abbia almeno
	 * tutte quelle della seconda) */
	private boolean matchConditions(List<String> sourceCond, List<String> destCond){
		boolean result = false;
		if(sourceCond.isEmpty() && destCond.isEmpty()){
			result = true;
		}else if(!sourceCond.isEmpty()){
			if(sourceCond.size()>=destCond.size()){
				result = true;
				for (String string : destCond) {
					if(!sourceCond.contains(string)){
						result = false;
						break;
					}
				}
			}
		}
		return result;
	}
	
	private boolean equalsConditions(List<String> sourceCond, List<String> destCond){
		boolean result = false;
		if(sourceCond.isEmpty() && destCond.isEmpty()){
			result = true;
		}else if(!sourceCond.isEmpty() && !destCond.isEmpty()){
			if(sourceCond.size()==destCond.size()){
				Collections.sort(sourceCond);
				Collections.sort(destCond);
				result = sourceCond.equals(destCond);
			}
		}
		return result;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getSide());
		sb.append("-k");
		sb.append(getNumKeys());
		sb.append("-a");
		sb.append(getNumAtts());
		sb.append("-fk");
		sb.append(getNumFkeys());
		sb.append("-ifk");
		sb.append(getIncomingFkeyNum());
		return sb.toString();
	}

	public Integer getRelId() {
		return relId;
	}

	public void setRelId(Integer relId) {
		this.relId = relId;
	}

	public boolean matchTargetWOEqualities(MatchingData other) {
		boolean result = false;
		if(this.getSide().equals(other.getSide())
				&& this.getNumKeys().compareTo(other.getNumKeys())<=0
				&& this.getNumFkeys().compareTo(other.getNumFkeys())<=0
				&& this.getNumAtts().compareTo(other.getNumAtts())<=0
				&& this.getIncomingFkeyNum().compareTo(other.getIncomingFkeyNum())<=0
				&& matchConditions(this.getAttributeConditions(), other.getAttributeConditions())
				&& matchConditions(this.getKeyConditions(), other.getKeyConditions())
				&& matchConditions(this.getFkeyConditions(), other.getFkeyConditions())
				&& matchConditions(this.getAttributeInequalityConditions(), other.getAttributeInequalityConditions())
				&& matchConditions(this.getKeyInequalityConditions(), other.getKeyInequalityConditions())
				&& matchConditions(this.getFkeyInequalityConditions(), other.getFkeyInequalityConditions())
				){
			result = true;
		}
		return result;
	}
	
//	@Override
//	public int compareTo(MatchingData o) {
//		int result;
//		if(this.getSide().equals(o.getSide())
//				&& this.getNumAtts().equals(o.getNumAtts())
//				&& this.getNumKeys().equals(o.getNumKeys())
//				&& this.getNumFkeys().equals(o.getNumFkeys())
//				&& this.getIncomingFkeyNum().equals(o.getIncomingFkeyNum())
//		){
//			result = 0;
//		}else if(
//				(this.getSide().equals("dx") && o.getSide().equals("sn"))
//				|| (this.getNumFkeys().compareTo(o.getNumFkeys())<0)
//				|| (this.getNumKeys().compareTo(o.getNumKeys())<0)
//				|| (this.getNumAtts().compareTo(o.getNumAtts())<0)
//				|| (this.getIncomingFkeyNum().compareTo(o.getIncomingFkeyNum())<0)
//			){
//			result = -1;
//		}else{
//			result = -1;
//		}
//		
//		return result;
//	}
	
}
