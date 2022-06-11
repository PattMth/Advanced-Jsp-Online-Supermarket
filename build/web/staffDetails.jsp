<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.iot.model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/layout.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Details Page</title>
    </head>
    <body>
        <%
            Staff staff = (Staff) session.getAttribute("staff");
            String updated = (String) session.getAttribute("updated");
            User_Account user = (User_Account) session.getAttribute("user");
            String nameErr = (String) session.getAttribute("nameErr");
            String numErr = (String) session.getAttribute("numErr");
            String passErr = (String) session.getAttribute("passErr");
            String empErr = (String) session.getAttribute("empErr");
        %>
        <div class="Container">
        <div class="topRight">
            <a href="staffMain.jsp"><button class="bttn">Go to Previous Page</button></a>
            <button><a class="bttn" href="LogoutServlet" style="color:white;">Logout</a></button>
        </div>
        <div class="container">
            <img src="css/logoiot.svg" style="margin-left:28%;">
        </div>
            <h1 style="text-align: center;">Staff Personal details </h1>
        <div class="center">
            <p>Enter the updated details and click the update button to sucessfully update your information</p>
            <span><%=(updated != null ? updated : "")%></span><span><%=(empErr != null ? empErr : "")%></span>
        </div>
        <form method="post" action="StaffUpdateServlet">
            <table>
                <tr><td>Full name: </td><td><input class="tb" type="text" name="name"value="<%=staff.getName()%>" ></td><td> <%=(nameErr != null ? nameErr : "")%></td></tr>                
                <tr><td>Date of birth: </td><td><input class="tb" type="date" name="dob" value=""<%=user.getDob()%>"></td></tr>
                <tr><td>Email: </td><td><input class="tb" type="text" name="email" value="<%=staff.getNumber()%>"></td></tr>
                <tr><td>Contact number: </td><td><input class="tb" type="text" name="number" value="<%=staff.getEmail()%>"></td><td> <%=(numErr != null ? numErr : "")%></td></tr>
                <tr><td>Address: </td><td><input class="tb" type="text" name="address" value="<%=staff.getAddress()%>"></td></tr>   
                <tr><td>Staff type</td>
                    <td>
                        <select name="type"value="<%=staff.getType()%>">
                            <option value="manager">Manager</option>
                            <option value="supervisor">Supervisor</option>
                            <option value="normal staff">Normal staff</option>
                        </select>
                    </td>
                </tr>
                <tr><td>Gender</td>
                    <td>
                        <select name="gender"value="<%=user.getGender()%>">
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                            <option value="Prefer not to say">Prefer not to say</option>
                        </select>
                    </td>
                </tr>
                <tr><td>Password: </td><td><input class="tb" type="password" name="password"value="<%=user.getPassword()%>" ></td><td> <%=(passErr != null ? passErr : "")%></td></tr>             
            </table>
            <div class="center">
                <a><input class="bttn" type="submit" value="Update"></a>
            </div>
        </form>
        <p>If you wish to delete your account click <a href="StaffDeleteServlet"><button class="delbttn">Delete</button></a></p>
        </div>
    </body>
</html>