package rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.io.PrintWriter;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
			Ehdokkaat asd=em.find(Ehdokkaat.class, id);
			em.remove(asd);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}return ID;
	}
	
	@GET
	@Path("/tuoEhdokkaat")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ehdokkaat> getKaikki() {
		EntityManagerFactory emf=null;
        EntityManager em = null;
        try {
  	      emf=Persistence.createEntityManagerFactory("vaalikones");
  	      em = emf.createEntityManager();
        }
        catch(Exception e) {
          	return null;
        }
        try {
        	List<Ehdokkaat> lista = new ArrayList<>();
        	Query query = em.createNativeQuery("SELECT ehdokas_id, sukunimi, etunimi FROM ehdokkaat");
        	lista = query.getResultList();
        	return lista;
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
        finally {
        	em.close();
        }
        return null;
	}
	
	
}