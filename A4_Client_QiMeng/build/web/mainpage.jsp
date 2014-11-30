<%-- 
    Document   : mainpage
    Created on : Nov 28, 2014, 6:02:54 PM
    Author     : Meng
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
      <%!
    public List<String> readTable(String name){
    try {
	qimeng.QiMengWebService_Service service = new qimeng.QiMengWebService_Service();
	qimeng.QiMengWebService port = service.getQiMengWebServicePort();
	java.util.List<java.lang.String> result = port.readTable(name);
        return result;
    } catch (Exception ex) {
	ex.printStackTrace();
    }
    return null;
    }
    %>
    
    <%! List<String> table = null;
    %>

    <body>
        <h1>Select a customer from the list below and click on Show Purchases - JSP</h1>
        <form action="showpurchases.jsp" method="get">
            <select required name="customerList" multiple="multiple">
    <% table = readTable("Customer");
        for(int i = 0; i<table.size(); i++){
            out.println("<option value=\""+i+"\">"+table.get(i) + "</option>");
        }
    %>
    <input type="submit" value="Submit" style="display: block">
    </select>
    </form>
    </body>
</html>
