<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/layout.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Register Page</title>
    </head>
    <body>

        <%
            String existErr = (String) session.getAttribute("existErr");
            String emailErr = (String) session.getAttribute("emailErr");
            String passErr = (String) session.getAttribute("passErr");
            String nameErr = (String) session.getAttribute("nameErr");
            String numErr = (String) session.getAttribute("numErr");
            String empErr = (String) session.getAttribute("empErr");
        %>
        <div class="Container reg-container">
        
            <img src="css/logoiot.svg" style="margin-left:25%">
        <h1>Staff Register</h1>
        <div class="center">
            <p><span><%=(existErr != null ? existErr : "")%></span><span><%=(empErr != null ? empErr : "")%></span></p>
        </div>
        <form method="post" action="StaffRegisterServlet">
            <table>
                <tr><td>Full name: </td><td><input class="tb" type="text" name="name" ></td><td> <%=(nameErr != null ? nameErr : "")%></td></tr>                
                <tr><td>Date of birth: </td><td><input class="tb" type="date" name="dob" ></td></tr>
                <tr><td>Email: </td><td><input class="tb" type="text" name="email" ></td><td> <%=(emailErr != null ? emailErr : "")%></td></tr>
                <tr><td>Contact number: </td><td><input class="tb" type="text" name="number" ></td><td> <%=(numErr != null ? numErr : "")%></td></tr>
                <tr><td>Address: </td><td><input class="tb" type="text" name="address" ></td></tr>   
                <tr><td>Staff type</td>
                    <td>
                        <select name="type">
                            <option value="manager">Manager</option>
                            <option value="supervisor">Supervisor</option>
                            <option value="normal staff">Normal staff</option>
                        </select>
                    </td>
                </tr>
                <tr><td>Gender</td>
                    <td>
                        <select name="gender">
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                            <option value="Prefer not to say">Prefer not to say</option>
                        </select>
                    </td>
                </tr>
                <tr><td>Password: </td><td><input class="tb" type="password" name="password" ></td><td> <%=(passErr != null ? passErr : "")%></td></tr>             
            </table>
            <div class="center">
                <button><a class="bttn" href="CancelServlet"style="color:white;">Cancel</a></button>
                <a><input class="bttn" type="submit" value="Register"></a>
            </div>
        </form>
        <div class="center">
            <p>Already have an account<a href="login.jsp">Login here</a></p>
            <p>Continue as guest <a href="main.jsp">click here</a> </p>
            <p>Register as a customer <a href="customerRegister.jsp">click here</a> </p>
        </div>
            </div>
    </body>
</html>