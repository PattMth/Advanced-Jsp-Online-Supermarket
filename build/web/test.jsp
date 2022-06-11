<%@page import="java.util.ArrayList"%>
<%@page import="uts.iot.model.Payment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
   <body>
        <h1>List of Students </h1>     
        
    <% 
         
        ArrayList <Payment> listPayment = new ArrayList();        
        listPayment = (ArrayList) request.getAttribute("listPayment");
        
       // Get the values stored in a session 
      //  listStudent = (ArrayList) session.getAttribute("listStudent");    
 
    %>
    
    <table>
        <thead>
            <tr>
                <th>Student Id</th>
                <th>Student Name</th>
            </tr>
        </thead>
        <tbody>     
                     

 

        <%    
           if(!listPayment.isEmpty()) { %>
               
              <% for (Payment p : listPayment) { %>
         
                <tr>
                   <td> <%= p.getPaymentID()%>
                   </td>
                   <td><%= p.getPaymentDate()%>
                   </td>                
                </tr>
                
               <%}%>
               
           <%} %>
        </tbody>
        
    </body>
</html>