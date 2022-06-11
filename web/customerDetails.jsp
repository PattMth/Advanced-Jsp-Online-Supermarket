<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.iot.model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/layout.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Personal Details Page</title>
    </head>
    <body>

        <%
            Customer customer = (Customer) session.getAttribute("customer");
            String updated = (String) session.getAttribute("updated");
            User_Account user = (User_Account) session.getAttribute("user");
            String nameErr = (String) session.getAttribute("nameErr");
            String numErr = (String) session.getAttribute("numErr");
            String passErr = (String) session.getAttribute("passErr");
            String empErr = (String) session.getAttribute("empErr");
        %>
        <div class="Container">
        <div class="topRight">
            <a href="main.jsp"><button class="bttn" style="margin-left:10%">Previous Page</button></a>
            <button><a class="bttn" href="LogoutServlet"style="color:white;">Logout</a></button>
        </div>
 
            <img src="css/logoiot.svg" style="margin-left: 30%">
       
        <h1 style="text-align: center;">Personal details </h1>
        <div class="center">
            <p>Enter the details to be changed below and click update to update your information</p>
            <span><%=(updated != null ? updated : "")%></span><span><%=(empErr != null ? empErr : "")%></span>
        </div>
        <form method="post" action="CustomerUpdateServlet">
            <table>
                <tr><td>Full name: </td><td><input class="tb" type="text" name="name" value="<%=customer.getName()%>"></td><td> <%=(nameErr != null ? nameErr : "")%></td></tr>                
                <tr><td>Date of birth: </td><td><input class="tb" type="date" name="dob" value="<%=user.getDob()%>"></td></tr>
                <tr><td>Email: </td><td><input class="tb" type="text" name="email" value="<%=customer.getEmail()%>" ></td></tr>
                <tr><td>Contact number: </td><td><input class="tb" type="text" name="number" value="<%=customer.getNumber()%>"></td><td> <%=(numErr != null ? numErr : "")%></td></tr>
                <tr><td>Gender</td>
                    <td>
                        <select name="gender" value="<%=user.getGender()%>">
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                            <option value="Prefer not to say">Prefer not to say</option>
                        </select>
                    </td>
                </tr>
                <tr><td>Address: </td><td><input class="tb" type="text" name="address" value="<%=customer.getAddress()%>"></td></tr>
                <tr><td>Password: </td><td><input class="tb" type="password" name="password" value="<%=user.getPassword()%>"</td><td> <%=(passErr != null ? passErr : "")%></td></tr>
                <tr><td>Subscribe to our email news</td><td><input type="checkbox" name="news" value="<%=user.isNews()%>"></td></tr>
                
            </table>
            <div class="center">
                <a><input class="bttn" type="submit" value="Update"></a>
            </div>
        </form>
        <p>If you wish to delete your account click <a href="DeleteServlet"><button class="bttn">Delete</button></a></p>
         </div>
        </body>
</html>