/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

/**
 *
 * @author Meng
 */
public class Customer {
    private String name=null;
    private int id=0;
    
    public Customer(int id,String name){
        this.name = name;
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    
}
