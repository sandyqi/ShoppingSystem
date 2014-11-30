/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Meng
 */
public class Cart {
    private List<String> productIds= null;
    private List<Integer> quantities = null;
    private List<String> orderItemInfo = null;
    public Cart(){
        productIds = new LinkedList<String>();
        quantities = new LinkedList<Integer>();
        orderItemInfo = new LinkedList<String>();
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }
    
    public List<String> getOrderItemInfo(){
        return orderItemInfo;
    }
    private String resetQuantity(String s, int q){
        for(int i = s.length()-1;i>0;i--){
            if(s.charAt(i) == ' '){
                return s.substring(0,i)+q;
            }
        }
        return null;
    }
    public boolean addToCart(String pId, int quantity, String orderItem){
        for(int i =0;i<productIds.size();i++){
            if (pId.equals(productIds.get(i))){
                quantities.set(i,quantities.get(i)+quantity);
                orderItemInfo.set(i, resetQuantity(orderItemInfo.get(i),quantities.get(i)));
                return true;
            }
        }
         productIds.add(pId);
        quantities.add(quantity);
        orderItemInfo.add(orderItem);
        return true;
                
       
    }
}
