<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Weather App</title>

    <!-- Bootstrap4 library & JQUERY -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

  </head>
  <body>
  	<div class="container col-md-6" style="margin-top: 100px">
      <div class="form-inline" style="width: 800px;">
        <input style="width: 600px;" type="text" class="form-control mb-2 mr-sm-2" placeholder="Search by city..." id="cityInput">
        <button type="button" class="btn btn-primary mb-2" id="searchWeatherBtn">Search</button>
      </div>

      <div style="display: none; width: 540px;" class="alert alert-warning" id="warning-message">
        Data not found. Please check your city's name?
      </div>

      <div id="weatherLogHistory">
      </div>
  	</div>


    <script src="<c:url value="/resources/js/weather.js" />"></script>
  </body>
</html>
