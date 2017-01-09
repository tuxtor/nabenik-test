package com.nabenik.test.nabeniktest.rest;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.nabenik.test.nabeniktest.controller.MD5EncryptedService;
import com.nabenik.test.nabeniktest.controller.RandomNumberService;

/**
 * Default swarm endpoint, modified for testing purposes
 * @author tuxtor
 *
 */
@Path("/hello")
public class HelloWorldEndpoint {
	
	@Inject
	RandomNumberService randomNumberService;
	
	@Inject
	MD5EncryptedService md5EncryptedService;

	@GET
	@Produces("text/plain")
	public Response doGet() {
		return Response.ok("Hello from WildFly Swarm!").build();
	}
	
	@SuppressWarnings("unused")
	@GET
	@Path("/check-polo")
	@Produces("application/json")
	public List<Integer> checkMarcoPolo(@QueryParam("marco") String marco, @QueryParam("polo") String polo, @QueryParam("md5sum") String md5sum) {
		if(marco==null){
			throw new WebApplicationException(
					Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
					.entity("El parámetro 'marco' es obligatorio.")
					.build()
			);
		}
		if(polo==null){
			throw new WebApplicationException(
					Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
					.entity("El parámetro 'polo' es obligatorio.")
					.build()
			);
		}
		if(md5sum==null){
			throw new WebApplicationException(
					Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
					.entity("El parámetro 'md5' es obligatorio.")
					.build()
			);
		}
		//Parámetros correctos...
		List<Integer> list = randomNumberService.generateNumbers(); 
		String anotherMD5 = md5EncryptedService.generateMD5(marco + polo);
		if(anotherMD5.compareTo(md5sum)==0){
			return list.stream()
				.filter(x -> x > 50)
				.sorted()
				.collect(Collectors.toList());
		}else{
			return list.stream()
				.filter(y -> y < 50)
				.sorted()
				.collect(Collectors.toList());
		}
		
	}
	
	
	
}