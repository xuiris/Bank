import java.sql.*;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kenkh
 */
public class monthlyStatement extends javax.swing.JFrame {
    
    private Connection conn = null;
    
    /**
     * Creates new form monthlyStatement
     */
    public monthlyStatement() {
        initComponents();
        Bank bank = new Bank();
        conn = bank.getConnection();
    }
    
    private String stringTransaction(String type, int tid, String taxID) {
        String res = "";
        try {
            Statement stmt = conn.createStatement();
            
            if (type.equals("Deposit")) {
                String qry = "SELECT * from Deposit t where t.tid = " + tid;
                ResultSet rs = stmt.executeQuery(qry);
                if (rs.next()) 
                    res = Transaction.stringDeposit(conn, rs.getDouble("amt"), rs.getInt("aid"), taxID);
                rs.close();
            }
            else if (type.equals("TopUp")){
                String qry = "SELECT * from TopUp t where t.tid = " + tid;
                ResultSet rs = stmt.executeQuery(qry);
                if (rs.next()) 
                    res = Transaction.stringTopUp(conn, rs.getDouble("amt"), rs.getInt("pid"), taxID);
                rs.close();
            }
            else if (type.equals("Withdraw")){
                String qry = "SELECT * from Withdraw t where t.tid = " + tid;
                ResultSet rs = stmt.executeQuery(qry);
                if (rs.next()) 
                    res = Transaction.stringWithdraw(conn, rs.getDouble("amt"), rs.getInt("aid"), taxID);
                rs.close();
            }
            else if (type.equals("Purchase")){
                String qry = "SELECT * from Purchase t where t.tid = " + tid;
                ResultSet rs = stmt.executeQuery(qry);
                if (rs.next()) 
                    res = Transaction.stringPurchase(conn, rs.getDouble("amt"), rs.getInt("pid"), taxID);
                rs.close();
            }
            else if (type.equals("Transfer")){
                String qry = "SELECT * from Transfer t where t.tid = " + tid;
                ResultSet rs = stmt.executeQuery(qry);
                if (rs.next()) 
                    res = Transaction.stringTransfer(conn, rs.getDouble("amt"), rs.getInt("fromAid"), rs.getInt("toAid"), taxID);
                rs.close();
            }
            else if (type.equals("Collect")){
                String qry = "SELECT * from Collect t where t.tid = " + tid;
                ResultSet rs = stmt.executeQuery(qry);
                if (rs.next()) 
                    res = Transaction.stringCollect(conn, rs.getDouble("amt"), rs.getInt("pid"), taxID);
                rs.close();
            }
            else if (type.equals("PayFriend")){
                String qry = "SELECT * from PayFriend t where t.tid = " + tid;
                ResultSet rs = stmt.executeQuery(qry);
                if (rs.next()) 
                    res = Transaction.stringPayFriend(conn, rs.getDouble("amt"), rs.getInt("fromPid"), rs.getInt("toPid"), taxID);
                rs.close();
            }
            else if (type.equals("Wire")){
                String qry = "SELECT * from Wire t where t.tid = " + tid;
                ResultSet rs = stmt.executeQuery(qry);
                if (rs.next()) 
                    res = Transaction.stringWire(conn, rs.getDouble("amt"), rs.getInt("fromAid"), rs.getInt("toAid"), taxID);
                rs.close();
            }
            else if (type.equals("WriteCheck")){
                String qry = "SELECT * from WriteCheck t where t.tid = " + tid;
                ResultSet rs = stmt.executeQuery(qry);
                if (rs.next()) 
                    res = Transaction.stringCheck(conn, rs.getDouble("amt"), rs.getInt("cid"), taxID);
                rs.close();
            }
            else if (type.equals("AccrueInterest")){
                String qry = "SELECT * from AccrueInterest t where t.tid = " + tid;
                ResultSet rs = stmt.executeQuery(qry);
                if (rs.next()) 
                    res = ""; // Not implemented
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error getting specific transaction");
        }
        
        return res;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        back = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        monthlyStatement = new javax.swing.JTextArea();
        taxIDField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        generateButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        back.setText("BACK");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        monthlyStatement.setColumns(20);
        monthlyStatement.setRows(5);
        jScrollPane1.setViewportView(monthlyStatement);

        taxIDField.setText("taxID");
        taxIDField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taxIDFieldActionPerformed(evt);
            }
        });

        jLabel1.setText("Customer taxID:");

        generateButton.setText("Generate");
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 600, Short.MAX_VALUE)
                        .addComponent(back)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(taxIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(generateButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(taxIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(generateButton))
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(back)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        dispose();
        new bankTeller().setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        try{
            if(conn!=null){
               conn.close();
               System.out.println("From MonthlyStatements: Closed connection...");
            }
         }catch(SQLException se){
            se.printStackTrace();
         }
    }//GEN-LAST:event_formWindowClosed

    private void taxIDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taxIDFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_taxIDFieldActionPerformed

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        // TODO add your handling code here:
        String taxID = taxIDField.getText();
        try {
            Customer c = Customer.getCustomer(conn, taxID);
        }catch(SQLException se){
            se.printStackTrace();
            monthlyStatement.setText("No such customer with this taxID.");
            return;
        }
        
        String ms = "";
        double totalBalance = 0.0; // get total from all account balances added.
        
        // Get all accounts of the customer they are primary owners of
        HashMap<Integer, Account> accounts = new HashMap<Integer, Account>();
        String qry = "SELECT DISTINCT a.aid, a.interest, a.balance, a.open, a.type FROM Accounts a, Owners o"
                    + " WHERE o.taxID = '" + taxID + "'"
                    + " AND a.aid = o.aid" 
                    + " AND o.type = 'Primary'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet accts = stmt.executeQuery(qry);

            while(accts.next()){
                    //Retrieve by column name
                    int aid  = accts.getInt("aid");

                    //Add to list of accounts for this customer
                    accounts.put(aid, Account.getAccount(conn, aid));
            }
            accts.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error getting accounts of this owner");
            monthlyStatement.setText("Error getting the primary accounts of this customer");
        } 
        
        // FOR EACH ACCOUNT: Print the info for each account,
        // Keep track of how much acct balance has changed, 
        // subtract from current to get init balance.
        for (Map.Entry<Integer, Account> a: accounts.entrySet()) {
            // Print out the account info
            ms = ms + System.lineSeparator() + System.lineSeparator() + a.getValue().toString();
            int aid = a.getValue().aid;
            totalBalance += a.getValue().balance; // add this balance to total
            
            // Print info of all customers who own this account
            qry = "SELECT DISTINCT o.taxID, o.type FROM Owners o"
		+ " WHERE o.aid = '" + aid + "'";
            try {
                Statement stmt = conn.createStatement();
                ResultSet owners = stmt.executeQuery(qry);

                while(owners.next()){
                        //Retrieve by column name
                        String id  = owners.getString("taxID");
                        String type = owners.getString("type");
                        
                        //Get this customer info
                        Customer c = Customer.getCustomer(conn, id);
                        ms = ms + System.lineSeparator() + "Owner type: " + type + " " + c.toString();
                }
                owners.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error getting customers who own this account");
                monthlyStatement.setText("Error getting customers who own this account");
            } 
            
            // Print intial balance of this account
            qry = "SELECT b.balance FROM InitialBalances b"
		+ " WHERE b.aid = '" + aid + "'";
            try {
                Statement stmt = conn.createStatement();
                ResultSet ib = stmt.executeQuery(qry);
                if(ib.next()){
                    ms = ms + System.lineSeparator() + "INITIAL BALANCE: " + ib.getDouble("balance");
                }
                ib.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error getting initial balance of account");
                monthlyStatement.setText("Error getting initial balance of account");
            } 
            
            // Need to now print the transactions for this specific account.
            // Get all the TIDs from each Transaction where account is referenced
            ArrayList<Integer> relatedTids = new ArrayList<Integer>();
            try {
                qry = "SELECT t.tid from Deposit t where t.aid = " + aid;
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(qry);
                while (rs.next()) 
                    relatedTids.add(rs.getInt("tid"));
                rs.close();

                qry = "SELECT t.tid from TopUp t where t.pid = " + aid;
                rs = stmt.executeQuery(qry);
                while (rs.next()) 
                    relatedTids.add(rs.getInt("tid"));
                rs.close();

                qry = "SELECT t.tid from Withdraw t where t.aid = " + aid;
                rs = stmt.executeQuery(qry);
                while (rs.next()) 
                    relatedTids.add(rs.getInt("tid"));
                rs.close();

                qry = "SELECT t.tid from Purchase t where t.pid = " + aid;
                rs = stmt.executeQuery(qry);
                while (rs.next()) 
                    relatedTids.add(rs.getInt("tid"));
                rs.close();

                qry = "SELECT t.tid from Transfer t where t.fromAid = " + aid + " or t.toAid = " + aid;
                rs = stmt.executeQuery(qry);
                while (rs.next()) 
                    relatedTids.add(rs.getInt("tid"));
                rs.close();

                qry = "SELECT t.tid from Collect t where t.fromPid = " + aid;
                rs = stmt.executeQuery(qry);
                while (rs.next()) 
                    relatedTids.add(rs.getInt("tid"));
                rs.close();

                qry = "SELECT t.tid from PayFriend t where t.fromPid = " + aid + " or t.toPid = " + aid;
                rs = stmt.executeQuery(qry);
                while (rs.next()) 
                    relatedTids.add(rs.getInt("tid"));
                rs.close();

                qry = "SELECT t.tid from Wire t where t.fromAid = " + aid + " or t.toAid = " + aid;
                rs = stmt.executeQuery(qry);
                while (rs.next()) 
                    relatedTids.add(rs.getInt("tid"));
                rs.close();

                qry = "SELECT t.tid from WriteCheck t where t.cid = " + aid;
                rs = stmt.executeQuery(qry);
                while (rs.next()) 
                    relatedTids.add(rs.getInt("tid"));
                rs.close();
//            
//                qry = "SELECT t.tid from AccrueInterest t where t.aid = " + aid;
//                rs = stmt.executeQuery(qry);
//                while (rs.next()) 
//                    relatedTids.add(rs.getInt("tid"));
//                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error getting transactions related to this account");
                monthlyStatement.setText("Error getting transactions related to this account");
            }
            
            // Now go through sorted TIDs and print each transaction for the specific account.
            Collections.sort(relatedTids);
            for (Integer tid: relatedTids) {
                qry = "SELECT t.type, t.day, t.taxID FROM Transactions t WHERE t.tid = '" + tid + "'";
                String type;
                java.sql.Date day;
                try {
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(qry);
                    if (rs.next()) {
                        type = rs.getString("type");
                        day = rs.getDate("day");
                        String id = rs.getString("taxID");
                        ms = ms + System.lineSeparator() + day + " " + stringTransaction(type, tid, id);
                    }
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error getting list of transactions");
                    monthlyStatement.setText("Error getting list of transactions");
                } 
            }
            
            
            // Now print the final account balance
            ms = ms + System.lineSeparator() + "FINAL BALANCE: " + a.getValue().balance;
            
            // loop through other accounts.
        }
        
        // Warn customers of limit if exceeded.
        if (totalBalance > 100000) {
            ms = "WARNING: Your total balance $" + totalBalance 
                    + " exceeds $100,000. The limit of insurance has been reached."
                    + ms;
        }
        monthlyStatement.setText(ms);
    }//GEN-LAST:event_generateButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(monthlyStatement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(monthlyStatement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(monthlyStatement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(monthlyStatement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new monthlyStatement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton generateButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea monthlyStatement;
    private javax.swing.JTextField taxIDField;
    // End of variables declaration//GEN-END:variables
}
