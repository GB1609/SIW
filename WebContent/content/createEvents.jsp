<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html"%>
<!DOCTYPE <!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1">
                    <title>SIW</title>

                    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
                        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

                            <script src="https://code.jquery.com/jquery-1.10.2.js"></script>

                            <link href="<%= request.getContextPath() %>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
                                <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
                                <link href="<%= request.getContextPath() %>/bootstrap/css/general.css" rel="stylesheet">
                                    <link href="<%= request.getContextPath() %>/bootstrap/css/jumbo.css" rel="stylesheet">
                                        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
                                        <link href="<%= request.getContextPath() %>/WEB-INF/lib/jquery.js">

                                            <script>

                                                $(document).on("click","#sub",function(event){
                                                  $.ajax({
                                                      url :"<%= request.getContextPath()%>/CreateAnEventServlet" ,
                                                      data : {
                                                        nome: $("#usr").val(),
                                                        categoria: $("#sel1").val(),
                                                        partecipanti: $("#select").val(),
                                                        citta: $("#cities").val() ,
                                                        luogo: $("#luogo").val(),
                                                        tipoBiglietti:$("#tickets").val() ,
                                                        costoBiglietti : $("#costo").val() ,
                                                        numeroBiglietti: $("#num").val() ,
                                                        description : $("#description").val(),
                                                        dataEvento : $("#dataEvento").val(),
                                                        immagine : $("#immagine").val()
                                                      } ,
                                                      type : "POST" ,
                                                      dataType: "json"
                                                  }).done(function(responseJson){
                                                    alert (responseJson);
                                                  }).fail(function (responseJson){
                                                	  alert (responseJson);
                                                  });
                                                });
                                            </script>

                                        </head>
                                        <body>
   	<%  if(! (session.getAttribute("tipe")=="organizator")){%>
                                             	<%@include file="navbar.jsp"%>
                                             	<% }else{ %>
                                             	<%@include file="organizatorNavbar.jsp"%>
                                             	<%} %>
                                            <br>
                                                <br>
                                                    <br>
                                                        <div class="container">
                                                            <h1>Creazione di un evento</h1>
                                                        </div>
                                                        <br>
                                                            <br>
                                                                <br>

                                                                    <div class="container">
                                                                        <div class="row">
                                                                            <div class="col-md-3">
                                                                                <label for="usr">Nome evento:</label>
                                                                                <input type="text" class="form-control" id="usr"></div>
                                                                                <div class="col-md-3">
                                                                                    <label for="sel1">Categoria:</label>
                                                                                    <select class="form-control" id="sel1" >
                                                                                        <c:forEach items="${allCat}" var="bubu">
                                                                                            <option>${bubu}</option>
                                                                                        </c:forEach>
                                                                                    </select>
                                                                                </div>
                                                                                <div class="col-md-3">
                                                                                    <div class="form-group">
                                                                                        <label for="select">Partecipanti:</label>
                                                                                        <select id="select" class="form-control">
                                                                                            <c:forEach items="${ allPartecipants}" var="bo">
                                                                                                <option>${bo}</option>
                                                                                            </c:forEach>

                                                                                        </select>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-md-3">
                                                                                    <div class="form-group">
                                                                                        <label for="sel1">City:</label>
                                                                                        <select id="cities" class="form-control">
                                                                                            <c:forEach items="${ allCities}" var="bo">
                                                                                                <option>${bo}</option>
                                                                                            </c:forEach>

                                                                                        </select>
                                                                                    </div>

                                                                                </div>
                                                                            </div>
                                                                            <br>
                                                                                <br>

                                                                                    <div class="row">
                                                                                        <div class="col-md-3">
                                                                                            <label for="luogo">Luogo:</label>
                                                                                            <select class="form-control" id="luogo">
                                                                                               <c:forEach items="${ allPlace}" var="bo2">
                                                                                                <option>${bo2}</option>
                                                                                            </c:forEach>
                                                                                            </select>
                                                                                            </div>
                                                                                            <div class="col-md-3">
                                                                                                <div class="form-group">
                                                                                                    <label for="tickets">Tipologia Biglietti:</label>
                                                                                                    <select id="tickets" class="form-control">
                                                                                                        <c:forEach items="${ allTickets}" var="ticket">
                                                                                                            <option>${ticket}</option>
                                                                                                        </c:forEach>
                                                                                                    </select>
                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="col-md-2">
                                                                                                <label for="costo">Costo Biglietti:</label>
                                                                                                <input type="text" class="form-control" id="costo"></div>
                                                                                                <div class="col-md-2">
                                                                                                    <label for="num">Numero Biglietti:</label>
                                                                                                    <input type="text" class="form-control" id="num" ></div>

                                                                                                </div>
                                                                                                <div class="row">
                                                                                                  <div class="col-md-3">
                                                                                                  <div class="form-group">
                                                                                                   <label for="description">Descrizione:</label>
                                                                                                   <textarea class="form-control" rows="5" id="description"></textarea>
                                                                                                 </div>
                                                                                               </div>
                                                                                               <div class="col-md-3">
                                                                                                   <label for="dataEvento">Data:</label>
                                                                                                 <input type="date"  id="dataEvento" class="form-control input-sm">
                                                                                               </div>
                                                                                               <div class="col-md-4">
                                                                                                   <label for="immagine">URL IMMAGINE:</label>
                                                                                                   <input type="text" class="form-control" id="immagine" ></div>

                                                                                               </div>

                                                                                                <br>
                                                                                                    <br>
                                                                                                        <br>

                                                                                                              <input type="submit" class="btn-primary" id="sub"></input>


                                                                                                        </div>
                                                                                                        <br> <br>

                                                                                                        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
                                                                                                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
                                                                                                        <!-- Include all compiled plugins (below), or include individual files as needed -->
                                                                                                
                                                                                                    </body>
                                                                                                </html>
