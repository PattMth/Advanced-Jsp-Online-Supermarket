<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.iot.model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/layout.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <a href="index.jsp"></a>
        <title>Index Page</title>
    </head>
    <body>
        <div class="Container">
            <header>
            <img class="logo" src="css/logoiot.svg">
        <div>
            <table class="center">
                <tr>
                    <td><a href="login.jsp"><button class="btn login-btn">Login</button></a></td>   
                </tr>
                <tr>
                  <td><a href="customerRegister.jsp"><button class="btn reg-btn">Register as a Customer</button></a></td>  
                </tr>
                <tr>
                    <td><a href="staffRegister.jsp"><button class="btn reg-btn">Register as a Staff</button></a></td>
                </tr>
                <tr>
                    <td>
                        <a href="main.jsp"><button id="btn btn-login">Continue to IoTBay</button></a>
                    </td>
                </tr>
            </table>
        </div>
       </header>
    
            
        
         </div>
        <jsp:include page="/ConnServlet" flush="true"/>
    </body>
</html>