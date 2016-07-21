<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html"%>

<!DOCTYPE <!DOCTYPE html>
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
<title>SIW</title>
<link href="<%= request.getContextPath() %>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<link href="<%= request.getContextPath() %>/bootstrap/css/general.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/bootstrap/css/jumbo.css" rel="stylesheet">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<link href="../WEB-INF/lib/jquery.js">
 <!--script>
 	$document.on("click",'button[name^="nomeEvento"]',function(event){
 		var ciao =$(this).attr(name).substring(10,$(this).attr(name).length);
  		window.location.href = "<%=request.getContextPath()%>/showInformationServlet?nominativo="+ciao ;
 	});

</script-->

  </head>
  <body>

	<jsp:include page="navbar.jsp"/>

	<br><br>

<br>
 <br>

  <div class="container" >

    <div class="row" id="eTMGH">

      <c:forEach items="${eventList}" var="bu">
        <div class="col-md-3">
        <div id="forCss">
        <a href="<%=request.getContextPath()%>/ShowInformationServlet?nominativo=${bu.name}">  
        <img src="${bu.img}" width="260" height="350">
        </img>
        </a>
        <br><br><br> 
          <br><br><br>
           </div>
           <div class="jumbotron">
          <dl>
          <dd>${bu.name}</dd>
          <dd>${bu.date}</dd>
          <dd>${bu.locality}</dd>
          <dd>${bu.city}</dd>
          </dl>
           </div>
        </div>
        </c:forEach>



          <c:forEach items="${emptyList}" var="bu2">
         	<h1>${bu2}</h1>
          </c:forEach>

    </div>
  </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->

  </body>


</html>
