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
										<div class="col-md-10"></div>
										<div class="col-md-1">
											<button class="btn btn-default" id="descriptionButton">Description</button>
										</div>
										<div class="col-md-1">
											<button class="btn btn-default" id="reviewButton">Review</button>
										</div>
									</div>
									<div class="row">
										<div class="col-md-3"></div>
										<div class="col-md-6" id="name">
											<h2></h2>
										</div>
										<div class="col-md-2">
											<h2>Feedback</h2>
										</div>
									</div>
									<div class="row">
										<!-- Image -->
										<div class="col-md-3" id="img">
											<img src=""></div>

											<div class="col-md-9" id="description">
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
											$("#name").replaceWith('<div class="col-md-6" id="name"> <h2>' + $($('#li').closest('ul').find('li:eq(0)')).text() + '</h2> </div>');
											$("#descriptionBody").replaceWith('<div class="panel-body" id "descriptionBody">' + $($('#li').closest('ul').find('li:eq(1)')).text() + '</div>');
											$("#img").replaceWith('<div class="col-md-3" id="img" >	<img src=" ' + $($('#li').closest('ul').find('li:eq(2)')).text() + ' " width="237" height="400"> </div>');

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
													$('<td id = "money' + index + '">').text(myspl[1] + '.00 ').appendTo('#r' + (index));
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
												$("#review").replaceWith('<div class="col-md-9" id="description"><div class="panel panel-default"><div class="panel-heading"><h3 class="panel-title">Description</h3></div><div class="panel-body" id "descriptionBody">' + $("#result1").text() + '</div></div>');
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
												$("#result").empty();
												var $ul = $("<ul>").appendTo($("#result"));
												$.each(responseJson, function (index, item) {
													$('<li id ="resultReviews">').text(item).appendTo($ul);
												});

												$("#description").replaceWith('<div class="col-md-9" id="review"><div class="panel panel-default "><div class="panel-title panel-heading"><h3 class="col-md-3 ">User</h3><h3 class="col-md-6">Comment</h3><h3 class="col-md-3">Reviews</h3></div><div class="panel-body pre-scrollable" i' +
														'd = "reviewBody"></div></div></div>');

												$("#review").replaceWith('<div class="col-md-9" id="review"><div class="panel panel-default "><div class="panel-title panel-heading"><h3 class="col-md-3 ">User</h3><h3 class="col-md-6">Comment</h3><h3 class="col-md-3">Reviews</h3></div><div class="panel-body pre-scrollable" i' +
														'd = "reviewBody"></div></div></div>');

												var $table = $('<table class = "table">').appendTo($("#reviewBody"));
												$("li").each(function (index, item) {
													if ($(item).is("#resultReviews")) {
														if (index % 2 == 0) {
															$('<tr id = "row' + index + '">').appendTo($table);
															$('<td id = "data' + index + '">').text($(item).text()).appendTo('#row' + index);
														} else {
															$('<td id = "data' + index + '">').text($(item).text()).appendTo('#row' + (index - 1));
														}
													}
												});
												$('<tr><td><button class = "btn btn-secondary" id ="addrevButton">').appendTo($table);

												$("#result").replaceWith('<div id="result"></div>');
											});
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

										$(document).on("click", "#cartBuy", function (event) {
											$.ajax({
												url: "<%=request.getContextPath()%>/BuyTicketServlet",

												//JSON
												data: {
													owner: "gio"
												},
												type: "POST",

												dataType: "json"
											}).done(function (responseJson) {
												if (responseJson === "DONE") {
													alert("ACQUISTO EFFETTUATO CORRETTAMENTE");
													$('#cartTable').replaceWith('<table class="table" id="cartTable"></table>');
												} else
													alert(responseJson);
												}
											);
										});

										$(document).on("click", "#wishlists #wish_list #sw", function (event) {
											var valueSplit = $("#sw").val().split("_");
											$.ajax({
												url: "<%=request.getContextPath()%>/AddWishTicketServlet",

												//JSON
												data: {
													listcode: valueSplit[0],
													owner: "gio",
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
