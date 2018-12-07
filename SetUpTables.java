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
							" FOREIGN KEY(aid) REFERENCES Accounts ON DELETE CASCADE," +
							" CHECK (type in ('Primary','Co-Owner')))";
			st.executeQuery(createTable);
			System.out.println("Owners table created");
			
			//Transactions Table
			createTable = "CREATE TABLE Transactions(tid INTEGER," +
                                                        " taxID CHAR(9)," +
							" day DATE," + 
							" type VARCHAR(15)," + 
							" PRIMARY KEY(tid)," +
                                                        " FOREIGN KEY (taxID) REFERENCES Customers ON DELETE CASCADE," +
							" CHECK (type in ('Deposit','TopUp', 'Withdraw', 'Purchase', 'Transfer', 'Collect', 'PayFriend', 'Wire', 'WriteCheck', 'AccrueInterest')))";;
			st.executeQuery(createTable);
			System.out.println("Transactions table created");
			
			//Deposit Table
			createTable = "CREATE TABLE Deposit(tid INTEGER," +
					" amt FLOAT," + 
					" aid INTEGER," +
					" PRIMARY KEY(tid)," + 
					" FOREIGN KEY (tid) REFERENCES Transactions ON DELETE CASCADE," +
					" FOREIGN KEY(aid) REFERENCES Accounts ON DELETE CASCADE)";
			st.executeQuery(createTable);
			System.out.println("Deposit table created");
			
			//TopUp Table
			createTable = "CREATE TABLE TopUp(tid INTEGER," +
					" amt FLOAT," + 
					" pid INTEGER," +
					" PRIMARY KEY(tid)," + 
					" FOREIGN KEY (tid) REFERENCES Transactions ON DELETE CASCADE," +
					" FOREIGN KEY(pid) REFERENCES Accounts ON DELETE CASCADE)";
			st.executeQuery(createTable);
			System.out.println("TopUp table created");
			
			//Withdraw Table
			createTable = "CREATE TABLE Withdraw(tid INTEGER," +
					" amt FLOAT," + 
					" aid INTEGER," +
					" PRIMARY KEY(tid)," + 
					" FOREIGN KEY (tid) REFERENCES Transactions ON DELETE CASCADE," +
					" FOREIGN KEY(aid) REFERENCES Accounts ON DELETE CASCADE )";
			st.executeQuery(createTable);
			System.out.println("Withdraw table created");
			
			//Purchase Table
			createTable = "CREATE TABLE Purchase(tid INTEGER," +
					" amt FLOAT," + 
					" pid INTEGER," +
					" PRIMARY KEY(tid)," + 
					" FOREIGN KEY (tid) REFERENCES Transactions ON DELETE CASCADE," +
					" FOREIGN KEY(pid) REFERENCES Accounts ON DELETE CASCADE)";
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
                                        " FOREIGN KEY(toAid) REFERENCES Accounts ON DELETE CASCADE)";
			st.executeQuery(createTable);
			System.out.println("Transfer table created");
                        
                        //Collect Table
			createTable = "CREATE TABLE Collect(tid INTEGER," +
					" amt FLOAT," + 
                                        " fromPid INTEGER," +
					" PRIMARY KEY(tid)," + 
					" FOREIGN KEY (tid) REFERENCES Transactions ON DELETE CASCADE," +
                                        " FOREIGN KEY(fromPid) REFERENCES Accounts ON DELETE CASCADE)";
			st.executeQuery(createTable);
			System.out.println("Collect table created");
                        
                        //PayFriend Table
			createTable = "CREATE TABLE PayFriend(tid INTEGER," +
					" amt FLOAT," + 
					" fromPid INTEGER," +
                                        " toPid INTEGER," +
					" PRIMARY KEY(tid)," + 
					" FOREIGN KEY (tid) REFERENCES Transactions ON DELETE CASCADE," +
					" FOREIGN KEY(fromPid) REFERENCES Accounts ON DELETE CASCADE," +
                                        " FOREIGN KEY(toPid) REFERENCES Accounts ON DELETE CASCADE)";
			st.executeQuery(createTable);
			System.out.println("PayFriend table created");
			
                        //Wire Table
			createTable = "CREATE TABLE Wire(tid INTEGER," +
					" amt FLOAT," + 
					" fromAid INTEGER," +
                                        " toAid INTEGER," +
					" PRIMARY KEY(tid)," + 
					" FOREIGN KEY (tid) REFERENCES Transactions ON DELETE CASCADE," +
					" FOREIGN KEY(fromAid) REFERENCES Accounts ON DELETE CASCADE," +
                                        " FOREIGN KEY(toAid) REFERENCES Accounts ON DELETE CASCADE)";
			st.executeQuery(createTable);
			System.out.println("Wire table created");
                        

                        //WriteCheck table
                        createTable = "CREATE TABLE WriteCheck(tid INTEGER," +
                                        "checkNum INTEGER," +
                                        "cid INTEGER," +
                                        "amt FLOAT" +
                                        "PRIMARY KEY (tid)," +
                                        "FOREIGN KEY(cid) REFERENCES Accounts ON DELETE CASCADE," +
                                        "FOREIGN KEY (tid) REFERENCES Transactions ON DELETE CASCADE)";
                        st.executeQuery(createTable);
                        System.out.println("WriteCheck table created");
                        
                        //AccrueInterest Table
			createTable = "CREATE TABLE AccrueInterest(tid INTEGER," +
                                        " day DATE," +
					" FOREIGN KEY(tid) REFERENCES Transactions ON DELETE CASCADE)";                      
			st.executeQuery(createTable);
			System.out.println("AccrueInterest table created");

                        //InitialBalances Table
			createTable = "CREATE TABLE InitialBalances(aid INTEGER," +
					" balance FLOAT," + 
                                        " PRIMARY KEY (aid)," +
					" FOREIGN KEY(aid) REFERENCES Accounts ON DELETE CASCADE)";                      
			st.executeQuery(createTable);
			System.out.println("InitialBalances table created");
                        
                        //Balances Table
			createTable = "CREATE TABLE Balances(aid INTEGER," +
					" balance FLOAT," + 
                                        " day DATE," +
					" FOREIGN KEY(aid) REFERENCES Accounts ON DELETE CASCADE)";                      
			st.executeQuery(createTable);
			System.out.println("Balances table created");
                       
                        
		
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
                        
           
                        deleteTable = "DROP TABLE AccrueInterest";
                        st.executeQuery(deleteTable);
      
                        deleteTable = "DROP TABLE WriteCheck";
			st.executeQuery(deleteTable);

			
			deleteTable = "DROP TABLE Transactions";
			st.executeQuery(deleteTable);
			
			deleteTable = "DROP TABLE Owners";
			st.executeQuery(deleteTable);
			
			deleteTable = "DROP TABLE Customers";
			st.executeQuery(deleteTable);
			
			deleteTable = "DROP TABLE LinkedPockets";
			st.executeQuery(deleteTable);
			
                        deleteTable = "DROP TABLE InitialBalances";
			st.executeQuery(deleteTable);
                        
                        deleteTable = "DROP TABLE Balances";
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
                data = "INSERT INTO Customers(taxID, PIN, address, name) VALUES ('122219876', 3856, '4321 State St', 'Elizabeth Sailor')";
                stmt.executeQuery(data);
                data = "INSERT INTO Customers(taxID, PIN, address, name) VALUES ('401605312', 8193, '3756 La Cumbre Plaza', 'Fatal Castro')";
                stmt.executeQuery(data);
                data = "INSERT INTO Customers(taxID, PIN, address, name) VALUES ('201674933', 9824, '5346 Foothill Av', 'George Brush')";
                stmt.executeQuery(data);
                data = "INSERT INTO Customers(taxID, PIN, address, name) VALUES ('212431965', 3532, '678 State St', 'Hurryson Ford')";
                stmt.executeQuery(data);
                data = "INSERT INTO Customers(taxID, PIN, address, name) VALUES ('322175130', 8471, '1235 Johnson Dr', 'Ivan Lendme')";
                stmt.executeQuery(data);
                data = "INSERT INTO Customers(taxID, PIN, address, name) VALUES ('344151573', 3692, '3210 State St', 'Joe Pepsi')";
                stmt.executeQuery(data);
                data = "INSERT INTO Customers(taxID, PIN, address, name) VALUES ('209378521', 4659, 'Santa Cruz #3579', 'Kelvin Coster')";
                stmt.executeQuery(data);   
                data = "INSERT INTO Customers(taxID, PIN, address, name) VALUES ('212116070', 9173, '2 Peoples Rd Beijing', 'Li Kung')";
                stmt.executeQuery(data);
                data = "INSERT INTO Customers(taxID, PIN, address, name) VALUES ('188212217', 7351, '3852 Court Rd', 'Magic Jordon')";
                stmt.executeQuery(data);
                data = "INSERT INTO Customers(taxID, PIN, address, name) VALUES ('203491209', 5340, '1997 Peoples St HK', 'Nam-hoi Chung')";
                stmt.executeQuery(data);
                data = "INSERT INTO Customers(taxID, PIN, address, name) VALUES ('210389768', 8452, '6689 El Colegio #151', 'Olive Stoner')";
                stmt.executeQuery(data);
                data = "INSERT INTO Customers(taxID, PIN, address, name) VALUES ('400651982', 1821, '911 State St', 'Pit Wilson')";
                stmt.executeQuery(data);

		data = "INSERT INTO Accounts(aid, balance, interest, open, type) VALUES (17431, 200.0, 0.0, '1', 'Student-Checking')";
                stmt.executeQuery(data);					
                data = "INSERT INTO Accounts(aid, balance, interest, open, type) VALUES (54321, 21000.0, 0.0, '1', 'Student-Checking')";
                stmt.executeQuery(data);
                data = "INSERT INTO Accounts(aid, balance, interest, open, type) VALUES (12121, 1150.0, 0.0, '1', 'Student-Checking')";
                stmt.executeQuery(data);
                data = "INSERT INTO Accounts(aid, balance, interest, open, type) VALUES (41725, 15000.0, 0.0, '1', 'Student-Checking')";
                stmt.executeQuery(data);
                
                data = "INSERT INTO Accounts(aid, balance, interest, open, type) VALUES (76543, 8456.0, 5.5, '1', 'Interest-Checking')";
                stmt.executeQuery(data);
                data = "INSERT INTO Accounts(aid, balance, interest, open, type) VALUES (93156, 2000000.0, 5.5, '1', 'Interest-Checking')";
                stmt.executeQuery(data);
                
                data = "INSERT INTO Accounts(aid, balance, interest, open, type) VALUES (43942, 1269.0, 7.5, '1', 'Savings')";
                stmt.executeQuery(data);
                data = "INSERT INTO Accounts(aid, balance, interest, open, type) VALUES (29107, 33970.0, 7.5, '1', 'Savings')";
                stmt.executeQuery(data);
                data = "INSERT INTO Accounts(aid, balance, interest, open, type) VALUES (19023, 2200.0, 7.5, '1', 'Savings')";
                stmt.executeQuery(data);
                data = "INSERT INTO Accounts(aid, balance, interest, open, type) VALUES (32156, 1000.0, 7.5, '1', 'Savings')";
                stmt.executeQuery(data);
                
                data = "INSERT INTO Accounts(aid, balance, interest, open, type) VALUES (53027, 50.0, 0.0, '1', 'Pocket')";
                stmt.executeQuery(data);
                data = "INSERT INTO Accounts(aid, balance, interest, open, type) VALUES (43947, 30.0, 0.0, '1', 'Pocket')";
                stmt.executeQuery(data);
                data = "INSERT INTO Accounts(aid, balance, interest, open, type) VALUES (60413, 20.0, 0.0, '1', 'Pocket')";
                stmt.executeQuery(data);
                data = "INSERT INTO Accounts(aid, balance, interest, open, type) VALUES (67521, 100.0, 0.0, '1', 'Pocket')";
                stmt.executeQuery(data);
                
                data = "INSERT INTO InitialBalances(aid, balance) VALUES (17431, 200.0)";
                stmt.executeQuery(data);
                data = "INSERT INTO InitialBalances(aid, balance) VALUES (54321, 21000.0)";
                stmt.executeQuery(data);
                data = "INSERT INTO InitialBalances(aid, balance) VALUES (12121, 1150.0)";
                stmt.executeQuery(data);
                data = "INSERT INTO InitialBalances(aid, balance) VALUES (41725, 15000.0)";
                stmt.executeQuery(data);
                data = "INSERT INTO InitialBalances(aid, balance) VALUES (93156, 2000000.0)";
                stmt.executeQuery(data);
                data = "INSERT INTO InitialBalances(aid, balance) VALUES (53027, 50.0)";
                stmt.executeQuery(data);
                data = "INSERT INTO InitialBalances(aid, balance) VALUES (43942, 1269.0)";
                stmt.executeQuery(data);
                data = "INSERT INTO InitialBalances(aid, balance) VALUES (29107, 33970.0)";
                stmt.executeQuery(data);
                data = "INSERT INTO InitialBalances(aid, balance) VALUES (19023, 2200.0)";
                stmt.executeQuery(data);
                data = "INSERT INTO InitialBalances(aid, balance) VALUES (60413, 20.0)";
                stmt.executeQuery(data);
                data = "INSERT INTO InitialBalances(aid, balance) VALUES (32156, 1000.0)";
                stmt.executeQuery(data);
                data = "INSERT INTO InitialBalances(aid, balance) VALUES (76543, 8456.0)";
                stmt.executeQuery(data);
                data = "INSERT INTO InitialBalances(aid, balance) VALUES (43947, 30.0)";
                stmt.executeQuery(data);
                data = "INSERT INTO InitialBalances(aid, balance) VALUES (67521, 100.0)";
                stmt.executeQuery(data);
                
                data = "INSERT INTO Balances(aid, balance, day) VALUES (17431, 200.0, TO_DATE('03-02-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Balances(aid, balance, day) VALUES (54321, 21000.0, TO_DATE('03-03-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Balances(aid, balance, day) VALUES (12121, 1150.0, TO_DATE('03-03-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Balances(aid, balance, day) VALUES (41725, 15000.0, TO_DATE('03-03-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Balances(aid, balance, day) VALUES (93156, 2000000.0, TO_DATE('03-03-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Balances(aid, balance, day) VALUES (53027, 50.0, TO_DATE('03-04-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Balances(aid, balance, day) VALUES (43942, 1269.0, TO_DATE('03-04-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Balances(aid, balance, day) VALUES (29107, 33970.0, TO_DATE('03-04-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Balances(aid, balance, day) VALUES (19023, 2200.0, TO_DATE('03-05-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Balances(aid, balance, day) VALUES (60413, 20.0, TO_DATE('03-05-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Balances(aid, balance, day) VALUES (32156, 1000.0, TO_DATE('03-05-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Balances(aid, balance, day) VALUES (76543, 8456.0, TO_DATE('03-05-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Balances(aid, balance, day) VALUES (43947, 30.0, TO_DATE('03-05-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Balances(aid, balance, day) VALUES (67521, 100.0, TO_DATE('03-06-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);

                data = "INSERT INTO LinkedPockets(pid, aid) VALUES (60413, 43942)";
                stmt.executeQuery(data);
                data = "INSERT INTO LinkedPockets(pid, aid) VALUES (53027, 12121)";
                stmt.executeQuery(data);
                data = "INSERT INTO LinkedPockets(pid, aid) VALUES (43947, 29107)";
                stmt.executeQuery(data);
                data = "INSERT INTO LinkedPockets(pid, aid) VALUES (67521, 19023)";
                stmt.executeQuery(data);
                

                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('344151573', 17431, 'Primary')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('412231856', 17431, 'Co-Owner')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('322175130', 17431, 'Co-Owner')";
                stmt.executeQuery(data);
               
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('212431965', 54321, 'Primary')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('412231856', 54321, 'Co-Owner')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('122219876', 54321, 'Co-Owner')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('203491209', 54321, 'Co-Owner')";
                stmt.executeQuery(data);
                
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('207843218', 12121, 'Primary')";
                stmt.executeQuery(data);
                
                
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('201674933', 41725, 'Primary')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('401605312', 41725, 'Co-Owner')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('231403227', 41725, 'Co-Owner')";
                stmt.executeQuery(data);
                
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('212116070', 76543, 'Primary')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('188212217', 76543, 'Co-Owner')";
                stmt.executeQuery(data);
                
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('209378521', 93156, 'Primary')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('188212217', 93156, 'Co-Owner')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('210389768', 93156, 'Co-Owner')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('122219876', 93156, 'Co-Owner')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('203491209', 93156, 'Co-Owner')";
                stmt.executeQuery(data);
                
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('361721022', 43942, 'Primary')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('400651982', 43942, 'Co-Owner')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('212431965', 43942, 'Co-Owner')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('322175130', 43942, 'Co-Owner')";
                stmt.executeQuery(data);
                
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('209378521', 29107, 'Primary')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('212116070', 29107, 'Co-Owner')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('210389768', 29107, 'Co-Owner')";
                stmt.executeQuery(data);
                
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('412231856', 19023, 'Primary')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('201674933', 19023, 'Co-Owner')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('401605312', 19023, 'Co-Owner')";
                stmt.executeQuery(data);
                
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('188212217', 32156, 'Primary')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('207843218', 32156, 'Co-Owner')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('122219876', 32156, 'Co-Owner')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('344151573', 32156, 'Co-Owner')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('203491209', 32156, 'Co-Owner')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('210389768', 32156, 'Co-Owner')";
                stmt.executeQuery(data);
                
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('207843218', 53027, 'Primary')";
                stmt.executeQuery(data);
                
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('212116070', 43947, 'Primary')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('210389768', 43947, 'Co-Owner')";
                stmt.executeQuery(data);
                
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('361721022', 60413, 'Primary')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('400651982', 60413, 'Co-Owner')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('122219876', 60413, 'Co-Owner')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('231403227', 60413, 'Co-Owner')";
                stmt.executeQuery(data);
                
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('209378521', 67521, 'Primary')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('401605312', 67521, 'Co-Owner')";
                stmt.executeQuery(data);
                data = "INSERT INTO Owners(taxID, aid, type) VALUES ('212431965', 67521, 'Co-Owner')";
                stmt.executeQuery(data);
                
                data= "INSERT INTO Balances(aid, balance, day) VALUES (17431, 9000.0, TO_DATE('03-02-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (1, 344151573, TO_DATE('03-02-2011', 'MM-DD-YYYY'), 'Deposit')";
                stmt.executeQuery(data);
                data= "INSERT INTO Deposit(tid, amt, aid) VALUES (1, 8800.0, 17431)";
                stmt.executeQuery(data);
                
                data= "INSERT INTO Balances(aid, balance, day) VALUES (54321, 18000.0, TO_DATE('03-03-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (2, 122219876, TO_DATE('03-03-2011', 'MM-DD-YYYY'), 'Withdraw')";
                stmt.executeQuery(data);
                data = "INSERT INTO Withdraw(tid, amt, aid) VALUES (2, 3000.0, 54321)";
                stmt.executeQuery(data);
                
                data = "INSERT INTO Balances(aid, balance, day) VALUES (76543, 4456.0, TO_DATE('03-05-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "NSERT INTO Transactions(tid, taxID, day, type) VALUES (4, 212116070, TO_DATE('03-05-2011', 'MM-DD-YYYY'), 'Withdraw')";
                stmt.executeQuery(data);
                data = "INSERT INTO Withdraw(tid, amt, aid) VALUES (4, 2000.0, 76543)";
                stmt.executeQuery(data);
                
                data = "INSERT INTO Balances(aid, balance, day) VALUES (53027, 40.0, TO_DATE('03-05-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (5, 207843218, TO_DATE('03-05-2011', 'MM-DD-YYYY'), 'Purchase')";
                stmt.executeQuery(data);
                data = "INSERT INTO Purchase(tid, amt, pid) VALUES (5, 5.0, 53027)";
                stmt.executeQuery(data);
                
                data = "INSERT INTO Balances(aid, balance, day) VALUES (93156, 1000000.0, TO_DATE('03-06-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (6, 188212217, TO_DATE('03-06-2011', 'MM-DD-YYYY'), 'Withdraw')";
                stmt.executeQuery(data);
                data = "INSERT INTO Withdraw(tid, amt, aid) VALUES (6, 1000000.0, 93156)";
                stmt.executeQuery(data);
                
                data = "INSERT INTO Balances(aid, balance, day) VALUES (29107, 29970.0, TO_DATE('03-06-2011', 'MM-DD-YYYY'))";               
                stmt.executeQuery(data);        
                data = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (7, 212116070, TO_DATE('03-06-2011', 'MM-DD-YYYY'), 'Withdraw')";
                stmt.executeQuery(data);
                data = "INSERT INTO Withdraw(tid, amt, aid) VALUES (7, 4000.0, 29107)";
                stmt.executeQuery(data);
                
                data = "INSERT INTO Balances(aid, balance, day) VALUES (93156, 1050000.0, TO_DATE('03-06-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (1, 209378521, TO_DATE('03-06-2011', 'MM-DD-YYYY'), 'WriteCheck')";
                stmt.executeQuery(data);
                data = "INSERT INTO WriteCheck(tid, checkNum, cid, amt) VALUES (1, 1, 93156, 950000.0)";
                stmt.executeQuery(data);
        
        
                data = "INSERT INTO Balances(aid, balance, day) VALUES (43942, 980.0, TO_DATE('03-07-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Balances(aid, balance, day) VALUES (17431, 489.0, TO_DATE('03-07-2011', 'MM-DD-YYYY')";
                stmt.executeQuery(data);
                data = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (2, 322175130, TO_DATE('03-07-2011', 'MM-DD-YYYY'), 'Transfer')";
                stmt.executeQuery(data);
                data = "INSERT INTO Transfer(tid, amt, fromAid, toAid) VALUES (2, 289.0, 43942, 17431)";
                stmt.executeQuery(data);
        
                data = "INSERT INTO Balances(aid, balance, day) VALUES (43942, 691.0, TO_DATE('03-07-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (3, 400651982, TO_DATE('03-07-2011', 'MM-DD-YYYY'), 'Withdraw')";
                stmt.executeQuery(data);

                data = "INSERT INTO Withdraw(tid, amt, aid) VALUES (3, 289.0, 43942)";
                stmt.executeQuery(data);


                data = "INSERT INTO Balances(aid, balance, day) VALUES (60413, 5.0, TO_DATE('03-08-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Balances(aid, balance, day) VALUES (67521, 110.0, TO_DATE('03-08-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (4, 400651982, TO_DATE('03-08-2011', 'MM-DD-YYYY'), 'PayFriend')";
                stmt.executeQuery(data);
                data = "INSERT INTO PayFriend(tid, amt, fromPid, toPid) VALUES (4, 10.0, 60413, 67521)";
                stmt.executeQuery(data);
        
                data ="INSERT INTO Balances(aid, balance, day) VALUES (93156, 1100000.0, TO_DATE('03-08-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (5, 210389768, TO_DATE('03-08-2011', 'MM-DD-YYYY'), 'Deposit')";
                stmt.executeQuery(data);
                data = "INSERT INTO Deposit(tid, amt, aid) VALUES (5, 50000.0, 93156)";
                stmt.executeQuery(data);

                data = "INSERT INTO Balances(aid, balance, day) VALUES (12121, 950.0, TO_DATE('03-08-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
data = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (6, 207843218, TO_DATE('03-08-2011', 'MM-DD-YYYY'), 'WriteCheck')";
stmt.executeQuery(data);
data = "INSERT INTO WriteCheck(tid, checkNum, cid, amt) VALUES (6, 2, 12121, 200.0)";
stmt.executeQuery(data);
        
                data = "INSERT INTO Balances(aid, balance, day) VALUES (41725, 14000.0, TO_DATE('03-08-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
data = "INSERT INTO Balances(aid, balance, day) VALUES (19023, 3200.0, TO_DATE('03-08-2011', 'MM-DD-YYYY'))";
stmt.executeQuery(data);
data = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (7, 201674933, TO_DATE('03-08-2011', 'MM-DD-YYYY'), 'Transfer')";
stmt.executeQuery(data);
data = "INSERT INTO Transfer(tid, amt, fromAid, toAid) VALUES (7, 1000.0, 41725, 19023)";
stmt.executeQuery(data);
        
                data = "INSERT INTO Balances(aid, balance, day) VALUES (32156, 5000.0, TO_DATE('03-09-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
data = "INSERT INTO Balances(aid, balance, day) VALUES (41725, 9920.0, TO_DATE('03-09-2011', 'MM-DD-YYYY'))";
stmt.executeQuery(data);
data = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (8, 401605312, TO_DATE('03-09-2011', 'MM-DD-YYYY'), 'Wire')";
stmt.executeQuery(data);
data = "INSERT INTO Wire(tid, amt, fromAid, toAid) VALUES (8, 4000.0, 41725, 32156)";
stmt.executeQuery(data);
        
                data = "INSERT INTO Balances(aid, balance, day) VALUES (53027, 35.0, TO_DATE('03-09-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
data = "INSERT INTO Balances(aid, balance, day) VALUES (60413, 15.0, TO_DATE('03-09-2011', 'MM-DD-YYYY'))";
stmt.executeQuery(data);
data = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (9, 207843218, TO_DATE('03-09-2011', 'MM-DD-YYYY'), 'PayFriend')";
stmt.executeQuery(data);
data = "INSERT INTO PayFriend(tid, amt, fromPid, toPid) VALUES (9, 10.0, 53027, 60413)";
stmt.executeQuery(data);
        
                data = "INSERT INTO Balances(aid, balance, day) VALUES (60413, 0.0, TO_DATE('03-10-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (10, 122219876, TO_DATE('03-10-2011', 'MM-DD-YYYY'), 'Purchase')";
                stmt.executeQuery(data);
                data = "INSERT INTO Purchase(tid, amt, pid) VALUES (10, 15.0, 60413)";
                stmt.executeQuery(data);
        
                data = "INSERT INTO Balances(aid, balance, day) VALUES (93156, 1080000.0, TO_DATE('03-12-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (11, 203491209, TO_DATE('03-12-2011', 'MM-DD-YYYY'), 'Withdraw')";
                stmt.executeQuery(data);
                data = "INSERT INTO Withdraw(tid, amt, aid) VALUES (11, 20000.0, 93156)";
                stmt.executeQuery(data);
        
                data = "INSERT INTO Balances(aid, balance, day) VALUES (76543, 8000.0, TO_DATE('03-12-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (12, 188212217, TO_DATE('03-12-2011', 'MM-DD-YYYY'), 'WriteCheck')";
                stmt.executeQuery(data);
                data = "INSERT INTO WriteCheck(tid, checkNum, cid, amt) VALUES (12, 3, 76543, 456.0)";
                stmt.executeQuery(data);
        
                data = "INSERT INTO Balances(aid, balance, day) VALUES (67521, 85.0, TO_DATE('03-14-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Balances(aid, balance, day) VALUES (53027, 55.0, TO_DATE('03-14-2011', 'MM-DD-YYYY'))";
                stmt.executeQuery(data);
                data = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (13, 212431965, TO_DATE('03-14-2011', 'MM-DD-YYYY'), 'PayFriend')";
                stmt.executeQuery(data);
                data = "INSERT INTO PayFriend(tid, amt, fromPid, toPid) VALUES (13, 20.0, 67521, 53027)";
                stmt.executeQuery(data);
                
                
                
        
                
                data = "UPDATE Accounts SET balance = 9289.0  WHERE aid = 17431";
                stmt.executeQuery(data);
                data = "UPDATE Accounts SET balance = 18000.0 WHERE aid = 54321";
                stmt.executeQuery(data);
                data = "UPDATE Accounts SET balance = 950.0 WHERE aid = 12121";
                stmt.executeQuery(data);
                data = "UPDATE Accounts SET balance = 10000 WHERE aid = 41725";
                stmt.executeQuery(data);
                data = "UPDATE Accounts SET balance = 80000 WHERE aid = 93156";
                stmt.executeQuery(data);
                data = "UPDATE Accounts SET balance = 50 WHERE aid = 53027";
                stmt.executeQuery(data);
                data = "UPDATE Accounts SET balance = 691 WHERE aid = 43942";
                stmt.executeQuery(data);
                data = "UPDATE Accounts SET balance = 29965 WHERE aid = 29107";
                stmt.executeQuery(data);
                data = "UPDATE Accounts SET balance = 3150 WHERE aid = 19023";
                stmt.executeQuery(data);
                data = "UPDATE Accounts SET balance = 5 WHERE aid = 60413";
                stmt.executeQuery(data);
                data = "UPDATE Accounts SET balance = 5000 WHERE aid = 32156";
                stmt.executeQuery(data);
                data = "UPDATE Accounts SET balance = 6000 WHERE aid = 76543";
                stmt.executeQuery(data);
                data = "UPDATE Accounts SET balance = 35 WHERE aid = 43947";
                stmt.executeQuery(data);
                data = "UPDATE Accounts SET balance = 140 WHERE aid = 67521";
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
