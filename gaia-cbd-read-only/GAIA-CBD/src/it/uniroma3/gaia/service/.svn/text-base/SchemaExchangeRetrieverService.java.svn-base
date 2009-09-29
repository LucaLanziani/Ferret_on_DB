package it.uniroma3.gaia.service;

import it.uniroma3.gaia.hibernate.model.Atom;
import it.uniroma3.gaia.hibernate.model.SchemaExchange;
import it.uniroma3.gaia.sama.Exchange;
import it.uniroma3.gaia.service.dto.MatchingData;

import java.util.List;
import java.util.Map;

public interface SchemaExchangeRetrieverService {
	
	public List<Exchange> getListBySource(it.uniroma3.gaia.sama.SchemaExchange se);

	public List<SchemaExchange> getListBySourceRepo(it.uniroma3.gaia.sama.SchemaExchange se);

	public List<SchemaExchange> findMatching(List<Atom> listaSource, List<SchemaExchange> list, boolean isOnlySource);
	
	public List<SchemaExchange> findMatching(List<Atom> listaSource, List<Atom> listatarget, List<SchemaExchange> list, boolean isOnlySource);
	
	/* Crea una lista di oggetti matching (uno per ogni relazione) a partire da
	 * una lista di atomi; ogni oggetto di matching tiene conto del numero di 
	 * attributi, chiavi, chiavi esterne uscenti e chiavi esterne entranti */
	public Map<Integer, MatchingData> getMatchingData(Map<Integer, String> relKeys, List<Atom> atoms, boolean isOnlySource);
	
	/* Date due liste di matching le confronta per verificare che siano esattamente
	 * lo stesso schema exchange */
	public boolean compareMatchingDatasForEquals(Map<Integer, MatchingData> source, Map<Integer, MatchingData> target);
	
	/* Date due liste di matching le confronta per vedere se le parti sinistre
	 * si matchano (tutto uguale, numero di attributi diverso (source<target), non considero gli archi equality) */
	public boolean compareMatchingDatasForMatchSource(Map<Integer, MatchingData> source, Map<Integer, MatchingData> target);

	
//	/* Date due liste di matching data relative a uno schema exchange o a uno
//	 * schema, le confronta e vede se si equivalgono */
//	public boolean compareMatch(Map<Integer, MatchingData> source, Map<Integer, MatchingData> target, boolean match);
//	public boolean compareMatch(Map<Integer, MatchingData> source, Map<Integer, MatchingData> target, boolean match, boolean isOnlySource);
}