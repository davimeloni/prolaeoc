package com.ibm.prolaeoc.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ibm.prolaeoc.model.Badge;
import com.ibm.prolaeoc.model.Person;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

@ManagedBean(name="testebpbean")
public class TesteBluePages {
	
	public List<Badge> getPersonBP() {
	
		Client c = Client.create();
	    WebResource wr = c.resource("http://localhost:8080/BluePagesRESTFulAPI/bluepages/100407631" );
	    String json = wr.get(String.class);
	    Gson gson = new Gson();
	    return gson.fromJson(json, new TypeToken<List<Badge>>(){}.getType());
	  }
	
	public Badge getBadgeBP(String uid) {
		
		Client c = Client.create();
	    WebResource wr = c.resource("http://localhost:8080/BluePagesRESTFulAPI/bluepages/100407631" + uid);
	    String json = wr.get(String.class);
	    Gson gson = new Gson();
	    return gson.fromJson(json, new TypeToken<Badge>(){}.getType());
	  }

}
