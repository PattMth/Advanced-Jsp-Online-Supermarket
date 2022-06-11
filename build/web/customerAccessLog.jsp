<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.iot.model.Access_Log" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/logs.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Access Log Page</title>
    </head>
    <body>
       
        <div class="topRight">
            <a href="main.jsp"><button class="bttn">Previous Page</button></a>
            <a class="bttn" href="LogoutServlet">Logout</a>
        </div>
        <img src="css/logoiot.svg" style="margin-left: 45%;">
       
        <h1>Access Logs</h1>
            
        <table>
            <tr> 
                <td>
                    <form action="CustomerFilterDateServlet" method="post">
                    <div class="center">
                        <input type="text" placeholder="Search Date" name="date">
                        <a><input class="sbttn" type="submit" value="Filter by date"></a>
                    </div>
                    </form>
                </td>
            </tr>
        </table>
        <table class="accessTable">
            <tr>
                <th>Date</th>
                <th>Time</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${list}" var="logs" >
            <tr>
                <td class="tabBorder"><c:out value="${logs.date}"/></td>                
                <td class="tabBorder"><c:out value="${logs.time}"/></td>                
                <td class="tabBorder"><c:out value="${logs.action}"/></td>                
            </tr>
        </c:forEach>
        </table>
        
    </body>
</html>