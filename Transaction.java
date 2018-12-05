import java.sql.*;

public class Transaction {
	// This class will handle creation and deletion
	// of transactions into the database.
	
	// also need to handle the DATE!
	
	public static boolean createDeposit(Connection conn, String day, double added, int aid, String taxID){
		try {
			Statement stmt = conn.createStatement();
			int tid = newTid(conn);
			String qry = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (" 
					+ tid 
                                        + ", " + taxID
					+ ", TO_DATE('" + day + "', 'MM-DD-YYYY')"
					+ ", 'Deposit')";
			System.out.println(qry);
			stmt.executeQuery(qry);
			qry = "INSERT INTO Deposit(tid, amt, aid) VALUES (" 
					+ tid 
					+ ", " + added
					+ ", " + aid + ")";
			stmt.executeQuery(qry);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
        
        public static String stringDeposit(Connection conn, double added, int aid, String taxID) throws SQLException {
            return Customer.getName(conn, taxID) 
                    + " deposits $" + added 
                    + " to account " + aid;
        }
	
	public static boolean createTopUp(Connection conn, String day, double added, int pid, String taxID){
		try {
			Statement stmt = conn.createStatement();
			int tid = newTid(conn);
			String qry = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (" 
					+ tid 
                                        + ", " + taxID
                                        + ", TO_DATE('" + day + "', 'MM-DD-YYYY')"
					+ ", 'TopUp')";
			System.out.println(qry);
			stmt.executeQuery(qry);
			qry = "INSERT INTO TopUp(tid, amt, pid) VALUES (" 
					+ tid 
					+ ", " + added
					+ ", " + pid + ")";
			stmt.executeQuery(qry);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static String stringTopUp(Connection conn, double added, int pid, String taxID) throws SQLException {
                Statement stmt = conn.createStatement();
                String qry = "SELECT * from LinkedPockets p where p.pid = " + pid;
                ResultSet rs = stmt.executeQuery(qry);
                int la;
                if (rs.next()) {
                    la = rs.getInt("aid");
                } else {
                    System.out.println("No linked account");
                    throw new SQLException("No linked account exists in DB");
                }
                return Customer.getName(conn, taxID) 
                    + " tops up $" + added 
                    + " to account " + pid
                    + " from account " + la;
        }
        
        public static boolean createWithdraw(Connection conn, String day, double subtracted, int aid, String taxID) {
		try {
			Statement stmt = conn.createStatement();
			int tid = newTid(conn);
			String qry = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (" 
					+ tid 
                                        + ", " + taxID
					+ ", TO_DATE('" + day + "', 'MM-DD-YYYY')"
					+ ", 'Withdraw')";
			System.out.println(qry);
			stmt.executeQuery(qry);
			qry = "INSERT INTO Withdraw(tid, amt, aid) VALUES (" 
					+ tid 
					+ ", " + subtracted
					+ ", " + aid  + ")";
			stmt.executeQuery(qry);
			return true;
		} catch (SQLException e) {
			return false;
		}	
	}
	
	public static String stringWithdraw(Connection conn, double subtracted, int aid, String taxID) throws SQLException {
                return Customer.getName(conn, taxID) 
                    + " withdraws $" + subtracted 
                    + " from account " + aid;
        }
        
        public static boolean createPurchase(Connection conn, String day, double amt, int pid, String taxID) {
		try {
			Statement stmt = conn.createStatement();
			int tid = newTid(conn);
			String qry = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (" 
					+ tid 
                                        + ", " + taxID
					+ ", TO_DATE('" + day + "', 'MM-DD-YYYY')"
					+ ", 'Purchase')";
			System.out.println(qry);
			stmt.executeQuery(qry);
			qry = "INSERT INTO Purchase(tid, amt, pid) VALUES (" 
					+ tid 
					+ ", " + amt
					+ ", " + pid  + ")";
			stmt.executeQuery(qry);
			return true;
		} catch (SQLException e) {
			return false;
		}	
	}
        
        public static String stringPurchase(Connection conn, double amt, int pid, String taxID) throws SQLException {
                return Customer.getName(conn, taxID) 
                    + " purchases $" + amt 
                    + " from account " + pid;
        }
        
        public static boolean createTransfer(Connection conn, String day, double amt, int from, int to, String taxID) {
            try {
                Statement stmt = conn.createStatement();
                int tid = newTid(conn);
                String qry = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (" 
                                + tid 
                                + ", " + taxID
                                + ", TO_DATE('" + day + "', 'MM-DD-YYYY')"
                                + ", 'Transfer')";
                System.out.println(qry);
                stmt.executeQuery(qry);
                qry = "INSERT INTO Transfer(tid, amt, fromAid, toAid) VALUES (" 
                                + tid 
                                + ", " + amt
                                + ", " + from
                                + ", " + to + ")";
                stmt.executeQuery(qry);
                return true;
            } catch (SQLException e) {
                return false;
            }	
	}
        
        public static String stringTransfer(Connection conn, double amt, int from, int to, String taxID) throws SQLException {
                return Customer.getName(conn, taxID) 
                    + " transfers $" + amt 
                    + " from account " + from
                    + " to account " + to;
        }
        
        public static boolean createCollect(Connection conn, String day, double amt, int pid, String taxID) {
		try {
			Statement stmt = conn.createStatement();
			int tid = newTid(conn);
			String qry = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (" 
					+ tid 
                                        + ", " + taxID
					+ ", TO_DATE('" + day + "', 'MM-DD-YYYY')"
					+ ", 'Collect')";
			System.out.println(qry);
			stmt.executeQuery(qry);
			qry = "INSERT INTO Collect(tid, amt, fromPid) VALUES (" 
					+ tid 
					+ ", " + amt
					+ ", " + pid  + ")";
			stmt.executeQuery(qry);
			return true;
		} catch (SQLException e) {
			return false;
		}	
	}
        
        public static String stringCollect(Connection conn, double amt, int pid, String taxID) throws SQLException {
                Statement stmt = conn.createStatement();
                String qry = "SELECT * from LinkedPockets p where p.pid = " + pid;
                ResultSet rs = stmt.executeQuery(qry);
                int la;
                if (rs.next()) {
                    la = rs.getInt("aid");
                } else {
                    System.out.println("No linked account");
                    throw new SQLException("No linked account exists in DB");
                }
                return Customer.getName(conn, taxID) 
                    + " collects $" + amt 
                    + " from account " + pid
                    + " to account " + la;
        }
        
        public static boolean createPayFriend(Connection conn, String day, double amt, int from, int to, String taxID) {
            try {
                Statement stmt = conn.createStatement();
                int tid = newTid(conn);
                String qry = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (" 
                                + tid 
                                + ", " + taxID
                                + ", TO_DATE('" + day + "', 'MM-DD-YYYY')"
                                + ", 'PayFriend')";
                System.out.println(qry);
                stmt.executeQuery(qry);
                qry = "INSERT INTO PayFriend(tid, amt, fromPid, toPid) VALUES (" 
                                + tid 
                                + ", " + amt
                                + ", " + from
                                + ", " + to + ")";
                stmt.executeQuery(qry);
                return true;
            } catch (SQLException e) {
                return false;
            }	
	}
	
        public static String stringPayFriend(Connection conn, double amt, int from, int to, String taxID) throws SQLException {
                return Customer.getName(conn, taxID) 
                    + " pay-friends $" + amt 
                    + " from account " + from
                    + " to account " + to;
        }
        
        public static boolean createWire(Connection conn, String day, double amt, int from, int to, String taxID) {
            try {
                Statement stmt = conn.createStatement();
                int tid = newTid(conn);
                String qry = "INSERT INTO Transactions(tid, taxID, day, type) VALUES (" 
                                + tid 
                                + ", " + taxID
                                + ", TO_DATE('" + day + "', 'MM-DD-YYYY')"
                                + ", 'Wire')";
                System.out.println(qry);
                stmt.executeQuery(qry);
                qry = "INSERT INTO Wire(tid, amt, fromAid, toAid) VALUES (" 
                                + tid 
                                + ", " + amt
                                + ", " + from
                                + ", " + to + ")";
                stmt.executeQuery(qry);
                return true;
            } catch (SQLException e) {
                return false;
            }	
	}
        
        public static String stringWire(Connection conn, double amt, int from, int to, String taxID) throws SQLException {
                return Customer.getName(conn, taxID) 
                    + " wires $" + amt 
                    + " from account " + from
                    + " to account " + to;
        }
        
	// Come up with an available and unique tid.
	private static int newTid(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		String qry = "SELECT MAX(t.tid) AS Max FROM Transactions t";
		ResultSet rs = stmt.executeQuery(qry);
		if (rs.next()) {
			// use next available index.
			return (rs.getInt("Max") + 1);
		} else {
			// no transactions yet. start at 0.
			return 0;			
		}
	}
}
