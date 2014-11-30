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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Meng
 */
@WebServlet(name = "ShowCartServlet", urlPatterns = {"/ShowCart"})
public class ShowCartServlet extends HttpServlet {

     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Cart cart;
        Customer c = (Customer)request.getSession().getAttribute("customer");
        String[] s = request.getParameterValues("productList");// get the selected item value in PlaceOrderServlet
        String pId = s[0].substring(0,4);// get the product Id
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        System.out.println("quantity: "+quantity);
        HttpSession session = request.getSession();
        if(session.getAttribute("cartInfo")==null){
            cart = new Cart();
        }
        else{
            cart = (Cart)session.getAttribute("cartInfo");
        }
        cart.addToCart(pId, quantity,s[0]+" "+quantity);
        session.setAttribute("cartInfo", cart);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowCartServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Customer:  " + c.getId()+" "+c.getName() + "</h1>");
            out.println("<br>");
            out.println("<form method=\"GET\" >");
            out.println("<select required name=\"orderItemList\" size=\"number_of_options\" multiple=\"multiple\">");
           // out.println("<h1>"+ s[0]+"  "+quantity + "</h1>");
            for(int i = 0; i<cart.getOrderItemInfo().size();i++){
                 out.println("<option value=\""+i+"\">"+cart.getOrderItemInfo().get(i)+"</option>");
                 System.out.println(cart.getOrderItemInfo().get(i));
            }
            out.println("</select>");
            out.println("</form>");
            out.println("<br>");
            out.println("<button type=\"button\"><a href=\"PlaceOrder\" style=\"text-decoration:none\">Back to Shopping</a></button>");
            out.println("<br>");
            out.println("<br>");
            out.println("<button type=\"button\"><a href=\"CompleteOrder\" style=\"text-decoration:none\">Complete Order</a></button>");
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

}
