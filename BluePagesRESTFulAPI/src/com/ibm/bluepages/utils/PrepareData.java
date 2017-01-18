package com.ibm.bluepages.utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.ibm.bluepages.BPResults;
import com.ibm.prolaeoc.bluepages.model.Person;


public class PrepareData {
	
	public static String formatNotesIdName(String notesId){
		
		String completeNotesId = null;
		
		if (notesId.contains("/")){
			
			String[] notesIdName = notesId.split("/");
			
			completeNotesId = "CN=" + notesIdName[0] + "/OU=" + notesIdName[1] + "/O=" + notesIdName[2]; 
			System.out.println("Notes id to be used in BP search: " + completeNotesId);			
			
		} else {
			System.out.println("Invalid format for : " + notesId);
			return null;
		}
		
		return completeNotesId;
	}
	
	
	public static List<String> storePersonDataFromBluepages(BPResults results) {
		
        Hashtable<String,String> row;     // One row of the results
        List<String> peronsList = new ArrayList<String>();
        
		/*
         * Make sure the method didn't fail unexpectedly.
         */
        if (!results.succeeded()) {
            System.err.println("Error: " + results.getStatusMsg());
            return null;
        }
		
        /*
         * Display the tie line and e-mail address for each matching
         * person.
         */
        if (results.rows() == 0)
            System.err.println("No matches found.");
        else {
            System.out.println(results.rows() + " record(s) found:\n");
            
            for (int i = 0; i < results.rows(); i++) {
            	
            	
            	Person person =  new Person();
            	// Getting row
                row = results.getRow(i);
                
                // Set persons data
                person.setUid(row.get("CNUM").toString());
                person.setBuilding(row.get("BLDG").toString());
                person.setLocation(row.get("LOCCITY").toString());
                person.setOfficePhone(row.get("XPHONE").toString());
         
//                person.setOrgtitle("NOT FOUND");
//                person.setId("NOT FOUND");
                
                person.setMobilePhone(row.get("CELLULAR").toString());
                person.setBio(row.get("JOBRESPONSIB").toString());
                
                if (row.get("EMPTYPE").toString().compareTo("C") == 0) {
                	// Contractor
                	person.setIsEmployee("false");                	
                } else {
                	// IBMer
                	person.setIsEmployee("true");
                }
                
                person.setFloor(row.get("FLOOR").toString());
                person.setOffice(row.get("OFFICE").toString());
                person.setEmail(row.get("INTERNET").toString());
                person.setName(row.get("NAME").toString());
                person.setImageUrl("NOT FOUND");
                person.setNotesId(row.get("NOTESID").toString());
                person.setName(row.get("NAME").toString());
                person.setImageUrl("http://w3.ibm.com/bluepages/photo/ImageServlet.wss/"+person.getUid()+ ".jpg?cnum=" + person.getUid());
                
                // Convert to JSON
                System.out.println(person.toJSON());
                
                // Add to the list
                peronsList.add(person.toJSON());
               
            }
        }        
        return peronsList;				
	}
}
