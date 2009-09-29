package it.uniroma3.gaia.service.impl;

import it.uniroma3.gaia.hibernate.model.SchemaExchange;
import it.uniroma3.gaia.sama.DataExchange;
import it.uniroma3.gaia.sama.Parser;
import it.uniroma3.gaia.sama.exception.AttributeNotFoundException;
import it.uniroma3.gaia.sama.exception.RelationNotFoundException;
import it.uniroma3.gaia.service.GaiaFrontendService;
import it.uniroma3.gaia.service.SchemaExchangeConverterService;
import it.uniroma3.gaia.service.SchemaExchangeParserService;
import it.uniroma3.gaia.service.SchemaExchangeRetrieverService;
import it.uniroma3.gaia.service.SchemaExchangeSaverService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GaiaFrontendServiceImpl implements GaiaFrontendService {
	
	private SchemaExchangeParserService schemaExchangeParserService;
	private SchemaExchangeSaverService schemaExchangeSaverService;
	private SchemaExchangeRetrieverService schemaExchangeRetrieverService;
	private SchemaExchangeConverterService schemaExchangeConverterService;
	

	public List<DataExchange> getDataExchangeMatchings(File file) {
		List<SchemaExchange> matchList = getSchemaExchangeMatchings(file);	
		List<DataExchange> result = new ArrayList<DataExchange>();
		for(SchemaExchange se: matchList){
			result.add(schemaExchangeConverterService.getDataExchange(se));
		}
		return result;
	}
	
	public List<DataExchange> getFormulaMatchings(File file) {
		List<SchemaExchange> matchList = getSchemaExchangeMatchings(file);	
		List<DataExchange> result = new ArrayList<DataExchange>();
		for(SchemaExchange se: matchList){
			result.add(schemaExchangeConverterService.getFormula(se));
		}
		return result;
	}
	
	public Boolean insertSchemaExchange(File file) {
		return schemaExchangeSaverService.saveSchemaExchange(schemaExchangeParserService.createSchemaExchangeRepo(file));
	}

	public List<DataExchange> getSchemaExchangeMatchings(DataExchange dataExchangeToMatch){
		List<SchemaExchange> matchList = null; 
		List<DataExchange> result = new ArrayList<DataExchange>();
		try {
			it.uniroma3.gaia.sama.SchemaExchange exchangeToMatch = dataExchangeToMatch.createSchemaExchange(null);
			
			matchList = schemaExchangeRetrieverService.getListBySourceRepo(exchangeToMatch);
			if(matchList.isEmpty())
				System.out.println("vuota");
		} catch (RelationNotFoundException e) {
			e.printStackTrace();
		} catch (AttributeNotFoundException e) {
			e.printStackTrace();
		}
		for(SchemaExchange se: matchList){
			result.add(schemaExchangeConverterService.getFormula(se));
		}
		return result;
	}
	
	
	private List<SchemaExchange> getSchemaExchangeMatchings(File file){
		Parser parser = new Parser(file);
		List<SchemaExchange> result = null; 
		try {
			DataExchange dataExchangeToMatch = parser.createDataExchange();
			it.uniroma3.gaia.sama.SchemaExchange exchangeToMatch = dataExchangeToMatch.createSchemaExchange(null);
			
			result = schemaExchangeRetrieverService.getListBySourceRepo(exchangeToMatch);
		} catch (RelationNotFoundException e) {
			e.printStackTrace();
		} catch (AttributeNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void setSchemaExchangeParserService(
			SchemaExchangeParserService schemaExchangeParserService) {
		this.schemaExchangeParserService = schemaExchangeParserService;
	}


	public void setSchemaExchangeSaverService(
			SchemaExchangeSaverService schemaExchangeSaverService) {
		this.schemaExchangeSaverService = schemaExchangeSaverService;
	}


	public void setSchemaExchangeConverterService(
			SchemaExchangeConverterService schemaExchangeConverterService) {
		this.schemaExchangeConverterService = schemaExchangeConverterService;
	}


	public void setSchemaExchangeRetrieverService(
			SchemaExchangeRetrieverService schemaExchangeRetrieverService) {
		this.schemaExchangeRetrieverService = schemaExchangeRetrieverService;
	}

}
