package rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;


@Path("/riistaservice")
public class HelloRestWorld {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String terve() {
		String output = "Terve!";
		return output;
	}


}