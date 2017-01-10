package com.nabenik.test.nabeniktest.rest;

import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

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
		
		if (marco == null) {
		    throw new WebApplicationException(
		      Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
		        .entity("marco parameter is mandatory")
		        .build()
		    );
		  }
		  if (polo == null) {
		    throw new WebApplicationException(
		      Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
		        .entity("polo parameter is mandatory")
		        .build()
		    );
		  }
		  if (md5sum == null) {
		    throw new WebApplicationException(
		      Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
		        .entity("polo parameter is mandatory")
		        .build()
		    );
		  }

		
		if(md5sum.compareTo(md5(marco+polo))==0){
			return randomNumberService.generateNumbers();
		}else{
			return randomNumberService.generateNumbers2();
		}
		
	}
	
	public String md5(String input){
		try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
	}
	
}