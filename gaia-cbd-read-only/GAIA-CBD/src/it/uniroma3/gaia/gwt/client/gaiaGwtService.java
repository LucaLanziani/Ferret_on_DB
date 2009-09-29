package it.uniroma3.gaia.gwt.client;


import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("gaiaGwtService")
public interface gaiaGwtService extends RemoteService {
	
	Map<String,ResultDTO> sendSchemaExchange(String input);
	Map<String,ResultDTO> sendDataExchange(String name);
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static gaiaGwtServiceAsync instance;
		public static gaiaGwtServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(gaiaGwtService.class);
			}
			return instance;
		}
	}
}
