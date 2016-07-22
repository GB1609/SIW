<head>
	<script>
		$(document).on("click", "#typeOfResearch", function () {
			if ($("#typeOfResearch").val() == "Date") {
				$("#inpid").replaceWith('<input type="date" id=inpid name="value" id="birth" class="form-control input-sm">');
			} else {
				$("#inpid").replaceWith('<input type="text" id=inpid name="value" class="form-control" placeholder="Search"></input>');
			}
		});
	</script>
	<script>

		$(document).on("click", "#loginButton", function (event) {

			$.ajax({
				url: '<%=request.getContextPath()%>/LogInServlet',
				data: {
					tipe: "logIn",
					username: $("#username").val(),
					password: $("#password").val()
				},
				type: "GET",
				dataType: "json"
			}).done(function (responseJson) {
				var split = responseJson.split("/");

				if (split[0]==="true" &&  split[1]==="organizator")
					$('#firstRow').replaceWith('<div align="center"> <h1> Complimenti!</h1> <br><br> Bentornato ' + split[2] + '</div><meta http-equiv="refresh" content="3;URL=<%=request.getContextPath()%>/EventsManagementServlet">');
				else if (split[0] === "true")
					$('#firstRow').replaceWith('<div align="center"> <h1> Complimenti!</h1> <br><br> Bentornato ' + split[2] + '</div><meta http-equiv="refresh" content="3;URL=home.jsp">');
				else
					$('#firstRow').replaceWith('<div align="center"> <h1> Attenzione!</h1> <br><br> Username o password errati </div><meta http-equiv="refresh" content="3;URL=home.jsp">');

				}
			)
		});
	</script>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#mainNavBar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a href="home.jsp" class="navbar-brand">
				<img class="img-responsive img-rounded" src="<%=request.getContextPath()%>/assets/logoSIW.png" alt="SIW" width="60"></a>
			</div>
			<div class="collapse navbar-collapse" id="mainNavBar">
				<div class="nav navbar-nav navbar-left">
					<li>
						<a href="<%=request.getContextPath()%>/content/home.jsp">Home</a>
					</li>

					<li>
						<a href="<%=request.getContextPath()%>/SearchEvents?typeOfResearch=All" id="eventi">Eventi</a>
					</li>
				</div>
				<ul class="nav navbar-nav  navbar-right">
					<li class="dropdown" id="userID">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">User
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="#">Profilo</a>
							</li>
							<li>
								<a href="#">Wishlist</a>
							</li>
						</ul>
					</li>
					<li class="dropdown" id="cartDrop">
						<a class="dropdown-toggle" data-toggle="dropdown">Carrello
							<span class="caret"></span>
						</a>
						<div class="dropdown-menu panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Item</h3>
							</div>
							<div class="panel-body pre-scrollable" id="cartBody">
								<table class="table" id="cartTable"></table>
							</div>
						</div>
					</li>
					<li>
						<form class="navbar-form" role="search" action="<%=request.getContextPath()%>/ParameterSearchEvents">
							<div class="form-group">
								<input type="text" id=inpid name="value" class="form-control" placeholder="Search"></input>
							</div>
							<input id="search" type="submit" class="btn btn-primary">
								<span class="glyphicon glyphicon-search"></span>
							</input>
							<div class="form-group">
								<select class="form-control" id="typeOfResearch" name="selezione">
									<option value="Category">Category</option>
									<option value="Date">Date</option>
									<option value="Name">Name</option>
									<option value="Price">Organizator</option>
									<option value="Partecipants">Partecipants</option>
									<option value="Place">Place</option>
									<option value="Price Max">Price Max</option>
									<option value="Price Min">Price Min</option>
								</select>
							</div>
						</form>
					</li>
					<%  if(!(session.getAttribute("name")!=null)){%>
					        <li class="dropdown">
					          <a class="dropdown-toggle" data-toggle="dropdown">Login
					            <span class="caret"></span>
					          </a>
					          <div class="dropdown-menu">
					            <div class="panel panel-default">
					              <div class="panel-heading">
					                <h3 class="panel-title">Effettua l'accesso</h3>
					              </div>
					              <div class="panel-body">
					                <div class="form-group">
					                  <input class="form-control" placeholder="Username" type="text" name="username" id="username"></div>
					                  <div class="form-group">
					                    <input class="form-control" placeholder="Password" name="password" type="password" id="password"></div>
					                    <div align="center">
					                      <button class="btn btn-primary" id="loginButton">Login</button>
					                    </div>
					                  </div>
					                </div>
					              </div>
					            </li>
					            <% }else{ %>
					          <li><a href="<%=request.getContextPath() %>/LogOutServlet" id="logoutButtonUser">Logout </a></li>
					            <%} %>

						</ul>
					</div>
				</nav>

				<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
			</html>
		</body>
