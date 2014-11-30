<%-- 
    Document   : showpurchases
    Created on : Nov 28, 2014, 6:21:23 PM
    Author     : Meng
--%>

<%@page import="classes.Customer"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%!int cId=0; String cName= null; List<String> list = null;HttpSession session = null;%> 
        <% cId = Integer.parseInt(request.getParameterValues("customerList")[0]); %>
    <%
    try {
	qimeng.QiMengWebService_Service service = new qimeng.QiMengWebService_Service();
	qimeng.QiMengWebService port = service.getQiMengWebServicePort();
	java.lang.String sql = "Select Customer.Name from Customer where Customer.cId = "+cId;
        cName = port.excuteSQL(sql).get(0);
        session = request.getSession();
        Customer c = new Customer(cId, cName);
        session.setAttribute("customer", c);
    } catch (Exception ex) {
	ex.printStackTrace();
    }
    
    try {
	qimeng.QiMengWebService_Service service = new qimeng.QiMengWebService_Service();
	qimeng.QiMengWebService port = service.getQiMengWebServicePort();
	list = port.listPurchases(String.valueOf(cId));
    } catch (Exception ex) {
	ex.printStackTrace();
    }
    %>
<h1>All purchases are made by <%=cId%> <%=cName%> - JSP </h1>
<%
for(int i =0 ; i < list.size(); i++){
    out.println("<h3>"+list.get(i) +"</h3>");
}
%>
<button><a href = "placeorder.jsp" style="text-decoration: none; display: block;">Place Order </button>
<br>
<button><a href = "mainpage.jsp" style="text-decoration: none;display: block;">Back to MainPage </button>
    </body>
</html>
