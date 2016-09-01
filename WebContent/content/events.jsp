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
<link href="<%= request.getContextPath() %>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/bootstrap/css/jumbo.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/bootstrap/css/general.css" rel="stylesheet">

	<style>
		body {
			background-color: #F0FFFF;
		}
	</style>


</head>
  <body>

	 	<%  if(! (session.getAttribute("tipe")=="organizator")){%>
                                             	<%@include file="navbar.jsp"%>
                                             	<% }else{ %>
                                             	<%@include file="organizatorNavbar.jsp"%>
                                             	<%} %>

	<div id="ris">
  <h1> Risultati della ricerca</h1>
    </div>

		<br><br><br><br>

    <div class="container">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3">
						<br><br><br>
				<div class=jumbotron>
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
