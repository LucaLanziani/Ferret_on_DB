package it.uniroma3.gaia.gwt.client;

import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class gaiaWeb implements EntryPoint {
	
	private final DecoratedTabPanel tp = new DecoratedTabPanel();
	public void onModuleLoad() {
		GaiaDockPanel panel=new GaiaDockPanel(this);
		RootPanel rootPanel = RootPanel.get();
		tp.setSize("100%","100%");
		tp.setAnimationEnabled(true);
	    tp.add(panel, "Insert");
	    tp.selectTab(0);
	    tp.getTabBar().addStyleName("gaiaTabBar");
	    rootPanel.add(new Image("images/title.jpg"));
	    rootPanel.add(tp);
		
	}
	public void addTab(String name,Map<String, ResultDTO> result) {
		
		if (result.values().isEmpty())
			Window.alert("no match found!");
		else{
		tp.add(new GaiaTabPanel(this,result,getTabsNumber()),name);
	    tp.selectTab(getTabsNumber()-1);
		}
	    
		
		
	}
	private int getTabsNumber() {
		return tp.getTabBar().getTabCount();
	}
	
	public void removeOldTabs(int id){
		for(int i=getTabsNumber()-1;i>id;i--){
			tp.remove(i);
		}
	}
}
