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
		
		//RequestDispatcher rdVH=request.getRequestDispatcher("VastaustenHaku");
		//rdVH.include(request, response);
		
		List<Kysymykset> kaikkiKysymykset=(List<Kysymykset>)(request.getAttribute("kaikkiKysymykset"));
		int lkm=kaikkiKysymykset.size();
		String[] vastaus=new String[lkm];
		String[] perustelu=new String[lkm];
		String[] kysymysnro=new String[lkm];

		for (int i=0;i<lkm;i++) {
			Kysymykset k=kaikkiKysymykset.get(i);
			kysymysnro[i]=request.getParameter("kysymysnro"+k.getKysymysId());
			vastaus[i]=request.getParameter("vastaus"+k.getKysymysId());
			perustelu[i]=request.getParameter("perusteluBox"+k.getKysymysId());
		}
		
		
		
		String vastaaja;
		String kysymysSTR;

		int kysymysmaara = 0;
		
		// TRY blokki
		try {
			vastaaja = request.getParameter("vastaaja");
			//kysymysSTR = request.getParameter("kysymysnro");
		} catch (Exception e) {
			vastaaja = null;
			//kysymysSTR = null;
			vastaus = null;
			perustelu = null;
		}
		
		// L‰hett‰‰ vastaaja-parametrin VastaustenHaku-servletille
		request.setAttribute("vastannut", vastaaja);
		
		// Debuggausta varten kirjoittaa konsoliin asioita
		System.out.println("Vastaaja oli: "+vastaaja);
		//System.out.println("Kysymys oli: "+kysymysSTR);
		System.out.println("Kysymyksi‰ kannassa: "+lkm);
		
		for (int i=0; i<lkm;i++) {
			System.out.print("Vastaus kysymykseen: "+kysymysnro[i]);
			System.out.println(" on: "+vastaus[i]);
			System.out.println("Ja perustelut: "+perustelu[i]+"\n");
		}
		
		// Tulostaa selaimeen teksti‰ merkkin‰ ett‰ virheit‰ ei esiintynyt:
		//response.getWriter().println(" ! KABOOM YEAH !");
		System.out.println("KABOOM YEAH, tavaraa meni kantaan, ehk‰?");
		
		// Luodaan vastauksesta, siis yhden kysymyksen vastauksesta olio.
		
		VastauksetPK vastausolioPK = new VastauksetPK(Integer.parseInt(vastaaja),0);
		Vastaukset vastausolio = new Vastaukset();
		
		for (int i=0; i<lkm;i++) {
			vastausolioPK.setKysymysId(Integer.parseInt(kysymysnro[i]));
			vastausolio.setVastauksetPK(vastausolioPK);
			vastausolio.setVastaus(Integer.parseInt(vastaus[i]));
			vastausolio.setKommentti(perustelu[i]);
			
			boolean onvst;
			onvst = request.getAttribute("onkovastannut") != null;
			
			lisaaVastaus(vastausolio, onvst);
		}
		
	}
	
	public void lisaaVastaus(Vastaukset kysymyksenvastaus, boolean onvst){
        EntityManagerFactory emf=null;
        EntityManager em = null;
        try {
  	      emf=Persistence.createEntityManagerFactory("vaalikones");
  	      em = emf.createEntityManager();
        }
        catch(Exception e) {
          	return;
        }
        
        em.getTransaction().begin();
		try {
			if (onvst == true) {
				em.merge(kysymyksenvastaus);
			} else {
				em.persist(kysymyksenvastaus);
			}
			
		} catch(EntityExistsException exe) {
			// KIRJOITA TƒHƒN VIRHEILMOITUS TMS. !
		}
		em.getTransaction().commit();
		em.close();

	}

	/* Metodi, jota ei koskaan saatu valmiiksi; aika ajoi sen ohi
	public void tarkistaVastaus(VastauksetPK vastausolioPK) {
		
	}
	*/
}
