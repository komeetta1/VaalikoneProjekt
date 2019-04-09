<%-- 
    Document   : vastaus
    Created on : 09-Apr-2015, 12:50:47
    Author     : Jonne
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page
	import="java.util.*,vaalikone.Vaalikone,vaalikone.KysymystenHaku,persist.*"%>
<%@page
	import="java.util.*,vaalikone.KysymystenHaku,vaalikone.EhdokkaidenHaku,persist.*"%>
<%@page import = "javax.persistence.EntityManagerFactory" %>
<%@page import = "javax.persistence.EntityManager" %>
<%@page import = "javax.persistence.Persistence" %>
<%@page import = "javax.persistence.Query" %>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Diginide Vaalikone 2.0</title>

<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>

	<div id="container">
		<img id="headerimg" src="images/Logo.png" width="500" height="144"
			alt="" />


		<%-- <%
			@SuppressWarnings("unchecked")
			List<Kysymykset> kysymykset = (List<Kysymykset>) request.getAttribute("kysymykset");
			for (Kysymykset kysymys : kysymykset) {
		%>
		<div class="kysymys">
			<%=kysymys.getKysymysId()%>
			/
			<%
				EntityManagerFactory emf = null;
					EntityManager em = null;
					try {
						emf = Persistence.createEntityManagerFactory("vaalikones");
						em = emf.createEntityManager();
					} catch (Exception e) {
						response.getWriter().println("EMF+EM EI Onnistu");

						e.printStackTrace(response.getWriter());

						return;
					}
					Query q = em.createQuery("SELECT COUNT(*) FROM kysymykset");
					out.println(q);
			%><br>
			<%=kysymys.getKysymys()%>
		</div>
		<form action="Vaalikone" id="vastausformi">
			<label>1</label><input type="radio" name="vastaus" value="1" /> <label>2</label><input
				type="radio" name="vastaus" value="2" /> <label>3</label><input
				type="radio" name="vastaus" value="3" checked="checked" /> <label>4</label><input
				type="radio" name="vastaus" value="4" /> <label>5</label><input
				type="radio" name="vastaus" value="5" /> <input type="hidden"
				name="q" value="<%=kysymys.getKysymysId()%>"> <input
				type="submit" id="submitnappi" value="Vastaa" />
		</form>
		<div class="kysymys">
			<small>1 = Täysin eri mieltä<br>2 = Osittain eri mieltä<br>3
				= En osaa sanoa<br>4 = Osittain samaa mieltä<br>5 = Täysin
				samaa mieltä
			</small>
		</div>
		<%
			}
		%> --%>


		<%
					RequestDispatcher rd=request.getRequestDispatcher("KysymystenHaku");
					rd.include(request, response);
		 
		            @SuppressWarnings("unchecked")
		            
		            List<Kysymykset> kysymykset = (List<Kysymykset>)request.getAttribute("kysymykset");
					int size = 0;
					size = kysymykset.size();
					
		            for (Kysymykset kysymys : kysymykset) { %>
		            <div class="kysymys">
		                <%= kysymys.getKysymysId() %> / 19 <%-- <%= size %>--%> <br>
		                <%= kysymys.getKysymys() %>
		                 </div>
		                <form action="Vaalikone" id="vastausformi">
		                    <label>1</label><input type="radio" name="vastaus" value="1" />
		                    <label>2</label><input type="radio" name="vastaus" value="2" />
		                    <label>3</label><input type="radio" name="vastaus" value="3" checked="checked" />
		                    <label>4</label><input type="radio" name="vastaus" value="4" />
		                    <label>5</label><input type="radio" name="vastaus" value="5" />
		                    <input type="hidden" name="q" value="<%= kysymys.getKysymysId() %>">
		                    <input type="submit" id="submitnappi" value="Vastaa" />
		                </form>
		                    <div class="kysymys"><small>1=Täysin eri mieltä 2=Osittain eri mieltä 3=En osaa sanoa, 4=Osittain samaa mieltä 5=Täysin samaa mieltä</small></div>
		                <%
		            } 
		        %>



	</div>

</body>
</html>
