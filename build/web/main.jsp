<%@page import="uts.iot.model.*"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="css/dash.css" rel="stylesheet" type="text/css">
        <title>Main Page of IoTBay</title>
        <% User_Account user = (User_Account)session.getAttribute("user"); 
           Customer customer = (Customer) session.getAttribute("customer");
        %>
    </head>
   
    <body>
        
        
      
        
       
        <%  if (user!=null) { %>
         <img src ="css/logoiot.svg"  alt="IoTBay" width="100px" height="95px" class="centerz">
          <p style="text-align:right"> ${user.email}  </p>  
         <table cellspacing="0" cellpadding="0" width="100%">
                            <tr>
                                 <th><a href="devices">Home &nbsp;</a></th>
                                <th>Shop by Category</th>
                                <th><a href="main.jsp">Deals of the Day</a></th>
                                <th> <a href="main.jsp">Recently Viewed</a></th>
                            <th class="dropdown"> &nbsp; &nbsp; &nbsp;Hi, ${customer.name}! 
                                <div class="dropdown-content">
                                  <a href="CustomerEditServlet?email='<%=user.getEmail()%>'&password='<%=user.getPassword()%>'" style="color:black;">View Profile</a>
                                    <a href="#" style="color:black"> My Shopping </a>
                                    <a href="#" style="color:black">  Find Shipment </a>
                                    <a href="#" style="color:black;"> View Shipment Details </a>
                                    <a href="paymentDetails.jsp" style="color:black"> View Payment Details</a>
                                    <a href="payment.jsp" style="color:black">Find Payment</a>
                                    <a href="#" style="color:black"> View Order History </a>
                                   
                                    <a href="CustomerAccessLogServlet?email='<%=user.getEmail()%>'&password='<%=user.getPassword()%>'" style="color:black;">View Logs</a>
                                    <a href="#" style="color:black"> View Shipment </a> 
                                      <a href="LogoutServlet" style="color:black;">Logout</a>

                                </div>
                                </div>
                            </th>
                            </tr>
         </table>
                       
                            <table class='viewprofile'>
                                <tr><h1>Account Details </h1></tr>
                                <tr class="viewprofiles"> <td class="title">Full Name </td> </tr>
                                <tr class="viewprofiles"> <td> ${customer.name} </td> </tr>
                                <tr><td> &nbsp;</td></tr>
                                <tr class="viewprofiles"> <td class="title">Email address</td> </tr>
                                <tr class="viewprofiles"><td>${user.email}</td></tr>
                                 <tr><td> &nbsp;</td></tr>
                                <tr class="viewprofiles"> <td class="title">Password</td> </tr>
                                <tr class="viewprofiles"><td>${user.password}</td></tr>
                                 <tr><td> &nbsp;</td></tr>
                                <tr class="viewprofiles"> <td class="title">Date of Birth:</td> </tr>
                                <tr class="viewprofiles"><td>${user.dob}</td></tr>
                                
                              
                            </table>
                   
     
         <% } else { %>
         <img src ="css/logoiot.svg"  alt="IoTBay" width="100px" height="95px" class="centerz">
            
                 <br> 
                    <table cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                            <th><a href="main.jsp"><div class="center"></div>Home &nbsp;</a></th>
                            <th><a href="#"><div class="center"></div>Shop by Category</a></th>
                            <th> <a href="#"><div class="center"> </div>Deals of the Day</th>
                            <th> <a href="#"><div class="center"> </div>Recently Viwed</th>
                            <th class="dropdown">&nbsp; &nbsp; &nbsp;Login or Register 
                                <div class="dropdown-content"><a href="login.jsp" style="color:black;"> Log In </a>
                                <a href="index.jsp" style="color:black;">Register</a> </div>
                                                                                                                          </div>
                            </th>
                        </tr>
                    </table>
       <% } %>

  
      
    </body>
</html>