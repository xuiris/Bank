
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kenkh
 */
public class bankTeller extends javax.swing.JFrame {

    private final Bank bank;
    private Connection conn;
    private Map<Integer, Account> accounts;
    private Map<String, Integer> owners;
    
    
    /**
     * Creates new form bankTeller
     */
    public bankTeller() {
        initComponents();
        bank = new Bank();
        conn = bank.getConnection();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        checkTrans = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        monthlySt = new javax.swing.JButton();
        closedAcc = new javax.swing.JButton();
        DTER = new javax.swing.JButton();
        customerReport = new javax.swing.JButton();
        addInterest = new javax.swing.JButton();
        createAcc = new javax.swing.JButton();
        deleteClosed = new javax.swing.JButton();
        deleteTrans = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 700));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        checkTrans.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        checkTrans.setText("Enter Check Transaction");
        checkTrans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkTransActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("What would you like to do? ");

        monthlySt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        monthlySt.setText("Generate Monthly Statement");
        monthlySt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthlyStActionPerformed(evt);
            }
        });

        closedAcc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        closedAcc.setText("List Closed Accounts");
        closedAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closedAccActionPerformed(evt);
            }
        });

        DTER.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        DTER.setText("Generate DTER");
        DTER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DTERActionPerformed(evt);
            }
        });

        customerReport.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        customerReport.setText("Generate Customer Report");
        customerReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerReportActionPerformed(evt);
            }
        });

        addInterest.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addInterest.setText("Add Interest");
        addInterest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addInterestActionPerformed(evt);
            }
        });

        createAcc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        createAcc.setText("Create Account");
        createAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createAccActionPerformed(evt);
            }
        });

        deleteClosed.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        deleteClosed.setText("Delete Closed Accounts and Customers");
        deleteClosed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteClosedActionPerformed(evt);
            }
        });

        deleteTrans.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        deleteTrans.setText("Delete Transactions");
        deleteTrans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTransActionPerformed(evt);
            }
        });

        backButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(deleteClosed)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(checkTrans, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(customerReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(deleteTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(monthlySt))
                        .addGap(186, 186, 186)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addInterest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(createAcc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(closedAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addGap(113, 113, 113))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(252, 252, 252)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(239, 239, 239))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkTrans)
                    .addComponent(createAcc))
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerReport)
                    .addComponent(addInterest))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteTrans, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(closedAcc))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(monthlySt)
                    .addComponent(DTER))
                .addGap(60, 60, 60)
                .addComponent(deleteClosed)
                .addGap(7, 7, 7)
                .addComponent(backButton)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        dispose();
        new BankForm().setVisible(true); 
    }//GEN-LAST:event_backButtonActionPerformed

    private void monthlyStActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthlyStActionPerformed
        // TODO add your handling code here:
        new monthlyStatement().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_monthlyStActionPerformed

    private void closedAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closedAccActionPerformed
        // TODO add your handling code here:
        new closedAccount().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_closedAccActionPerformed

    private void checkTransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkTransActionPerformed
        // TODO add your handling code here:
        new checkTrans().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_checkTransActionPerformed

    private void customerReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerReportActionPerformed
        // TODO add your handling code here:
        new customerReport().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_customerReportActionPerformed

    private void deleteTransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteTransActionPerformed
        try {
            StringBuilder warnings = new StringBuilder();
            
            //Deletes all current transactions
            Statement st = conn.createStatement();
            String qry = "DELETE FROM Transactions";
            st.executeQuery(qry);
            warnings.append("All Transactions have been deleted");
            if( warnings.length()> 0) {
            JOptionPane.showMessageDialog(this, warnings.toString(), "SUCCESSFUL", JOptionPane.WARNING_MESSAGE);
            }
            
            //Delete initial balances
            qry = "DELETE FROM InitialBalances";
            st.executeQuery(qry);
            
 
            //get all aid       
            accounts = new HashMap<Integer, Account>();            
            qry = "SELECT * FROM Accounts";
            ResultSet rs = st.executeQuery(qry);
            while(rs.next()){
                int aid = rs.getInt("aid");
                accounts.put(aid, Account.getAccount(conn, aid));    
            }
            rs.close();  
            
            //Generate new Initial Balances
            for (Map.Entry<Integer, Account> a: accounts.entrySet()){
                Statement stmt = conn.createStatement();
                qry = "INSERT INTO InitialBalances(aid, balance) VALUES (" + a.getKey() + ", " + a.getValue().balance + ")";
                stmt.executeQuery(qry);
            }
        } catch (SQLException ex) {
            Logger.getLogger(bankTeller.class.getName()).log(Level.SEVERE, null, ex);
        }                      
    }//GEN-LAST:event_deleteTransActionPerformed

    private void deleteClosedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteClosedActionPerformed
      
        try{
            //Find all closed accounts
            StringBuilder warnings = new StringBuilder();
            accounts = new HashMap<Integer, Account>();
            String qry = "SELECT aid FROM Accounts WHERE open = '0'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(qry);
            
            while(rs.next()){
                int aid = rs.getInt("aid");
                accounts.put(aid, Account.getAccount(conn, aid));
            }
            rs.close();
            
            //Find the associated customers with each closed account
            owners = new HashMap<String, Integer>();
            for(Map.Entry<Integer, Account> a: accounts.entrySet()){
                st= conn.createStatement();
                qry = "SELECT * from Owners o where o.aid = " + a.getKey();
                rs = st.executeQuery(qry);
                if(rs.next()){
                    owners.put(rs.getString("taxID"), rs.getInt("aid"));
                }                
            }
            rs.close();
            
            //Delete customers 
            for(Map.Entry<String, Integer> c: owners.entrySet()){
                st = conn.createStatement();
                qry = "DELETE from Customers c where c.taxID = " + c.getKey();
                rs = st.executeQuery(qry);    
            }
            rs.close();
            
            //Delete Accounts
            for(Map.Entry<Integer, Account> a: accounts.entrySet()){
                st= conn.createStatement();
                String qry2 = "DELETE from Accounts a where a.aid = " + a.getKey();
                rs = st.executeQuery(qry2);
            }
            rs.close();
            warnings.append("Accounts and Customers have been deleted");
            if( warnings.length()> 0) {
            JOptionPane.showMessageDialog(this, warnings.toString(), "SUCCESSFUL", JOptionPane.WARNING_MESSAGE);
            }
            
                    
        }catch (SQLException ex) {
                Logger.getLogger(bankTeller.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_deleteClosedActionPerformed

    private void createAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createAccActionPerformed
        // TODO add your handling code here:
        new createAccount().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_createAccActionPerformed

    private void addInterestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addInterestActionPerformed
        // TODO add your handling code here:
        new addInterest().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_addInterestActionPerformed

    private void DTERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DTERActionPerformed
        // TODO add your handling code here:
        new DTER().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_DTERActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        try{
            if(conn!=null){
               conn.close();
               System.out.println("From BankTeller: Closed connection...");
            }
         }catch(SQLException se){
            se.printStackTrace();
         }
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(bankTeller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bankTeller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bankTeller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bankTeller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new bankTeller().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DTER;
    private javax.swing.JButton addInterest;
    private javax.swing.JButton backButton;
    private javax.swing.JButton checkTrans;
    private javax.swing.JButton closedAcc;
    private javax.swing.JButton createAcc;
    private javax.swing.JButton customerReport;
    private javax.swing.JButton deleteClosed;
    private javax.swing.JButton deleteTrans;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton monthlySt;
    // End of variables declaration//GEN-END:variables
}
