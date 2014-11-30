/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package qimeng;

import classes.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Meng
 */
@WebServlet(name = "PlaceOrderServlet", urlPatterns = {"/PlaceOrder"})
public class PlaceOrderServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/mengqi_8080/A4_WebService_QiMeng/QiMengWebService.wsdl")
    private QiMengWebService_Service service;

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Customer c = (Customer)session.getAttribute("customer");
        String name = c.getName();
        List<String> productList = readTable("Product");
        int id = c.getId();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PlaceOrder</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Customer: " + id+" "+name + "</h1>");
            out.println("<br>");
            out.println("<h3>Select a product, enter quantity, and click on Add to Cast  </h3>");
            out.println("<br>");
            out.println("<h3>Purchases: </h3>");
            out.println("<form method=\"GET\" action=\"ShowCart\">");
            out.println("<select required name=\"productList\" size=\"number_of_options\" multiple=\"multiple\">");
            for(int i = 0; i<productList.size();i++){
                 out.println("<option value=\""+productList.get(i)+"\">"+productList.get(i)+"</option>");
            }
            out.println("</select>");
            out.println("<br>");
            out.println("<br>");
            out.println("Quantity:");
            out.println("<input type = \"text\" name = \"quantity\" >  </input>");
            out.println("<br>");
            out.println("<br>");
            out.println("<input type=\"submit\" value=\"Add to Cart\" style=\"display:block\">");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private java.util.List<java.lang.String> readTable(java.lang.String name) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        qimeng.QiMengWebService port = service.getQiMengWebServicePort();
        return port.readTable(name);
    }

}
