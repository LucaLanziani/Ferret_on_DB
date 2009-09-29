package it.uniroma3.gaia.hibernate.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Atom extends GaiaBo {
	
//	private Integer position;
	private String side;
	private Atom equality;
	private String constant;
	private String inequalityConstant;
	private SchemaExchange schemaExchange;
	private Atom father;
	private AtomType atomType;
	private Atom fkRefer;
	
	private String realName;
	private List<String> attributeRealNames = new ArrayList<String>();
	
//	public Integer getPosition() {
//		return position;
//	}
//	public void setPosition(Integer position) {
//		this.position = position;
//	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public Atom getEquality() {
		return equality;
	}
	public void setEquality(Atom equality) {
		this.equality = equality;
	}
	public String getConstant() {
		return constant;
	}
	public void setConstant(String constant) {
		this.constant = constant;
	}
	public String getInequalityConstant() {
		return inequalityConstant;
	}
	public void setInequalityConstant(String inequalityConstant) {
		this.inequalityConstant = inequalityConstant;
	}
	public SchemaExchange getSchemaExchange() {
		return schemaExchange;
	}
	public void setSchemaExchange(SchemaExchange schemaExchange) {
		this.schemaExchange = schemaExchange;
	}
	public Atom getFather() {
		return father;
	}
	public void setFather(Atom father) {
		this.father = father;
	}
	public AtomType getAtomType() {
		return atomType;
	}
	public void setAtomType(AtomType atomType) {
		this.atomType = atomType;
	}
	public Atom getFkRefer() {
		return fkRefer;
	}
	public void setFkRefer(Atom fkRefer) {
		this.fkRefer = fkRefer;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		if(obj!=null && obj instanceof Atom){
			Atom a = (Atom) obj;
			if((this.getConstant()==null || this.getConstant().equals(a.getConstant())) &&
//					(this.getPosition().equals(a.getPosition())) &&
					(this.getFather()==null || this.getFather().equals(a.getFather())) &&
					(this.getEquality()== null || this.getEquality().equals(a.getEquality())) &&
					(this.getFkRefer()==null || this.getFkRefer().equals(a.getFkRefer()))
//					&& (this.getPosition().equals(a.getPosition()))
					){
				isEqual = true;
			}
		}
		return isEqual;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.side + "-");
		sb.append(this.getId() + "-");
		sb.append(this.getAtomType().getDescription());
		if(this.getFather()!=null){
			sb.append("-Father" + this.getFather().getId());
		}
		if(this.getFkRefer()!=null){
			sb.append("-FKRefer: " + this.getFkRefer().getId());
		}
		if(this.getEquality() != null){
			sb.append("-Equality:" + this.getEquality().getId());
		}
		if(this.getConstant()!=null){
			sb.append("-Constant: " + this.getConstant());
		}
		if(this.getInequalityConstant()!=null){
			sb.append("-InequalityConstant:" + this.getInequalityConstant());
		}
		if(!StringUtils.isEmpty(this.getRealName())){
			sb.append(" (" + this.getRealName() + ")");
		}
		if(!this.attributeRealNames.isEmpty()){
			sb.append("-Attributi:");
			for (String attributeName : this.attributeRealNames) {
				sb.append(" " + attributeName);
			}
		}
		if(StringUtils.equals(this.side, "dx") && this.getEquality()!=null){
			if(!StringUtils.isEmpty(this.getEquality().getRealName())){
				sb.append(" (" + this.getEquality().getRealName() + ")");
			}
		}
		return  sb.toString();
	}
	
	public String getRealName() {
		String name = getFakeName();
		if(StringUtils.isEmpty(this.constant)){
			if(StringUtils.equals(this.side, "sn")){
				if(StringUtils.isNotEmpty(this.realName)){
					name = this.realName;
				}
			}else if(StringUtils.equals(this.side, "dx")){
				if(this.getEquality()!=null){
					name = this.getEquality().getRealName();
				}
			}
		}
		return name;
	}
	public String getFakeName(){
		StringBuilder name = new StringBuilder();
		if(StringUtils.isEmpty(this.constant)){
			if(StringUtils.equals(this.side, "sn") || this.getEquality()==null){
				if(this.atomType.getId().equals(AtomTypeEnum.RELATION.getId())){
					name.append("R");
				}else if(this.atomType.getId().equals(AtomTypeEnum.ATTRIBUTE.getId())){
					name.append("a");
				}else if(this.atomType.getId().equals(AtomTypeEnum.ATTRIBUTE_KEY.getId())){
					name.append("k");
				}else if(this.atomType.getId().equals(AtomTypeEnum.ATTRIBUTE_FKEY.getId())){
					name.append("fk");
				}
				name.append(this.getId());
			}else if(StringUtils.equals(this.side, "dx")){
				name = new StringBuilder(this.getEquality().getFakeName());
			}
		}else{
			name = new StringBuilder(this.getConstant());
		}
		return name.toString();
	}
	
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public List<String> getAttributeRealNames() {
		return attributeRealNames;
	}
	public void addAttributeRealName(String attributeRealName) {
		this.attributeRealNames.add(attributeRealName);
	}
	public void setAttributeRealNames(List<String> attributeRealNames) {
		this.attributeRealNames = attributeRealNames;
	}
}
