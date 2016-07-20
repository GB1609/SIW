<!DOCTYPE html>
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
							<link href="../bootstrap/css/siw.css" rel="stylesheet">
								<style>
									body {
										background-color: lightblue;
									}

								</style>

								<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
								<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
								<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
								<jsp:include page="navbar.html"/>
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
											<img></div>

											<div class="col-md-9" id="description">
												<div class="panel panel-default">
													<!-- Head -->
													<div class="panel-heading">
														<h3 class="panel-title">Description</h3>
													</div>
													<!-- Body -->
													<div class="panel-body" id="descriptionBody"></div>
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

									<div class="modal fade" id="wishlists" tabindex="-1" role="dialog" aria - labelledby="" aria - hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-hi dden="true">&times;</button>
													<h4 class="modal-title" id="">Select Wishlist</h4>
												</div>
												<div class="modal-body" id="wish_list"></div>
											</div>
										</div>
									</div>

									<script>
										$(document).on("click", "#reviewButton", function () {

											$.ajax({
												url: "../ShowReviewsServlet",

												//JSON
												data: {
													eventcode: 29
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

												$("#result").replaceWith('<div id="result"></div>');
											});
										});

										$(document).on("click", "#descriptionButton", function (event) {

											$.ajax({
												url: "../ShowInformationServlet",

												//JSON
												data: {
													eventcode: 34
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
											});
										});

										$(document).ready(function () {
											$.ajax({
												url: "../ShowInformationServlet",

												//JSON
												data: {
													eventcode: 34
												},
												type: "GET",

												dataType: "json"
											}).done(function (responseJson) {
												$("#result").empty();
												var $ul = $('<ul id = "resultList">').appendTo($("#result"));
												$.each(responseJson, function (index, item) {
													$('<li id ="result' + index + '">').text(item).appendTo($ul);
												});

												$("#name").replaceWith('<div class="col-md-6" id="name"> <h2>' + $("#result0").text() + '</h2> </div>');
												$("#descriptionBody").replaceWith('<div class="panel-body" id "descriptionBody">' + $("#result1").text() + '</div>');
												$("#img").replaceWith('<div class="col-md-3" id="img" >	<img src=" ' + $("#result2").text() + ' " width="237" height="400"> </div>');
												$("#result").replaceWith('<div id="result"></div>');
											});
										});

										$(document).ready(function () {
											$.ajax({
												url: "../ShowTicketServlet",

												//JSON
												data: {
													eventcode: 29
												},
												type: "GET",

												dataType: "json"
											}).done(function (responseJson) {
												$("#resultTicket").empty();
												var $ul = $('<ul>').appendTo($("#resultTicket"));
												$.each(responseJson, function (index, item) {
													$('<li id ="resultTickets">').text(item).appendTo($ul);
												});

												$("li").each(function (index, item) {
													if ($(item).is("#resultTickets")) {
														if (index % 2 == 0) {
															$('<tr id = "r' + index + '">').appendTo("#ticketBody");
															$('<td id = "d' + index + '">').text($(item).text()).appendTo('#r' + index);
														} else {
															$('<td id = "d' + index + '">').text($(item).text() + '.00 ').appendTo('#r' + (index - 1));
															$(' <span class="glyphicon glyphicon-euro"></span>').appendTo('#d' + index);
															$('<td> <button type="button" class="btn btn-default" data-toggle="modal" data-target="#wishlists" id ="wishlist">ADD TO WISHLIST <span class="glyphicon glyphicon-star"></span></button> <button class="btn btn-default" id = "buy">BUY <span class="glyphic' +
																	'on glyphicon-shopping-cart"></span></button>').appendTo('#r' + (index - 1));
														}
													}
												});

												$("#resultTicket").replaceWith('<div id="resultTicket"></div>');

											});
										});

										$(document).on("click", "#ticketTable tbody #buy", function (event) {
											var t = $($(this).closest('tr').find('td:eq(0)')).text();
											var p = $($(this).closest('tr').find('td:eq(1)')).text();
											$.ajax({
												url: "../BuyTicketServlet",

												//JSON
												data: {
													eventcode: 29,
													type: t,
													price: p
												},
												type: "GET",

												dataType: "json"
											}).done(function (responseJson) {
												// $("#result").empty(); var $ul = $('<ul id = "resultList">').appendTo($("#result")); $.each(responseJson, function (index, item) { $('<li id ="result' + index + '">').text(item).appendTo($ul); }); $("#result").replaceWith('<div
												// id="result"></div>');
											});
										});

										$(document).on("click", "#ticketTable tbody #wishlist", function (event) {
											//var t = $($(this).closest('tr').find('td:eq(0)')).text(); var p = $($(this).closest('tr').find('td:eq(1)')).text(); alert("WISHLIST");
											$("#wish_list").replaceWith('<div class="modal-body" id="wish_list"></div>');
											var t = $($(this).closest('tr').find('td:eq(0)')).text();
											var p = $($(this).closest('tr').find('td:eq(1)')).text();
											$.ajax({
												url: "../ShowWishlistServlet",

												//JSON
												data: {
													//DOBBIAMO PRENDERE L'USER E VEDERE SE E' CONNESSO QUALCUNO.
													owner: "gio",
													//eventcode: 29, type: t, price: p
												},
												type: "POST",

												dataType: "json"
											}).done(function (responseJson) {
												if (responseJson === "EMPTY") {
													alert("EMPTY");
													//PANNELLO DI SCELTA SE CREARE NUOVA WISHLIST.
												} else {
													var $ul = $('	<ul class="list-group">').appendTo($("#wish_list"));
													$.each(responseJson, function (index, item) {
														//$('<li id ="result' + index + '">').text(item).appendTo($ul); alert(responseJson); var itemtmp = $(item).text();
														var mysplit = item.split(" ");
														$('<button type ="button" data-dismiss="modal" value=' + mysplit[0] + ' class="btn btn-default list-group-item" id ="sw">').text(mysplit[1]).appendTo($ul);
													});

												}
											});

											$(document).on("click", "#wishlists #wish_list #sw", function (event) {
												var lcode = $("#sw").val();
												$.ajax({
													url: "../AddWishTicketServlet",

													//JSON
													data: {
														listcode: lcode,
														owner: "gio",
														eventcode: 29,
														type: t,
														price: p
													},
													type: "GET",

													dataType: "json"
												}).done(function (responseJson) {
													if (responseJson === 1) {
														alert("Cannot add selected Ticket at your Wishlist! it's already In.");
													} else if (responseJson === -1) {
														alert("Cannot add selected Ticket at your Wishlist! You aren't the Owner!!");
													}else if(responseJson === 0) {
															alert("Added!!");
													}
												});
											});
										});
									</script>

									<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
									<script src="https://ajax.googleapis.com/ajax / libs / jquery / 1.11 .3 / jquery.min.js "></script>
									<!-- Include all compiled plugins (below), or include individual files as needed -->
									<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
									<script src="index_ajax.js"></script>
									<script src="login.js"></script>
									<div id="result"></div>
									<div id="resultTicket"></div>
								</body>
							</html>
