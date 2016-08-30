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

                if (split[0] === "true" && split[1] === "organizator")
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
                            <button class="btn btn-default list-group-item" data-toggle="modal" data-target="#wishlists" id="wishlistsUser">wishlist</button>
                        </ul>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/content/shoppingCart.jsp">Carrello</a>
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
                                <li>
                                    <a href="<%=request.getContextPath() %>/LogOutServlet" id="logoutButtonUser">Logout
                                    </a>
                                </li>
                            <%} %>

                        </ul>
                    </div>
                </nav>

                <div class="modal fade" id="wishlists" tabindex="-1" role="dialog" aria - labelledby="" aria - hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hi dden="true">&times;</button>
                                <h4 class="modal-title" id="">Wishlist</h4>
                            </div>
                            <div class="modal-body" id="wish_list"></div>
                        </div>
                    </div>
                </div>

                <script>
                    $(document).on("click", "#ticketTable tbody #wishlist", function (event) {
                        $("#wish_list").replaceWith('<div class="modal-body" id="wish_list"></div>');
                        var t = $($(this).closest('tr').find('td:eq(0)')).text();
                        var p = $($(this).closest('tr').find('td:eq(1)')).text();
                        $.ajax({
                            url: "<%=request.getContextPath()%>/ShowWishlistServlet",

                            //JSON
                            data: {
                                owner: "${name}"
                            },
                            type: "POST",

                            dataType: "json"
                        }).done(function (responseJson) {
                            if (responseJson === "EMPTY") {
                                alert("EMPTY");
                            } else {
                                var $ul = $('<ul class="list-group">').appendTo($("#wish_list"));
                                $.each(responseJson, function (index, item) {
                                    var mysplit = item.split(" ");
                                    $('<button type ="button" data-dismiss="modal" value="' + mysplit[0] + "_" + t + "_" + p + '" class="btn btn-default list-group-item" id ="sw">').text(mysplit[1]).appendTo($ul);
                                });
                            }
                        });
                    });

                    $(document).on("click", "#wishlistsUser", function (event) {
                        $("#wish_list").replaceWith('<div class="modal-body" id="wish_list"></div>');
                        $.ajax({
                            url: "<%=request.getContextPath()%>/ShowWishlistServlet",

                            //JSON
                            data: {
                                owner: "${name}"
                            },
                            type: "POST",

                            dataType: "json"
                        }).done(function (responseJson) {
                            if (responseJson === "EMPTY") {
                                var $ul = $('<ul class="list-group">').appendTo($("#wish_list"));
                                $('<button type ="button" class="btn btn-default list-group-item" id ="createWishList">').text("Vuoi crearne una?").appendTo($ul);
                            } else {
                                var $ul = $('<ul class="list-group" id = "wishList_ul">').appendTo($("#wish_list"));
                                $.each(responseJson, function (index, item) {
                                    var mysplit = item.split(" ");
                                    $('<button type="button" value=' + mysplit[0] + ' class="btn btn-default list-group-item" id="showWT"><div class="col-md-11" id="wishName"> ' + mysplit[1] + '</div> <div class="col-md-1"> <span class="caret"></span></div></button>').appendTo($ul);
                                });
                                $('<button type ="button" class="btn btn-default list-group-item" id ="createWishList">').text("add").appendTo($ul);
                            }
                        });
                    });

                    $(document).on("click", "#wishlists #wish_list #showWT", function (event) {
                        var lcode = $("#showWT").val();
                        var ltext = $("#wishName").text();
                        $.ajax({
                            url: "<%=request.getContextPath()%>/showWishTicket",

                            //JSON
                            data: {
                                listcode: lcode
                            },
                            type: "GET",

                            dataType: "json"
                        }).done(function (responseJson) {
                            $('#wishList_ul').replaceWith('<ul class="list-group" id = "wishList_ul">');
                            var $ul = $('<li class="dropdown list-group-item" id="dropWTItem"></li>').appendTo($("#wishList_ul"));
                            $('<button type="button" class="btn btn-default dropdown-toggle list-group-item" data-toggle="dropdown" value="' + lcode + '" id = "showWT""><div class="col-md-11" id ="wishName">' + ltext + '</div><div class="col-md-1"><span class="caret"></span></div></button>').appendTo($ul);
                            var $dropmenu = $('<ul class ="dropdown-menu">').appendTo($ul);
                            if (responseJson === "EMPTY") {
                                $('<li><div class = "dropdown-toggle list-group-item" data-toggle="dropdown">Empty</div></li>').appendTo($dropmenu);
                            } else {
                                $.each(responseJson, function (index, item) {
                                    var mysplit = item.split("_");
                                    $('<li><div class = "list-group-item" id="valueWT">Evento:' + mysplit[2] + '; Tipo: ' + mysplit[0] + ';prezzo: ' + mysplit[1] + ';<button class="btn btn-danger" id="removeWT" value="' + lcode + '_' + mysplit[4] + '_' + ltext + '">-</button> <button class="btn btn-success" id ="buyWT" value="' + lcode + "_" + mysplit[4] + "_" + ltext + '">BUY<span class="glyphicon glyphicon-shopping-cart"></span></button></div></li>').appendTo($dropmenu);
                                });
                            }
                        })
                    });

                    $(document).on("click", "#removeWT", function (event) {
                        var ticketSplit = $(this).val().split("_");
                        $.ajax({
                            url: "<%=request.getContextPath()%>/RemoveWishTicket",

                            //JSON
                            data: {
                                listcode: ticketSplit[0],
                                ticketcode: ticketSplit[1]
                            },
                            type: "POST",

                            dataType: "json"
                        }).done(function (responseJson) {
                            if (responseJson === "DONE") {
                                $("#dropWTItem").replaceWith('<button type="button" value="' + ticketSplit[0] + '" class="btn btn-default list-group-item" id="showWT"><div class="col-md-11">' + ticketSplit[2] + '</div> <div class="col-md-1"> <span class="caret"></span></div></button>');
                                alert("Cancellazione riuscita con successo");
                            } else {
                                alert("Errore!");
                            }
                        });

                    });

                    $(document).on("click", "#buyWT", function (event) {
                        var ticketSplit = $(this).val().split("_");
                        $.ajax({
                            url: "<%=request.getContextPath()%>/BuyFromWishList",

                            //JSON
                            data: {
                                listcode: ticketSplit[0],
                                ticketcode: ticketSplit[1]
                            },
                            type: "POST",

                            dataType: "json"
                        }).done(function (responseJson) {
                            if (responseJson === "DONE") {
                                $("#dropWTItem").replaceWith('<button type="button" value="' + ticketSplit[0] + '" class="btn btn-default list-group-item" id="showWT"><div class="col-md-11">' + ticketSplit[2] + '</div> <div class="col-md-1"> <span class="caret"></span></div></button>');
                                alert("Aggiunto al carrello");
                            } else {
                                alert("Errore!");
                            }
                        });
                    });

                    $(document).on("click", "#createWishList", function (event) {
                        $("#wish_list").replaceWith('<div class="modal-body" id="wish_list"></div>');
                        var $ul = $('<ul class="list-group">').appendTo($("#wish_list"));
                        $('<div class="col-md-10"><input type="name" name="name" id="wishListName" class="form-control input-sm" placeholder="Name">').appendTo($ul);
                        $('<div class="col-md-2"><button class="btn btn-success" data-dismiss="modal" id="saveWishList">Add</button>').appendTo($ul);
                    });

                    $(document).on("click", "#saveWishList", function (event) {
                        $.ajax({
                            url: "<%=request.getContextPath()%>/CreateWishlistServlet",

                            //JSON
                            data: {
                                owner: "${name}",
                                name: $("#wishListName").val()
                            },
                            type: "POST",

                            dataType: "json"
                        }).done(function (responseJson) {
                            if (responseJson === "DONE")
                                alert("Creazione avvenuta con successo!");
                            else {
                                alert("Errore!");
                            }
                        });
                    });
                </script>

                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
            </html>
        </body>
