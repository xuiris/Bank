//STEP 1. Import required packages
import java.sql.*;
import java.io.*;

public class Bank {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";  
   static final String DB_URL = "jdbc:oracle:thin:@cloud-34-133.eci.ucsb.edu:1521:XE";

   //  Database credentials
   static final String USERNAME = "e_khurelbaatar";
   static final String PASSWORD = "enjilove"; 
   Connection conn = null;
   
  public Bank(){}//end main
  
    public Connection getConnection(){
       if (conn == null) {
           // No connection to database yet. Create one.
           try{
                //Register JDBC driver
                Class.forName(JDBC_DRIVER);

                //Open a connection
                System.out.println("Connecting to a selected database...");
                conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                System.out.println("Connected database successfully...");

                }catch(SQLException se){
                    //Handle errors for JDBC
                    se.printStackTrace();
                }catch(Exception e){
                    //Handle errors for Class.forName
                    e.printStackTrace();
                }
       }
       return conn;
    }
}//end JDBCExample