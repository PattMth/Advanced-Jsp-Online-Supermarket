<%@page import="java.util.ArrayList"%>
<%@page import="uts.iot.model.Supplier"%>
<%@page import="uts.iot.model.dao.SupplierDBManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Suppliers</title>
         <link rel="stylesheet" href="css/supplier.css">
    </head>
    <body>
        <%
            SupplierDBManager manager = (SupplierDBManager) session.getAttribute("supplierManager");
            ArrayList<Supplier> suppliers = (ArrayList)request.getAttribute("suppliers");
        %>
        <h1>Suppliers</h1>
         <div class="topRight">
            <a href="staffMain.jsp"><button class="bttn">Go back to main dashboard</button></a>
            <a class="bttn" href="LogoutServlet">Logout</a>
        </div>
        
        <br>
        <form method="post" action="FilterSupplierServlet">
            <table class="center">
                <tr>
                    <td>Filter:</td><td><input type="text" placeholder="Enter name" name="name"></td>
                    <td><input type="text" placeholder="Enter type" name="type"></td>
                    <td><input class="button" type="submit" value="filter"></td>
                    <td><a href = "addSuppliers.jsp">add supplier</a></td>
                    <td><a href="suppliers.jsp">clear filter</a></td>
                </tr>              
            </table>
        </form>
        <div class="tcontainer" style="margin-left: 5%;">
        <table class="center">
            <tr>
                <th>Name</th>
                <th>Type</th>
                <th>Contact No.</th>
                <th>Email</th>
                <th>Address</th>
                <th>Status</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
                    <% for (Supplier s : suppliers) {%>
            <tr><td><%=s.getSupplierName()%></td><td><%=s.getCompanyType()%></td><td><%=s.getContactNumber()%></td>  
                <td><%=s.getEmail()%></td><td><%=s.getAddress()%></td><td><%=s.getStatus()%></td>  
                <td><a href="editSupplier.jsp?name=<%=s.getSupplierName()%>">edit</a></td>
                <td><a href="DeleteSupplierServlet?id=<%=s.getSupplierID()%>">delete</a></td>
            <% }%>

        </table>
            </div>
    </body>
</html>
