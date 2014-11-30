<%-- 
    Document   : showcart
    Created on : Nov 28, 2014, 7:38:46 PM
    Author     : Meng
--%>

<%@page import="classes.Cart"%>
<%@page import="classes.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%!Cart cart = null; Customer c = null; int quantity = 0;String id=null; %>
    <%
        c = (Customer)request.getSession().getAttribute("customer");
        quantity = Integer.parseInt(request.getParameter("quantity"));
        String s = request.getParameterValues("orderList")[0];
        id = s.split("\\s")[0];
        if(request.getSession().getAttribute("cart")==null){
            cart = new Cart();
        }else{
            cart = (Cart)request.getSession().getAttribute("cart");
        }
        cart.addToCart(id, quantity, s+" "+quantity);
        request.getSession().setAttribute("cart", cart);
    %>
    <h1>Customer:  <%=c.getId()%> <%=c.getName()%></h1>
    <br>
    <form>
    <select multiple="multiple">
    <%
    for(int i = 0; i<cart.getProductIds().size();i++){
        out.println("<option>" +cart.getOrderItemInfo().get(i)+ "</option>");
    }
    %>
    </select>
    </form>
    <br>
    <button> <a href="placeorder.jsp" style="text-decoration: none">Add More</a></button>
    <br>
    <br>
    <button> <a href="completeorder.jsp" style="text-decoration: none">Complete Order</a></button>
    </body>
</html>
