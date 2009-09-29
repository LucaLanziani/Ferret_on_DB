package it.uniroma3.gaia.hibernate.model;


public class SchemaExchangeType extends GaiaBo implements Comparable<SchemaExchangeType>{
	
	private Integer snRelNum;
	private Integer dxRelNum;
	
	public Integer getSnRelNum() {
		return snRelNum;
	}
	public void setSnRelNum(Integer snRelNum) {
		this.snRelNum = snRelNum;
	}
	public Integer getDxRelNum() {
		return dxRelNum;
	}
	public void setDxRelNum(Integer dxRelNum) {
		this.dxRelNum = dxRelNum;
	}
	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		if(obj instanceof SchemaExchangeType){
			SchemaExchangeType set = (SchemaExchangeType) obj;
			if(this.getSnRelNum().equals(set.getSnRelNum())
					&& this.getDxRelNum().equals(set.getDxRelNum())){
				isEqual = true;
			}
		}
		return isEqual;
	}
	
	@Override
	public int compareTo(SchemaExchangeType o) {
		int result = -1;
		if(this.snRelNum.compareTo(o.snRelNum)>0){
			result = 1;
		}else if(this.snRelNum.compareTo(o.snRelNum)==0){
			result = this.dxRelNum.compareTo(o.dxRelNum);
		}
		return result;
	}
}
