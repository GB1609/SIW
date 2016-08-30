<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html"%>
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
                            <link href="<%=request.getContextPath()%>/bootstrap/css/general.css" rel="stylesheet">

                                <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
                                <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
                                <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
                                <jsp:include page="navbar.jsp"/>
                            </head>
                            <body>
                                <div class="col-md-10" id="cartList">
                                    <table class="table" id="cartTableHTML">
                                        <thead>
                                            <tr>
                                                <th width="200">
                                                    <h2>Carrello</h2>
                                                </th>
                                                <th></th>
                                                <th>Prezzo</th>
                                                <th>Quantita</th>
                                            </tr>
                                        </thead>
                                        <tbody id="cartTableBodyHTML"></tbody>
                                    </table>
                                </div>
                                <div class="col-md-2">
                                    <div class="panel panel-primary">
                                        <div class="panel-heading">
                                            <h5>TOTALE</h>
                                        </div>
                                        <div class="panel-body">
                                            <tr>
                                                <td>
                                                    <h3 id="totalShow">0,00 EUR</h3>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <button type="button" class="btn btn-success btn-lg" id="BuyButton">ACQUISTA<span class="glyphicon glyphicon-shopping-cart"></span>
                                                    </button>
                                                </td>
                                            </tr>
                                        </div>
                                    </div>
                                </div>

                                <script>
                                    var total = 0;
                                    $(document).ready(function () {
                                        $.ajax({url: "<%=request.getContextPath()%>/ShowCartServlet"}).done(function (responseJson) {
                                            $('#cartTableBodyHTML').replaceWith('<tbody id="cartTableBodyHTML"></tbody>');
                                            if (responseJson === "EMPTY") {
                                                var $tr = $('<tr>').appendTo('#cartTableBodyHTML');
                                                $('<td id = "cartData">').text("Empty").appendTo($tr);
                                            } else {
                                                $.each(responseJson, function (index, item) {
                                                    var myspl = item.split("_");
                                                    var $tr = $('<tr id = "' + myspl[0] + '">').appendTo('#cartTableBodyHTML');
                                                    $('<td> <img src="' + myspl[4] + '" width="150" height="150"></img> </td> <td> <div class="row"><div class="col-lg-8">  <h3>' + myspl[3] + ' </h> </div> <div class = "col-md-2"> <h3>' + myspl[1] + '</h> </div></div> <div class="row"><button class="btn btn-danger" value="' + myspl[0] + '" id="removeTicket"><span class="glyphicon glyphicon-trash"></span></button></div> </td><td><h3>' + myspl[2] + '<span class="glyphicon glyphicon-euro"></span > </h></td>  <td><h3><input type="number" name="quant" id="quant" min="1" max="3" class="form-control input-sm" value="1"></input></h3></input></td><td><h3><button class="btn btn-success" id="quantityButt' +
                                                            'on" value="' + parseFloat(myspl[2]) + "_" + 1 + "_" + myspl[0] + '">SAVE</button></h3></td>').appendTo($tr);
                                                    total += (parseFloat(myspl[2]) * 1);
                                                });
                                                $("#totalShow").replaceWith('<h3 id = "totalShow">' + total + ',00 EUR</h>');
                                            }
                                        });
                                    });

                                    $(document).on("click", "#quantityButton", function (event) {
                                        var spl = $(this).val().split("_");
                                        var price = parseFloat(spl[0]);
                                        var quant = spl[1];
                                        var code = spl[2];
                                        total -= (price * quant);
                                        total += (price * $("#quant").val());
                                        $.ajax({
                                            url: "<%=request.getContextPath()%>/ChangeTicketQuantity",
                                            data: {
                                                ticketcode: code,
                                                quantity: $("#quant").val()
                                            },
                                            type: "GET",

                                            dataType: "json"
                                        }).done(function (responseJson) {
                                            if (responseJson === "SUCCESS") {
                                                $(this).val(price + "_" + $("#quant").val());
                                                $("#totalShow").replaceWith('<h3 id = "totalShow">' + total + ',00 EUR</h>');
                                            } else {
                                                alert(responseJson);
                                            }
                                        });
                                    });

                                    $(document).on("click", "#removeTicket", function (event) {
                                        var t = $(this).val();
                                        $.ajax({
                                            url: "<%=request.getContextPath()%>/removeFromCart",
                                            data: {
                                                ticketcode: t
                                            },
                                            type: "GET",

                                            dataType: "json"
                                        }).done(function (responseJson) {
                                            if (responseJson === DONE) {
                                                $('#' + t).replaceWith("");
                                                total -= parseFloat($($(this).closest("tr").find("h3")).text());
                                                $("#totalShow").replaceWith('<h3 id = "totalShow">' + total + ',00 EUR</h>');
                                            } else {
                                                alert("Errore!");
                                            }
                                        })
                                    });

                                    $(document).on("click", "#BuyButton", function (event) {
                                        $.ajax({
                                            url: "<%=request.getContextPath()%>/BuyTicketServlet",

                                            //JSON
                                            data: {
                                                owner: "${name}"
                                            },
                                            type: "POST",

                                            dataType: "json"
                                        }).done(function (responseJson) {
                                            if (responseJson === "DONE") {
                                                alert("ACQUISTO EFFETTUATO CORRETTAMENTE");
                                                total = 0;
                                                $("#totalShow").replaceWith('<h3 id = "totalShow">0,00 EUR</h>');
                                                $('#cartTableBodyHTML').replaceWith('<tbody id="cartTableBodyHTML"></tbody>');
                                            } else
                                                alert(responseJson);
                                            }
                                        );
                                    });
                                </script>
                                <jsp:include page="footer.html"></jsp:include>
                                <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
                            </body>
                        </html>
