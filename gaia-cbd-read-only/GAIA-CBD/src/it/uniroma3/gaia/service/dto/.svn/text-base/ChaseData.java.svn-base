package it.uniroma3.gaia.service.dto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChaseData {

	private Set<String> atts;
	
	private Set<String> keys;
	
	private Map<String, String> fkeys;
	
	public ChaseData(){
		this.atts = new HashSet<String>();
		this.keys = new HashSet<String>();
		this.fkeys = new HashMap<String, String>();
	}
	
	public boolean addAttribute(String att){
		boolean inserted = false;
		if(!this.keys.contains(att)
				&& !this.fkeys.containsKey(att)){
			inserted = this.atts.add(att);
		}
		return inserted;
	}
	
	public boolean addKey(String key){
		boolean inserted = false;
		if(this.atts.contains(key)){
			this.atts.remove(key);
		}
		if(this.fkeys.keySet().contains(key)){
			this.fkeys.remove(key);
		}
		inserted = this.keys.add(key);
		return inserted;
	}
	
	public boolean addFkey(String fkey, String refRel){
		boolean inserted = false;
		if(this.atts.contains(fkey)){
			this.atts.remove(fkey);
		}
		if(!this.fkeys.containsKey(fkey) && !this.keys.contains(fkey)){
			inserted = true;
			this.fkeys.put(fkey, refRel);
		}
		return inserted;
	}

	public Set<String> getAtts() {
		return this.atts;
	}

	public Set<String> getKeys() {
		return this.keys;
	}

	public Map<String, String> getFkeys() {
		return this.fkeys;
	}
	
}
