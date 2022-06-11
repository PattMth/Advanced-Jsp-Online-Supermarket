
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/logs.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Access Log Page</title>
    </head>
    <body>
        <div class="topRight">
            <a href="staffMain.jsp"><button class="bttn">Go to Previous Page</button></a>
            <a class="bttn" href="LogoutServlet">Logout</a>
        </div>
        <div class="container">
            <img src="css/logoiot.svg" style="margin-left: 45%;">
        </div>
        <h1 style="color:white;">Access Logs</h1>
        <table>
            <tr> 
                <td>
                    <form action="FilterDateServlet" method="post">
                        <div class="center">
                            <input type="text" placeholder="Search Date" name="date">
                            <input type="text" placeholder="Search user Id" name="userId">
                            <a><input class="sbttn" type="submit" value="Filter"></a>
                        </div>
                    </form>
                </td>
            </tr>
        </table>
        <table class="accessTable">
            <tr>
                <th>Id</th>
                <th>Date</th>
                <th>Time</th>
                <th>Action</th>
            </tr>
            
        </table>
    </body>
</html>