<%@page import="uts.iot.model.Payment"%>
<%@page import="uts.iot.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/layout.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Page</title>
    </head>
    <body>
        <%
            String existErr = (String) session.getAttribute("existErr");
            String idErr = (String) session.getAttribute("idErr");
            String dateErr = (String) session.getAttribute("dateErr");
            String empErr = (String) session.getAttribute("empErr");
            Payment payment = (Payment) session.getAttribute("payment");
        %>
        <div class="container">
            <img src="css/logoiot.svg" style="margin-left:45%;">
        </div>
        <div class="Container">
        <h1 style="text-align: center;">Payment </h1>  <p><span><%=(empErr != null ? empErr : "")%></span></p>
        <div class="center">

        </div>
        <div class="center">
            <p><span><%=(existErr != null ? existErr : "")%></span><span><%=(empErr != null ? empErr : "")%></span></p>
            <form action="PaymentServlet" method="post" >
                <table class="center">
                    <tr >
                        <td>ID</td>
                    </tr>
                    <tr>
                        <td><input class="tb" type="text" placeholder="<%=(idErr != null ? idErr : "Enter id")%>" name="paymentID" required ></td>
                    </tr>
                    
                    <tr>
                        <td>Date</td>
                    </tr>
                    <tr>
                        <td><input class="tb" type="text" placeholder="<%=(dateErr != null ? dateErr : "Enter date")%>" name="paymentDate"required ></td>
                    </tr>
                    
                </table>
                <div class="center">
                    <button><a class="bttn" href="CancelServlet" style="color:white;">Cancel</a></button>
                    <a><input class="bttn" type="submit" value="Search"></a>
                </div>

            </form>
        </div>
                    </div>
        <% if (payment != null) {%>

       <table style="border: 0px solid #ddd; border-collapse: collapse; border-color: black; margin-left: 250px;" cellspacing="120"> 
                    <tr style="padding: 20px; border-bottom: 1px solid #ddd;">
                        <td style="border-bottom: 1px solid #ddd; border-color:gray; color:black; padding:10px;"> Payment ID </td>
                        <td style="border-bottom: 1px solid #ddd; border-color:gray; color:black; padding:10px;"> Payment Date </td> 
                        <td style="border-bottom: 1px solid #ddd; border-color:gray; color:black; padding:10px;"> Total Price </td> 
                        <td style="border-bottom: 1px solid #ddd; border-color:gray; color:black; padding:10px;"> Payment Type </td> 
                        <td style="border-bottom: 1px solid #ddd; border-color:gray; color:black; padding:10px;"> Payment Status </td> 
                        
                    </tr>
                    <tr style="border: 0px">
                        <td style="border-bottom: 0px solid #ddd; border-color:gray; color:black; padding:10px;">${payment.paymentID} </td>
                        <td style="border-bottom: 0px solid #ddd; border-color:gray; color:black; padding:10px;">${payment.paymentDate} </td>
                        <td style="border-bottom: 0px solid #ddd; border-color:gray; color:black; padding:10px;"> ${payment.totalPrice}</td>
                        <td style="border-bottom: 0px solid #ddd; border-color:gray; color:black; padding:10px;"> ${payment.paymentStatus} </td>
                        <td style="border-bottom: 0px solid #ddd; border-color:gray; color:black; padding:10px;"> ${payment.paymentType}} </td>                       
                        <td style="border-bottom: 0px solid #ddd; border-color:gray; color:black; padding:10px;"> COMPLETED </td>
                    </tr> 
                </table>
                    <%  } else {} %>

    </body>
</html>