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
        
        public static String stringDeposit(String day, double added, int aid, String taxID) {
            return "";
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
