
import java.sql.*;

public class Account {
	public int aid;
	public double interest;
	public double balance;
	public boolean isOpen;
	public String type;
	
	Account() {}
	
	Account(int Aid, double Balance, double Interest, boolean Open, String Type) {
		aid = Aid;
		interest = Interest;
		balance = Balance;
		isOpen = Open;
		type = Type;
	}
        
        public static Account createAccount(Connection conn, int aid, double balance, double interest, boolean open, String type) throws SQLException {
            if ((balance <= 0) || (interest < 0)) {
                throw new SQLException("Cannot create Account instance with invalid balance/interest");
            }
            
            //Creates account and stores in database
            Account a = new Account(aid, balance, interest, true, type);
            
            

            Statement stmt = conn.createStatement();
            String qry = "INSERT INTO Accounts(aid, balance, interest, open, type)" +
                    " VALUES (" +
                    aid + ", " +
                    balance + ", " +
                    interest + ", " +
                    "'1', " +
                    "'" + type + "')";
            stmt.executeQuery(qry);
            return a;  
        }
	
	public static Account getAccount(Connection conn, int aid) throws SQLException {
		Statement stmt = conn.createStatement();
		String qry = "SELECT * from Accounts a where a.aid = " + aid;
		ResultSet rs = stmt.executeQuery(qry);
		Account a = new Account();
		
		if (rs.next()) {
			a.aid = rs.getInt("aid");
			a.interest = rs.getDouble("interest");
			a.balance = rs.getDouble("balance");
			String status = rs.getString("open");
			a.isOpen = true;
			if (status.equals("0")) a.isOpen = false;
			a.type = rs.getString("type");
        } 
		else {
			System.out.println("No account found with aid: " + aid);
	        throw new SQLException("Access with invalid aid");
		}
		
		return a;
	}
	
	public boolean updateAccountDB(Connection conn){
		try {
			Statement stmt = conn.createStatement();
			int status = 0;
			if (balance == 0) {
                            isOpen = false;
                            status = 0;
                        }
                        else {
                            isOpen = true;
                            status = 1;
                        }
			String qry = "UPDATE Accounts "
					+ "SET interest = " + interest 
					+ ", balance = " + balance 
					+ ", open = " + status 				
					+ " WHERE aid = " + aid;
			System.out.println(qry);
			stmt.executeQuery(qry);
                        String day = SystemTime.getSystemDate();
			qry = "INSERT INTO Balances(aid, balance, day) VALUES (" 
					+ aid 
					+ ", " + balance
					+ ", TO_DATE('" + day + "', 'MM-DD-YYYY'))";
			System.out.println(qry);
			stmt.executeQuery(qry);
                        return true;	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error updating account " + aid);
			return false;
		}	
	}
	
	public String toString() {
		return ("aid: " + aid +  ",     balance: " + String.format("%.2f", balance) + ",     interest: " + interest + ",     open: " + isOpen + ",     type: " + type);
	}
		
	
}