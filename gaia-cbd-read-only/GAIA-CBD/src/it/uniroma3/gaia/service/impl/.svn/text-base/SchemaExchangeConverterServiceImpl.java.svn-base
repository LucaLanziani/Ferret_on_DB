package it.uniroma3.gaia.service.impl;

import it.uniroma3.gaia.hibernate.dao.AtomTypeDao;
import it.uniroma3.gaia.hibernate.dao.SchemaExchangeTypeDao;
import it.uniroma3.gaia.hibernate.model.Atom;
import it.uniroma3.gaia.hibernate.model.AtomType;
import it.uniroma3.gaia.hibernate.model.AtomTypeEnum;
import it.uniroma3.gaia.hibernate.model.SchemaExchange;
import it.uniroma3.gaia.hibernate.model.SchemaExchangeType;
import it.uniroma3.gaia.model.Attribute;
import it.uniroma3.gaia.model.AttributeFkey;
import it.uniroma3.gaia.model.AttributeKey;
import it.uniroma3.gaia.model.InequalityCondition;
import it.uniroma3.gaia.model.Relation;
import it.uniroma3.gaia.model.SamenessCondition;
import it.uniroma3.gaia.sama.DataExchange;
import it.uniroma3.gaia.sama.Exchange;
import it.uniroma3.gaia.sama.exception.RelationNotFoundException;
import it.uniroma3.gaia.service.GaiaService;
import it.uniroma3.gaia.service.SchemaExchangeConverterService;
import it.uniroma3.gaia.service.dto.ChaseData;
import it.uniroma3.gaia.service.dto.ConversionData;
import it.uniroma3.gaia.service.util.GaiaServiceUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class SchemaExchangeConverterServiceImpl extends GaiaService implements SchemaExchangeConverterService{
	
	SchemaExchangeTypeDao schemaExchangeTypeDao;
	AtomTypeDao atomTypeDao;
	
//	/* Dato uno schema exchange preso dal repository lo ritrasforma in uno
//	 * schema exchange secondo il modello canonico */
//	/* (non-Javadoc)
//	 * @see it.uniroma3.gaia.service.SchemaExchangeConverterService#convertFromRepository(it.uniroma3.gaia.hibernate.model.SchemaExchange)
//	 */
//	public Exchange convertFromRepository(SchemaExchange schemaExchange) throws RelationNotFoundException{
//		Exchange e = new it.uniroma3.gaia.sama.SchemaExchange(null);
//		Map<Integer, String> targetRelsNames = new HashMap<Integer, String>();
//		for(Atom a: schemaExchange.getAtoms()){
//			if(a.getAtomType().getId().equals(AtomTypeEnum.RELATION.getId())){
//				if(StringUtils.equals(a.getSide(), "sn")){
//					e.addRelationToSource("R" + a.getId());
//				}else if(StringUtils.equals(a.getSide(), "dx")){
//					String name;
//					if(a.getEquality()!=null){
//						name = "R" + a.getEquality().getId();
//					}else{
//						name = "R" + a.getId();
//					}
//					targetRelsNames.put(a.getId(), name);
//					e.addRelationToTarget(name);
//				}
//			}
//		}
//		for(Atom a: schemaExchange.getAtoms()){
//			if(a.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE.getId())){
//				if(StringUtils.equals(a.getSide(), "sn")){
//					e.addAttributeToSource("R" + a.getFather().getId(), "a" + a.getId());
//				}else if(StringUtils.equals(a.getSide(), "dx")){
//					if(a.getEquality()!=null){
//						e.addAttributeToTarget(targetRelsNames.get(a.getFather().getId()), "a" + a.getEquality().getId());
//					}else{
//						e.addAttributeToTarget(targetRelsNames.get(a.getFather().getId()), "a" + a.getId());
//					}
//				}
//			}else if(a.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE_KEY.getId())){
//				if(StringUtils.equals(a.getSide(), "sn")){
//					e.addAttributeKeyToSource("R" + a.getFather().getId(), "k" + a.getId());
//				}else if(StringUtils.equals(a.getSide(), "dx")){
//					if(a.getEquality()!=null){
//						e.addAttributeKeyToTarget(targetRelsNames.get(a.getFather().getId()), "k" + a.getEquality().getId());
//					}else{
//						e.addAttributeKeyToTarget(targetRelsNames.get(a.getFather().getId()), "k" + a.getId());
//					}
//				}
//			}else if(a.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE_FKEY.getId())){
//				if(StringUtils.equals(a.getSide(), "sn")){
//					e.addAttributeFkeyToSource("R" + a.getFather().getId(), "R" + a.getFkRefer().getId(),"fk" + a.getId());
//				}else if(StringUtils.equals(a.getSide(), "dx")){
//					if(a.getEquality()!=null){
//						e.addAttributeFkeyToTarget(targetRelsNames.get(a.getFather().getId()), targetRelsNames.get(a.getFkRefer().getId()), "fk" + a.getEquality().getId());
//					}else{
//						e.addAttributeFkeyToTarget(targetRelsNames.get(a.getFather().getId()), targetRelsNames.get(a.getFkRefer().getId()),"fk" + a.getId());
//					}
//				}
//			}
//		}
//		return e;
//	}
	
	
	/* Dato uno schema exchange preso dal repository lo ritrasforma in uno
	 * schema exchange secondo il modello canonico */
	/* (non-Javadoc)
	 * @see it.uniroma3.gaia.service.SchemaExchangeConverterService#convertFromRepository(it.uniroma3.gaia.hibernate.model.SchemaExchange)
	 */
	public Exchange convertFromRepository(SchemaExchange schemaExchange) throws RelationNotFoundException{
		Exchange e = new it.uniroma3.gaia.sama.SchemaExchange(null);
		Map<Integer, String> targetRelsNames = new HashMap<Integer, String>();
		for(Atom a: schemaExchange.getAtoms()){
			if(a.getAtomType().getId().equals(AtomTypeEnum.RELATION.getId())){
				if(StringUtils.equals(a.getSide(), "sn")){
					e.addRelationToSource(a.getFakeName());
				}else if(StringUtils.equals(a.getSide(), "dx")){
					targetRelsNames.put(a.getId(), a.getFakeName());
					e.addRelationToTarget(a.getFakeName());
				}
			}
		}
		for(Atom a: schemaExchange.getAtoms()){
			if(a.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE.getId())){
				if(StringUtils.equals(a.getSide(), "sn")){
					e.addAttributeToSource(a.getFather().getFakeName(), a.getFakeName());
				}else if(StringUtils.equals(a.getSide(), "dx")){
					e.addAttributeToTarget(targetRelsNames.get(a.getFather().getId()), a.getFakeName());
				}
			}else if(a.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE_KEY.getId())){
				if(StringUtils.equals(a.getSide(), "sn")){
					e.addAttributeKeyToSource(a.getFather().getFakeName(), a.getFakeName());
				}else if(StringUtils.equals(a.getSide(), "dx")){
					e.addAttributeKeyToTarget(targetRelsNames.get(a.getFather().getId()), a.getFakeName());
				}
			}else if(a.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE_FKEY.getId())){
				if(StringUtils.equals(a.getSide(), "sn")){
					e.addAttributeFkeyToSource(a.getFather().getFakeName(), a.getFkRefer().getFakeName(), a.getFakeName());
				}else if(StringUtils.equals(a.getSide(), "dx")){
					e.addAttributeFkeyToTarget(targetRelsNames.get(a.getFather().getId()), targetRelsNames.get(a.getFkRefer().getId()), a.getFakeName());
				}
			}
		}
		log.debug("Exchange model created: " + e.getTgd());
		return e;
	}
	
	
	/* (non-Javadoc)
	 * @see it.uniroma3.gaia.service.SchemaExchangeConverterService#convertToRepository(it.uniroma3.gaia.sama.Exchange, java.lang.String)
	 */
	public SchemaExchange convertToRepository(Exchange exchange,  String description){
		Collection<Relation> source = exchange.getRelationOfSource().values();
		Collection<Relation> dest = exchange.getRelationOfTarget().values();
		SchemaExchange exchangeModel = new SchemaExchange();
		exchangeModel.setDescription(description);
		SchemaExchangeType schemaExchangeType = new SchemaExchangeType();
		schemaExchangeType.setSnRelNum(source.size());
		schemaExchangeType.setDxRelNum(dest.size());
//		SchemaExchangeType schemaExchangeType = schemaExchangeTypeDao.getByRelNumSnDx(source.size(), dest.size());
		exchangeModel.setSchemaExchangeType(schemaExchangeType);
		ConversionData data = new ConversionData();
		List<Relation> listTarget = new ArrayList<Relation>();
		listTarget.addAll(dest);
		List<Relation> listSource = new ArrayList<Relation>();
		listSource.addAll(source);
		convertHalf(listSource, data, "sn");
		convertHalf(listTarget, data, "dx");
		exchangeModel.addAtoms(data.getResult());
		log.debug("Exchange repo created: " + exchangeModel.toString());
		return exchangeModel;
	}
	
	/* (non-Javadoc)
	 * @see it.uniroma3.gaia.service.SchemaExchangeConverterService#convertToRepositoryForCompareHalf(java.util.List)
	 */
	public List<Atom> convertToRepositoryForCompareHalf(it.uniroma3.gaia.sama.SchemaExchange se){
		List<Relation> source = new ArrayList<Relation>();
		source.addAll(se.getRelationOfSource().values());
		ConversionData data = new ConversionData();
		return convertHalf(source, data, "sn",se.getCorrispondenzaNomi());
	}
	
	@Override
	/* Dato uno schema exchange ritorna il data Exchange contenente anche le sostituzioni
	 * dei nomi reali */
	public DataExchange getDataExchange(SchemaExchange se) {
		log.debug("Calculating chase.");
		return getDataExchangeFormula(se, false);
	}
	
	public DataExchange getFormula(SchemaExchange se){
		log.debug("Calculating chase formula.");
		return getDataExchangeFormula(se, true);
	}
	
	private DataExchange getDataExchangeFormula(SchemaExchange se, boolean isFormula) {
		DataExchange result = new DataExchange(se.getDescription());
		
		/* Creo una mappa in cui a ciascuna relazione di sinistra associo tutti i suoi figli
		 * inoltre comincio a creare le strutture per il chase con le relazioni
		 * e gli elementi sicuri (chiavi e chiavi esterne di sinistra)
		 * -PROBLEMA SE IL NOME DELLA RELAZIONE VIENE DA UN ATTRIBUTO */
		Map<Atom,List<Atom>> relsMap = new HashMap<Atom, List<Atom>>();
		Map<String, ChaseData> chaseMap = new HashMap<String, ChaseData>();
		Map<String, ChaseData> chaseMapSn = new HashMap<String, ChaseData>();
		for (Atom atom : se.getAtoms()) {
			if(atom.getAtomType().getId().equals(AtomTypeEnum.RELATION.getId())){
				if(StringUtils.equals(atom.getSide(), "sn")){
					relsMap.put(atom, new ArrayList<Atom>());
					chaseMapSn.put(atom.getRealName(), new ChaseData());
				}else if(StringUtils.equals(atom.getSide(), "dx")){
					chaseMap.put(atom.getRealName(), new ChaseData());
				}
			}
		}
		for (Atom atom : se.getAtoms()) {
			if(StringUtils.equals(atom.getSide(), "sn") && !atom.getAtomType().getId().equals(AtomTypeEnum.RELATION.getId())){
				relsMap.get(atom.getFather()).add(atom);
				
				ChaseData data = chaseMapSn.get(atom.getFather().getRealName());
				if(atom.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE_KEY.getId())){
					if(!isFormula){
						data.addKey(atom.getRealName());
					}else{
						data.addKey(atom.getFakeName());
					}
				}else if(atom.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE_FKEY.getId())){
					if(!isFormula){
						data.addFkey(atom.getRealName(), atom.getFkRefer().getRealName());
					}else{
						data.addFkey(atom.getFkRefer().getRealName(), atom.getFkRefer().getRealName());
					}
				}
			}
			if(StringUtils.equals(atom.getSide(), "dx") 
					&& !atom.getAtomType().getId().equals(AtomTypeEnum.RELATION.getId())
					&& (atom.getEquality()==null || !atom.getEquality().getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE.getId()))){
				ChaseData data = chaseMap.get(atom.getFather().getRealName());
				if(atom.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE.getId())){
					if(!isFormula){
						data.addAttribute(atom.getRealName());
					}else{
						data.addAttribute(atom.getFakeName());
					}
				}else if(atom.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE_KEY.getId())){
					if(!isFormula){
						data.addKey(atom.getRealName());
					}else{
						data.addKey(atom.getFakeName());
					}
				}else if(atom.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE_FKEY.getId())){
					if(!isFormula){
						data.addFkey(atom.getRealName(), atom.getFkRefer().getRealName());
					}else{
						data.addFkey(atom.getFkRefer().getRealName(), atom.getFkRefer().getRealName());
					}
				}
			}
		}
		
		/* Per ogni relazione calcolo tutte le possibili assegnazioni dei nomi
		 * reali di attributo agli attributi effettivi e associo di volta in
		 * volta l'id dell'attributo al nome associato */
		int attCounter = 0;
		List<List> relsAssegnazioni = new ArrayList<List>();
		for(Atom rel: relsMap.keySet()){
			List<Atom> atts = getAtts(relsMap.get(rel));
			List<String> attsNames = rel.getAttributeRealNames();
			
			if(isFormula){
				List<String> listaFakes = new ArrayList<String>();
				for(String s: attsNames){
					listaFakes.add("a" + attCounter);
					attCounter++;
				}
				attsNames = listaFakes;
			}
			
			List<List> namesDisp = GaiaServiceUtils.calcolaDisposizioni(attsNames.size(), atts.size(), attsNames);
			List<Map<Integer, String>> assegnazioni = new ArrayList<Map<Integer,String>>();
			for(List<String> disp: namesDisp){
				Map<Integer, String> ass = new HashMap<Integer, String>();
				for(int i=0; i<disp.size(); i++){
					ass.put(atts.get(i).getId(), disp.get(i));
				}
				assegnazioni.add(ass);
			}
			relsAssegnazioni.add(assegnazioni);
		}
		/* Date tutte le associazioni possibili di nomi reali di attributo agli
		 * attributi per ogni relazione, combino ogni assegnazione di ciascuna
		 * relazione con ogni assegnazione di tutte le altre */
		List<List> combinazioniAssegnazioni = GaiaServiceUtils.combine(relsAssegnazioni);
		/* Appiattisco le assegnazioni: da una lista di liste di mappe, la trasformo
		 * in una lista di mappe, tanto le mappe di ciascuna lista si possono ricondurre
		 * ad una singola mappa di assegnazioni */
		List<Map<Integer, String>> flattenCombinazioneAssegnazioni = new ArrayList<Map<Integer,String>>();
		for (List<Map<Integer, String>> list : combinazioniAssegnazioni) {
			Map<Integer, String> map = new HashMap<Integer, String>();
			for (Map<Integer, String> m : list) {
				map.putAll(m);
			}
			flattenCombinazioneAssegnazioni.add(map);
		}
		
		/* Comincio a creare il chase aggiungendo gli elementi sicuri del source
		 * (Relazioni, Chiavi e Foreign Key) */
		
		
		
		/* Per ogni combinazione di assegnazione di attributi cerco di aggiungere
		 * elementi al chase target */
		for(Map<Integer, String> assegnaz: flattenCombinazioneAssegnazioni){
			for(Atom atom : se.getAtoms()){
				if(StringUtils.equals(atom.getSide(), "dx") 
						&& !atom.getAtomType().getId().equals(AtomTypeEnum.RELATION.getId())
						&& (atom.getEquality()!=null && atom.getEquality().getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE.getId()))){
					ChaseData data = chaseMap.get(atom.getFather().getRealName());
					if(assegnaz.keySet().contains(atom.getEquality().getId())){
						if((StringUtils.isEmpty(atom.getEquality().getConstant())
								|| StringUtils.equalsIgnoreCase(atom.getEquality().getConstant(), assegnaz.get(atom.getEquality().getId())))
							&&(StringUtils.isEmpty(atom.getConstant())
									|| StringUtils.equalsIgnoreCase(atom.getConstant(), assegnaz.get(atom.getEquality().getId())))){
							if(atom.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE.getId())){
								data.addAttribute(assegnaz.get(atom.getEquality().getId()));
							}else if(atom.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE_KEY.getId())){
								data.addKey(assegnaz.get(atom.getEquality().getId()));
							}else if(atom.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE_FKEY.getId())){
								data.addFkey(assegnaz.get(atom.getEquality().getId()), atom.getFkRefer().getRealName());
							}
						}
					}else{
						if(atom.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE.getId())){
							data.addAttribute(atom.getRealName());
						}else if(atom.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE_KEY.getId())){
							data.addKey(atom.getRealName());
						}else if(atom.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE_FKEY.getId())){
							data.addFkey(atom.getRealName(), atom.getFkRefer().getRealName());
						}
					}
				}else if(StringUtils.equals(atom.getSide(), "sn") 
						&& !atom.getAtomType().getId().equals(AtomTypeEnum.RELATION.getId())
						&& atom.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE.getId())){
					ChaseData data = chaseMapSn.get(atom.getFather().getRealName());
					if(assegnaz.keySet().contains(atom.getId())
							&& (StringUtils.isEmpty(atom.getConstant()) || StringUtils.equalsIgnoreCase(atom.getConstant(), assegnaz.get(atom.getId())))){
						data.addAttribute(assegnaz.get(atom.getId()));
					}else{
						data.addAttribute(atom.getRealName());
					}
				}
			}
		}
		
		for(String relName: chaseMap.keySet()){
			ChaseData data = chaseMap.get(relName);
			result.addRelationToTarget(relName);
			for(String key: data.getKeys()){
				result.addAttributeKeyToTarget(relName, key);
			}
			for(String att: data.getAtts()){
				result.addAttributeToTarget(relName, att);
			}
		}
		for(String relName: chaseMap.keySet()){
			ChaseData data = chaseMap.get(relName);
			for(String fkey: data.getFkeys().keySet()){
				try {
					result.addAttributeFkeyToTarget(relName, data.getFkeys().get(fkey), fkey);
				} catch (RelationNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		
		for(String relName: chaseMapSn.keySet()){
			ChaseData data = chaseMapSn.get(relName);
			result.addRelationToSource(relName);
			for(String key: data.getKeys()){
				result.addAttributeKeyToSource(relName, key);
			}
			for(String att: data.getAtts()){
				result.addAttributeToSource(relName, att);
			}
		}
		for(String relName: chaseMapSn.keySet()){
			ChaseData data = chaseMapSn.get(relName);
			for(String fkey: data.getFkeys().keySet()){
				try {
					result.addAttributeFkeyToSource(relName, data.getFkeys().get(fkey), fkey);
				} catch (RelationNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		log.debug("Chase for " + se.getDescription());
		log.debug("Logical formula: " + result.getLogicalFormula());
		log.debug("Tgd: " + result.getTgd());
		return result;
	}
	
	
	
	private List<Atom> getAtts(Collection<Atom> l){
		List<Atom> result = new ArrayList<Atom>();
		for (Atom atom : l) {
			if(atom.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE.getId())){
				result.add(atom);
			}
		}
		return result;
	}
	
//	
//	public List<Atom> convertToRepositoryForCompare(List<Relation> source, List<Relation> target){
//		ConversionData data = new ConversionData();
//		List<Atom> l = new ArrayList<Atom>();
//		l.addAll(convertHalf(source, data, "sn",true));
//		l.addAll(convertHalf(target, data, "dx",true));
//		return l;
//	}
	
	
	private List<Atom> convertHalf(List<Relation> sePart, ConversionData data, String side){
		return convertHalf(sePart, data, side, null);
	}
	
	/* Metodo che salva una delle due metà di uno schema exchange */
	private List<Atom> convertHalf(List<Relation> sePart, ConversionData data, String side, Map<String, String> corrispondenzaNomi){
//		int index = 0;
		AtomType relationType = atomTypeDao.getByTypeEnum(AtomTypeEnum.RELATION);
		for(Relation relation: sePart){
//			index++;
			Atom rel = new Atom();
			rel.setAtomType(relationType);
			rel.setSide(side);
//			rel.setPosition(index);
			rel.setId(data.getCurrentIndex());
			if(corrispondenzaNomi!=null){
				rel.setRealName(corrispondenzaNomi.get(relation.getName()));
			}
			Collection<Attribute> atts = relation.getAttribute().values();
			if(!atts.isEmpty()){
				for (String s: relation.getAttsRealName()) {
					rel.addAttributeRealName(s);
				}
			}
			if(StringUtils.equals(side, "sn")){
				data.addSourceAtom(relation.getName(), rel);
			}else if(StringUtils.equals(side, "dx")){
				if(data.getSourceAtoms().containsKey(relation.getName())){
					rel.setEquality(data.getSourceAtom(relation.getName()));
				}
				data.addDestAtom(relation.getName(), rel);
			}
//			log.debug(rel.toString());
			data.addResultAtom(rel);
			convertAttributes(relation.getAttribute().values(), rel, side, data, null);
			convertAttributes(relation.getAttributeKey().values(), rel, side, data, corrispondenzaNomi);
			convertAttributes(relation.getAttributeFkey().values(), rel, side, data, corrispondenzaNomi);
		}
		for(Relation relation: sePart){
			for(AttributeFkey attFkey: relation.getAttributeFkey().values()){
				Atom a = data.getFkey(attFkey.getName());
				Atom refer = null;
				if(StringUtils.equals(side, "sn")){
					refer = data.getSourceAtom(attFkey.getRelation().getName());
				}else if(StringUtils.equals(side, "dx")){
					refer = data.getDestAtom(attFkey.getRelation().getName());
				}
				a.setFkRefer(refer);
			}
		}
		return data.getResult();
	}
	
	@SuppressWarnings("unchecked")
	private void convertAttributes(Collection list, Atom father, String side, ConversionData data,  Map<String, String> corrispondenzaNomi){
		List<Atom> result = new ArrayList<Atom>();
		AtomType keyType = atomTypeDao.getByTypeEnum(AtomTypeEnum.ATTRIBUTE_KEY);
		AtomType fkeyType = atomTypeDao.getByTypeEnum(AtomTypeEnum.ATTRIBUTE_FKEY);
		AtomType attributeType = atomTypeDao.getByTypeEnum(AtomTypeEnum.ATTRIBUTE);
//		int index = 0;
		for (Attribute attribute : (Collection<Attribute>)list) {
//			index++;
			Atom a = new Atom();
			a.setId(data.getCurrentIndex());
			a.setSide(side);
			a.setFather(father);
//			a.setPosition(index);
			if(attribute.getConditions()!=null && !attribute.getConditions().isEmpty()
					&& !StringUtils.startsWith(attribute.getConditions().get(0).getValue(), "?")){
				if(attribute.getConditions().get(0) instanceof SamenessCondition){
					a.setConstant(attribute.getConditions().get(0).getValue());
				}else if(attribute.getConditions().get(0) instanceof InequalityCondition
						&& !StringUtils.startsWith(attribute.getConditions().get(0).getValue(), "?")){
					a.setInequalityConstant(attribute.getConditions().get(0).getValue());
				}
			}
			if(attribute instanceof AttributeKey){
				a.setAtomType(keyType);
			}else if(attribute instanceof AttributeFkey){
				a.setAtomType(fkeyType);
				data.addFkey(attribute.getName(), a);
			}else if(attribute instanceof Attribute){
				a.setAtomType(attributeType);
			}
			if(corrispondenzaNomi!=null && !a.getAtomType().equals(attributeType)){
				a.setRealName(corrispondenzaNomi.get(attribute.getName()));
			}
			if(StringUtils.equals(side, "dx")){
				if(data.getSourceAtoms().containsKey(attribute.getName())){
					a.setEquality(data.getSourceAtom(attribute.getName()));
				}
				data.addDestAtom(attribute.getName(), a);
				
			}else if(StringUtils.equals(side, "sn")){
				data.addSourceAtom(attribute.getName(), a);
			}
//			log.debug(a.toString());
			result.add(a);
		}
		data.addResultAtoms(result);
		//return data;
	}


	public void setSchemaExchangeTypeDao(SchemaExchangeTypeDao schemaExchangeTypeDao) {
		this.schemaExchangeTypeDao = schemaExchangeTypeDao;
	}


	public void setAtomTypeDao(AtomTypeDao atomTypeDao) {
		this.atomTypeDao = atomTypeDao;
	}
	
	
//	private List<Atom> convertSourceSideAttributes(Collection list, Atom father){
//	List<Atom> result = new ArrayList<Atom>();
//	
//	int index = 0;
//	for (Attribute attribute : (Collection<Attribute>)list) {
//		index++;
//		Atom a = setCommons(attribute, father, "sn", index);
//	}
//	return result;
//}
//
//private List<Atom> convertDestSideAttributes(Collection list, Atom father){
//	List<Atom> result = new ArrayList<Atom>();
//	
//	int index = 0;
//	for (Attribute attribute : (Collection<Attribute>)list) {
//		index++;
//		Atom a = setCommons(attribute, father, "sn", index);
//	}
//	return result;
//}
//
//private Atom setCommons(Attribute attribute, Atom father, String side, Integer position){
//	Atom a = new Atom();
//	a.setSide(side);
//	a.setFather(father);
//	a.setPosition(position);
//	if(attribute instanceof AttributeKey){
//		a.setAtomType(ATOM_TYPE_ATTRIBUTE_KEY);
//	}else if(attribute instanceof AttributeFkey){
//		a.setAtomType(ATOM_TYPE_ATTRIBUTE_FKEY);
//	}else if(attribute instanceof Attribute){
//		a.setAtomType(ATOM_TYPE_ATTRIBUTE);
//	}
//	return a;
//}
}
