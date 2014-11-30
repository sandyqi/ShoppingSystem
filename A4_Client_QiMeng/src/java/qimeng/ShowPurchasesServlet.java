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
@WebServlet(name = "ShowPurchasesServlet", urlPatterns = {"/ShowPurchases"})
public class ShowPurchasesServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/mengqi_8080/A4_WebService_QiMeng/QiMengWebService.wsdl")
    private QiMengWebService_Service service;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
              response.setContentType("text/html;charset=UTF-8");
        String[] s = request.getParameterValues("customerList");
        List<String> list = readTable("Customer");
        
        int cId = Integer.parseInt(s[0]);// get customer id
        String cName = excuteSQL("Select Customer.Name from Customer where Customer.CId = "+cId).get(0);
    //    String[] two = devideTwo(list.get(cId));// devide a string into two parts including id, name.
        
        Customer currentC = new Customer(cId, cName); // create a Customer object
        
        HttpSession session = request.getSession();
        
        session.setAttribute("customer", currentC);
        
        List<String> purchasesList = listPurchases(String.valueOf(cId));
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowPurchasesServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>All purchases made by  "+cId+" "+cName+"</h1>"); 
            out.println("<br>");
            for(int i = 0; i<purchasesList.size();i++){
                 out.println("<h3 value=\""+i+"\">"+purchasesList.get(i)+"</h3>");
            }
            out.println("<br>");
            out.println("<button type=\"button\" style=\"display:block\"><a href=\"PlaceOrder\" style=\"text-decoration:none\">Start Order</a></button>");
            out.println("<br>");
            out.println("<button type=\"button\" style=\"display:block\"><a href=\"MainPage\" style=\"text-decoration:none\">Back to MainPage</a></button>");
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

    private java.util.List<java.lang.String> listPurchases(java.lang.String id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        qimeng.QiMengWebService port = service.getQiMengWebServicePort();
        return port.listPurchases(id);
    }

    private java.util.List<java.lang.String> excuteSQL(java.lang.String sql) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        qimeng.QiMengWebService port = service.getQiMengWebServicePort();
        return port.excuteSQL(sql);
    }

    private java.util.List<java.lang.String> readTable(java.lang.String name) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        qimeng.QiMengWebService port = service.getQiMengWebServicePort();
        return port.readTable(name);
    }
    
    

}
