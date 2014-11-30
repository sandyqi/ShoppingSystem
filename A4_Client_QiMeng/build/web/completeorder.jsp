<%-- 
    Document   : completeorder
    Created on : Nov 29, 2014, 12:00:58 AM
    Author     : Meng
--%>

<%@page import="java.util.List"%>
<%@page import="classes.Cart"%>
<%@page import="classes.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%!Customer c = null;Cart cart = null;List<String> info = null; %>
    <%
        c = (Customer)request.getSession().getAttribute("customer");
        cart = (Cart)request.getSession().getAttribute("cart");
        info = cart.getOrderItemInfo();
    try {
	qimeng.QiMengWebService_Service service = new qimeng.QiMengWebService_Service();
	qimeng.QiMengWebService port = service.getQiMengWebServicePort();
	java.lang.String customerId = String.valueOf(c.getId());
	java.util.List<java.lang.String> productIds = cart.getProductIds();
	java.util.List<java.lang.Integer> quantities = cart.getQuantities();
	port.placeOrder(customerId, productIds, quantities);
        request.getSession().removeAttribute("cart");
        request.getSession().removeAttribute("customer");
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <h1>Following Order completed successfully: </h1>
    <h2>Customer: <%=c.getId()%> <%=c.getName()%></h2>
    <% for(int i = 0; i<info.size();i++){
        out.println("<h3>" +info.get(i) + "</h3>");
    }
    %>
    <button><a href="mainpage.jsp">Go to Mainpage </a></button>
    </body>
</html>
