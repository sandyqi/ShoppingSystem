/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package qimeng;

import java.util.List;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import mengQidbllib.Sandy;

/**
 *
 * @author Meng
 */
@WebService(serviceName = "QiMengWebService")
public class QiMengWebService {

  Sandy sandy= new Sandy("OrdersDB", "ism6236", "ism6236bo");


    
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "readTable")
    public List<String> readTable(@WebParam(name = "name") String name) {
       return sandy.readTable(name);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "excuteSQL")
    public List<String> excuteSQL(@WebParam(name = "sql") String sql) {
       return sandy.excuteSQL(sql);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "listPurchases")
    public List<String> listPurchases(@WebParam(name = "id") String id) {
        return sandy.listPurchases(id);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "placeOrder")
    @Oneway
    public void placeOrder(@WebParam(name = "customerId") String customerId, @WebParam(name = "productIds") String[] productIds, @WebParam(name = "quantities") int[] quantities) {
        sandy.placeOrder(customerId, productIds, quantities);
    }
    
    
    
}
