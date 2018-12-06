
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kenkh
 */
public class LinkedPockets {
    
    public int aid;
    public int pid;
    
    
    LinkedPockets() {}
    
    LinkedPockets( int aid, int pid) {
        this.aid = aid;
        this.pid = pid;
    }
    
    public static LinkedPockets createPockets(Connection conn, int aid, int pid) throws SQLException  {
        
        LinkedPockets linked = new LinkedPockets(aid, pid);
        
        Statement st = conn.createStatement();
        String qry = "INSERT INTO LinkedPockets(aid, pid)" +
                " VALUES (" +
                aid + ", " +
                pid + ")";
        st.executeQuery(qry);
        return linked; 
    }
        
    
}
