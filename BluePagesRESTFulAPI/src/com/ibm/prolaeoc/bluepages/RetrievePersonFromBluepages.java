package com.ibm.prolaeoc.bluepages;


import java.util.List;
import com.ibm.bluepages.BluePages;
import com.ibm.bluepages.BPResults;
import com.ibm.bluepages.utils.PrepareData;



public class RetrievePersonFromBluepages {

	private List<String> peronsList;
	
	
	public List<String> getPeronsList() {
		return peronsList;
	}


	public void setPeronsList(List<String> peronsList) {
		this.peronsList = peronsList;
	}


	public List<String> getBluePagesUsersByName(String targetName) {
		
		/**
		 * Calling method from Bluepages API that retrieves person by part of the name
		 * BPResults represents a table constructed by the results retrieved 
		 * from Web Server API (WSAPI) functions by certain methods in the BluePages class. 
		 */
		BPResults results = BluePages.getPersonsByNameFuzzy(targetName);
		
		this.setPeronsList(PrepareData.storePersonDataFromBluepages(results));        
		
        return this.getPeronsList();
	}
	
	
	public List<String> getBluePagesUsersByNotesID(String notesID) {
		
		
		/**
		 * Calling method from Bluepages API that retrieves person by notes ID
		 * BPResults represents a table constructed by the results retrieved 
		 * from Web Server API (WSAPI) functions by certain methods in the BluePages class. 
		 */
		
		BPResults results = BluePages.getPersonsByNotesID(notesID,"UTF-8");
		
		this.setPeronsList(PrepareData.storePersonDataFromBluepages(results));
		
        return this.getPeronsList();
	}
	
public List<String> getBluePagesUsersByUID(String targetUID) {
		
		/**
		 * Calling method from Bluepages API that retrieves person by part of the name
		 * BPResults represents a table constructed by the results retrieved 
		 * from Web Server API (WSAPI) functions by certain methods in the BluePages class. 
		 */
		BPResults results = BluePages.getPersonByCnum(targetUID);
		
		this.setPeronsList(PrepareData.storePersonDataFromBluepages(results));        
		
        return this.getPeronsList();
	}
}
