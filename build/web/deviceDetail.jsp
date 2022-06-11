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
            <a href="main.jsp" class="main" >
              <button class="btn btn-primary"> Go back to HomePage</button>
                </a>
        <% Device d = (Device) request.getAttribute("currentDevice");%>
        <script type="text/javascript">
            var price = <%=d.getPrice()%>;
            function setTotal() {
                var qty = document.getElementById("qty").value;
                if (qty !== "" && !isNaN(qty)) {
                    var total = parseInt(qty) * price;
                    document.getElementById("totalDisplay").innerHTML = '$' + total + '.0';
                    document.getElementById("total").value = total;
                }
            }
        </script>
        <div class="Container">
        <div class="main-container">
            <h1 class="text-danger"><%=d.getName()%></h1>
            <form action="CreateOrderServlet" method="POST">
                <table class="table table-bordered">
                    <tr>
                        <th>Price: </th>
                        <td>$<%=d.getPrice()%> / <%=d.getUnit()%></td>
                    </tr>
                    <tr>
                        <th>Type:</th>
                        <td><%=d.getType()%></td>
                    </tr>
                    <tr>
                        <th>Device ID: </th>
                        <td><%=d.getId()%></td>
                    </tr>
                    <tr>
                        <th>Stock:</th>
                        <td><%=d.getStock()%></td>
                    </tr>
                    <tr>
                        <th>Qty:</th>
                        <td><input id="qty" name="quantity" value="1" onkeyup="setTotal()" type="number"></td>
                    </tr>
                    <tr>
                        <th>Total: </th>
                        <td id="totalDisplay">$<%=d.getPrice()%></td>
                    </tr>
                    <input name="deviceId" value="<%=d.getId()%>" style="display: none">
                    <input id="total" name="total" value="<%=d.getPrice()%>" style="display: none">
                </table>
                <div class="text-danger mb-2"><%= session.getAttribute("DeviceDetailError") == null ? "" : "Error: " + session.getAttribute("DeviceDetailError")%></div>
                <center><button class="btn btn-success" type="submit">Place Order</button></center>
            </form>
                
        </div>
</div>
 

    </body>
</html>