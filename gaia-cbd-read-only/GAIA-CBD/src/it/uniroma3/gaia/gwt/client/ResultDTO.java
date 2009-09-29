package it.uniroma3.gaia.gwt.client;

import java.io.Serializable;

public class ResultDTO implements Serializable{
	public ResultDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String formula;
	private String tgd;
	private String dEname;
	public String getDEname() {
		return dEname;
	}
	public void setDEname(String ename) {
		dEname = ename;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public String getTgd() {
		return tgd;
	}
	public void setTgd(String tgd) {
		this.tgd = tgd;
	}
}
