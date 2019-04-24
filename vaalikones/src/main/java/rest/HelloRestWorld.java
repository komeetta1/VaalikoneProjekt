package rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.persistence.Query;

//import data.Riista;
import persist.Ehdokkaat;
import persist.Kysymykset;

@Path("/vaalikone")
public class HelloRestWorld {

	@GET
	@Path("/terve")
	@Produces(MediaType.TEXT_PLAIN)
	public String tervehdi() {
		String output = "Terve, Pena!";
		return output;
	}

	@GET
	@Path("/tuoEhdokkaat")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Ehdokkaat> getKaikki() {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("vaalikones");
			em = emf.createEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<Ehdokkaat> list = new ArrayList<>();
		Query query = em.createNamedQuery("Ehdokkaat.findAll");

		list.addAll(query.getResultList());
		return list;
	}
}