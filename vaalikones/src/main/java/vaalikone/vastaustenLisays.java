package vaalikone;


import java.io.IOException;
import java.util.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persist.*;

/**
 * Servlet implementation class vastaustenLisays
 */
@WebServlet(name= "vastaustenLisays",
		description = "Lis‰‰ vastauksen tietokantaan", 
			urlPatterns = { "/vastaustenLisays" })
public class vastaustenLisays extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public vastaustenLisays() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Vastaustenlisaaminenjsp:lt‰ saatuja vastauksen parametrej‰:
		// MALLI: request.getParameter("parametrin nimi");
		
		RequestDispatcher rdKH=request.getRequestDispatcher("KysymystenHaku");
		rdKH.include(request, response);
		List<Kysymykset> kaikkiKysymykset=(List<Kysymykset>)(request.getAttribute("kaikkiKysymykset"));
		int lkm=kaikkiKysymykset.size();
		String[] vastaus=new String[lkm];
		String[] perustelu=new String[lkm];

		for (int i=0;i<lkm;i++) {
			Kysymykset k=kaikkiKysymykset.get(i);
			vastaus[i]=request.getParameter("vastaus"+k.getKysymysId());
			perustelu[i]=request.getParameter("perusteluBox"+k.getKysymysId());
		}
		
		String vastaaja;
		String kysymysSTR;

		int kysymysmaara = 0;
		
		// TRY blokki
		try {
			vastaaja = request.getParameter("vastaaja");
			kysymysSTR = request.getParameter("kysymysnro");
		} catch (Exception e) {
			vastaaja = null;
			kysymysSTR = null;
			vastaus = null;
			perustelu = null;
		} 
		
		//kysymysmaara = vastaus.length;
		
		// Debuggausta varten kirjoittaa konsoliin asioita
		System.out.println("Vastaaja oli: "+vastaaja);
		System.out.println("Kysymys oli: "+kysymysSTR);
		System.out.println(kysymysmaara);
		
		for (String H : vastaus) {
			System.out.println("Vastaus kysymykseen nro."+kysymysSTR+" on: "+H);
			//System.out.println("Perustelut: "+);
		}
		
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
			// KƒYTƒ em.merge !! Merge korvaa vanhan tiedon!
			em.persist(kysymyksenvastaus);
		} catch(EntityExistsException exe) {
			// KIRJOITA TƒHƒN VIRHEILMOITUS TMS. !
		}
	}
	
}
