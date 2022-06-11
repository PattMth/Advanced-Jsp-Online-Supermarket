
<%@page import="uts.iot.model.Order"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Orders</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/layout.css">
    </head>
    <body>
        <div class="container">
            <img src="css/IoTBay_Logo.png">
        </div>
        <div class="center-container">
            <h1>Orders</h1>
            <form action="OrderHistoryServlet" method="GET">
                <div class="input-group my-2">
                    Order ID: <input type="text" class="form-control" placeholder="Search for Orders ..." name="searchId">
                    From: <input type="date" class="form-control" placeholder="From" name="dateFrom">
                    To: <input type="date" class="form-control" placeholder="To" name="dateTo">
                    <div class="input-group-append">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </div>
                </div>
            </form>
            <table class="table table-striped">
                <tr>
                    <th>ID</th>
                    <th>Total Price</th>
                    <th>Date</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                <%
                    List<Order> orders = (List<Order>) request.getAttribute("orders");
                    for (Order o : orders) {
                %>
                <tr>
                    <td><%= o.getId()%></td>
                    <td><%= o.getTotal()%></td>
                    <td><%= o.getDate()%></td>
                    <td><%= o.getStatus()%></td>
                    <td><a href="OrderDetailServlet?oid=<%= o.getId()%>">Details</a></td>
                </tr>
                <%
                    }
                %>

            </table>
        </div>
    </body>
</html>