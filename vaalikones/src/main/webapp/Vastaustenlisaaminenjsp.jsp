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
				<option value="ehdokkaan id t‰h‰n">ehdokkaan nimi t‰h‰n</option>
			</select> <input id="submitnappi" type="submit" value="vastaa"
				name="btnEhVastaa" />
		</form>


	</div>
</body>
</html>