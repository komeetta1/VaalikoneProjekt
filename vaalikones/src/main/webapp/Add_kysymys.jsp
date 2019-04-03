<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Kysymyksen lis�ys</title>
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

		<h3>Kirjoita kysymys t�h�n:</h3>
		<input type="text" step="any" name="kysymys_add" id="kysymystext">

		<form action="pl��pl��">
			<input id="submitnappi" type="submit" value="Lis�� kysymys"
				name="btnAloita" />
			<%
				try {
					String connectionURL = "jdbc:mysql://localhost:3306/vaalikone";
					Connection connection = null;
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					connection = DriverManager.getConnection(connectionURL, "pena", "kukkuu");
					if (!connection.isClosed())
						out.println("Successfully connected to " + "MySQL server using TCP/IP...");

					String queryString = "insert into stu_info(Name,City,Phone) values(?,?,?)";
					pstatement = connection.prepareStatement(queryString);
					
					connection.close();
				} catch (Exception ex) {
					out.println("Unable to connect to database.");
				}
			%>
		</form>
		<%
			try {
				String connectionURL = "jdbc:mysql://localhost:3306/vaalikone";
				Connection connection = null;
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				connection = DriverManager.getConnection(connectionURL, "pena", "kukkuu");
				String queryString = "insert into stu_info(kysymystext) values(?)";
				if (!connection.isClosed())
					out.println("Successfully connected to " + "MySQL server using TCP/IP...");



	</div>
</body>

</html>