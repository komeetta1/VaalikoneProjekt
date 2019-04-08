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
		<form action="Vastaa">
			<select name="vastaaja">
				<!-- T‰h‰n kai pit‰isi tehd‰ java-looppi joka lukee ehdokkaan IDn ja nimen
				ja tulostaa ne oikein optioniksi, kuten alla: -->
				<%
					for(Ehdokkaat ehdokas : ehdokkaat) {%>
						<option value=<%ehdokas.getEhdokasId();%>><%=ehdokas.getEhdokasId() %> - <%=ehdokas.getEtunimi() %>  <%=ehdokas.getSukunimi() %> - <%=ehdokas.getPuolue() %></option>
					<%}
				%>
				
			</select>
		<p>T‰h‰n listataan kysymykset</p>
		

		<div class=kysymys2>	
		<%
		if (kysymykset==null){
			out.println("KYsymyslista oli tyhj‰, ja t‰m‰ on virheilmoitus: oh no :| ");
			return;
		}
		%>
		<table id=vastaustaulu>
		
		<%for (Kysymykset kysymys : kysymykset) {
			// tulostaa kysymykset konsoliin, l‰hinn‰ debuggausta varten
			// System.out.println(kysymys);%>
			<tr>
				<td>Kysymys nro. <%= kysymys.getKysymysId() %></td>
				<td><%= kysymys.getKysymys() %></td>
			</tr>
			<tr>
					<td>
                    <label>1</label><input type="radio" name="vastaus" value="1" /><small>T‰ysin eri mielt‰</small><br>
                    <label>2</label><input type="radio" name="vastaus" value="2" /><small>Osittain eri mielt‰</small><br>
                    <label>3</label><input type="radio" name="vastaus" value="3" checked="checked" /><small>En osaa sanoa</small><br>
                    <label>4</label><input type="radio" name="vastaus" value="4" /><small>Osittain samaa mielt‰</small><br>
                    <label>5</label><input type="radio" name="vastaus" value="5" /><small>T‰ysin samaa mielt‰</small><br>
                  	</td>
                  	
                  	<td><p><small>Kirjoita t‰h‰n perustelut vastaukselle (max. 100 merkki‰)</small></p>
                  	<textarea name="perusteluBox" cols="50" rows="5"></textarea>
                  	</td>
			</tr>
			<tr>
			<td><hr></td>
			</tr>
			
		<%}%>
		</table>
		<input id="submitnappi" type="submit" value="vastaa" name="btnEhVastaa" />
		</div>
		
				
		</form>
		
		


	</div>
</body>
</html>