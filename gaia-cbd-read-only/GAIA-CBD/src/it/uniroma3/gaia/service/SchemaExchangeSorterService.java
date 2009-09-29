package it.uniroma3.gaia.service;

import it.uniroma3.gaia.hibernate.model.SchemaExchange;
import it.uniroma3.gaia.service.dto.SchemaExchangeSortable;

import java.util.List;

public interface SchemaExchangeSorterService {
	
	public List<SchemaExchangeSortable> sort(List<SchemaExchange> list);
	public SchemaExchangeSortable generateSchemaExchangeSortable(SchemaExchangeSortable exchange);
	
}
