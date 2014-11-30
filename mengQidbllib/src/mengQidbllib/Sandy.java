/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mengQidbllib;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Meng
 */
public class Sandy {
     private Connection con;
    public Sandy(String dbName, String userName, String password){
        try{
        setConnection(dbName,userName, password);
        System.out.println("Connected to database successfully");
        }catch(Exception e){
        e.printStackTrace();
        return;   //why return?
        }
    }
    public void setConnection(String dbName, String userName, String password){
        String connectionUrl = "jdbc:sqlserver://localhost;"
                    + "databaseName=" + dbName + ";user=" + userName + ";password=" + password + ";";
        try {
            con = DriverManager.getConnection(connectionUrl);
        } catch (Exception ex) {
            Logger.getLogger(Sandy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<String> excuteSQL(String SQL){
        List<String> content = new LinkedList<>();
        String eachRow;
        try{
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(SQL);
            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next()){
                eachRow ="";
                for(int i = 1; i<=rsmd.getColumnCount();i++){
                   
                    eachRow+=String.format("%-20s",rs.getString(i) );
                }
               
                content.add(eachRow);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return content;
    }
    public List<String> readTable(String tableName){
       List<String> content = new LinkedList<>(); 
       String sql = "SELECT * FROM "+tableName;
       String header="";
       Statement s;
        try {
            s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();
            //first element is the header
            for(int i=1;i<=count;i++){
                header += String.format("%-20s", rsmd.getColumnName(i));
            }
            content.add(header);
            //get each row of the table
            while(rs.next()){
                String c ="";
                String[] content2 = new String[count];
                for(int i=1;i<=count;i++){
                    content2[i-1] = rs.getString(i);
                    content2[i-1]= String.format("%-20s", content2[i-1]);
                    c +=content2[i-1];
                }
                content.add(c);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Sandy.class.getName()).log(Level.SEVERE, null, ex);
            return content;
        }
       return content;
    }
    public List<String> listPurchases(String cid){
    List<String> purchases = new LinkedList<>();
    String header = "";
    String sql = "SELECT Orders.OrderDate,Product.Pid, Product.Price, Customer.Name, LineItem.Quantity from Customer, Product, Orders, LineItem " +
"where Customer.Cid =?  and Customer.Cid = Orders.Cid\n" +
"and Orders.Oid = LineItem.Oid\n" +
"and LineItem.Pid = Product.Pid;";
        try {
            PreparedStatement ps = con.prepareCall(sql);
            ps.setInt(1, Integer.parseInt(cid));
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();

            purchases.add(String.format("%-25s %-15s %-20s %-40s %-20s", rsmd.getColumnName(1),rsmd.getColumnName(2),rsmd.getColumnName(3),rsmd.getColumnName(4),rsmd.getColumnName(5)));
            //get each line
            while(rs.next()){
             String oDate = rs.getString(1);
             int pNumber = rs.getInt(2);
             float price = rs.getFloat(3);
             String cName = rs.getString(4);
             int quantity = rs.getInt(5);
                purchases.add(String.format("%-25s %-15s %-20s %-40s %-20s",oDate,pNumber,price,cName,quantity));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Sandy.class.getName()).log(Level.SEVERE, null, ex);
        }
    return purchases;
    }

    public void placeOrder(String customerId, String[] productIds, int[] quantities){
        String sql = "select * from Orders";
        String sql1 = "insert into Orders(Oid, OrderDate, Cid) values(?, ?, ?);";
        String sql2 = "insert into LineItem(Oid, Pid, Quantity) values(?, ?, ?);";
        int orderNum=1;
        PreparedStatement ps1=null;
        PreparedStatement ps2=null;
     
        try {
           // Get former information of table Orders
           Statement statement = con.createStatement();
           con.setAutoCommit(false);
           ResultSet rs1 = statement.executeQuery(sql);
           while(rs1.next()){
               orderNum++;
           };
     
           //Get current date
           //DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
           Calendar d = Calendar.getInstance();
           int year = d.get(Calendar.YEAR)-1900;
           int month = d.get(Calendar.MONTH);
           int day = d.get(Calendar.DAY_OF_MONTH);
           System.out.println("DAY: "+day);
           java.sql.Date orderDate = new java.sql.Date(year, month, day);
           ps1 = con.prepareCall(sql1);
           //create a new order
           ps1.setInt(1, orderNum);
           ps1.setDate(2, orderDate);
           ps1.setInt(3, Integer.parseInt(customerId));
           ps1.executeUpdate();
           System.out.println("An order is created \n"+ orderNum+"   "+orderDate+"   "+customerId);
        } catch (SQLException ex) {
            System.out.println("Exception in creating a new order");
            Logger.getLogger(Sandy.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            ps2 = con.prepareCall(sql2);
            //create each line in LineItem
            for(int i =0;i<productIds.length;i++){
                ps2.setInt(1, orderNum);
                ps2.setInt(2, Integer.parseInt(productIds[i]));
                ps2.setInt(3, quantities[i]);
                ps2.executeUpdate();
                System.out.println("A LineItem is created \n"+ orderNum+"   "+productIds[i]+"   "+quantities[i]);
            }
            con.commit();
            ps2.close();
        }catch(SQLException ex){
            System.out.println("Exception in creating LineItem" );
            try {
                //rollback if there is an error
                con.rollback();
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        }finally{
            try {
                con.setAutoCommit(true);
                if(ps2!= null  && ps1!=null){
                    ps2.close();
                    ps2.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Sandy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     private String fixStringFromJDBC(String s) {
        if (s == null) {
            s = "";
        }
        if (s.equals(" ")) {
            s = "";
        }
        s = s.replace('`', '\'');
        return s;
    }
}
