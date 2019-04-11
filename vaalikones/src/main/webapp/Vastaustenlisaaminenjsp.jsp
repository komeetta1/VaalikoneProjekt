<%@page import="java.awt.print.Printable"%>
<%@page import="java.util.*,vaalikone.KysymystenHaku,vaalikone.EhdokkaidenHaku,persist.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Shynet vaalikoneen vastausten lis‰‰minen</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<%  
		RequestDispatcher rdKH=request.getRequestDispatcher("KysymystenHaku");
		rdKH.include(request, response);
		List<Kysymykset> kysymykset = (List<Kysymykset>)request.getAttribute("kaikkiKysymykset");
		int kysymysmaara = kysymykset.size();
		
		RequestDispatcher rdEH=request.getRequestDispatcher("EhdokkaidenHaku");
		rdEH.include(request, response);
		List<Ehdokkaat> ehdokkaat = (List<Ehdokkaat>)request.getAttribute("kaikkiEhdokkaat");
		%>

		<img id="headerimg" src="Logo.png" width="720" />
		<div class="kysymys">
			<h1>Vaalikone</h1>
			<br>
			<h3>Kysymyksiin vastaaminen</h3>
		</div>
		<br>
		<form action="/vastaustenLisays" method="GET">
		<br>
		<br>
		<center>
		<br><p>Vastaaja:</p>
			<select name="vastaaja">
				<% /** Looppi, joka k‰y tietokannasta saadut ehdokkaat l‰pi ja tulostaa ne dropdown-listaan*/
					for(Ehdokkaat ehdokas : ehdokkaat) {%>
						<option value="<%=ehdokas.getEhdokasId()%>"><%=ehdokas.getEhdokasId() %> - <%=ehdokas.getEtunimi() %>  <%=ehdokas.getSukunimi() %> - <%=ehdokas.getPuolue() %></option>
					<%}
				%>
			</select>
			</center>
			<br>
		<hr>
		

		<div class=kysymys2>	
		<%
		if (kysymykset==null){
			out.println("Kysymyslista oli tyhjÔøΩ, ja tÔøΩmÔøΩ on virheilmoitus: oh no :| ");
			return;
		}
		%>
		<table name=vastaustaulu>
		<%
		/**Looppaa tietokannasta saadut kysymykset l‰pi, ja asettaa ne sopivaan HTML taulukkoon*/
		for (Kysymykset kysymys : kysymykset) {%>
			<%
			// tulostaa kysymykset konsoliin, l‰hinn‰ debuggausta varten
			// System.out.println(kysymys);%>
			<tr>
				<input type="hidden" name="kysymysnro<%=kysymys.getKysymysId()%>" value="<%=kysymys.getKysymysId()%>">
				<td>Kysymys nro. <%= kysymys.getKysymysId() %></td>
				<td><%= kysymys.getKysymys() %></td>
				</div>
			</tr>
			<tr>
					<td>
                    <label>1</label><input type="radio" name="vastaus<%=kysymys.getKysymysId()%>" value="1" /><small>T‰ysin eri mielt‰</small><br>
                    <label>2</label><input type="radio" name="vastaus<%=kysymys.getKysymysId()%>" value="2" /><small>Osittain eri mielt‰</small><br>
                    <label>3</label><input type="radio" name="vastaus<%=kysymys.getKysymysId()%>" value="3" checked="checked" /><small>En osaa sanoa</small><br>
                    <label>4</label><input type="radio" name="vastaus<%=kysymys.getKysymysId()%>" value="4" /><small>Osittain samaa mielt‰</small><br>
                    <label>5</label><input type="radio" name="vastaus<%=kysymys.getKysymysId()%>" value="5" /><small>T‰ysin samaa mielt‰</small><br>
                  	</td>
                  	<td>
                  	<textarea name="perusteluBox<%=kysymys.getKysymysId()%>" cols="50" rows="5" maxlength="100" placeholder="Perustelut vastaukselle (max. 100 merkki‰)"></textarea>
                  	</td>
			</tr>
			<tr>
			<td><hr></td>
			</tr>

		<%}%>
		</table>
		<input id="submitnappi" type="submit" value="vastaa" name="btnEhVastaa"/>
		</div>
		
				
		</form>
		
		


	</div>
</body>
</html>