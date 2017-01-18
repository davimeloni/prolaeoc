package com.ibm.prolaeoc.bluepages.test;

import java.io.IOException;
import java.net.URI;

/*
 * https://grizzly.java.net/httpserverframework.html
 */
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;


public class Server {

	public static HttpServer startServer(){
		ResourceConfig config = new ResourceConfig().packages("com.ibm.bluepages");
		URI uri = URI.create("http://localhost:8080/");
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		return server;
	}

	public static void main(String[] args) throws IOException {

		URI uri = URI.create("http://localhost:8080/");
		ResourceConfig config = new ResourceConfig().packages("com.ibm.bluepages");
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		System.out.println("Servidor rodando");
		System.in.read();
		server.stop();
	}
	
}
