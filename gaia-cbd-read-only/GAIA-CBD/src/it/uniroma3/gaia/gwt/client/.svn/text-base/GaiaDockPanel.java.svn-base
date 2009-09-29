package it.uniroma3.gaia.gwt.client;

import it.uniroma3.gaia.gwt.client.gaiaGwtService.Util;

import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GaiaDockPanel extends DockPanel {
	public GaiaDockPanel(final gaiaWeb gaia){
		super();
		this.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		final Button sendButton=new Button("send");
		final TextArea text=new TextArea();
		text.setPixelSize(500,300);
		VerticalPanel tmp=new VerticalPanel();
		tmp.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		tmp.add(new HTML("<h1>Insert your Data-Exchange</h1>"));
		tmp.add(text);
		tmp.add(sendButton);
		this.add(tmp, DockPanel.CENTER);
		sendButton.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				Util.getInstance().sendSchemaExchange(text.getText(), new AsyncCallback<Map<String,ResultDTO>>(){
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Failure");
					}
					
					@Override
					public void onSuccess(Map<String,ResultDTO> result) {
						gaia.removeOldTabs(0);
						if(result==null)
							Window.alert("Nessun risultato");
						else
							gaia.addTab("risultato",result);
					}
				});
			}
		});		
	}
}
