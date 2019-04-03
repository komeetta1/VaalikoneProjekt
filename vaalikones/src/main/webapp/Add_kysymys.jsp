<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Kysymyksen lisï¿½ys</title>
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
		<input type="text" step="any" name="kysymys_add" id="kysymystext">

		<form action="/kysymys_Servlet">
			<input id="submitnappi" type="submit" value="Lisää kysymys"
				name="btnAloita" />
		</form>


	</div>
</body>

</html>