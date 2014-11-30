<%-- 
    Document   : placeorder
    Created on : Nov 28, 2014, 8:16:53 PM
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
    <%!List<String> list = null; Customer c = null; %>
    <%
    try {
	qimeng.QiMengWebService_Service service = new qimeng.QiMengWebService_Service();
	qimeng.QiMengWebService port = service.getQiMengWebServicePort();
	java.lang.String name = "Product";
	list = port.readTable(name);
        c= (Customer)request.getSession().getAttribute("customer");
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
            <h1>Customer: <%=c.getId()%> <%=c.getName()%></h1>
            <br>
    <form action="showcart.jsp" id="form1">
        <select required name="orderList" multiple="multiple" >
            <%
            for(int i = 0; i<list.size();i++){
                out.println("<option value=\""+list.get(i)+"\">"+list.get(i)+"</option>");
            }
            %>
        </select>
        <br>
        Quantity: <input type="text" name="quantity" style="display: block">
        <br>
    <input type="submit" value="Add to Cart" style="display: block" >
    </form>
    </body>
</html>
