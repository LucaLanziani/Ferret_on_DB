package it.uniroma3.gaia.gwt.server;

import it.uniroma3.gaia.gwt.client.ResultDTO;
import it.uniroma3.gaia.gwt.client.gaiaGwtService;
import it.uniroma3.gaia.sama.DataExchange;
import it.uniroma3.gaia.service.GaiaFrontendService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sun.org.apache.xml.internal.security.Init;

public class gaiaGwtServiceImpl extends RemoteServiceServlet implements gaiaGwtService {

	private Map<String,DataExchange> name2DataExchange =new HashMap<String,DataExchange>();
	@Override
	public Map<String,ResultDTO> sendSchemaExchange(String input) {
		File file= new File("tmp");
		try {
			FileWriter fw= new FileWriter(file);
			fw.write(input);
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		List<DataExchange> de= null;
		try{
		de= this.getGaiaFrontendService().getDataExchangeMatchings(file);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		file.delete();
		}
		return generateResult(de);
	}
	public Map<String,ResultDTO> sendDataExchange(String name) {
		
		DataExchange dEx=name2DataExchange.get(name);
		DataExchange tmp=new DataExchange("tmp");
		tmp.setSource(dEx.getRelationOfTarget());
		List<DataExchange> de= null;
		try{
		de= this.getGaiaFrontendService().getSchemaExchangeMatchings(tmp);
		}catch(Exception e){
			e.printStackTrace();
		}
		return generateResult(de);
	}
	private Map<String, ResultDTO> generateResult(List<DataExchange> de) {
		if(de==null){
			return null;
		}
		Map<String,ResultDTO> name2formula= new HashMap<String,ResultDTO>();
		for(DataExchange d : de){
			name2DataExchange.put(d.getName(),d);
			ResultDTO res= new ResultDTO();
			res.setDEname(d.getName());
			res.setFormula(d.getLogicalFormula());
			res.setTgd(d.getTgd());
			name2formula.put(d.getName(),res);
		}
		return name2formula;
	}
	private GaiaFrontendService getGaiaFrontendService() {
		ApplicationContext factory = new ClassPathXmlApplicationContext("it/uniroma3/gaia/spring/gaia-spring.xml");
		GaiaFrontendService service = (GaiaFrontendService) factory.getBean("gaiaFrontendService");
		return service;
	}
	
}
