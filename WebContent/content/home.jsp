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
                            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
                                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
                                    <title>SIW</title>
                                    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
                                        <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
                                        <link href="../bootstrap/css/general.css" rel="stylesheet">
                                            <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
                                            <link href="../WEB-INF/lib/jquery.js">
                                                <script>
                                                    $(document).on('click', '#buttonRegister', function (event) {
                                                        $('#firstRow').replaceWith('<jsp:include page="registration.html"></jsp:include>');
                                                    });
                                                </script>

                                                <script>
                                                    $(document).on("click", "#registerNow", function (event) {
                                                        $.ajax({

                                                            url: "../RegistrationServlet",
                                                            data: {
                                                                username: $("#user").val(),
                                                                password: $("#password").val(),
                                                                first_name: $("#first_name").val(),
                                                                last_name: $("#last_name").val(),
                                                                address: $("#address").val(),
                                                                birth: $("#birth").val()
                                                            },
                                                            type: "GET",
                                                            dataType: "json"
                                                        }).done(function (responseJson) {
                                                        	alert("ciao");
                                                            if (responseJson == "notExist") {
                                                                $('#firstRow').replaceWith('<div align="center"> <h1> Complimenti!</h1> <br><br> Ora sei registrato sarai portato alla pagina principale entro pochi secondi</div><meta http-equiv="refresh" content="3;URL=home.jsp">');
                                                            } else {
                                                                $('#firstRow').replaceWith('<div align="center"> <h1> UserName gia utilizzato!</h1> <br><br> Cambia username e riprovare</div><meta http-equiv="refresh" content="3;URL=home.jsp">');
                                                            }
                                                         
                                                 
                                                        
                                                        })
                                                    });
                                                </script>
                                            </head>
                                            <body>
                                           	<%  if(session.getAttribute("name")==null){%>
                                             	<%@include file="navbar.jsp"%>
                                             	<% }else{ %>
                                             	<%@include file="organizatorNavbar.jsp"%>
                                             	<%} %>
                                                <div class="container">
                                                    <div class="row" id="firstRow">
                                                        <div class="col-md-8">
                                                            <img class="img-responsive img-rounded" src="<%=request.getContextPath()%>/assets/locandina.png" alt="No possible load Image sorry!"></div>
                                                            <div class="col-md-3">
                                                                <h1 class="text-center">Registrati subito!</h1>
                                                                <p>Se vuoi essere sempre comprare biglietti per gli eventi riguardanti le tue passioni registrati sul nostro sito, non te ne pentirai!</p>
                                                                <a class="btn btn-primary" id="buttonRegister">Registrati!</a>
                                                            </div>
                                                        </div>
                                                        <hr>
                                                            <div class="row">
                                                                <div class="col-lg-12">
                                                                    <div class="well text-center">SIW TicketSeller, il sito che ti permette di tenere sotto controllo le tue passioni!</div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-4">
                                                                    <h2>Sport</h2>
                                                                    <p>Sei appassionato di calcio, formula uno o di Tennis? Guarda i prezzi dei biglietti degli eventi sportivi</p>
                                                                    <a class="btn btn-primary" href="#">Guarda ora</a>
                                                                </div>
                                                                <div class="col-md-4">
                                                                    <h2>Musica</h2>
                                                                    <p>Scopri gli eventi musicali vicino a te!!!</p>
                                                                    <a class="btn btn-primary" href="#">Guarda ora</a>
                                                                </div>
                                                                <div class="col-md-4">
                                                                    <h2>Cinema</h2>
                                                                    <p>Voglia di rilassarti, di vedere un bel film? Guarda i prezzi dei cinema intorno a te!</p>
                                                                    <a class="btn btn-primary" href="#">Guarda Ora</a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <jsp:include page="footer.html"></jsp:include>
                                                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
                                                    </body>
                                                </html>
