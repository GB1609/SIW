<head>
	<script>
		$(document).on("click","#typeOfResearch",function(){
			if( $("#typeOfResearch").val() == "Date" )
			{
				$("#inpid").replaceWith('<input type="date" id=inpid name="value" id="birth" class="form-control input-sm">');
			}
			else
			{
				$("#inpid").replaceWith('<input type="text" id=inpid name="value" class="form-control" placeholder="Search"></input>');
			}
		});
	</script>
</head>
<body>
	<nav class="navbar navbar-default" navbar navbar-fixed-top>
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#mainNavBar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a href="#" class="navbar-brand"> <img class="img-responsive img-rounded" src="<%= request.getContextPath() %>/assets/logoSIW.png" alt="SIW" width="60">
			</a>
		</div>
		<div class="collapse navbar-collapse" id="mainNavBar">
			<div class="nav navbar-nav navbar-left">
				<li> <a href="<%= request.getContextPath() %>/content/home.jsp">Home</a> </li>
				<li> <a href="<%= request.getContextPath() %>/SearchEvents?typeOfResearch=All" id="eventi">Eventi</a> </li>

			</div>
			<ul class="nav navbar-nav  navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Organizator<span class="caret"> </span>
				</a>
					<ul class="dropdown-menu">
						<li> <a href="<%= request.getContextPath() %>/DataForOrganizatorServlet" id="organizza">Crea un evento</a> </li>
						<li><a href="#">Gestisci eventi</a></li>
					</ul>
				</li>
				<li>
					<form class="navbar-form" role="search" action="<%= request.getContextPath() %>/ParameterSearchEvents">
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
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">Logout
							<span class="caret"></span>
					</a>

				</li>
			</ul>
		</div>
<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

		</body>
	</nav>
