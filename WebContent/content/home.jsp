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
                                    <link href="<%=request.getContextPath() %>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
                                        <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
                                        <link href="<%=request.getContextPath() %>/bootstrap/css/general.css" rel="stylesheet">
                                            <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
                                            <link href="<%=request.getContextPath() %>/WEB-INF/lib/jquery.js">
                                                <script>
                                                    $(document).on('click', '#buttonRegister', function (event) {
                                                        $('#firstRow').replaceWith('<jsp:include page="registration.html"></jsp:include>');
                                                    });
                                                </script>

                                                <script>
                                                    $(document).on("click", "#registerNow", function (event) {
                                                        $.ajax({

                                                            url: "<%=request.getContextPath() %>/RegistrationServlet",
                                                            data: {
                                                                username: $("#user").val(),
                                                                passwords: $("#passwords").val(),
                                                                first_name: $("#first_name").val(),
                                                                last_name: $("#last_name").val(),
                                                                address: $("#address").val(),
                                                                birth: $("#birth").val()
                                                            },
                                                            type: "GET",
                                                            dataType: "json"
                                                        }).done(function (responseJson) {
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
                                                <%  if(! (session.getAttribute("tipe")=="organizator")){%>
                                                    <%@include file="navbar.jsp"%>
                                                <% }else{ %>
                                                    <%@include file="organizatorNavbar.jsp"%>
                                                <%} %>
                                                <div class="container">
                                                    <div class="row" id="firstRow" align="center">
                                                        <div class="col-md-8 col-sm-12 col-xs-16">
                                                            <img class="img-responsive img-rounded" src="<%=request.getContextPath()%>/assets/locandina.png" alt="No possible load Image sorry!"></div>
                                                            <div class="col-md-3 col-sm-12 col-xs-16">
                                                                <h1 class="text-center">Registrati subito!</h1>
                                                                <p>Se vuoi essere sempre informato o comprare biglietti per gli eventi riguardanti le tue passioni registrati sul nostro sito, non te ne pentirai!</p>
                                                                <a class="btn btn-primary" id="buttonRegister">Registrati!</a>
                                                            </div>
                                                        </div>
                                                        <hr>
                                                          <div class="row" align="center">
                                                              <h2>Dove puoi trovare la nostra sede</h>
                                                              <br>
                                                              <br>
                                                              <div id="apiMAP"></div>
                                                          </div>
                                                        <hr>
                                                            <div class="row">
                                                                <div class="col-lg-12 col-md-11 col-sm-11 col-xs-11">
                                                                    <div class="well text-center">SIW TicketSeller, il sito che ti permette di tenere sotto controllo le tue passioni!</div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-4 col-sm-6 col-xs-8">
                                                                    <h2>Sport</h2>
                                                                    <p>Sei appassionato di calcio, formula uno o di Tennis? Guarda i prezzi dei biglietti degli eventi sportivi</p>
                                                                    <a class="btn btn-primary" href="<%=request.getContextPath()%>/ParameterSearchEvents?selezione=Category&&value=Sport">Guarda ora</a>
                                                                </div>
                                                                <div class="col-md-4 col-sm-6 col-xs-8">
                                                                    <h2>Musica</h2>
                                                                    <p>Scopri gli eventi musicali vicino a te!!!</p>
                                                                    <a class="btn btn-primary" href="<%=request.getContextPath()%>/ParameterSearchEvents?selezione=Category&&value=Musica">Guarda ora</a>
                                                                </div>
                                                                <div class="col-md-4 col-sm-6 col-xs-8">
                                                                    <h2>Cinema</h2>
                                                                    <p>Voglia di rilassarti, di vedere un bel film? Guarda i prezzi dei cinema intorno a te!</p>
                                                                    <a class="btn btn-primary" href="<%=request.getContextPath()%>/ParameterSearchEvents?selezione=Category&&value=Cinema">Guarda Ora</a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <jsp:include page="footer.html"></jsp:include>
                                                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
                                                        <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCx9TL6fDN_8sc6Ee3pPBjpDN6GYIpbE2I"></script>
    													<script type="text/javascript" src="<%=request.getContextPath()%>/content/ticketSellerLocation.js"></script>
                                                    </body>
                                                </html>
