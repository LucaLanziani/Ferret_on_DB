package it.uniroma3.gaia.service.dto;

import it.uniroma3.gaia.hibernate.model.Atom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConversionData {
	
	private Map<String, Atom> sourceAtoms = new HashMap<String, Atom>();
	private Map<String, Atom> destAtoms = new HashMap<String, Atom>();
	private Map<String, Atom> fKeys = new HashMap<String, Atom>();
	private List<Atom> result = new ArrayList<Atom>();
	private Integer currentIndex = 0;
	
	public Integer getCurrentIndex() {
		currentIndex++;
		return currentIndex;
	}
	public Atom getFkey(String key){
		return this.fKeys.get(key);
	}
	public void addFkey(String key, Atom atom){
		this.fKeys.put(key, atom);
	}
	public List<Atom> getResult() {
		return result;
	}
	public void addResultAtoms(List<Atom> list){
		this.result.addAll(list);
	}
	public void addResultAtom(Atom atom){
		this.result.add(atom);
	}
	public void addSourceAtom(String key, Atom atom){
		this.sourceAtoms.put(key, atom);
	}
	public void addDestAtom(String key, Atom atom){
		this.destAtoms.put(key, atom);
	}
	public Atom getSourceAtom(String key){
		return this.sourceAtoms.get(key);
	}
	public Atom getDestAtom(String key){
		return this.destAtoms.get(key);
	}
	public Map<String, Atom> getSourceAtoms(){
		return this.sourceAtoms;
	}
	public Map<String, Atom> getDestAtoms(){
		return this.destAtoms;
	}
}
