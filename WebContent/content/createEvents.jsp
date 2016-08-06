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
                                        var index=0;

                                          $(document).on("click","#addPart", function(event){
                                            index++;

                                          $("#buttonAddPart").remove();
                                          $("#part0").append('<div class="col-md-1"></div>  <div class="col-md-3">  <div class="form-group"> <label for="select'+index+'">Partecipanti:</label> <select id="select'+index+'" class="form-control"> <c:forEach items="${ allPartecipants}" var="bo"> <option>${bo}</option> </c:forEach></select></div></div> <div class="col-md-1" id="buttonAddPart"> <label for="addPart"> Add: </label> <button class="btn btn-success" id="addPart"> + </button> </div> ');
                                          });

                                      </script>



                                        <script>
                                        var index2=0;

                                          $(document).on("click","#addTickets", function(event){
                                            index2++;
                                            $("#buttonAddTicket").remove();
                                            $("#farlocco").append('<div class="row" id="ticketsRow'+index2+'"> <div class="col-md-3"><div class="form-group"><label for="tickets'+index2+'">Tipologia Biglietti:</label>  <input type="text" class="form-control" id="tickets'+index2+'"> </div> </div> <div class="col-md-2"> <label for="costo'+index2+'">Costo Biglietti:</label> <input type="text" class="form-control" id="costo'+index2+'"></div> <div class="col-md-2"> <label for="num'+index2+'">Numero Biglietti:</label>  <input type="text" class="form-control" id="num'+index2+'" ></div> <div class="col-md-1" id="buttonAddTicket"> <label for="addTickets"> Add: </label>  <button class="btn btn-success" id="addTickets"> + </button> </div></div><br>');
                                            });
                                        </script>

                                            <script>


                                                $(document).on("click","#sub",function(event){

                                                	var ticketsNumber = [];

                                                	var ticketsCost = [];

                                                	var ticketsType = [] ;

                                                	for (var i=0; i<index2+1;i++)
                                                	{
                                                		ticketsNumber.push($("#num"+i).val());
                                                  	  	ticketsCost.push($("#costo"+i).val());
                                                    	ticketsType.push($("#tickets"+i).val());
                                                	}

                                                	var partecipants = [];

                                                	for (var i=0; i<index+1; i++)
                                                		{

                                                		partecipants.push($("#select"+i).val());
                                                	}

                                                  $.ajax({
                                                      url :"<%= request.getContextPath()%>/CreateAnEventServlet" ,
                                                      data : {
                                                        nome: $("#usr").val(),
                                                        categoria: $("#sel1").val(),
                                                        partecipanti: partecipants,
                                                        citta: $("#cities").val() ,
                                                        luogo: $("#luogo").val(),
                                                        tipoBiglietti:ticketsType ,
                                                        costoBiglietti : ticketsCost ,
                                                        numeroBiglietti: ticketsNumber ,
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

                                             	<%@include file="organizatorNavbar.jsp"%>

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
                                                                          </div><br>

                                                                            <div class="row">
                                                                                <div class="col-md-3">
                                                                                    <label for="sel1">Categoria:</label>
                                                                                    <select class="form-control" id="sel1" >
                                                                                        <c:forEach items="${allCat}" var="bubu">
                                                                                            <option>${bubu}</option>
                                                                                        </c:forEach>
                                                                                    </select>
                                                                                </div>
                                                                              </div><br>

                                                                              <div class="row" id="part0">


                                                                                <div class="col-md-3">
                                                                                    <div class="form-group">
                                                                                        <label for="select0">Partecipanti:</label>
                                                                                        <select id="select0" class="form-control">
                                                                                            <c:forEach items="${ allPartecipants}" var="bo">
                                                                                                <option>${bo}</option>
                                                                                            </c:forEach>

                                                                                        </select>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="col-md-1" id="buttonAddPart">
                                                                                      <label for="addPart"> Add: </label>
                                                                                <button class="btn btn-success" id="addPart"> + </button>
                                                                                </div>

                                                                              </div> <br>
                                                                              <div class="row">
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
                                                                                          </div> <br>
                                                                                          <div id="farlocco">
                                                                                          <div class="row" id="ticketsRow0">
                                                                                            <div class="col-md-3">
                                                                                                <div class="form-group">
                                                                                                    <label for="tickets0">Tipologia Biglietti:</label>

                                                                                                    <input type="text" class="form-control" id="tickets0">

                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="col-md-2">
                                                                                                <label for="costo0">Costo Biglietti:</label>
                                                                                                <input type="text" class="form-control" id="costo0"></div>
                                                                                                <div class="col-md-2">
                                                                                                    <label for="num0">Numero Biglietti:</label>
                                                                                                    <input type="text" class="form-control" id="num0" ></div>

                                                                                                    <div class="col-md-1" id="buttonAddTicket">
                                                                                                          <label for="addTickets"> Add: </label>
                                                                                                    <button class="btn btn-success" id="addTickets"> + </button>
                                                                                                    </div>


                                                                                                  </div><br>
                                                                                                  </div>
                                                                                              <div class="row">
                                                                                                  <div class="col-md-3">
                                                                                                  <div class="form-group">
                                                                                                   <label for="description">Descrizione:</label>
                                                                                                   <textarea class="form-control" rows="5" id="description"></textarea>
                                                                                                 </div>
                                                                                               </div>
                                                                                             </div> <br>
                                                                                             <div class="row">
                                                                                               <div class="col-md-3">
                                                                                                   <label for="dataEvento">Data:</label>
                                                                                                 <input type="date"  id="dataEvento" class="form-control input-sm">
                                                                                               </div>
                                                                                             </div><br>
                                                                                             <div class="row">
                                                                                               <div class="col-md-4">
                                                                                                   <label for="immagine">URL IMMAGINE:</label>
                                                                                                   <input type="text" class="form-control" id="immagine" ></div>

                                                                                               </div>

                                                                                                <br>
                                                                                                    <br>
                                                                                                        <br>

                                                                                                    <button class="btn btn-primary" id="sub">Save</button>


                                                                                                        </div>
                                                                                                        <br> <br>

                                                                                                        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
                                                                                                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
                                                                                                        <!-- Include all compiled plugins (below), or include individual files as needed -->

                                                                                                    </body>
                                                                                                </html>
