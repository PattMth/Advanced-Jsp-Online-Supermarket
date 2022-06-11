<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.iot.model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/staffMain.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Main Page</title>
    </head>
    <body>
        <%
            Staff staff = (Staff) session.getAttribute("name");
            User_Account user = (User_Account) session.getAttribute("user");
        %>            
        <img src="css/logoiot.svg" class="logo">        
        <div class="topRight">
            <a class="bttn" href="LogoutServlet">Logout</a>
        </div>
        <div class="user">               
            <p style="font-size:120%; background-color: blue;color:white;border-radius:8%;width:200px"> <%=user.getEmail()%></p>         
        </div>                   
        <div class="center">
       
        </div>        
        <table class="dashboardTable">
            <tr>
                <th class="tabBorder"><a href="StaffEditServlet?email='<%=user.getEmail()%>'&password='<%=user.getPassword()%>'">   Profile</th>
                <th class="tabBorder"><a href="StaffAccessLogServlet?email='<%=user.getEmail()%>'&password='<%=user.getPassword()%>'">   Logs</th>
                <th class="tabBorder">Orders</th>
                <th class="tabBorder"><a href="suppliers.jsp">Suppliers</th>
                <th><a href="#">View cart</a></th>
                <th class="tabBorder">About us</th>
                <th class="tabBorder">Contact us</th>
                <th><form method="post">
                <input class="searchTb" type="search" placeholder="Search..." name="search">
                    </form></th>
            </tr>
        </table>        
    </body>
</html>