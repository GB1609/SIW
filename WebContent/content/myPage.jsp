<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SIW</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<script src="https://code.jquery.com/jquery-1.10.2.js"></script>

<link
	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<link href="<%=request.getContextPath()%>/bootstrap/css/general.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/bootstrap/css/jumbo.css"
	rel="stylesheet">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<link href="<%=request.getContextPath()%>/WEB-INF/lib/jquery.js">

</head>
<body>

	<jsp:include page="organizatorNavbar.jsp" />

	<br>


	<div class="container">
		<h2>Storico</h2>
		<br>
		<div class="row">
			<div class="table-responsive">
				<table class="table table-hover">

					<thead>
						<tr>
							<th>Codice Evento</th>
							<th>Nome Evento</th>
							<th>Citta</th>
							<th>Biglietti totali</th>
							<th>Biglietti rimasti</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${eventi}" var="value">

<tr>
		<td>${value.code}</td>
	<td>${value.name}</td>
		<td>${value.city}</td>
<td>${value.bigl} <td/>
<td>${value.rim}<td/>
<td>
<form action="<%=request.getContextPath()%>/OneEventManagementServlet?nome=${value.name}&&update=no" method="post">
	<button type="submit" class="btn btn-block btn-primary">Gestisci
		evento</button>
</form>
</td>
</tr>
	 </c:forEach>



					</tbody>
				</table>
			</div>
		</div>
		<br> <br>
		<div class="row">
			<div class="col-md-offset-10 col-md-2">
				<form method="post"
					action="<%=request.getContextPath()%>/DataForOrganizatorServlet">
					<button type="submit" class="btn btn-primary btn-block">Crea
						un nuovo evento</button>
				</form>
			</div>
		</div>
	</div>




	<!--div class="container">
  <h1>Eventi organizzati</h1>
  <br>
  <form action="<%=request.getContextPath()%>/EventsManagementServlet" method="post">

  <button type="submit">Modifica</button>
  </form>
  </div-->




	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->

</body>
</html>
