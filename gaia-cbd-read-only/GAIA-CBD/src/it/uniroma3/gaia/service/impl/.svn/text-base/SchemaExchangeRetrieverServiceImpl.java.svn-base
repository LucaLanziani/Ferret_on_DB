package it.uniroma3.gaia.service.impl;

import it.uniroma3.gaia.hibernate.dao.SchemaExchangeDao;
import it.uniroma3.gaia.hibernate.model.Atom;
import it.uniroma3.gaia.hibernate.model.AtomTypeEnum;
import it.uniroma3.gaia.hibernate.model.SchemaExchange;
import it.uniroma3.gaia.sama.Exchange;
import it.uniroma3.gaia.sama.exception.RelationNotFoundException;
import it.uniroma3.gaia.service.GaiaService;
import it.uniroma3.gaia.service.SchemaExchangeConverterService;
import it.uniroma3.gaia.service.SchemaExchangeRetrieverService;
import it.uniroma3.gaia.service.dto.EqualityArcType;
import it.uniroma3.gaia.service.dto.MatchingData;
import it.uniroma3.gaia.service.util.GaiaServiceUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class SchemaExchangeRetrieverServiceImpl extends GaiaService implements SchemaExchangeRetrieverService{
	
	private SchemaExchangeConverterService schemaExchangeConverterService;// = (SchemaExchangeConverterService)ServiceFactory.getInstance(ServiceMapping.SCHEMA_EXCHANGE_CONVERTER);
	
	private SchemaExchangeDao schemaExchangeDao;// = (SchemaExchangeDao) DaoFactory.getInstance(DaoMapping.SCHEMA_EXCHANGE);
	
	/* (non-Javadoc)
	 * @see it.uniroma3.gaia.service.SchemaExchangeRetrieverService#getListBySource(it.uniroma3.gaia.sama.SchemaExchange)
	 */
	public List<Exchange> getListBySource(it.uniroma3.gaia.sama.SchemaExchange se){
		List<SchemaExchange> listaRepo = getListBySourceRepo(se);
		List<Exchange> listaExchange = new ArrayList<Exchange>();
		
		for (SchemaExchange schemaExchange : listaRepo) {
			try {
				listaExchange.add(schemaExchangeConverterService.convertFromRepository(schemaExchange));
			} catch (RelationNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return listaExchange;
	}
	
	/* (non-Javadoc)
	 * @see it.uniroma3.gaia.service.SchemaExchangeRetrieverService#getListBySourceRepo(it.uniroma3.gaia.sama.SchemaExchange)
	 */
	public List<SchemaExchange> getListBySourceRepo(it.uniroma3.gaia.sama.SchemaExchange se){
		log.debug("Searching match for: " + se.getTgd());
		List<Atom> listaSource = schemaExchangeConverterService.convertToRepositoryForCompareHalf(se);
		List<SchemaExchange> list = schemaExchangeDao.getListBySourceRelNumber(se.getRelationOfSource().values().size());
		return findMatching(listaSource, list, true);
	}
	
	

	/* (non-Javadoc)
	 * @see it.uniroma3.gaia.service.SchemaExchangeRetrieverService#findMatching(java.util.List, java.util.List, boolean)
	 */
	public List<SchemaExchange> findMatching(List<Atom> listaSource, List<SchemaExchange> list, boolean isOnlySource){
		return findMatching(listaSource, null, list, isOnlySource);
	}
	
	public List<SchemaExchange> findMatching(List<Atom> listaSource, List<Atom> listaTarg, List<SchemaExchange> list, boolean isOnlySource){
		Map<Integer, String> l = new HashMap<Integer, String>();
		
		List<Atom> listaComplete = new ArrayList<Atom>(listaSource);
		if(listaTarg!=null&&!listaTarg.isEmpty()){
			listaComplete.addAll(listaTarg);
		}
		
		/* ciclo per determinare il numero di relazioni, i cui id sono aggiunti
		 * ad una lista */
		for (Atom atom : listaComplete) {
			if(atom.getAtomType().getId().equals(AtomTypeEnum.RELATION.getId())){
				l.put(atom.getId(), atom.getSide());
			}
		}
		/* creazione della lista di matchingdata (uno per ogni relazione) dello
		 * schema exchange o dello schema corrente */
		Map<Integer, MatchingData> matchingDataSource = getMatchingData(l, listaComplete, (listaTarg==null||listaTarg.isEmpty())? isOnlySource: !isOnlySource);
		
		Map<Integer, MatchingData> matchingDataSourceSn = new HashMap<Integer, MatchingData>();
		Map<Integer, MatchingData> matchingDataSourceDx = new HashMap<Integer, MatchingData>();
		for(Integer i: matchingDataSource.keySet()){
			if(StringUtils.equals(matchingDataSource.get(i).getSide(), "sn")){
				matchingDataSourceSn.put(i, matchingDataSource.get(i));
			}else{
				matchingDataSourceDx.put(i, matchingDataSource.get(i));
			}
		}
		
		List<SchemaExchange> matching = new ArrayList<SchemaExchange>();
		/* per ogni schema exchange candidato, costruisco il matching data e lo
		 * confronto con quello da controllare... se il confronto va a buon fine
		 * lo aggiungo alla lista in uscita */
		for (SchemaExchange schemaExchange : list) {
			Map<Integer, String> lTemp = new HashMap<Integer, String>();
			for (Atom atom : schemaExchange.getAtoms()) {
				if(atom.getAtomType().getId().equals(AtomTypeEnum.RELATION.getId())){
					if(!isOnlySource || (listaTarg!=null && !listaTarg.isEmpty()) || StringUtils.equals(atom.getSide(), "sn")){
						lTemp.put(atom.getId(), atom.getSide());
					}
				}
			}
			List<Atom> listAtom = new ArrayList<Atom>();
			listAtom.addAll(schemaExchange.getAtoms());
			Map<Integer, MatchingData> matchMap = getMatchingData(lTemp, listAtom, (listaTarg==null||listaTarg.isEmpty())? isOnlySource: !isOnlySource);
			
			Map<Integer, MatchingData> matchMapSn = new HashMap<Integer, MatchingData>();
			Map<Integer, MatchingData> matchMapDx = new HashMap<Integer, MatchingData>();
			for(Integer i: matchMap.keySet()){
				if(StringUtils.equals(matchMap.get(i).getSide(), "sn")){
					matchMapSn.put(i, matchMap.get(i));
				}else{
					matchMapDx.put(i, matchMap.get(i));
				}
			}
			
			if(isOnlySource){
				if(compareMatchingDatasForMatchSource(matchingDataSourceSn, matchMapSn) 
						&& ((listaTarg==null||listaTarg.isEmpty()) || compareMatchingDatasForMatchTarget(matchingDataSourceDx, matchMapDx))){
					setRealNamesToMatch(listaSource, schemaExchange);
					matching.add(schemaExchange);
					log.info("Found match: " + schemaExchange.toString() );
				}
			}else{
				if(compareMatchingDatasForEquals(matchingDataSource, matchMap)){
					matching.add(schemaExchange);
					log.info("Found match: " + schemaExchange.toString() );
				}
			}
//			if(compareMatch(matchingDataSource, matchMap, isOnlySource)){
//				matching.add(schemaExchange);
//				log.info("Found match: " + schemaExchange.toString() );
//			}
		}
		return matching;
	}

	/* Crea una lista di oggetti matching (uno per ogni relazione) a partire da
	 * una lista di atomi; ogni oggetto di matching tiene conto del numero di 
	 * attributi, chiavi, chiavi esterne uscenti e chiavi esterne entranti */
	public Map<Integer, MatchingData> getMatchingData(Map<Integer, String> relKeys, List<Atom> atoms, boolean isOnlySource){
		/* creo tanti oggetti di matching quante sono gli atomi relazione 
		 * e li metto dentro una mappa con indici le chiavi delle relazioni */
		Map<Integer, MatchingData> matchingData = new HashMap<Integer, MatchingData>();
		for (Integer key : relKeys.keySet()) {
			MatchingData md = new MatchingData();
			md.setSide(relKeys.get(key));
			md.setRelId(key);
			matchingData.put(key, md);
		}
		/* ciclo per determinare il numero di attributi, chiavi e chiavi esterne
		 * per ogni relazione */
		for (Atom atom : atoms) {
			if(!atom.getAtomType().getId().equals(AtomTypeEnum.RELATION.getId())){
				if(!isOnlySource || StringUtils.equals(atom.getSide(), "sn")){
					Integer fatherId = atom.getFather().getId();
					if(atom.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE.getId())){
						matchingData.get(fatherId).addAttribute();
//						matchingData.get(fatherId).getAttributeRealNames().add(atom.getRealName());
					}else if(atom.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE_KEY.getId())){
						matchingData.get(fatherId).addKey();
						matchingData.get(fatherId).getKeyRealNames().add(atom.getRealName());
					}else if(atom.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE_FKEY.getId())){
						matchingData.get(fatherId).addFkey();
						matchingData.get(fatherId).addFkRefId(atom.getFkRefer().getId());
						/* inoltre tengo conto di quante chiavi esterne puntano 
						 * ad ogni relazione */
						matchingData.get(atom.getFkRefer().getId()).addIncomingFkey();
						matchingData.get(fatherId).getFkeyRealNames().add(atom.getRealName());
					}
					if(atom.getEquality()!=null){
						EqualityArcType eqT = new EqualityArcType(atom.getAtomType(), atom.getEquality().getAtomType());
						matchingData.get(fatherId).addOutgoingEqualsNum(eqT);
						if(atom.getEquality().getAtomType().getId().equals(AtomTypeEnum.RELATION.getId())){
							matchingData.get(atom.getEquality().getId()).addIncomingEqualsNum(eqT);
						}else{
							matchingData.get(atom.getEquality().getFather().getId()).addIncomingEqualsNum(eqT);
						}
					}
					if(!StringUtils.isEmpty(atom.getConstant())){
						if(atom.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE.getId())){
							matchingData.get(fatherId).getAttributeConditions().add(atom.getConstant());
						}else if(atom.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE_KEY.getId())){
							matchingData.get(fatherId).getKeyConditions().add(atom.getConstant());
						}else if(atom.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE_FKEY.getId())){
							matchingData.get(fatherId).getFkeyConditions().add(atom.getConstant());
						}
					}
					if(!StringUtils.isEmpty(atom.getInequalityConstant())){
						if(atom.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE.getId())){
							matchingData.get(fatherId).getAttributeInequalityConditions().add(atom.getInequalityConstant());
						}else if(atom.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE_KEY.getId())){
							matchingData.get(fatherId).getKeyInequalityConditions().add(atom.getInequalityConstant());
						}else if(atom.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE_FKEY.getId())){
							matchingData.get(fatherId).getFkeyInequalityConditions().add(atom.getInequalityConstant());
						}
					}
				}
			}else if(!isOnlySource || StringUtils.equals(atom.getSide(), "sn")){
				if(atom.getAttributeRealNames()!=null){
					matchingData.get(atom.getId()).setAttributeRealNames(atom.getAttributeRealNames());
				}
			}
		}		
		return matchingData;
	}
	
	/* Date due liste di matching le confronta per verificare che siano esattamente
	 * lo stesso schema exchange */
	public boolean compareMatchingDatasForEquals(Map<Integer, MatchingData> source, Map<Integer, MatchingData> target){
		boolean result = false;
		if(source.size()==target.size()){
			List<MatchingData> sourceList = new ArrayList<MatchingData>();
			sourceList.addAll(source.values());
			List<MatchingData> targetList = new ArrayList<MatchingData>();
			targetList.addAll(target.values());
			
			Collections.sort(sourceList);
			Collections.sort(targetList);
			result = true;
			
			for (int i = 0; i < sourceList.size(); i++) {
				result = compareSingleMatchingDataForEquals(source, target, sourceList.get(i), targetList.get(i));
				if(!result){
					break;
				}
			}
		}
		return result;
	}
	
	public boolean compareSingleMatchingDataForEquals(Map<Integer, MatchingData> source, Map<Integer, MatchingData> target,MatchingData s,MatchingData t){
		boolean result = true;
		if(s.equals(t)) {
			if(!s.getNumFkeys().equals(new Integer(0))
					&& s.getNumFkeys().equals(t.getNumFkeys())){
				List<MatchingData> fkeySourceMatch = new ArrayList<MatchingData>();
				for(Integer fkey: s.getFkRefIds()){
					fkeySourceMatch.add(source.get(fkey));
				}
				List<MatchingData> fkeyTargetMatch = new ArrayList<MatchingData>();
				for(Integer fkey: t.getFkRefIds()){
					fkeyTargetMatch.add(target.get(fkey));
				}
				Collections.sort(fkeySourceMatch);
				Collections.sort(fkeyTargetMatch);
				for(int j=0; j<fkeySourceMatch.size(); j++){
					if(!fkeySourceMatch.get(j).equals(fkeyTargetMatch.get(j))){
						result = false;
						break;
					}
				}
			}else if(!s.getNumFkeys().equals(t.getNumFkeys())){
				result = false;
			}
		}else{
			result = false;
		}
		return result;
	}
	
	public boolean compareMatchingDatasForMatchTarget(Map<Integer, MatchingData> source, Map<Integer, MatchingData> target){
		boolean result = false;
		List<MatchingData> matchSourceList = new ArrayList<MatchingData>(source.values());
		List<List> permutazioni = GaiaServiceUtils.calcolaDisposizioni(matchSourceList.size(), matchSourceList.size(), matchSourceList);
		
		for(List<MatchingData> lista: permutazioni){
			boolean foundPerm = true;
			for(MatchingData matchSource: lista){
				boolean found = false;
				for(Integer relTarg: target.keySet()){
					MatchingData matchTarg = target.get(relTarg);
					if(matchSource.matchTargetWOEqualities(matchTarg)){ 
						target.remove(relTarg);
						found = true;
						break;
					}
				}
				if(!found){
					foundPerm = false;
					break;
				}
			}
			if(foundPerm){
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	/* Date due liste di matching le confronta per vedere se le parti sinistre
	 * si matchano (tutto uguale, numero di attributi diverso (source<target), non considero gli archi equality) */
	public boolean compareMatchingDatasForMatchSource(Map<Integer, MatchingData> source, Map<Integer, MatchingData> target){
		boolean result = false;
		
		List<MatchingData> sourceList = new ArrayList<MatchingData>();
		for (MatchingData matchingData : source.values()) {
			if(matchingData.getSide().equals("sn")){
				sourceList.add(matchingData);
			}
		}
		List<MatchingData> targetList = new ArrayList<MatchingData>();
		for (MatchingData matchingData : target.values()) {
			if(matchingData.getSide().equals("sn")){
				targetList.add(matchingData);
			}
		}
		if(sourceList.size()==targetList.size()){
			Collections.sort(sourceList);
			Collections.sort(targetList);
			result = true;
			for (int i = 0; i < sourceList.size(); i++) {
				if(sourceList.get(i).matchWOEqualities(targetList.get(i))){
					if(!sourceList.get(i).getNumFkeys().equals(new Integer(0))
							&& sourceList.get(i).getNumFkeys().equals(targetList.get(i).getNumFkeys())){
						List<MatchingData> fkeySourceMatch = new ArrayList<MatchingData>();
						for(Integer fkey: sourceList.get(i).getFkRefIds()){
							fkeySourceMatch.add(source.get(fkey));
						}
						List<MatchingData> fkeyTargetMatch = new ArrayList<MatchingData>();
						for(Integer fkey: targetList.get(i).getFkRefIds()){
							fkeyTargetMatch.add(target.get(fkey));
						}
						Collections.sort(fkeySourceMatch);
						Collections.sort(fkeyTargetMatch);
						for(int j=0; j<fkeySourceMatch.size(); j++){
							if(!fkeySourceMatch.get(j).matchWOEqualities(fkeyTargetMatch.get(j))){
								result = false;
								break;
							}
						}
						if(!result){
							break;
						}
					}else if(!sourceList.get(i).getNumFkeys().equals(targetList.get(i).getNumFkeys())){
						result = false;
					}
				}else{
					result = false;
					break;
				}
			}
		}
		return result;
	}
	
	public void setRealNamesToMatch(List<Atom> listaSource, SchemaExchange match){
		Map<Integer, String> l = new HashMap<Integer, String>();
		/* ciclo per determinare il numero di relazioni, i cui id sono aggiunti
		 * ad una lista */
		for (Atom atom : listaSource) {
			if(atom.getAtomType().getId().equals(AtomTypeEnum.RELATION.getId())){
				l.put(atom.getId(), atom.getSide());
			}
		}
		/* creazione della lista di matchingdata (uno per ogni relazione) dello
		 * schema exchange o dello schema corrente */
		Map<Integer, MatchingData> matchingDataSource = getMatchingData(l, listaSource, true);
		
		Map<Integer, String> lTemp = new HashMap<Integer, String>();
		for (Atom atom : match.getAtoms()) {
			if(atom.getAtomType().getId().equals(AtomTypeEnum.RELATION.getId())){
				if(StringUtils.equals(atom.getSide(), "sn")){
					lTemp.put(atom.getId(), atom.getSide());
				}
			}
		}
		List<Atom> listAtom = new ArrayList<Atom>();
		listAtom.addAll(match.getAtoms());
		Map<Integer, MatchingData> matchMap = getMatchingData(lTemp, listAtom, true);
		
		List<MatchingData> startingDatas = new ArrayList<MatchingData>(matchingDataSource.values());
		List<MatchingData> matchDatas = new ArrayList<MatchingData>(matchMap.values());
		Collections.sort(startingDatas);
		Collections.sort(matchDatas);
		/* Per ogni relazionedel source sia per lo schema di partenza che per
		 * quello di match... */
		for(int i=0; i<startingDatas.size(); i++){
			Integer relS = startingDatas.get(i).getRelId();
			Integer relT = matchDatas.get(i).getRelId();
			/* Cerco l'atomo che rappresenta la relazione nello schema di partenza */
			for(Atom a: listaSource){
				boolean found = false;
				if(a.getId().equals(relS)){
					/* quando lo trovo cerco l'atomo che rappresenta la relazione
					 * corrispondente nello schema di match */
					for(Atom a1: match.getAtoms()){
						if(a1.getId().equals(relT)){ 
							/* Quando la trovo, gli setto il real name e i nomi
							 * degli attributi */
							a1.setRealName(a.getRealName());
							a1.setAttributeRealNames(a.getAttributeRealNames());
							/* cerco tra i figli della relazione corrente tutte
							 * le chiavi e le fk nello schema di partenza per
							 * assegnare il nome reale nell'elemento corrispondente
							 * dello schema di match */
							for(Atom figlio: listaSource){
								/* Considero solo i figli della relazione corrente */
								if(figlio.getFather()!=null && figlio.getFather().getId().equals(relS)){
									/* Se il figlio è una chiave (si suppone ce ne sia
									 * solo una per ogni relazione) cerco la chiave
									 * nello schema match e gli assegno il nome */
									if(figlio.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE_KEY.getId())){
										for(Atom figlio1: match.getAtoms()){
											if(figlio1.getFather()!=null && figlio1.getFather().getId().equals(relT) && StringUtils.equals(figlio1.getSide(), "sn")
													&& figlio1.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE_KEY.getId())){
												figlio1.setRealName(figlio.getRealName());
												break;
											}
										}
									}
									/* se il figlio è una fk cerco la fk corrispondente */
									if(figlio.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE_FKEY.getId())){
										for(Atom figlio1: match.getAtoms()){
											if(figlio1.getFather()!=null && figlio1.getFather().getId().equals(relT) && StringUtils.equals(figlio1.getSide(), "sn")
													&& figlio1.getAtomType().getId().equals(AtomTypeEnum.ATTRIBUTE_FKEY.getId())){
												if(compareSingleMatchingDataForEquals(matchingDataSource, matchMap, matchingDataSource.get(figlio.getFkRefer().getId()), matchMap.get(figlio1.getFkRefer().getId()))){
													figlio1.setRealName(figlio.getRealName());
												}
											}
										}
									}
								}
							}
							break;
						}
						
					}
				}
				if(found){
					break;
				}
			}
		}
	}
	
//	/* Date due liste di matching data relative a uno schema exchange o a uno
//	 * schema, le confronta e vede se si equivalgono */
//	public boolean compareMatch(Map<Integer, MatchingData> source, Map<Integer, MatchingData> target, boolean match, boolean isOnlySource){
//		boolean result;
//		
//		List<MatchingData> sourceList = new ArrayList<MatchingData>();
//		sourceList.addAll(source.values());
//		List<MatchingData> targetList = new ArrayList<MatchingData>();
//		targetList.addAll(target.values());
//		
//		Collections.sort(sourceList);
//		Collections.sort(targetList);
//		result = true;
//		int bound;
//		if (sourceList.size()<targetList.size()){
//			bound = sourceList.size();
//		}else{
//			bound = targetList.size();
//		}
//		for (int i = 0; i < bound; i++) {
//			if((match && !isOnlySource && (sourceList.get(i).getSide().equals("dx") || sourceList.get(i).match(targetList.get(i))))
//					|| (match && isOnlySource && (sourceList.get(i).getSide().equals("dx") || sourceList.get(i).matchWOEqualities(targetList.get(i))))
//					|| (!match && !isOnlySource && sourceList.get(i).equals(targetList.get(i)))
//					|| (!match && isOnlySource && (sourceList.get(i).getSide().equals("dx") || sourceList.get(i).equalsWOEqualities(targetList.get(i))))
//			){
//				if(!sourceList.get(i).getNumFkeys().equals(new Integer(0))
//						&& sourceList.get(i).getNumFkeys().equals(targetList.get(i).getNumFkeys())){
//					List<MatchingData> fkeySourceMatch = new ArrayList<MatchingData>();
//					for(Integer fkey: sourceList.get(i).getFkRefIds()){
//						fkeySourceMatch.add(source.get(fkey));
//					}
//					List<MatchingData> fkeyTargetMatch = new ArrayList<MatchingData>();
//					for(Integer fkey: targetList.get(i).getFkRefIds()){
//						fkeyTargetMatch.add(target.get(fkey));
//					}
//					Collections.sort(fkeySourceMatch);
//					Collections.sort(fkeyTargetMatch);
//					for(int j=0; j<fkeySourceMatch.size(); j++){
//						if((match && !fkeySourceMatch.get(j).match(fkeyTargetMatch.get(j)))
//								|| !fkeySourceMatch.get(j).equals(fkeyTargetMatch.get(j))){
//							result = false;
//							break;
//						}
//					}
//					if(!result){
//						break;
//					}
//				}else if(!sourceList.get(i).getNumFkeys().equals(targetList.get(i).getNumFkeys())){
//					result = false;
//				}
//			}else{
//				result = false;
//				break;
//			}
//		}
//		return result;
//	}
//	
//	public boolean compareMatch(Map<Integer, MatchingData> source, Map<Integer, MatchingData> target, boolean match){
//		return compareMatch(source, target, match, false);
//	}

	public void setSchemaExchangeConverterService(
			SchemaExchangeConverterService schemaExchangeConverterService) {
		this.schemaExchangeConverterService = schemaExchangeConverterService;
	}

	public void setSchemaExchangeDao(SchemaExchangeDao schemaExchangeDao) {
		this.schemaExchangeDao = schemaExchangeDao;
	}

}
