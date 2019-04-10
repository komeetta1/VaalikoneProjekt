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
@WebServlet(name= "vastaustenLisays",
		description = "Lisää vastauksen tietokantaan", 
			urlPatterns = { "/vastaustenLisays" })
public class vastaustenLisays extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public vastaustenLisays() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Vastaustenlisaaminenjsp:ltä saatuja vastauksen parametrejä:
		// MALLI: request.getParameter("parametrin nimi");
		
		
		
		String vastaaja;
		String kysymysSTR;
		String[] vastaus;
		String[] perustelu;
		
		// TRY blokki
		try {
			vastaaja = request.getParameter("vastaaja");
			kysymysSTR = request.getParameter("kysymysnro");
			vastaus = request.getParameterValues("vastaus[]");
			perustelu = request.getParameterValues("perusteluBox[]");
		} catch (Exception e) {
			vastaaja = null;
			kysymysSTR = null;
			vastaus = null;
			perustelu = null;
		}
		
		System.out.println("Vastaaja oli: "+vastaaja);
		System.out.println("Kysymys oli: "+kysymysSTR);
		//System.out.println("Vastaus kysymykseen: "+vastaus[]);
		//System.out.println("Perustelu vastaukseen: "+perustelu[kysymys]);
		
		response.getWriter().println(" ! KABOOM YEAH !");
	}
	
	public void lisaaVastaus(Vastaukset kysymyksenvastaus){
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
			// KÄYTÄ em.merge !! Merge korvaa vanhan tiedon!
			em.persist(kysymyksenvastaus);
		} catch(EntityExistsException exe) {
			// KIRJOITA TÄHÄN VIRHEILMOITUS TMS. !
		}
	}
	
}
