package it.uniroma3.gaia.service.impl;

import it.uniroma3.gaia.sama.SchemaExchange;
import it.uniroma3.gaia.sama.exception.RelationNotFoundException;
import it.uniroma3.gaia.service.SchemaExchangeConverterService;
import it.uniroma3.gaia.service.SchemaExchangeParserService;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.JDOMParseException;
import org.jdom.input.SAXBuilder;



/**
 * Questa classe si occupa di caricare uno schema exchange da un file xml. <br/>
 * Per la lettura sfrutta il package jdom.
 */
public class SchemaExchangeParserServiceImpl implements SchemaExchangeParserService{
	
	private SchemaExchangeConverterService schemaExchangeConverterService;
	
	public void setSchemaExchangeConverterService(
			SchemaExchangeConverterService schemaExchangeConverterService) {
		this.schemaExchangeConverterService = schemaExchangeConverterService;
	}

	public it.uniroma3.gaia.hibernate.model.SchemaExchange createSchemaExchangeRepo(String fileName) {
		SchemaExchange se = null;
		try {
			se = createSchemaExchange(fileName);
		} catch (RelationNotFoundException e) {
			e.printStackTrace();
		}
		return schemaExchangeConverterService.convertToRepository(se, se.getName());
	}
	
	public SchemaExchange createSchemaExchange(String fileName) throws RelationNotFoundException {
		return createSchemaExchange(new File(fileName));
	}
	
	public it.uniroma3.gaia.hibernate.model.SchemaExchange createSchemaExchangeRepo(File file) {
		SchemaExchange se = null;
		try {
			se = createSchemaExchange(file);
		} catch (RelationNotFoundException e) {
			e.printStackTrace();
		}
		return schemaExchangeConverterService.convertToRepository(se, se.getName());
	}
	
	/**
	 * Questo metodo analizza il file XML contente la descrizione del
	 * data exchange e ne costruisce il corrispondente oggetto DataExchange
	 * @return il data exchange descritto nel file XML 
	 * @throws RelationNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public SchemaExchange createSchemaExchange(File file) throws RelationNotFoundException {
		// Il data exchange da restituire
		SchemaExchange schemaExchange=null;
		
		// Crea un SAXBulder indicandogli di effettuare la validazione
		SAXBuilder sb = new SAXBuilder(true);
		
		// L'iteratore per scorrere sulle relazioni
		Iterator<Element> itRelations;
		// L'iteratore per scorrere sugli attributi chiave
		Iterator<Element> itKeys;
		// L'iteratore per scorrere sugli attributi
		Iterator<Element> itAttributes;
		// L'iteratore per scorrere sugli attributi chiave esterna
		Iterator<Element> itFKeys;
		// L'iteratore per scorrere sulle funzioni
		Iterator<Element> itFunction;
		
		try {
			// Apre il file e ne trasforma il contenuto 
			// in un oggetto Document in modo da rendere navigabile 
			// l'albero XML
			// Questa operazione effettua anche la validazione del
			// documento XML. Se non è valido, genera una eccezione di
			// tipo JDOMParseException
			Document document = sb.build(file);
			
			// Prende l'elemento radice dell'albero cioè dataExchange
			Element root = document.getRootElement();
			
			// Prende l'attributo name di root e crea l'oggetto
			// dataEchange da restituire
			schemaExchange = new SchemaExchange(root.getAttributeValue("name"));

			// Compila il lato source
			// Prende l'elemento source
			Element source = root.getChild("source");
			// Itera sui figli di source
			itRelations = source.getChildren().iterator();
			// Finché ci sono relazioni...
			while(itRelations.hasNext()) {
				// La relazione corrente
				Element relCorrente = itRelations.next();
				
				// Aggiunge la relazione corrente alla lista di relazioni
				// del source
				schemaExchange.addRelationToSource(relCorrente.getAttributeValue("name"));
				// Itera sulle chiavi di relCoorente
				itKeys = relCorrente.getChildren("key").iterator();
				// Finché ci sono chiavi...
				while(itKeys.hasNext()) {
					Element keyCorrente = itKeys.next();
					// Aggiunge la chiave corrente alla lista delle chiavi della
					// relazione corrente
					schemaExchange.addAttributeKeyToSource(
							relCorrente.getAttributeValue("name"),
							keyCorrente.getAttributeValue("name"));
					if(StringUtils.isNotEmpty(keyCorrente.getAttributeValue("constant"))){
						schemaExchange.addSamenessConditionsToKeyInSource(relCorrente.getAttributeValue("name"), keyCorrente.getAttributeValue("name"), keyCorrente.getAttributeValue("constant"));
					}
				}
				// Itera sugli attributi di relCoorente
				itAttributes = relCorrente.getChildren("attribute").iterator();
				// Finché ci sono attributi...
				while(itAttributes.hasNext()) {
					Element attCorrente = itAttributes.next();
					
					// Aggiunge l'attributo corrente alla lista degli
					// attributi di relCorrente
					schemaExchange.addAttributeToSource(
							relCorrente.getAttributeValue("name"),
							attCorrente.getAttributeValue("name"));
					if(StringUtils.isNotEmpty(attCorrente.getAttributeValue("constant"))){
						schemaExchange.addSamenessConditionsToAttributeInSource(relCorrente.getAttributeValue("name"), attCorrente.getAttributeValue("name"), attCorrente.getAttributeValue("constant"));
					}
				}
				
			}
			// A questo punto si occupa dell'aggiungta delle chiavi esterne
			// Itera sulle relazioni del source
			itRelations = source.getChildren().iterator();
			// Finché ci sono relazioni...
			while(itRelations.hasNext()) {
				// La relazione corrente
				Element relCorrente = itRelations.next();
				
				// Itera sulle chiavi esterne di relCorrente
				itFKeys = relCorrente.getChildren("fkey").iterator();
				// Finch� ci sono chiavi...
				while(itFKeys.hasNext()) {
					// L'attributo di chiave esterna corrente
					Element keyCorrente = itFKeys.next();
					
					// Aggiunge la chiave esterna alla relazione relCorrente

					schemaExchange.addAttributeFkeyToSource(
							relCorrente.getAttributeValue("name"),
							keyCorrente.getChildText("refRelName"), 
							keyCorrente.getAttributeValue("name"));
					if(StringUtils.isNotEmpty(keyCorrente.getAttributeValue("constant"))){
						schemaExchange.addSamenessConditionsToFkeyInSource(relCorrente.getAttributeValue("name"), keyCorrente.getAttributeValue("name"), keyCorrente.getAttributeValue("constant"));
					}
				}
			}
			
			// Compila il lato target
			// Prende l'elemento target
			Element target = root.getChild("target");
			// Itera sui figli di target
			itRelations = target.getChildren().iterator();
			// Finché ci sono relazioni...
			while(itRelations.hasNext()) {
				// La relazione corrente
				Element relCorrente = itRelations.next();
				
				// Aggiunge la relazione corrente alla lista di relazioni
				// del source
				schemaExchange.addRelationToTarget(relCorrente.getAttributeValue("name"));
				// Itera sulle chiavi di relCoorente
				itKeys = relCorrente.getChildren("key").iterator();
				// Finché ci sono chiavi...
				while(itKeys.hasNext()) {
					// Aggiunge la chiave corrente alla lista delle chiavi della
					// relazione corrente
					schemaExchange.addAttributeKeyToTarget(
							relCorrente.getAttributeValue("name"),
							itKeys.next().getAttributeValue("name"));
				}
				// Itera sugli attributi di relCoorente
				itAttributes = relCorrente.getChildren("attribute").iterator();
				// Finché ci sono attributi...
				while(itAttributes.hasNext()) {
					// Aggiunge l'attributo corrente alla lista degli
					// attributi di relCorrente
					schemaExchange.addAttributeToTarget(
							relCorrente.getAttributeValue("name"),
							itAttributes.next().getAttributeValue("name"));
				}
				
				// Itera sulle funzioni di relCorrente
				itFunction = relCorrente.getChildren("function").iterator();
				// Finché ci sono funzioni...
				while(itFunction.hasNext()) {
					// La funzione corrente
					Element funCorrente = itFunction.next();
					
					// Aggiunge la funzione corrente alla relazione corrente
					schemaExchange.addFunctionToTarget(
							relCorrente.getAttributeValue("name"),
							funCorrente.getAttributeValue("name"),
							funCorrente.getChildText("attributeName"));
				}
				
			}
			
			// A questo punto si occupa dell'aggiungta delle chiavi esterne
			// Itera sulle relazioni del target
			itRelations = target.getChildren().iterator();
			// Finché ci sono relazioni...
			while(itRelations.hasNext()) {
				// La relazione corrente
				Element relCorrente = itRelations.next();
				
				// Itera sulle chiavi esterne di relCoorente
				itFKeys = relCorrente.getChildren("fkey").iterator();
				// Finché ci sono chiavi...
				while(itFKeys.hasNext()) {
					// L'attributo di chiave esterna corrente
					Element keyCorrente = itFKeys.next();
					
					// Aggiunge la chiave esterna alla relazione relCorrente
					schemaExchange.addAttributeFkeyToTarget(
							relCorrente.getAttributeValue("name"),
							keyCorrente.getChildText("refRelName"), 
							keyCorrente.getAttributeValue("name"));
				
				}
			}
		} catch (JDOMParseException e) {
			System.out.println(e.getMessage());
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			
		} catch (JDOMException e) {
			System.out.println(e.getMessage());
			
		}
		
		return schemaExchange;
		
	}

}
