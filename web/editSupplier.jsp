<%@page import="uts.iot.model.Supplier"%>
<%@page import="uts.iot.model.dao.SupplierDBManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Supplier</title>
         <link rel="stylesheet" href="css/supplier.css">
    </head>
    <body>
        <%
            SupplierDBManager manager = (SupplierDBManager) session.getAttribute("supplierManager");
            String name = request.getParameter("name");
            Supplier supplier = manager.findSupplierByName(name);
                       
            String emptyErr = (String) session.getAttribute("supplierEmptyErr");
            String emailErr = (String) session.getAttribute("supplierEmailErr");
            String numberErr = (String) session.getAttribute("supplierNumberErr");
        %>
        <div class="Container">
        
        <h1 style="margin-bottom:30px;">Edit Supplier</h1>
        <div class="center">
            <a href = suppliers.jsp class="bttn">Previous Page</a>
        </div>
        
        <p><span><%=(emptyErr != null ? emptyErr : "")%></span></p>
        
        <form method="post" action="EditSupplierServlet">
            <table class="center">
                <input type="hidden" value="<%=supplier.getSupplierID()%>" name="id">
                <tr><th>Name: </th><td><input type="text" value="<%=supplier.getSupplierName()%>" name="name"></td></tr>
                <tr><th>Type: </th><td><input type="text" value="<%=supplier.getCompanyType()%>" name="type"></td></tr>
                <tr>
                    <th>Contact No.: </th><td><input type="text" value="<%=supplier.getContactNumber()%>" name="number"></td>
                    <td> <%=(numberErr != null ? numberErr : "")%></td>
                </tr>
                <tr>
                    <th>Email: </th><td><input type="text" value="<%=supplier.getEmail()%>" name="email"></td>
                    <td> <%=(emailErr != null ? emailErr : "")%></td>
                </tr>
                <tr><th>Address: </th><td><input type="text" value="<%=supplier.getAddress()%>" name="address"></td></tr>
                <%  if(supplier.getStatus()){%>
                <tr>
                    <th>Status: </th>
                    <td><input type="radio" name="status" value="Active" checked><label for="Active">Active</label>
                        <input type="radio" name="status" value="Inactive"><label for="Inactive">Inactive</label></td>
                </tr>
                <%  }else {%>
                <tr>
                    <th>Status: </th>
                    <td><input type="radio" name="status" value="Active" checked><label for="Active">Active</label>
                        <input type="radio" name="status" value="Inactive"><label for="Inactive">Inactive</label></td>
                </tr>        
                <%  }%>
                <tr>
                    <td></td><td><input class="ebttn" type="submit" value="edit"></td>
                </tr>               
            </table>                   
        </form>
                </div>
    </body>
</html>
