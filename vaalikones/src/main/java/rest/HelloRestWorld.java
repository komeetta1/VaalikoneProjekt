package rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.io.PrintWriter;
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

	//Ehdokkaiden lisäys
	@POST
	@Path("/lisaa")
	@Consumes(MediaType.APPLICATION_JSON)
	public void getEhdokkaat(Ehdokkaat x) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("vaalikones");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(x);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Ehdokkaiden muokkaus
	@POST
	@Path("/muokkaa")
	@Consumes(MediaType.APPLICATION_JSON)
	public void muokkaaEhdokkaat(Ehdokkaat x) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("vaalikones");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(x);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Ehdokkaiden poisto
	@GET
	@Path("/poista/{param}")
	@Produces(MediaType.TEXT_PLAIN)
	public String poistaEhdokkaat(@PathParam("param")int id) {
		String ID = "Poistettu ID " + id;
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("vaalikones");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Ehdokkaat x=em.find(Ehdokkaat.class, id);
			em.remove(x);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ID;
	}
}