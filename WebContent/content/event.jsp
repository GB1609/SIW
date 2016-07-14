<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<title>SIW</title>
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../bootstrap/css/siw.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/content/navbar.html" />
	<div class="container">

		<div class="row">
			<div class="col-md-10"></div>
			<div class="col-md-1">
				<button class="btn btn-default">Description</button>
			</div>
			<div class="col-md-1">
				<button class="btn btn-default">Review</button>
			</div>
		</div>

		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<h2>Name</h2>
			</div>
			<div class="col-md-2">
				<h2>Feedback</h2>
			</div>
		</div>

		<div class="row">
			<!-- Image -->
			<div class="col-md-3">
				<img src="img_0018.jpg" width="237" height="400">
			</div>
			<!-- Text -->
			<div class="col-md-1">Text</div>
		</div>

		<!-- Ticket -->
		<br></br>
		<div class="row">
			<table class="table">
				<thead>
					<tr>
						<th>Event</th>
						<th>Type</th>
						<th>Price</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>concerto</td>
						<td>Main Stand</td>
						<td>100</td>
						<td>
							<button class="btn btn-default">
								BUY <span class="glyphicon glyphicon-plus"></span>
							</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script stc="index_ajax.js"></script>
	<script stc="login.js"></script>
</body>
<jsp:include page="/content/footer.html" />
</html>
