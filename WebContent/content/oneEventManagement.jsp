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
<script>

$(document).on("click",'button[name^="prezzo"]',function(event){

var ciao = $(this).attr("name").substring(6,$(this).attr("name").length);
var ciao2 = $("#prezzo"+ciao).val();
window.location.href = "<%=request.getContextPath()%>/OneEventManagementServlet?tipB="+ciao+"&&nome=${info.name}&&update=yes&&price="+ciao2 ;
});
</script>

<script>

$(document).on("click","#startstop",function(event){
	$.ajax({

		  url: '<%=request.getContextPath()%>/StartStopServlet',
          data: {
              evento: $("#eventName").text() ,

          },
          type: "GET",
          dataType: "json"

	}).done(function (responseJson) {

		var myVar = responseJson.split(" ");
		alert (responseJson);
		if (myVar[1] === "avviata")
		 $("#startstop").replaceWith('<button class="btn btn-success" id="startstop">Ferma vendita</button>');
		else
			$("#startstop").replaceWith('<button class="btn btn-success" id="startstop">Avvia vendita</button>');
	});
});
</script>


</head>
<body>

	<jsp:include page="organizatorNavbar.jsp" />

	<br>



<form action="<%=request.getContextPath()%>/RemoveEventServlet?event=${info.name}" method="post">
<div class="container">
<div class="row">
<div class="col-md-3 col-sm-2 col-xs-2">
<label>Evento</label>
</div>
</div>
<div class="row">
<div class = "col-md-3 col-sm-2 col-xs-2">
	<div class="panel panel-default">
	<div class="panel-body" id="eventName">${info.name}</div>
	</div>

</div>
<div class="col-md-2 col-sm-1 col-xs-1">
<button type="submit" class="btn btn-danger">Elimina evento</button>
</div>


</div>

<br><br>
</div>
</form>


<div class="container">
<div class="row">
<div class="col-md-3 col-sm-2">
	<label>Tipologia biglietto</label>
</div>
<div class="col-md-2 col-sm-1">
	<label>Costo</label>
</div>
<div class ="col-md-1 col-sm-1"></div>
<div class="col-md-2 col-sm-2">
	<label>Salva Modifiche</label>
</div>
</div>
<c:forEach items="${tipiBigl}" var="hashMap">
	<div class = "row">
	<div class="col-md-3 col-sm-2">

		<div class="panel panel-default">
		<div class="panel-body">${hashMap.key}</div>
	</div>
	</div>
	<div class="col-md-2 col-sm-2">

		<input type="text" id="prezzo${hashMap.key}" class="form-control" value="${hashMap.value}">


	</div>
	<div class ="col-md-1 col-sm-1">
		<span class="glyphicon glyphicon-euro"></span>
		</div>

	<div class = "col-md-1 col-sm-1">
	<button type="submit" id="button${hashMap.key}" name="prezzo${hashMap.key}" class="btn btn-block btn-primary">*</button>
	<!--  form action="<%=request.getContextPath()%>/OneEventManagementServlet?tipB=${hashMap.key}&&nome=${info.name}&&update=yes&&price=" method="post">
	<button type="submit" class="btn btn-block btn-primary">-</button>
	</form-->
	</div>
	</div>

</c:forEach>
<div class ="row">
<div class = "col-md-2 col-sm-2">
<%if (request.getAttribute("stato").equals(false)){%>
<button class="btn btn-success" id="startstop">Avvia vendita</button>
<%}else{ %>
<button class="btn btn-success" id="startstop">Ferma vendita</button>
<%} %>
</div>
</div>

</div>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->

</body>
</html>
