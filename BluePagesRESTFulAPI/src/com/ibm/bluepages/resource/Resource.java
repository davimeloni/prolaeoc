package com.ibm.bluepages.resource;

import java.util.ArrayList;
import java.util.List;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ibm.prolaeoc.bluepages.RetrievePersonFromBluepages;

@Path("/bluepages")
public class Resource {
	
//	@Path("{tagname}")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public String search4PersonByName(@PathParam("tagname") String tagName) {
//		List<String> peronsList = new ArrayList<String>();
//		RetrievePersonFromBluepages retrievepersonfrombluepages = new RetrievePersonFromBluepages();
//		peronsList = retrievepersonfrombluepages.getBluePagesUsersByName(tagName);
//		return peronsList.toString();
//	}
	
	@Path("{taguid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String search4PersonByUID(@PathParam("taguid") String tagUID) {
		List<String> peronsList = new ArrayList<String>();
		RetrievePersonFromBluepages retrievepersonfrombluepages = new RetrievePersonFromBluepages();
		peronsList = retrievepersonfrombluepages.getBluePagesUsersByUID(tagUID);
		return peronsList.toString();
	}

//	@Path("{notesId}")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public String search4PersonByNotesID(@PathParam("notesId") String notesId) {
//		List<String> peronsList = new ArrayList<String>();
//		RetrievePersonFromBluepages retrievepersonfrombluepages = new RetrievePersonFromBluepages();
//		peronsList = retrievepersonfrombluepages.getBluePagesUsersByNotesID(PrepareData.formatNotesIdName(notesId));
//		return peronsList.toString();
//	}
//	
	

}
