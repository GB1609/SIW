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
			<ul class="nav navbar-nav navbar-left">
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Organizator<span class="caret"> </span>
				</a>
					<ul class="dropdown-menu">
						<li> <a href="<%= request.getContextPath() %>/DataForOrganizatorServlet" id="organizza">Crea un evento</a> </li>
						<li><a href="<%= request.getContextPath() %>/EventsManagementServlet">Gestisci eventi</a></li>
					</ul>
				</li>
			

			</ul>
			<ul class="nav navbar-nav  navbar-right">
			
				
				<li >
					<a href= "<%=request.getContextPath() %>/LogOutServlet" id="logoutButtonUser">Logout
							
					</a>

				</li>
			</ul>
		</div>
<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

		</body>
	</nav>
