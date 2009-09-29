package it.uniroma3.gaia.service.impl;

import it.uniroma3.gaia.hibernate.model.Atom;
import it.uniroma3.gaia.hibernate.model.AtomTypeEnum;
import it.uniroma3.gaia.hibernate.model.SchemaExchange;
import it.uniroma3.gaia.service.GaiaService;
import it.uniroma3.gaia.service.SchemaExchangeRetrieverService;
import it.uniroma3.gaia.service.SchemaExchangeSorterService;
import it.uniroma3.gaia.service.dto.MatchingData;
import it.uniroma3.gaia.service.dto.SchemaExchangeSortable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchemaExchangeSorterServiceImpl extends GaiaService implements
		SchemaExchangeSorterService {
	
	private SchemaExchangeRetrieverService schemaExchangeRetrieverService;// = (SchemaExchangeRetrieverService)ServiceFactory.getInstance(ServiceMapping.SCHEMA_EXCHANGE_RETRIEVER);

	@Override
	public List<SchemaExchangeSortable> sort(List<SchemaExchange> list) {
		log.debug("Start ordering");
		List<SchemaExchangeSortable> listToSort = new ArrayList<SchemaExchangeSortable>();
		/* Prendo i matching data di ogni schema exchange da ordinare */
		for (SchemaExchange schemaExchange : list) {
			SchemaExchangeSortable ses = generateSchemaExchangeSortable(new SchemaExchangeSortable(schemaExchange));
			listToSort.add(ses);
		}
		for (SchemaExchangeSortable schemaExchangeSortable : listToSort) {
			for (SchemaExchangeSortable schemaExchangeSortableBis : listToSort) {
				//log.debug("Confronto tra " + schemaExchangeSortable.getDescription() + " e " + schemaExchangeSortableBis.getDescription());
				if(schemaExchangeSortable!=schemaExchangeSortableBis && !schemaExchangeRetrieverService.compareMatchingDatasForEquals(schemaExchangeSortable.getMatchingDatas(), schemaExchangeSortableBis.getMatchingDatas())){
					setSon(schemaExchangeSortable, schemaExchangeSortableBis);
				}
			}
		}
		
		log.debug("Ordering finished");
		return listToSort;
	}
	
	public SchemaExchangeSortable generateSchemaExchangeSortable(SchemaExchangeSortable exchange){
		Map<Integer, String> lTemp = new HashMap<Integer, String>();
		for (Atom atom : exchange.getAtoms()) {
			if(atom.getAtomType().getId().equals(AtomTypeEnum.RELATION.getId())){
				lTemp.put(atom.getId(), atom.getSide());
			}
		}
		List<Atom> listAtom = new ArrayList<Atom>(exchange.getAtoms());
		Map<Integer, MatchingData> matchMap = schemaExchangeRetrieverService.getMatchingData(lTemp, listAtom, false);
		exchange.setMatchingDatas(matchMap);
		return exchange;
	}
	
	private void setSon(SchemaExchangeSortable fatherCandidate, SchemaExchangeSortable sonCandidate){
		if(isFather(fatherCandidate, sonCandidate) && !isFather(sonCandidate, fatherCandidate)){
			boolean found = false;
			for (SchemaExchangeSortable ses : fatherCandidate.getSons()) {
				if(sonCandidate!=ses){
					found = isFather(ses, sonCandidate);
					if(found){
						setSon(ses, sonCandidate);
						//TODO controllare che effettivamente qui bisogna uscire dal ciclo!!! e nel caso in cui inserisco un figlio ,
						// controllare tra tutti i discendenti del padre se devo rivedere l'albero.
						break;
					}
				}else{
					found = true;
				}
			}
			if(!found){
				fatherCandidate.addSon(sonCandidate);
				sonCandidate.setIsRoot(false);
				log.debug(sonCandidate.getDescription() + " is son of " + fatherCandidate.getDescription());
			}
		}
	}
	
	private boolean isFather(SchemaExchangeSortable fatherCandidate, SchemaExchangeSortable sonCandidate){
		return schemaExchangeRetrieverService.compareMatchingDatasForMatchSource(sonCandidate.getMatchingDatas(), fatherCandidate.getMatchingDatas());
	}
	
	public void setSchemaExchangeRetrieverService(
			SchemaExchangeRetrieverService schemaExchangeRetrieverService) {
		this.schemaExchangeRetrieverService = schemaExchangeRetrieverService;
	}
}
