package it.uniroma3.gaia.service.impl;

import it.uniroma3.gaia.hibernate.HibernateHelper;
import it.uniroma3.gaia.hibernate.HibernateHelperException;
import it.uniroma3.gaia.hibernate.dao.SchemaExchangeDao;
import it.uniroma3.gaia.hibernate.dao.SchemaExchangeTypeDao;
import it.uniroma3.gaia.hibernate.model.Atom;
import it.uniroma3.gaia.hibernate.model.AtomTypeEnum;
import it.uniroma3.gaia.hibernate.model.SchemaExchange;
import it.uniroma3.gaia.hibernate.model.SchemaExchangeType;
import it.uniroma3.gaia.sama.Exchange;
import it.uniroma3.gaia.service.GaiaService;
import it.uniroma3.gaia.service.SchemaExchangeConverterService;
import it.uniroma3.gaia.service.SchemaExchangeParserService;
import it.uniroma3.gaia.service.SchemaExchangeRetrieverService;
import it.uniroma3.gaia.service.SchemaExchangeSaverService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class SchemaExchangeSaverServiceImpl extends GaiaService implements SchemaExchangeSaverService{
	
	private SchemaExchangeConverterService schemaExchangeConverterService;// = (SchemaExchangeConverterService)ServiceFactory.getInstance(ServiceMapping.SCHEMA_EXCHANGE_CONVERTER);

	private SchemaExchangeRetrieverService schemaExchangeRetrieverService;// = (SchemaExchangeRetrieverService)ServiceFactory.getInstance(ServiceMapping.SCHEMA_EXCHANGE_RETRIEVER);
	
	private SchemaExchangeDao schemaExchangeDao;// = (SchemaExchangeDao)DaoFactory.getInstance(DaoMapping.SCHEMA_EXCHANGE);
	
	private SchemaExchangeTypeDao schemaExchangeTypeDao;
	
	/* Metodo che dato un exchange in modello di dominio, lo trasforma in un 
	 * exchange in modello di memorizzazione e lo salva. */
	/* (non-Javadoc)
	 * @see it.uniroma3.gaia.service.SchemaExchangeSaverService#saveSchemaExchange(it.uniroma3.gaia.sama.Exchange, java.lang.String)
	 */
	public void saveSchemaExchange(Exchange exchange, String description){
			
			SchemaExchange se = schemaExchangeConverterService.convertToRepository(exchange, description);
			saveSchemaExchange(se);
	}
	
	public Boolean saveSchemaExchange(SchemaExchange se){
		Boolean result = Boolean.FALSE;
		try{
			int countSn = 0;
			int countDx = 0;
			if(se.getSchemaExchangeType()==null){
				for(Atom a: se.getAtoms()){
					if(a.getAtomType().getId().equals(AtomTypeEnum.RELATION.getId())){
						if(StringUtils.equals(a.getSide(), "sn")){
							countSn++;
						}else if(StringUtils.equals(a.getSide(), "dx")){
							countDx++;
						}
					}
				}
			}else{
				countSn = se.getSchemaExchangeType().getSnRelNum();
				countDx = se.getSchemaExchangeType().getDxRelNum();
			}
			SchemaExchangeType schemaExchangeType = schemaExchangeTypeDao.getByRelNumSnDx(new Integer(countSn), new Integer(countDx));
			se.setSchemaExchangeType(schemaExchangeType);
			HibernateHelper.beginTransaction();
//			List<SchemaExchange> list = schemaExchangeDao.getListByExample(se);
			List<SchemaExchange> list = schemaExchangeDao.getListBySourceTargetRelNumber(countSn, countDx);
			if(list.isEmpty()){
				schemaExchangeDao.save(se);
				result = Boolean.TRUE;
				log.info("Schema Exchange " + se.toString() + " inserted in the repository.");
			}else{
				List<Atom> atomiSource = new ArrayList<Atom>();
				atomiSource.addAll(se.getAtoms());
				List<SchemaExchange> res = schemaExchangeRetrieverService.findMatching(atomiSource, list, false);
				if(res.isEmpty()){
					schemaExchangeDao.save(se);
					result = Boolean.TRUE;
					log.info("Schema Exchange " + se.toString() + " inserted in the repository.");
				}else{
					log.info("Schema Exchange not inserted in the repository: found matchings:");
					for(SchemaExchange s:res){
						log.info("\t " + s.getDescription());
					}
				}
			}
			HibernateHelper.endTransaction(true);
		} catch (HibernateHelperException e) {
			HibernateHelper.endTransaction(false);
			e.printStackTrace();
		}finally{
			HibernateHelper.closeSession();
		}
		return result;
	}
	
	public void setSchemaExchangeConverterService(
			SchemaExchangeConverterService schemaExchangeConverterService) {
		this.schemaExchangeConverterService = schemaExchangeConverterService;
	}

	public void setSchemaExchangeRetrieverService(
			SchemaExchangeRetrieverService schemaExchangeRetrieverService) {
		this.schemaExchangeRetrieverService = schemaExchangeRetrieverService;
	}

	public void setSchemaExchangeDao(SchemaExchangeDao schemaExchangeDao) {
		this.schemaExchangeDao = schemaExchangeDao;
	}
	
	public void setSchemaExchangeTypeDao(SchemaExchangeTypeDao schemaExchangeTypeDao){
		this.schemaExchangeTypeDao = schemaExchangeTypeDao;
	}
}
