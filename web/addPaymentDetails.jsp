<%@page import="uts.iot.model.PaymentMethod"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uts.iot.model.*"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/payment.css" rel="stylesheet" type="text/css">
        <title>Main Page of IoTBay</title>
        <% User_Account user = (User_Account) session.getAttribute("user");
            PaymentMethod p = (PaymentMethod) session.getAttribute("p");
            String cvcErr = (String) session.getAttribute("postcodeErr");
            String addSuccess = (String) session.getAttribute("addSuccess");
        %>
    </head>

    <body> <img src="css/logoiot.svg" style="margin-left: 45%;">
        <div class="Container">   
    <h1 style="color:tomato; text-align:left; margin-left:20px;display:inline-block;font-size: 25px; color: black;"> Add Payment Details: &nbsp; &nbsp;  <div style="color:tomato; float:right;" class="shakeanimation"> <%= (cvcErr != null ? cvcErr : "")%> </div></h1>
        <form action="AddPaymentMethodServlet" method="post">
            
            <table style="border-bottom:1px; border-left:0px; border-right:0px; border-top:0px; border-left:0px; padding:3.8px;">

                <tr><td>Card Name </td></tr>
                <tr><td><input type="text" class="outline" name="cardName" placeholder="Card Name" ></td></tr>
                <tr><td>Card Number</td></tr>
                <tr><td><input type="int" class="outline" name="cardNumber" placeholder="Card Number" ></td></tr>
                <tr><td>CVC</td></tr>
                <tr><td><input type="int" class="outline" name="cvc" placeholder="cvc" required></td></tr>
                <tr><td>Bank Name</td></tr>
                <tr><td><input type="text" class="outline" name="bankName" placeholder="bankName" ></td></tr>
                
                               
            </table>
            <br><br>
            <button class="button2" href="CancelServlet">Cancel</button>
            <button class="button2" href="paymentDetails.jsp">Back</button>
            
            <button class="button2" type="submit" style="margin-left:0px"><b> Create </b> </button>
           

        </form>
</div>



       



    </body>
</html>