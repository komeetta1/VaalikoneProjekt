<%@page import="vaalikone.kysymys_Servlet"%>
<%@page import="persist.Kysymykset"%>
<%@page import="persist.Vastaukset"%>
<%@page import="java.util.List"%>
<%@page import="persist.Ehdokkaat"%>
<%@page import="vaalikone.Kysymys_poisto"%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Kysymyksen lisays</title>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="style.css" rel="stylesheet" type="text/css">
<style>
input {
	width: 100%;
}
</style>
</head>
<body>
	<div id="container">

		<img id="headerimg" src="Logo.png" width="720" /> <br>

		<h3>Kirjoita kysymys:</h3>

		<form action="/kysymys_Servlet" method="post">
			<input type="text" step="any" name="kysymys_add"> <input
				id="submitnappi" type="submit" value="Lisaa kysymys"
				name="btnAloita" />
		</form>

		<br>
		
		<form action = "/Kysymys_poisto">
			
			<input type="submit" value="Siirry kysymyksien hallintaan"
				name="btnPoista" />
		</form>
		
		<form action = "index.html">
			
			<input type="submit" value="Siirry etusivulle"
				name="btnEtusivu" />
		</form>
	</div>
</body>
</html>