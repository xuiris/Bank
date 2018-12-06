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
public class Owners {
    
    public int aid;
    public String taxID;
    public String type;
    
    Owners() {}
    
    Owners( String taxID, int aid, String type){
        this.aid = aid;
        this.taxID = taxID;
        this.type = type;
    }
    
    public static Owners createOwners( Connection conn, String taxID, int aid, String type) throws SQLException{
        
        Owners o = new Owners(taxID, aid, type);
        
        Statement stmt = conn.createStatement();
        String qry = "INSERT INTO Owners(taxID, aid, type)" +
                " VALUES (" +
                "'" + taxID + "', " +
                 aid  + "," +
                "'" + type + "')";
        stmt.executeQuery(qry);
        return o;  
    }
    
    
    
}
