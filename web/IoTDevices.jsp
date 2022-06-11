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
              <button class="btn btn-primary"> Go back to HomePage </button>
                </a>
            <div class="Container">
        <div class="main-container">
            <h1 class="text-danger">Device List</h1>
            <button class="btn btn-success btn-sm add-btn" onclick="window.location.href = 'editDevice'">  Add New Device</button>
            <form action="devices" method="GET">
                <div class="input-group my-2">
                    <input type="text" class="form-control" placeholder="Search for Product Name ..." name="keyword">
                    <input type="text" class="form-control" placeholder="Search for Product ID ..." name="deviceId">
                    <div class="input-group-append">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </div>
                </div>
            </form>
            <table class="table table-striped">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Unit</th>
                    <th>Price</th>
                    <th>Stock</th>
                    <th>Actions</th>
                </tr>
                <%
                    List<Device> deviceList = (List<Device>) request.getAttribute("devices");
                    for (Device d : deviceList) {
                %>
                <tr>
                    <td><%= d.getId()%></td>
                    <td><%= d.getName()%></td>
                    <td><%= d.getType()%></td>
                    <td><%= d.getUnit()%></td>
                    <td><%= d.getPrice()%></td>
                    <td><%= d.getStock()%></td>
                    <td>
                        <button class="btn btn-sm btn-info" onclick="window.location.href='DeviceDetailServlet?deviceId=<%=d.getId()%>'">View</button>
                        <button class="btn btn-sm btn-warning" onclick="window.location.href='editDevice?deviceId=<%=d.getId()%>'">Update</button>
                        <form action="devices?deviceId=<%=d.getId()%>" method="POST" style="display:inline">
                            <button class="btn btn-sm btn-danger" type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
            </div>
           
        </div>

 

    </body>
</html>