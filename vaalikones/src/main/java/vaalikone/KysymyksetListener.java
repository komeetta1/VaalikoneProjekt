package vaalikone;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import persist.Kysymykset;
/**
 * Application Lifecycle Listener implementation class VastausListener
 *
 */
@WebListener
public class KysymyksetListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public KysymyksetListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // "listenerin main"
    	ServletContext sc = sce.getServletContext();
    	Kysymykset kysymys = new Kysymykset(0," ");
    	sc.setAttribute("kyssari", kysymys);
    	
    }
	
}
