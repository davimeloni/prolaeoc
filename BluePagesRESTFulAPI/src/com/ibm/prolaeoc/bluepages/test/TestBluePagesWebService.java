package com.ibm.prolaeoc.bluepages.test;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ibm.bluepages.utils.PrepareData;
import com.ibm.prolaeoc.bluepages.RetrievePersonFromBluepages;


public class TestBluePagesWebService {
	
	private HttpServer server;

	@Before
	public void startServer(){
		this.server = Server.startServer();
	}
	
	@After
	public void stopServer(){
		server.stop();
	}
	
	
	@Test
	public void testGetBluePagesUsersByName() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		String conteudo = target.path("bluepages/dmeza").request().get(String.class);
		System.out.println(conteudo);
		
	}
	
	@Test
	public void testGetBluePagesUsersByNotesID(){
		
		List<String> peronsList = new ArrayList<String>();
		
		RetrievePersonFromBluepages personfrombluepages = new RetrievePersonFromBluepages();
		
		String test = PrepareData.formatNotesIdName("playerc/San Jose/IBM@IBMUS");
		
		personfrombluepages.getBluePagesUsersByNotesID(test);
		
		for(String person: peronsList) {
			System.out.println(person);
		}
		
		System.out.println("----------------------------------------------------");
		personfrombluepages.getBluePagesUsersByName("Player");
	}

}
