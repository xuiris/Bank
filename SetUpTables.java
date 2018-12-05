//STEP 1. Import required packages
import java.sql.*;

public class SetUpTables {
	
	private Connection conn;

	SetUpTables(Connection c)
	{
            conn = c;
	}
	
	public void create() 
	{
		try {
			System.out.println("Initializing Banking System tables");
			
			//Accounts Table 
			Statement st = conn.createStatement();
			String createTable =  "CREATE TABLE Accounts(aid INTEGER," +
									" balance FLOAT," +
									" interest FLOAT," +
									" open CHAR(1)," + // 0 if closed
									" type VARCHAR(20)," +
									" PRIMARY KEY (aid)," +
									" CHECK (type IN ('Savings','Student-Checking','Interest-Checking','Pocket')))";
			st.executeQuery(createTable);
			System.out.println("Accounts table created");
			
			//Pocket Accounts Table
			createTable =  "CREATE TABLE LinkedPockets(pid INTEGER," 
					+ " aid INTEGER,"
					+ " PRIMARY KEY (pid)," 
					+ " FOREIGN KEY (aid) REFERENCES Accounts ON DELETE CASCADE,"
					+ " FOREIGN KEY (pid) REFERENCES Accounts ON DELETE CASCADE)";
			st.executeQuery(createTable);
			System.out.println("Linked Pocket Account table created");
			
			//Customers Table
			createTable = "CREATE TABLE Customers ( taxID CHAR(9)," + 
                                                                "PIN INTEGER," + 
                                                                "address CHAR(40)," +
                                                                "name CHAR(20)," +
                                                                "PRIMARY KEY(taxID))";
			st.executeQuery(createTable);
			System.out.println("Customers table created");
			
			//Owners Table
			createTable = "CREATE TABLE Owners(taxID CHAR(9)," +
							" aid INTEGER," + 
							" type VARCHAR(8)," +
							" PRIMARY KEY( taxID, aid)," + 
							" FOREIGN KEY (taxID) REFERENCES Customers ON DELETE CASCADE," +
							" FOREIGN KEY(aid) REFERENCES Accounts," +
							" CHECK (type in ('Primary','Co-Owner')))";
			st.executeQuery(createTable);
			System.out.println("Owners table created");
			
			//Transactions Table
			createTable = "CREATE TABLE Transactions(tid INTEGER," +
                                                        " taxID CHAR(9)," +
							" day DATE," + 
							" type VARCHAR(15)," + 
							" PRIMARY KEY(tid)," +
                                                        " FOREIGN KEY (taxID) REFERENCES Customers," +
							" CHECK (type in ('Deposit','TopUp', 'Withdraw', 'Purchase', 'Transfer', 'Collect', 'PayFriend', 'Wire', 'WriteCheck', 'AccrueInterest')))";;
			st.executeQuery(createTable);
			System.out.println("Transactions table created");
			
			//Deposit Table
			createTable = "CREATE TABLE Deposit(tid INTEGER," +
					" amt FLOAT," + 
					" aid INTEGER," +
					" PRIMARY KEY(tid)," + 
					" FOREIGN KEY (tid) REFERENCES Transactions ON DELETE CASCADE," +
					" FOREIGN KEY(aid) REFERENCES Accounts)";
			st.executeQuery(createTable);
			System.out.println("Deposit table created");
			
			//TopUp Table
			createTable = "CREATE TABLE TopUp(tid INTEGER," +
					" amt FLOAT," + 
					" pid INTEGER," +
					" PRIMARY KEY(tid)," + 
					" FOREIGN KEY (tid) REFERENCES Transactions ON DELETE CASCADE," +
					" FOREIGN KEY(pid) REFERENCES Accounts)";
			st.executeQuery(createTable);
			System.out.println("TopUp table created");
			
			//Withdraw Table
			createTable = "CREATE TABLE Withdraw(tid INTEGER," +
					" amt FLOAT," + 
					" aid INTEGER," +
					" PRIMARY KEY(tid)," + 
					" FOREIGN KEY (tid) REFERENCES Transactions ON DELETE CASCADE," +
					" FOREIGN KEY(aid) REFERENCES Accounts)";
			st.executeQuery(createTable);
			System.out.println("Withdraw table created");
			
			//Purchase Table
			createTable = "CREATE TABLE Purchase(tid INTEGER," +
					" amt FLOAT," + 
					" pid INTEGER," +
					" PRIMARY KEY(tid)," + 
					" FOREIGN KEY (tid) REFERENCES Transactions ON DELETE CASCADE," +
					" FOREIGN KEY(pid) REFERENCES Accounts)";
			st.executeQuery(createTable);
			System.out.println("Deposit table created");
                        
                        //Transfer Table
			createTable = "CREATE TABLE Transfer(tid INTEGER," +
					" amt FLOAT," + 
					" fromAid INTEGER," +
                                        " toAid INTEGER," +
					" PRIMARY KEY(tid)," + 
					" FOREIGN KEY (tid) REFERENCES Transactions ON DELETE CASCADE," +
					" FOREIGN KEY(fromAid) REFERENCES Accounts," +
                                        " FOREIGN KEY(toAid) REFERENCES Accounts)";
			st.executeQuery(createTable);
			System.out.println("Transfer table created");
                        
                        //Collect Table
			createTable = "CREATE TABLE Collect(tid INTEGER," +
					" amt FLOAT," + 
                                        " fromPid INTEGER," +
					" PRIMARY KEY(tid)," + 
					" FOREIGN KEY (tid) REFERENCES Transactions ON DELETE CASCADE," +
                                        " FOREIGN KEY(fromPid) REFERENCES Accounts)";
			st.executeQuery(createTable);
			System.out.println("Collect table created");
                        
                        //PayFriend Table
			createTable = "CREATE TABLE PayFriend(tid INTEGER," +
					" amt FLOAT," + 
					" fromPid INTEGER," +
                                        " toPid INTEGER," +
					" PRIMARY KEY(tid)," + 
					" FOREIGN KEY (tid) REFERENCES Transactions ON DELETE CASCADE," +
					" FOREIGN KEY(fromPid) REFERENCES Accounts," +
                                        " FOREIGN KEY(toPid) REFERENCES Accounts)";
			st.executeQuery(createTable);
			System.out.println("PayFriend table created");
			
                        //Wire Table
			createTable = "CREATE TABLE Wire(tid INTEGER," +
					" amt FLOAT," + 
					" fromAid INTEGER," +
                                        " toAid INTEGER," +
					" PRIMARY KEY(tid)," + 
					" FOREIGN KEY (tid) REFERENCES Transactions ON DELETE CASCADE," +
					" FOREIGN KEY(fromAid) REFERENCES Accounts," +
                                        " FOREIGN KEY(toAid) REFERENCES Accounts)";
			st.executeQuery(createTable);
			System.out.println("Wire table created");
                        
//			String createTrigger = "CREATE TRIGGER checkBalance" +
//					" AFTER UPDATE OF balance ON Accounts" + 
//					" FOR EACH ROW" +
//					" UPDATE Accounts" +
//					" SET open = 0" +
//					" WHERE balance = 0";
//			st.executeQuery(createTrigger);
//			System.out.println("Trigger to close zero balance accounts created");
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("error");
                        try{
                            if(conn!=null){
                                conn.close();
                                System.out.println("Closed connection to database...");
                             }
                         }catch(SQLException se){
                            se.printStackTrace();
                         }
			System.exit(0);
		}
	}
	
	public void destroy() 
	{
		try {
			Statement st = conn.createStatement();
			String deleteTable = "";
			
			deleteTable = "DROP TABLE Deposit";
			st.executeQuery(deleteTable);
			
			deleteTable = "DROP TABLE TopUp";
			st.executeQuery(deleteTable);
			
			deleteTable = "DROP TABLE Withdraw";
			st.executeQuery(deleteTable);
			
			deleteTable = "DROP TABLE Purchase";
			st.executeQuery(deleteTable);
                        
                        deleteTable = "DROP TABLE Transfer";
			st.executeQuery(deleteTable);
                        
                        deleteTable = "DROP TABLE PayFriend";
			st.executeQuery(deleteTable);
                        
                        deleteTable = "DROP TABLE Collect";
			st.executeQuery(deleteTable);
                        
                        deleteTable = "DROP TABLE Wire";
			st.executeQuery(deleteTable);
			
			deleteTable = "DROP TABLE Transactions";
			st.executeQuery(deleteTable);
			
			deleteTable = "DROP TABLE Owners";
			st.executeQuery(deleteTable);
			
			deleteTable = "DROP TABLE Customers";
			st.executeQuery(deleteTable);
			
			deleteTable = "DROP TABLE LinkedPockets";
			st.executeQuery(deleteTable);
			
			deleteTable = "DROP TABLE Accounts";
			st.executeQuery(deleteTable);
			
			System.out.println("Tables are deleted");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("error");
                        try{
                            if(conn!=null)
                               conn.close();
                         }catch(SQLException se){
                            se.printStackTrace();
                         }
			System.exit(0);		}
	}
	
	public void initData()
	{
            try {
                System.out.println("Adding data tables...");
                Statement stmt = conn.createStatement();
                String data = "";
                data = "INSERT INTO Customers(taxID, PIN, address, name) VALUES ('361721022', 1234, '6667 El Colegio #40', 'Alfred Hitchcock')";
                stmt.executeQuery(data);
                data = "INSERT INTO Customers(taxID, PIN, address, name) VALUES ('231403227', 1468, '5777 Hollister', 'Billy Clinton')";
                stmt.executeQuery(data);
                data = "INSERT INTO Customers(taxID, PIN, address, name) VALUES ('207843218', 8582, '1357 State St', 'David Copperfill')";
                stmt.executeQuery(data);
                data = "INSERT INTO Customers(taxID, PIN, address, name) VALUES ('412231856', 3764, '7000 Hollister', 'Cindy Laugher')";
                stmt.executeQuery(data);

                data = "INSERT INTO Accounts(aid, balance, interest, open, type) VALUES (43942, 1000.0, 7.5, '1', 'Savings')";
                stmt.executeQuery(data);
                data = "INSERT INTO Accounts(aid, balance, interest, open, type) VALUES (60413, 500.0, 0.0, '1', 'Pocket')";
                stmt.executeQuery(data);
                data = "INSERT INTO Accounts(aid, balance, interest, open, type) VALUES (12121, 800.0, 0.0, '1', 'Student-Checking')";
                stmt.executeQuery(data);
                data = "INSERT INTO Accounts(aid, balance, interest, open, type) VALUES (53027, 200.0, 0.0, '1', 'Pocket')";
                stmt.executeQuery(data);
                data = "INSERT INTO Accounts(aid, balance, interest, open, type) VALUES (17431, 2000.0, 1.0, '1', 'Student-Checking')";
                stmt.executeQuery(data);

                data = "INSERT INTO LinkedPockets(pid, aid) VALUES (60413, 43942)";
                stmt.executeQuery(data);
                data = "INSERT INTO LinkedPockets(pid, aid) VALUES (53027, 12121)";
                stmt.executeQuery(data);

                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('361721022', 43942, 'Primary')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('361721022', 60413, 'Co-Owner')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('231403227', 60413, 'Co-Owner')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('412231856', 17431, 'Primary')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('207843218', 12121, 'Primary')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('207843218', 53027, 'Primary')";
                stmt.executeQuery(data);

                System.out.println("Done with setup...");
            }catch(Exception e){
                    e.printStackTrace();
                    System.out.println("error");
                    try{
                        if(conn!=null)
                           conn.close();
                     }catch(SQLException se){
                        se.printStackTrace();
                     }
                    System.exit(0);
            }
        }
	
}
