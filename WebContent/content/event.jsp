<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html"%>
<html lang="en">

	<head>
		<meta charset="utf-8">
			<meta http-equiv="X-UA-Compatible" content="IE=edge">
				<meta name="viewport" content="width=device-width, initial-scale=1">
					<title>Event Page</title>

					<!-- Bootstrap -->
					<script src="http://code.jquery.com/jquery-latest.min.js"></script>
					<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
						<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
							<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.2/css/bootstrap-select.min.css"/>
							<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.2/js/bootstrap-select.min.js"></script>
							<link href="<%=request.getContextPath()%>/bootstrap/css/general.css" rel="stylesheet">

								<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
								<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
								<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
								<jsp:include page="navbar.jsp"/>
							</head>

							<body>
								<div class="container">
									<!-- NAVBAR -->
									<!-- Button -->
									<div class="row">
										<div class="col-md-10 col-sm-9 col-xs-9"></div>
										<div class="col-md-1 col-sm-1 col-xs-1">
											<button class="btn btn-default" id="descriptionButton">Description</button>
										</div>
										<div class="col-md-1 col-sm-1 col-xs-1">
											<button class="btn btn-default" id="reviewButton">Review</button>
										</div>
									</div>
									<div class="row">
										<div class="col-md-3 col-sm-2 col-xs-2"></div>
										<div class="col-md-5 col-sm-4 col-xs-4" id="name">
											<h2></h2>
										</div>
										<div class="col-md-3 col-sm-2 col-xs-2" id="mediaVoti">
											<h2>Feedback</h2>
										</div>
									</div>
									<div class="row">
										<!-- Image -->
										<div class="col-md-3 col-sm-2 col-xs-2" id="img">
											<img src=""></div>

											<div class="col-md-9 col-sm-8 col-xs-8" id="description">
												<div class="panel panel-default">
													<!-- Head -->
													<div class="panel-heading">
														<h3 class="panel-title">Description</h3>
													</div>
													<!-- Body -->
													<div class="panel-body" id="descriptionBody">${it}</div>
												</div>
											</div>
										</div>

										<!-- Ticket -->
										<br></br>
										<div class="row">
											<table class="table" id="ticketTable">
												<thead>
													<tr>
														<th>Type</th>
														<th>Price</th>
														<th></th>
													</tr>
												</thead>
												<tbody id="ticketBody"></tbody>
											</table>
										</div>
									</div>

									<div id="informationDiv">
										<ul id="infoul">
											<c:forEach items="${list}" var="it">
												<li id='li'>${it}</li>
											</c:forEach>
										</ul>
									</div>

									<script>
										var value = $($('#li').closest('ul').find('li:eq(4)')).text();
										$(document).ready(function () {
											$("#name").replaceWith('<div class="col-md-6 col-sm-6 col-xs-6" id="name"> <h2>' + $($('#li').closest('ul').find('li:eq(0)')).text() + '</h2> </div>');
											$("#descriptionBody").replaceWith('<div class="panel-body" id "descriptionBody">' + $($('#li').closest('ul').find('li:eq(1)')).text() + '</div>');
											$("#img").replaceWith('<div class="col-md-3 col-sm-2 col-xs-2" id="img" >	<img src=" ' + $($('#li').closest('ul').find('li:eq(2)')).text() + ' " width="237" height="400"> </div>');

											$.ajax({
												url: "<%=request.getContextPath()%>/ShowTicketServlet",
												//JSON
												data: {
													eventcode: value
												},
												type: "GET",

												dataType: "json"
											}).done(function (responseJson) {
												$("#resultTicket").empty();
												var $ul = $('<ul>').appendTo($("#resultTicket"));
												$.each(responseJson, function (index, item) {
													var myspl = item.split("_");
													$('<tr id = "r' + index + '">').appendTo("#ticketBody");
													$('<td id = "d' + index + '">').text(myspl[0]).appendTo('#r' + index);
													$('<td id = "money' + index + '">').text(myspl[1]).appendTo('#r' + (index));
													$('<span class="glyphicon glyphicon-euro"></span>').appendTo('#money' + (index));
													$('<td> <button type="button" class="btn btn-default" data-toggle="modal" data-target="#wishlists" id ="wishlist">ADD TO WISHLIST <span class="glyphicon glyphicon-star"></span></button> <button class="btn btn-default" id = "buy">BUY <span class="glyphic' +
															'on glyphicon-shopping-cart"></span></button>').appendTo('#r' + (index));
												});

											})

											$("#informationDiv").replaceWith('<div id="informationDiv" value =' + $($('#li').closest('ul').find('li:eq(4)')).text() + '></div>');
										});

										$(document).on("click", "#descriptionButton", function (event) {

											$.ajax({
												url: "<%=request.getContextPath()%>/descriptionButtonServlet",

												//JSON
												data: {
													eventcode: value
												},
												type: "GET",

												dataType: "json"
											}).done(function (responseJson) {
												$("#result").empty();
												var $ul = $('<ul id = "resultList">').appendTo($("#result"));
												$.each(responseJson, function (index, item) {
													$('<li id ="result' + index + '">').text(item).appendTo($ul);
												});
												$("#addReviewButton").replaceWith("");
												$("#review").replaceWith('<div class="col-md-9 col-sm-8 col-xs-8" id="description"><div class="panel panel-default"><div class="panel-heading"><h3 class="panel-title">Description</h3></div><div class="panel-body" id "descriptionBody">' + $("#result1").text() + '</div></div>');
												$("#result").replaceWith('<div id="result"></div>');
											})
										});

										$(document).on("click", "#reviewButton", function () {

											$.ajax({
												url: "<%=request.getContextPath()%>/ShowReviewsServlet",

												//JSON
												data: {
													eventcode: value
												},
												type: "GET",

												dataType: "json"
											}).done(function (responseJson) {
												$("#review").replaceWith('<div class="col-md-9 col-sm-8 col-xs-8" id="review"><div class="panel panel-default"><div class="panel-title panel-heading col-md-4 col-sm-3 col-xs-3">User</div><div class="panel-title panel-heading col-md-4 col-sm-3 col-xs-3">Comment</div><div class' +
														'="panel-title panel-heading col-md-4 col-sm-3 col-xs-3">Reviews</div><div class="panel-body pre-scrollable" id = "reviewBody"></div></div><button class ="btn btn-default" id ="addReviewButton">Aggiungi <span class="glyphicon glyphicon-plus"></span></' +
														'button></div>');
												$("#description").replaceWith('<div class="col-md-9 col-sm-8 col-xs-8" id="review"><div class="panel panel-default"><div class="panel-title panel-heading col-md-4 col-sm-3 col-xs-3">User</div><div class="panel-title panel-heading col-md-4 col-sm-3 col-xs-3">Comment</div><div class' +
														'="panel-title panel-heading col-md-4 col-sm-3 col-xs-3">Reviews</div><div class="panel-body pre-scrollable" id = "reviewBody"></div></div><button class ="btn btn-default" id ="addReviewButton">Aggiungi <span class="glyphicon glyphicon-plus"></span></' +
														'button></div>');
												var $table = $('<table class = "table">').appendTo($("#reviewBody"));
												$.each(responseJson, function (index, item) {
													mysplit = item.split("_");
													if (mysplit[0] === "media") {
														$("#mediaVoti").replaceWith('<div class="col-md-3 col-sm-2 col-xs-2" id="mediaVoti"> <h2>Voto: ' + mysplit[1] + '/10</h2> </div>');
													} else {
														$('<tr id = "row' + index + '">').appendTo($table);
														$('<td id = "data' + index + '">').appendTo('#row' + index);
														$('<div class="col-md-4 col-sm-3">' + mysplit[0] + '</div><div class="col-md-4 col-sm-3 col-xs-3">' + mysplit[1] + '</div><div class="col-md-4 col-sm-3 col-xs-3">' + mysplit[2] + '</div>').appendTo('#data' + index);
													}
												});
											});
										});

										$(document).on("click", "#addReviewButton", function (event) {
											$("#review").replaceWith('<div class="col-md-9 col-sm-8 col-xs-8" id="review"><input type="number" name="vote" id="vote" min="1" max="10" class="form-control input-sm" cols="5"></input></input><textarea type="text" name="reviewForm" id="reviewForm" class="form-control input-s' +
													'm" placeholder="Scrivi Qui.." cols="30" rows="10"></textarea><button class="btn btn-success" id="saveReview">Add</button></div>');
										});

										$(document).on("click", "#saveReview", function (event) {
											$.ajax({
												url: "<%=request.getContextPath()%>/AddReview",

												//JSON
												data: {
													eventcode: value,
													user: "${name}",
													vote: $("#vote").val(),
													description: $("#reviewForm").val()
												},
												type: "POST",

												dataType: "json"
											})

											$("#review").replaceWith('<div class="col-md-9 col-sm-8 col-xs-8" id="review"><div class="panel panel-default"><div class="panel-title panel-heading col-md-4 col-sm-3 col-xs-3">User</div><div class="panel-title panel-heading col-md-4 col-sm-3 col-xs-3">Comment</div><div class' +
													'="panel-title panel-heading col-md-4 col-sm-3 col-xs-3">Reviews</div><div class="panel-body pre-scrollable" id = "reviewBody"></div></div><button class ="btn btn-default" id ="addReviewButton">Aggiungi <span class="glyphicon glyphicon-plus"></span></' +
													'button></div>');
										});

										$(document).on("click", "#ticketTable tbody #buy", function (event) {
											var t = $($(this).closest('tr').find('td:eq(0)')).text();
											var p = $($(this).closest('tr').find('td:eq(1)')).text();
											$.ajax({
												url: "<%=request.getContextPath()%>/ShoppingCartServlet",

												//JSON
												data: {
													eventcode: value,
													type: t,
													price: p
												},
												type: "GET",

												dataType: "json"
											}).done(function (responseJson) {
												alert(responseJson);
											});
										});

										$(document).on("click", "#wishlists #wish_list #sw", function (event) {
											var valueSplit = $("#sw").val().split("_");
											$.ajax({
												url: "<%=request.getContextPath()%>/AddWishTicketServlet",

												//JSON
												data: {
													listcode: valueSplit[0],
													owner: "${name}",
													eventcode: value,
													type: valueSplit[1],
													price: valueSplit[2]
												},
												type: "GET",

												dataType: "json"
											}).done(function (responseJson) {
												if (responseJson === 1) {
													alert("Cannot add selected Ticket at your Wishlist! it's already In.");
												} else if (responseJson === -1) {
													alert("Cannot add selected Ticket at your Wishlist! You aren't the Owner!!");
												} else if (responseJson === 0) {
													alert("Added!!");
												}
											});
										});

										//LOOK IT
									</script>
									<div id="result"></div>
									<div id="resultTicket"></div>
									<jsp:include page="footer.html"></jsp:include>
									<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
								</body>
							</html>
