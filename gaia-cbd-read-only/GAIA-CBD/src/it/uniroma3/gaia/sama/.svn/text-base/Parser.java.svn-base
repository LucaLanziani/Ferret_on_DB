package it.uniroma3.gaia.sama;

import it.uniroma3.gaia.sama.exception.RelationNotFoundException;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.JDOMParseException;
import org.jdom.input.SAXBuilder;



/**
 * Questa classe si occupa di caricare un data schema da un file xml. <br/>
 * Per la lettura sfrutta il package jdom.
 */
public class Parser {
	// Il nome del file xml
	private File file;
	
	/**
	 * Crea un oggetto Parser. <br />
	 * Notare che fileName deve essere espresso nel seguente modo: 
	 * C:/file.xml ovvero <b>usando il carattere / al posto del carattere \</b>
	 * <br />
	 * Prima di procedere con la costruzione dell'oggetto dataExchange,
	 * effettua una validazione del file XML e in caso di non conformit� al 
	 * dtd, lo segnala tramite una eccezione in console.
	 * @param fileName il nome del file xml da aprire e analizzare
	 */
	public Parser(String fileName) {
		// Imposta il nome del file
		this.file = new File(fileName);
		
	}
	
	public Parser(File file) {
		// Imposta il nome del file
		this.file = file;
		
	}
	
	/**
	 * Questo metodo imposta il path e nome del file xml contenente la 
	 * descrizione di un data exchange. 
	 * @param fileName il path del file XML con la descrizione del data 
	 * exchange 
	 */
	public void setFile(File file) {
		this.file = file;
		
	}
	
	/**
	 * Questo metodo analizza il file XML contente la descrizione del
	 * data exchange e ne costruisce il corrispondente oggetto DataExchange
	 * @return il data exchange descritto nel file XML 
	 * @throws RelationNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public DataExchange createDataExchange() throws RelationNotFoundException {
		// Il data exchange da restituire
		DataExchange dataExchange=null;
		
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
			Document document = sb.build(this.file);
			
			// Prende l'elemento radice dell'albero cioè dataExchange
			Element root = document.getRootElement();
			
			// Prende l'attributo name di root e crea l'oggetto
			// dataEchange da restituire
			dataExchange = new DataExchange(root.getAttributeValue("name"));

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
				dataExchange.addRelationToSource(relCorrente.getAttributeValue("name"));
				// Itera sulle chiavi di relCoorente
				itKeys = relCorrente.getChildren("key").iterator();
				// Finché ci sono chiavi...
				while(itKeys.hasNext()) {
					// Aggiunge la chiave corrente alla lista delle chiavi della
					// relazione corrente
					dataExchange.addAttributeKeyToSource(
							relCorrente.getAttributeValue("name"),
							itKeys.next().getAttributeValue("name"));
				}
				// Itera sugli attributi di relCoorente
				itAttributes = relCorrente.getChildren("attribute").iterator();
				// Finché ci sono attributi...
				while(itAttributes.hasNext()) {
					// Aggiunge l'attributo corrente alla lista degli
					// attributi di relCorrente
					dataExchange.addAttributeToSource(
							relCorrente.getAttributeValue("name"),
							itAttributes.next().getAttributeValue("name"));
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

					dataExchange.addAttributeFkeyToSource(
							relCorrente.getAttributeValue("name"),
							keyCorrente.getChildText("refRelName"), 
							keyCorrente.getAttributeValue("name"));
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
				dataExchange.addRelationToTarget(relCorrente.getAttributeValue("name"));
				// Itera sulle chiavi di relCoorente
				itKeys = relCorrente.getChildren("key").iterator();
				// Finché ci sono chiavi...
				while(itKeys.hasNext()) {
					// Aggiunge la chiave corrente alla lista delle chiavi della
					// relazione corrente
					dataExchange.addAttributeKeyToTarget(
							relCorrente.getAttributeValue("name"),
							itKeys.next().getAttributeValue("name"));
				}
				// Itera sugli attributi di relCoorente
				itAttributes = relCorrente.getChildren("attribute").iterator();
				// Finché ci sono attributi...
				while(itAttributes.hasNext()) {
					// Aggiunge l'attributo corrente alla lista degli
					// attributi di relCorrente
					dataExchange.addAttributeToTarget(
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
					dataExchange.addFunctionToTarget(
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
					dataExchange.addAttributeFkeyToTarget(
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
		
		return dataExchange;
		
	}

}
