package it.uniroma3.gaia.sama;

import it.uniroma3.gaia.model.Attribute;
import it.uniroma3.gaia.model.AttributeFkey;
import it.uniroma3.gaia.model.AttributeKey;
import it.uniroma3.gaia.model.Function;
import it.uniroma3.gaia.model.Relation;
import it.uniroma3.gaia.sama.exception.AttributeNotFoundException;
import it.uniroma3.gaia.sama.exception.RelationNotFoundException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Questa classe rappresenta un Data Exchange. <br/>
 * Estende la classe Exchange.
 * @see Exchange
 *
 */
public class DataExchange extends Exchange {
	
	/**
	 * Crea un DataExchange
	 * @param name il nome del data exchange
	 */
	public DataExchange(String name) {
		super(name);
		
	}
	

	/**
	 * Questo metodo di supporto verifica se l'insieme set1 di stringhe
	 * contiene almeno un elemento dell'insieme set2 (di stringhe)
	 * @param set1 il primo insieme
	 * @param set2 il secondo insieme
	 * @return true se set1 contiene almeno un elemento di set2, 
	 * false altrimenti
	 */
	private boolean existLeastOneAttribute(Set<String> set1, Set<String> set2) {
		// Il valore da restituire
		boolean exist = false;
		
		// Itera su att2
		Iterator<String> itSet2 = set2.iterator();
		// Finch� ci sono stringhe...
		while(itSet2.hasNext()) {
			// ...verifica se set1 contiene la stringa corrente...
			if(set1.contains(itSet2.next())) {
				// ...e imposta a true l'indicatore di esistenza dell'elemento
				exist = true;
			}
		}
		
		return exist;
		
	}
	
	/**
	 * Questo metodo crea lo schema exchange relativo ad un determinato
	 * data exchange.
	 * @param name il nome da assegnare allo schema exchange
	 * @return lo schema exchange relativo a questo data exchange
	 * @throws RelationNotFoundException 
	 * @throws AttributeNotFoundException
	 */
	public SchemaExchange createSchemaExchange(String name) throws RelationNotFoundException, AttributeNotFoundException {
		// Una mappa in cui teniamo le corrispondenze fra 
		// nomi simbolici (n'r) e nome reali delle relazioni (course)
		Map<String, String> corrispondenzaSR = new HashMap<String, String>();
		
		// Una mappa in cui teniamo le corrispondenze fra 
		// nomi simbolici (n'k) e nome reali degli attributi chiave (cod)
		Map<String, String> corrispondenzaSK = new HashMap<String, String>();
		
		// Una mappa in cui teniamo le corrispondenze fra 
		// nomi simbolici (n'k) e nome reali degli attributi chiave esterna 
		// (cod)
		Map<String, String> corrispondenzaSKF = new HashMap<String, String>();
		
		// Lo schema exchange da restituire
		SchemaExchange schemaExc = new SchemaExchange(name);
		
		// Un iteratore per scorrere nelle relazioni del source
		Iterator<Relation> itRelSource;
		
		// Un iteratore per scorrere nelle relazione del target
		Iterator<Relation> itRelTarget;
		
		// Una variabile di appoggio che contiene i nomi delle relazioni source
		Set<String> sourceRelName = this.getRelationOfSource().keySet();
		
		// Una variabile di appoggio che contiene i nomi delle relazioni target
		Set<String> targetRelName = this.getRelationOfTarget().keySet();
		
		// La variabile utilizzata per assegnare il nome alla relazione nel 
		// caso non cambi il nome della relazione nel passaggio da source a
		// target
		int relationName = 1;
		
		// La variabile utilizzata per assegnare il nome alla relazione nel
		// caso cambi il nome della relazione nel passaggio da source a
		// target
		int relationNameChange = 1;
		
		// Il nome da assegnare alla chiave
		int keyName = 1;
		
		// La variabile utilizzata per assegnare il nome all'attributo
		int attributeName = 1;
		
		// La variabile utilizzata per assegnare il nome all'attributo aggiunto
		int attributeNameAgg = 1;
		
		// La variabile utilizzata per assegnare il nome all'attributo 
		// funzione
		int functionName = 1;
		
		// La variabile per assegnare il nome alle chiavi esterne
		int foreignKeyName = 1; 

		// Passi 1 e 1a. Obiettivo: controllo del nome delle relazioni
		// Un iteratore per scorrere nella lista delle relazioni del source
		Iterator<String> itSource = sourceRelName.iterator();
		// Finch� ci sono relazioni...
		while(itSource.hasNext()) {
			// ...prende un riferimento al nome di relazione corrente...
			String nomeCorrente = itSource.next();
			// ...se il target contiene una relazione con stesso nome...
			if(targetRelName.contains(nomeCorrente)) {
				//Passo 1.
				// ...aggiunge una relazione nel source e nel target...
				schemaExc.addRelationToSource("r" + relationName);
				schemaExc.addRelationToTarget("r" + relationName);
				
				// ...aggiunge la corrispondenza tra relationName e nomeCorrente...
				corrispondenzaSR.put(nomeCorrente, "r" + relationName);
				schemaExc.addCorrispondenzaNome(nomeCorrente, "r" + relationName);
		
				// ...aggiorna relationName in modo da preparasi all'assegnazione
				// del nome alla relazione successiva
				relationName++;
				
			} else {
				
				// Aggiunge un nome di relazione per quelle relazioni che
				// compaiono solo nel source
				schemaExc.addRelationToSource("r" + relationName);
				
				// Aggiunge la corrispondenza tra relationName e nomeCorrente
				// nella mappa delle corrispondenze
				corrispondenzaSR.put(nomeCorrente, "r" + relationName);
				schemaExc.addCorrispondenzaNome(nomeCorrente, "r" + relationName);
								
				// Aggiorna relationName in modo da preparasi all'assegnazione
				// del nome alla relazione successiva
				relationName++;
				
			}
			
//			// Passo 1a.
//			// Per ogni elemento in targetRelName che non � stato analizzato,
//			// crea una nuova relazione nel target
//			Iterator<String> itTar = targetRelName.iterator();
//			// Finch� ci sono relazioni...
//			while(itTar.hasNext()) {
//				// ...prende un riferimento al nome di relazione corrente...
//				nomeCorrente = itTar.next();
//				// ...se il source non contiene una relazione con questo nome...
//				if(!(sourceRelName.contains(nomeCorrente))) {
//					// ...aggiunge una relazione al target
//					schemaExc.addRelationToTarget("R" + relationNameChange);
//				
//					// Aggiunge la corrispondenza tra relationName e nomeCorrente
//					// nella mappa delle corrispondenze
//					corrispondenzaSR.put(nomeCorrente, "R" + relationNameChange);
//					schemaExc.addCorrispondenzaNome(nomeCorrente, "r" + relationName);
//					
//					// Aggiorna relationNameChange in modo da prepararsi
//					// all'assegnazione del nome alla prossima relazione
//					relationNameChange++;
//				}
			
		}
		//TODO spostato fuori...controllare se va bene
		// Passo 1a.
		// Per ogni elemento in targetRelName che non � stato analizzato,
		// crea una nuova relazione nel target
		Iterator<String> itTar = targetRelName.iterator();
		// Finch� ci sono relazioni...
		while(itTar.hasNext()) {
			// ...prende un riferimento al nome di relazione corrente...
			String nomeCorrente = itTar.next();
			// ...se il source non contiene una relazione con questo nome...
			if(!(sourceRelName.contains(nomeCorrente))) {
				// ...aggiunge una relazione al target
				schemaExc.addRelationToTarget("R" + relationNameChange);
			
				// Aggiunge la corrispondenza tra relationName e nomeCorrente
				// nella mappa delle corrispondenze
				corrispondenzaSR.put(nomeCorrente, "R" + relationNameChange);
				schemaExc.addCorrispondenzaNome(nomeCorrente, "r" + relationName);
				
				// Aggiorna relationNameChange in modo da prepararsi
				// all'assegnazione del nome alla prossima relazione
				relationNameChange++;
			}
			
		}
		
		// Passo 1.5. Obiettivo: gestire le chiavi esterne
		// Gestione lato fource
		// Itera sulle relazioni del source
		itRelSource = this.getRelationOfSource().values().iterator();
		// Finch� ci sono relazioni...
		while(itRelSource.hasNext()) {
			// ...un riferimento alla relazione corrente...
			Relation relSCorrente = itRelSource.next();
			// ...itera sulle chiavi esterne...
			Iterator<AttributeFkey> itAttFKeyS = relSCorrente.getAttributeFkey().values().iterator();
			// ...finch� ci sono chiavi esterne...
			while(itAttFKeyS.hasNext()) {
				// ...la chiave esterna corrente...
				AttributeFkey attFCorrenteS = itAttFKeyS.next();
				// ...aggiunge una chiave esterna allo schema exchange (lato
				// source)

				schemaExc.addAttributeFkeyToSource(
						corrispondenzaSR.get(relSCorrente.getName()),
						corrispondenzaSR.get(attFCorrenteS.getRelation().getName()),
						"fk" + foreignKeyName);
				
				// Aggiunge la corrispondenza nella mappa
				corrispondenzaSKF.put(attFCorrenteS.getName(), "fk" + foreignKeyName);
				schemaExc.addCorrispondenzaNome(attFCorrenteS.getName(), "fk" + foreignKeyName);
				
				foreignKeyName++;
				
			}
			
		}
		
		// Stesso procedimento sul target
		// Itera sulle relazioni del target...
		itRelTarget = this.getRelationOfTarget().values().iterator();
		// ...finch� ci sono relazioni...
		while(itRelTarget.hasNext()) {
			// ...un riferimento alla relazione corrente...
			Relation relTCorrente = itRelTarget.next();
			// ...itera sulle chiavi esterne...
			Iterator<AttributeFkey>  itAttFKeyT = relTCorrente.getAttributeFkey().values().iterator();
			// ...finch� ci sono chiavi esterne...
			while(itAttFKeyT.hasNext()) {
				// ...un riferimento alla chiave esterna corrente...
				AttributeFkey attFCorrenteT = itAttFKeyT.next();
				// ...se � uguale a qualche chiave esterna del source...
				if(corrispondenzaSKF.containsKey(attFCorrenteT.getName())) {
					// ...aggiungi una chiave esterna al target 
					// con stesso nome...
					
					schemaExc.addAttributeFkeyToTarget(
							corrispondenzaSR.get(relTCorrente.getName()),
							corrispondenzaSR.get(
									attFCorrenteT.getRelation().getName()),
							corrispondenzaSKF.get(attFCorrenteT.getName()));
					
				} else {
					// ...altrimenti crea un attributo di chiave esterna
					// con nome diverso...
					schemaExc.addAttributeFkeyToTarget(
							corrispondenzaSR.get(relTCorrente.getName()),
							corrispondenzaSR.get(
									attFCorrenteT.getRelation().getName()),
									"fk" + foreignKeyName);
					// ...aggiorna il nome per la prossima chiave esterna
					foreignKeyName++;
				}
				
			}

		}
		
		// Passo 2 e 2a. Obiettivo: analizzare le chiavi
		// Itera sulle relazioni del source
		itRelSource = this.getRelationOfSource().values().iterator();
		// L'insieme di tutti gli attributi del target
		Map<String, String> targetAttributes = this.getAllAttributesOfTarget();
		
		// Finch� ci sono relazioni...
		while(itRelSource.hasNext()) {
			// ...un riferimento alla relazione corrente
			Relation relCorrente = itRelSource.next();
			
			// ...un iteratore per scorrere nelle chiavi della relazione corrente
			Iterator<AttributeKey> itKeys = relCorrente.getAttributeKey().values().iterator();
			
			// ...finch� ci sono delle chiavi...
			while(itKeys.hasNext()) {
				// ...un riferimento alla chiave corrente...
				AttributeKey keyCorrente = itKeys.next();
				
				// ...aggiunge key corrente all'insieme delle chiavi della relazione 
				// corrente...
				schemaExc.addAttributeKeyToSource(
						corrispondenzaSR.get(relCorrente.getName()), "k" + keyName);
				
				// ...aggiunge la corrispondenza nella relativa mappa...
				corrispondenzaSK.put(keyCorrente.getName(), "k" + keyName);
				schemaExc.addCorrispondenzaNome(keyCorrente.getName(), "k" + keyName);
				
				// ...controlla se chiave corrente compare come attributo di qualche relazione
				// nel target...
				if(targetAttributes.containsKey(keyCorrente.getName()))
					// ...aggiunge l'attributo alla relazione del target...
					schemaExc.addAttributeToTarget(
							corrispondenzaSR.get(targetAttributes.get(keyCorrente.getName())),
							"k" + keyName);
				// ...infine prepara la varibile per assengare il nome alla prossima chiave
				keyName++;
				
			}
			
		}
		
		// Procedimento simile per il target
		// Itera sulle relazioni del target
		itRelTarget = this.getRelationOfTarget().values().iterator();
		
		// Finch� ci sono relazioni...
		while(itRelTarget.hasNext()) {
			// ...un riferimento alla relazione corrente...
			Relation relCorrente = itRelTarget.next();
			
			// ...itera sulle chiavi della relazione corrente...
			Iterator<AttributeKey> itKeys = relCorrente.getAttributeKey().values().iterator();
			
			// ...finch� ci sono chiavi...
			while(itKeys.hasNext()) {
				// ...un riferimento alla chiave corrente...
				AttributeKey keyCorrente = itKeys.next();
				
				// ...se keyCorrente esiste gi� nella mappa delle corrispondenze...
				if(corrispondenzaSK.containsKey(keyCorrente.getName()))
					// ...aggiunge una chiave al target prelevandone il nome simbolico dalla
					// mappa
					schemaExc.addAttributeKeyToTarget(
							corrispondenzaSR.get(relCorrente.getName()),
							corrispondenzaSK.get(keyCorrente.getName()));
				else {
					// ...altrimenti aggiungi una nuova chiave...
					try{
						schemaExc.addAttributeKeyToTarget(
								corrispondenzaSR.get(relCorrente.getName()), 
								"k" + keyName);
					}catch(NullPointerException e){}

					// ...e prepara la varibile per assengare il nome alla prossima chiave
					keyName++;
					
				}
				
			}
			
		}
		
		/* Aggiungo una lista di nomi reali di attributo a ciascuna relazione */
		for(Relation rel: this.getRelationOfSource().values()){
			for(Attribute att:rel.getAttribute().values()){
				schemaExc.getRelationOfSource().get(corrispondenzaSR.get(rel.getName())).addAttRealName(att.getName());
			}
		}
		for(Relation rel: this.getRelationOfTarget().values()){
			for(Attribute att:rel.getAttribute().values()){
				try{
					schemaExc.getRelationOfTarget().get(rel.getName()).addAttRealName(att.getName());
				}catch(NullPointerException e){
					
				}
			}
		}
		
		
		// Passo 3. Obiettivo: individuare gli attributi derivati
		// La lista degli attributi che nel target hanno funzioni applicate e
		// che compaiono come attributi solo nel source
		// La chiave rappresenta il nome della relazione, il valore � 
		// costituito da una lista di attributi
		// (Risolve il caso in cui esiste una relazione con pi� di un 
		// attributo a cui viene applicata una funzione.)
		Map<String, Set<Attribute>> attributiSospesi = new HashMap<String, Set<Attribute>>();
		
		// Gli attributi del target
		Map<String, String> attTarget = this.getAllAttributesOfTarget();
		
		// Controlla, per ogni funzione nel target, se � applicata ad uno 
		// degli attributi presenti in attTarget
		// Itera sulle relazioni del target
		itRelTarget = this.getRelationOfTarget().values().iterator();
		
		// Finch� ci sono relazioni...
		while(itRelTarget.hasNext()) {
			// ...prende un riferimento alla relazione corrente...
			Relation relazioneCorrente = itRelTarget.next();
			// ...richiede le funzioni di questa relazione...
			List<Function> functions = relazioneCorrente.getFunctions();
			// Itera sulle funzioni
			Iterator<Function> itFunction = functions.iterator();
			// ...finch� ci sono funzioni...
			while(itFunction.hasNext()) {
				// ...la funzione corrente... 
				Function functionCorrente = itFunction.next();
				// ...controlla se l'attributo a cui si applica non � presente
				// come attributo nel target...
				if(!attTarget.containsKey(functionCorrente.getAttributeName())) {
					if(this.getAllAttributesOfSource().get(
							functionCorrente.getAttributeName()) == null) {
						throw new AttributeNotFoundException(
								relazioneCorrente.getName(),
								functionCorrente.getAttributeName());
					}
					// ...aggiunge l'attributo da eliminare alla lista degli
					// attributi momentaneamente sospesi dall'elaborazione
					Attribute att = this.getRelationOfSource().get(
							this.getAllAttributesOfSource().get(
									functionCorrente.getAttributeName())).
									getAttribute().get(
											functionCorrente.getAttributeName());
					
					// Se attributiSospesi alla posizione 
					// relazioneCorrente.getName() contiene null creiamo la lista
					// vuota
					if(attributiSospesi.get(relazioneCorrente.getName()) == null) {
						attributiSospesi.put(relazioneCorrente.getName(),
								new HashSet<Attribute>());
					}
					
					// Aggiunge l'attributo
					attributiSospesi.get(relazioneCorrente.getName()).add(
							att);
														
				}
				
			}
			
		}
		
		// Passo 4. Obiettivo: aggiungere al target gli attributi che non
		// compaiono nel source
		// La lista degli attributi nella sorgente
		Map<String, String> attSource = this.getAllAttributesOfSource();
		// La lista delle chiavi nella sorgente
		Set<String> keys = this.getAllKeyOfSource().keySet();
		// L'iteratore per scorrere nell'insieme delle relazioni target
		Iterator<Relation> relTarget = this.getRelationOfTarget().values().iterator();
		
		// Finch� ci sono relazioni...
		while(relTarget.hasNext()) {
			// La relazione corrente
			Relation relCorrente = relTarget.next();
			// Itera sugli attributi della relazione corrente
			Iterator<String> attributi = relCorrente.getAttribute().keySet().iterator();
			
			// Finch� ci sono attributi...
			while(attributi.hasNext()) {
				// L'attributo corrente
				// ...controlla se non esiste questo attributo nella sorgente
				// e che non sia chiave
				String attCorrente = attributi.next();
				if(!(attSource.containsKey(attCorrente) || keys.contains(attCorrente))) {
					// ...aggiunge l'attributo al target...
					
					try{
						schemaExc.addAttributeToTarget(corrispondenzaSR.get(relCorrente.getName()), "A" + attributeNameAgg);
					}catch(NullPointerException e){}
					// Aggiorna attributeName in modo da prepararsi alla
					// assegnazione del nome al prossimo attributo
					attributeNameAgg++;
					
				}
					
			}
				
		}
		
		// Passo 5
		// Il numero di attributi (non chiave) nel source
		int numAttSource = attSource.size();
		// Il numero di attributo del source che sono presenti anche nel
		// target
		int numAttTarget = 0;
		
		// L'iteratore per scorrere nell'insieme degli attributi del source
		Iterator<String> itAttSource = attSource.keySet().iterator();
		// La mappa degli attributi del target
		Map<String, String> attsOfTarget = this.getAllAttributesOfTarget();
		// Finch� ci sono attributi...
		while(itAttSource.hasNext()) {
			// ... se lo stesso attributo compare nel target...
			if(attsOfTarget.containsKey(itAttSource.next())) {
				// ...incrementa il numero di attributi del target
				numAttTarget += 1;
				
			}
			
		}
		
		// La differenza tra sorgente e destinazioni
		int X = numAttSource - numAttTarget;
		
		if(X >= numAttTarget || X==0) {
			// Caso A
			// Itera sugli attributi del source
			itAttSource = attSource.keySet().iterator();
			// Finch� ci sono attributi...
			while(itAttSource.hasNext()) {
				// Il nome dell'attributo corrente
				String attCorrente = itAttSource.next();
				// ... controlla se fra gli attributi del target c'� un 
				// attributo con nome attCorrente ...
				//TODO controllare se va fatto così o come era prima
				schemaExc.addAttributeToSource(corrispondenzaSR.get(
						attSource.get(attCorrente)), "a" + attributeName);
				if(attsOfTarget.containsKey(attCorrente)) {
					// ... aggiunge l'attributo a sorgente ...
//					schemaExc.addAttributeToSource(corrispondenzaSR.get(
//							attSource.get(attCorrente)), "a" + attributeName);
					// ... aggiunge l'attributo a target ...
					schemaExc.addAttributeToTarget(corrispondenzaSR.get(
							attTarget.get(attCorrente)), "a" + attributeName);
					
					// ... aggiunge la condizione di copia al source.
					schemaExc.addSamenessConditionsToAttributeInSource(
							corrispondenzaSR.get(
									attSource.get(attCorrente)),
									"a" + attributeName,
									attCorrente);
					
					// Aggiorna il nome per il prossimo attributo
					//attributeName++;
				}
				attributeName++;
			}
			
		} else {
		
			if(X < numAttTarget) {
				// Itera sulle relazioni del source
				itRelSource = this.getRelationOfSource().values().iterator();
				// Finch� ci sono relazioni...
				while(itRelSource.hasNext()) {
					// ...la relazione corrente
					Relation relCorrente = itRelSource.next();
					// Gli attributi della relazione corrente
					Set<String> attRelCorrente = relCorrente.getAttribute().keySet();
					// Itera sulle relazioni del target
					itRelTarget = this.getRelationOfTarget().values().iterator();
					// Finch� ci sono relazioni nel target...
					while(itRelTarget.hasNext()) {
						// La relazione corrente
						Relation relCorrenteTarget = itRelTarget.next();
						// Se la relazione corrente lato target contiene 
						// almeno uno degli attributi della relazione corrente 
						if(this.existLeastOneAttribute(relCorrenteTarget.getAttribute().keySet()
								, attRelCorrente)) {
							// Itera sugli attributi attRelCorrente
							Iterator<String> itAttRelCorrente = attRelCorrente.iterator();
							boolean fatto = false;
							// Finch� ci sono attributi...
							while(itAttRelCorrente.hasNext()) {
								// L'attributo corrente
								String attCorrente = itAttRelCorrente.next();
								// se gli attributi della relazione corrente del target
								// non contengono attCorrente, allora aggiungilo nello schema
								if(!relCorrenteTarget.getAttribute().keySet().contains(attCorrente)) {
									// se non � stato ancora aggiunto...
									if(fatto == false) {
										// ...aggiungilo...
									schemaExc.addAttributeToSource(
											corrispondenzaSR.get(relCorrente.getName())
											, "a" + attributeName);
									// ...e indica che lo hai gi� aggiunto
									fatto = true;
									}
									// Aggiunge la condizione di disuguaglianza
									schemaExc.addInequalityConditionsToAttributeInSource(
											corrispondenzaSR.get(relCorrente.getName())
											, "a" + attributeName, attCorrente);
								}
								
							}
							
							// Scrive lato target
							schemaExc.addAttributeToTarget(
									corrispondenzaSR.get(relCorrenteTarget.getName()), 
									"a" + attributeName);
							
							// Aggiorna attributeName in modo da essere pronto
							// all'assegnazione del nome all'attributo 
							// successivo
							attributeName++;
	
						}
					}
					
				}
			
			}
			
		}
		
		// Passo 6. Obiettivo: aggiunge gli attributi derivati
		// Se ci sono attributi sospesi...
		if(attributiSospesi.size() > 0) {
			// ...andiamo ad aggiungere nello schema da restituire le
			// informazioni sugli attributi sospesi con la relativa condizione di uguaglianza.
			// Itera sulla lista degli attributi sospesi
			Iterator<String> itAttSospesi = attributiSospesi.keySet().iterator();
			
			// Finch� ci sono attributi sospesi...
			while(itAttSospesi.hasNext()) {
				// Un riferimento al nome della relazione corrente
				String relCorrente = itAttSospesi.next();
				
				// Scorre su tutti gli attributi della relazione corrente...
				Iterator<Attribute> itAttributi = attributiSospesi.get(
						relCorrente).iterator();
				while(itAttributi.hasNext()) {
					// ... aggiunge un attributo nel source ...
					schemaExc.addAttributeToSource(
							corrispondenzaSR.get(relCorrente), "a" + attributeName);
					// ... aggiunge la condizione di uguaglianza al source per ogni
					// attributo nella lista degli attributi sospesi relativi 
					// alla relazione corrente
					schemaExc.addSamenessConditionsToAttributeInSource(
							corrispondenzaSR.get(relCorrente), 
							"a" + attributeName, 
							itAttributi.next().getName());
					
					// Aggiorna il nome per il prossimo attributo
					attributeName++;
				}

			}
		}
		
		// Ora bisogna aggiungere gli attributi e le condizioni di uguaglianza 
		// sulle funzioni nel target
		// Itera sulle relazioni
		itRelTarget = this.getRelationOfTarget().values().iterator();
		// Finch� ci sono relazioni...
		while(itRelTarget.hasNext()) {
			// La relazione corrente
			Relation relazioneCorrente = itRelTarget.next();
			// Le funzioni di questa relazione
			List<Function> functions = relazioneCorrente.getFunctions();
			// Per ogni funzione...
			Iterator<Function> itFunction = functions.iterator();
			while(itFunction.hasNext()) {
				// ... un riferimento alla funzione corrente ...
				Function functionCorrente = itFunction.next();
				// ...aggiunge un attributo derivato al target dello
				// schema exchange...
				schemaExc.addDerivatedAttribute(corrispondenzaSR.get(
						relazioneCorrente.getName()), "F" + functionName,
						functionCorrente.getFunctionName(), 
						functionCorrente.getAttributeName());
				
				// Aggiorna il nome dell'attributo funzione
				functionName++;
				
			}
		}
		
		return schemaExc;
	}
	
	/**
	 * Questo metodo restituisce una stringa che rappresenta la forma
	 * logica dell'exchange
	 * @return la formula logica
	 */
	public String getLogicalFormula() {
		// La stringa da restituire
		StringBuffer formula = new StringBuffer();
		// L'iteratore per scorrere nelle relazioni
		Iterator<Relation> itRel;
		
		// Itera sulle relazioni del source
		itRel = super.source.values().iterator();
		// Finch� ci sono relazioni...
		while (itRel.hasNext()) {
			// ...richiede la formula logica alla relazione corrente...
			formula.append(itRel.next().getLogicalFormula());
			
			// ...se ci sono altre relazioni, aggiunge una virgola
			if(itRel.hasNext())
				formula.append(", ");
		}
		
		// Aggiunge la freccia
		formula.append(" -> ");

		// Itera sulle relazioni del target
		itRel = super.target.values().iterator();
		
		// Finch� ci sono relazioni...
		while (itRel.hasNext()) {
			// ...richiede la descrizione della formula logica
			// alla relazione corrente...
			formula.append(itRel.next().getLogicalFormula());

			// ...se ci sono altre relazioni, aggiunge una virgola
			if(itRel.hasNext())
				formula.append(", ");
		}
		
		// Restituisco la descrizione testuale della tgd
		return formula.toString();
	}

}