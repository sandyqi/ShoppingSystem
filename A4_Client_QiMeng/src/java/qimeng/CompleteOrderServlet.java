/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package qimeng;

import classes.Cart;
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
@WebServlet(name = "CompleteOrderServlet", urlPatterns = {"/CompleteOrder"})
public class CompleteOrderServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/mengqi_8080/A4_WebService_QiMeng/QiMengWebService.wsdl")
    private QiMengWebService_Service service;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Customer c = (Customer)session.getAttribute("customer");
        Cart cart = (Cart)session.getAttribute("cartInfo");
        String cId = String.valueOf(c.getId());
        List<String> productIds1 = cart.getProductIds();
        String[] productIds = productIds1.toArray(new String[productIds1.size()]);
        List<Integer> quantities1 = cart.getQuantities();
        int[] quantities=new int[quantities1.size()];
        for(int i=0;i<quantities.length;i++){
            quantities[i] = quantities1.get(i);
        }
        placeOrder(cId, productIds1, quantities1);  
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CompleteOrderServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Following Order completed successfully: </h1>");
            out.println("<br>");
            out.println("<h3>Customer: "+c.getId()+" "+c.getName()+"</h3>");
            out.println("<br>");      
            out.println("<h3>Products:</h3>");
            List<String> orderItemInfo = cart.getOrderItemInfo();
            for(int i = 0;i<orderItemInfo.size();i++ ){
            out.println("<h3>"+orderItemInfo.get(i)+"</h3>");
            }
            out.println("<br>"); 
            out.println("<br>"); 
            out.println("<button type=\"button\"><a href=\"MainPage\" style=\"text-decoration:none\">Back to Mainpage</a></button>");
            out.println("</body>");
            out.println("</html>");
            session.removeAttribute("customer");
            session.removeAttribute("cartInfo");
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

    private void placeOrder(java.lang.String customerId, java.util.List<java.lang.String> productIds, java.util.List<java.lang.Integer> quantities) {
        //why this is changed?
        qimeng.QiMengWebService port = service.getQiMengWebServicePort();
        port.placeOrder(customerId, productIds, quantities);
    }

  

}
