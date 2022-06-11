<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/layout.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <%
            String existErr = (String) session.getAttribute("existErr");
            String emailErr = (String) session.getAttribute("emailErr");
            String passErr = (String) session.getAttribute("passErr");
            String empErr = (String) session.getAttribute("empErr");
        %>
        <div class="Container reg-container">
            <img src="css/logoiot.svg" style="margin-left:35%;">
        <h1 style="text-align:center;">Login </h1>
        <div class="center">
            <p><span><%=(existErr != null ? existErr : "")%></span><span><%=(empErr != null ? empErr : "")%></span></p>
        </div>
        <div class="center">
            <form method="post" action="LoginServlet">
                <table class="center">
                    <tr>
                        <td>Useremail</td>
                        <td><input class="tb" type="text" placeholder="<%=(emailErr != null ? emailErr : "Enter email")%>" name="email" ></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input class="tb" type="password" placeholder="<%=(passErr != null ? passErr : "Enter password")%>" name="password" ></td>
                    </tr>
                </table>
                <div class="center">
                    <button><a class="bttn" href="CancelServlet" style="color:white;">Cancel</a></button>
                    <a><input class="bttn" type="submit" value="Login"></a>
                </div>
            </form>
        </div>
        <div class="center">
            <p>Dont have an account. Register as <a href="customerRegister.jsp">Customer</a> or <a href ="staffRegister.jsp">Staff</a></p>
            <p>Continue as guest <a href="main.jsp">Click here</a> </p>
        </div>
                    </div>
    </body>
</html>