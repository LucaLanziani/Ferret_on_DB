package it.uniroma3.gaia.gwt.client;

import it.uniroma3.gaia.gwt.client.gaiaGwtService.Util;

import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class GaiaTabPanel extends DecoratedTabPanel {

	public GaiaTabPanel(final gaiaWeb gaia,Map<String,ResultDTO> result, int id){
		super();
		final int index=id;
		this.setWidth("640px");
		this.setAnimationEnabled(true);
		for(String key : result.keySet()){
			final String chiave=new String(key);
			VerticalPanel panel=new VerticalPanel();
			panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
			HorizontalPanel tmp=new HorizontalPanel();
			tmp.setVerticalAlignment(HorizontalPanel.ALIGN_MIDDLE);
			tmp.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
			tmp.add(getResultHTML(result.get(key)));
			tmp.add(getGraph(result.get(key)));
			tmp.setWidth("640px");
			panel.add(tmp);
			final Button sendButton=new Button("send "+key);
			panel.add(sendButton);
			this.add(panel, key);
			sendButton.addClickHandler(new ClickHandler(){
				@Override
				public void onClick(ClickEvent event) {	
					Util.getInstance().sendDataExchange(chiave, new AsyncCallback<Map<String,ResultDTO>>(){
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Failure");
						}				
						@Override
						public void onSuccess(Map<String,ResultDTO> result) {
							gaia.removeOldTabs(index);
							gaia.addTab(chiave,result);
						}
					});
				}
			});
			
    }
    this.selectTab(0);
    
    
	}
	private Widget getResultHTML(ResultDTO resultDTO) {
		return new HTML(resultDTO.getFormula()+"<br/><br/>"+resultDTO.getTgd());
	}
	//TODO
	private Widget getGraph(ResultDTO resultDTO) {
		
		int i=(int)(Math.random()*9);
		return new Image("images/Albero"+i+".jpg");
	}
}
