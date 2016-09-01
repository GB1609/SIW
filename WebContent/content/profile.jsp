<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>PROFILO</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.2/css/bootstrap-select.min.css"/>
        <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.2/js/bootstrap-select.min.js"></script>
        <link href="../bootstrap/css/general.css" rel="stylesheet">

  </head>
  <body>
  <div class="container">
    <div class="row"><div class="col-md-9" id="ticketBuyed">
      <div class="panel panel-default">
        <!-- Head -->
        <div class="panel-heading">
          <h3 class="panel-title">Description</h3>
        </div>
        <!-- Body -->
        <div class="panel-body" id="ticketBuyedBody"></div>
      </div>
    </div>
    </div>
    </div>
    <script>
    $(document).ready(function () {

      $.ajax({
        url: "../SearchTicketBuyed",

        //JSON
        data: {
          username: "gio"
        },
        type: "GET",

        dataType: "json"
      }).done(function (responseJson) {

        $("#ticketBuyed").replaceWith('<div class="col-md-9" id="ticketBuyed"><div class="panel panel-default "><div class="panel-title panel-heading"><h3 class="col-md-3 ">User</h3><h3 class="col-md-6">Comment</h3><h3 class="col-md-3">Ticket</h3></div><div class="panel-body pre-scrollable" i' +
            'd = "ticketBuyedBody"></div></div></div>');

        var $table = $('<table class = "table">').appendTo($("#ticketBuyedBody"));
        $.each(responseJson, function (index, item) {
          var mysplit = item.split(" ");
              $('<tr id = "row' + index + '">').appendTo($table);
              $('<td id = "data' + index + '">').text(split[0]).appendTo('#row' + index);
              $('<td id = "data' + index + '">').text(split[1]).appendTo('#row' + index);
              $('<td id = "data' + index + '">').text(split[2]).appendTo('#row' + index);
        });

      });
    });</script>

    <div id="result"></div>
    <div id="resultTicket"></div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  </body>
</html>
