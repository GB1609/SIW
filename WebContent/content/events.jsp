<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<title>SIW</title>
<link href="../bootstrap/css/general.css" rel="stylesheet">
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../bootstrap/css/siw.css" rel="stylesheet">
<link href="../bootstrap/css/bo.css" rel="stylesheet">
	<link href="../bootstrap/css/jumbo.css" rel="stylesheet">
	<style>
		body {
			background-color: #F0FFFF;
		}
	</style>

<script>

$.ajax({
		url : "SearchServlet",

		data:{

		} ,

		type: "GET" ,

		dataType : "json"

}).done(function(){

});

</script>

</head>
  <body>

		<jsp:include page="navbar.html"/><br><br>

	<div id="ris">
  <h1> Risultati della ricerca</h1>
    </div>

		<br><br><br><br>

    <div class="container">
			<div class="row">
				<div class=col-md-3>
						<img src="../assets/lp.jpg" width="260" height="350">
						<br><br><br>
				<div class=jumbotron>Concerto Linkin Park
				</div>
				</div>
				<div class=col-md-3>
				<img src="../assets/stadium.jpg" width="260" height="350">
				<br><br><br>
			<div class=jumbotron>Juventus - Inter
				</div>
				</div>
				<div class="col-md-3">
				<img src="../assets/civlWar.jpeg" width="260" height="350">
				<br><br><br>
			<div class=jumbotron>Captain America : Civil War
			</div>
				</div>
				<div class="col-md-3">
				<img src="../assets/gpMessico.jpg" width="260" height="350">
				<br><br><br>
				<div class=jumbotron>Formula 1 : Gp del Messico
		</div>
				</div>
</div>
</div>
<br><br><br>

<!--div>
	<li><a href="http://localhost:8080//?typeOfResearch=Category&=">
	Parameter Servlet</a></li>
</div-->
	  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  </body>
</html>
