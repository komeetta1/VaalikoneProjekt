package vaalikone;


import java.io.IOException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persist.Vastaukset;

/**
 * Servlet implementation class vastaustenLisays
 */
@WebServlet(description = "Lisää vastauksen tietokantaan", 
			urlPatterns = { "/vastaustenLisays" })
public class vastaustenLisays extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public vastaustenLisays() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
	}
	
	public void lisaaVastaus(Vastaukset vastaus){
        EntityManagerFactory emf=null;
        EntityManager em = null;
        try {
  	      emf=Persistence.createEntityManagerFactory("vaalikones");
  	      em = emf.createEntityManager();
        }
        catch(Exception e) {
          	return;
        }
		
		try {
			em.persist(vastaus);
		} catch(EntityExistsException exe) {
			// KIRJOITA TÄHÄN VIRHEILMOITUS TMS. !
		}
	}
	
}
