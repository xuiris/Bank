import java.io.Serializable;
import java.sql.*;

      /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kenkh
 */
public class Customer implements Serializable {
    private String taxID;
    private String name;
    private int PIN;
    private String address;
   
    
    Customer() {}
    
    Customer(String taxID, String name, String address, int PIN) {
        this.taxID = taxID;
        this.name = name;
        this.PIN = PIN;
        this.address = address;
       
    }
    
    public static Customer createCustomer(Connection conn, String taxID, String name, String address) throws SQLException {
        // Creates customer and stores in database
        // default pin: 1717
        Customer c = new Customer(taxID, name, address, 1717);
        
        Statement stmt = conn.createStatement();
        String qry = "INSERT INTO Customers(taxID, PIN, address, name)" +
                " VALUES (" +
                "'" + c.taxID + "', " +
                "'1717', " +
                "'" + c.address + "'," +
                "'" + c.name + "')";
        stmt.executeQuery(qry);
        return c;  
    }
    
    public static Customer getCustomer(Connection conn, String taxID) throws SQLException {
        // Tries to find customer in database and return as Customer object
        String qry = "SELECT * from Customers c where c.taxID = '" + taxID + "'";
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(qry);
        Customer c = new Customer();
        if (rs.next()) {
	    c.taxID = rs.getString("taxID");
            c.name = rs.getString("name");
            c.address = rs.getString("address").trim();
            c.PIN = rs.getInt("PIN");
        } else {
            System.out.println("No customer found with this taxID");
            throw new SQLException("No such customer exists in DB");
        }
        
        return c;
    }
    
    public static String getName(Connection conn, String taxID) throws SQLException {
        String qry = "SELECT C.name from Customers c where c.taxID = '" + taxID + "'";
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(qry);
        if (rs.next()) {
            return rs.getString("name").trim();
        }
        else {
            System.out.println("No customer found with this taxID");
            throw new SQLException("No such customer exists in DB");
        }
    }
        
    @Override
    public String toString(){
        return  "taxID: " + gettaxID() + 
                ",     Address: " + getaddress() + 
                ",     Name: " + getname();
                
    }
      
    public String gettaxID(){
        return taxID;
    }

   
    public String getname() {
        return name;
    }
    
    public int getPIN() {
        return PIN;
    }
    
    public String getaddress(){
        return address;
    }
    
    
}
