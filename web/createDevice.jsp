
<%@page import="uts.iot.model.Device"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Devices</title>
        <link href="css/devices.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
        <link href="css/devices.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
            <img src="css/logoiot.svg">
            <a href="main.jsp" class="main">
              <button class="btn btn-primary" > Go to Homepage </button>
                </a>
            <div class="Container">
        <div class="main-container">
            <h1 class="text-danger">New Device</h1>
            <form action="editDevice" method="POST">
                <input type="text" class="d-none" name="deviceId" id="deviceName">
                <div class="form-group">
                    <label for="deviceName">Name</label>
                    <input type="text" class="form-control" name="deviceName" id="deviceName">
                </div>
                <div class="form-group">
                    <label for="deviceType">Type</label>
                    <input type="text" class="form-control"name="deviceType" id="deviceType">
                </div>
                <div class="form-group">
                    <label for="deviceUnit">Unit</label>
                    <select class="form-control" id="deviceUnit" name="deviceUnit">
                        <option>each</option>
                        <option>pack</option>
                        <option>box</option>
                        <option>kg</option>
                        <option>g</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="devicePrice">Price</label>
                    <input type="number" class="form-control"name="devicePrice" id="devicePrice">
                </div>
                <div class="form-group">
                    <label for="deviceStock">Stock</label>
                    <input type="number" class="form-control"name="deviceStock" id="deviceStock">
                </div>

 

               <center> <button type="submit" class="btn btn-primary">Submit</button> </center>

 

            </form>
             
        </div>
                </div>

 

    </body>
</html>