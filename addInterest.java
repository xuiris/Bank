
import java.sql.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import static java.lang.Math.toIntExact;
import java.text.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kenkh
 */
public class addInterest extends javax.swing.JFrame {

    private final Bank bank;
    private Connection conn;
    private java.util.Date sysDate;
    
    /**
     * Creates new form addInterest
     */
    public addInterest() {
        initComponents();
        bank = new Bank();
        conn = bank.getConnection();      
        
        // check if interest has already been added in the past month.
        String qry = "SELECT MAX(day) as lastday FROM AccrueInterest";
        try {
            DateFormat format = new SimpleDateFormat("MM-dd-yyyy");
            String d = SystemTime.getSystemDate();
            sysDate = format.parse(d);
            
            Statement stmt = conn.createStatement();
            ResultSet t = stmt.executeQuery(qry);
            if (t.next()){
                java.util.Date lastDay = t.getDate("lastday");
                if (lastDay == null) {
                    // No record that interest has ever been added. Perform,
                    performAddInterest();
                }
                else if (diffDays(lastDay, sysDate) > 30) {
                    // Interest hasn't been accrued in past 30 days.
                    // Can now do it...
                    performAddInterest();
                } else {
                    status.setText("Interest has already been added this month.");
                }                   
            }        
            t.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding interest");
            status.setText("Error adding interest");
        } catch (ParseException e) {
            System.out.println("Issue with parsing system time");
            status.setText("Issue with parsing system time");
        } 
    }
    
    public void performAddInterest() {
        String date = SystemTime.getSystemDate();
        
        // get all accounts in bank
        String qry = "SELECT a.aid, a.interest FROM Accounts a";
        try {
            Statement stmt = conn.createStatement();
            ResultSet accts = stmt.executeQuery(qry);

            while(accts.next()){
                int aid  = accts.getInt("aid");
                double interest = accts.getDouble("interest");
                double avg = getAvgBalance(aid); // average daily balance
                System.out.println("Avg daily for " + aid + " is " + avg);
                
                Account a = Account.getAccount(conn, aid);
                a.balance += avg*(interest/100.0);
                a.updateAccountDB(conn);
            }
            accts.close();
            
            Transaction.createAccrueInterest(conn, date);
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding interest");
            status.setText("Error adding interest");
        } 
        
        status.setText("Interest added for this month");
    }
    
    public double getAvgBalance(int aid) {
        
        // day, amt 
        ArrayList<Map.Entry<java.util.Date, Double>> daily = new ArrayList<Map.Entry<java.util.Date, Double>>();
        
        String qry = "SELECT avg(b.balance) as avgbal, b.day FROM Balances b" 
                + " WHERE b.aid = " + aid
                + " GROUP BY day";
        try {
            Statement stmt = conn.createStatement();
            ResultSet b = stmt.executeQuery(qry);

            while(b.next()){
                double bal  = b.getDouble("avgbal");
                java.util.Date d = b.getDate("day");
                daily.add(new AbstractMap.SimpleEntry<>(d, bal));
            }
            
            // delete his balances to set up for next time.
            qry = "DELETE FROM Balances b" 
                + " WHERE b.aid = " + aid;
            stmt.executeQuery(qry);
            
            b.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error getting balances database");
            status.setText("Error getting balances database");
        } 
        
        double sum = 0.0; 
        double days = diffDays(sysDate, daily.get(0).getKey()) + 1;
        for (int i = 1; i <= daily.size()-1; i++) {
            // get diff in the days since last balance change
            java.util.Date last = daily.get(i-1).getKey();
            int elapsed = diffDays(daily.get(i).getKey(), last) + 1; 
            
            System.out.println("Was at " + daily.get(i).getValue() + " for " + elapsed + " days.");
            System.out.println(last + " to " + daily.get(i).getKey());
            
            double bal = daily.get(i).getValue();
            sum += bal * (elapsed/days);
        }
        int lastElement = daily.size()-1;
        int elapsed = diffDays(sysDate, daily.get(lastElement).getKey()) + 1;
        double bal = daily.get(lastElement).getValue();
        System.out.println("Was at " + bal + " for " + elapsed + " days.");
        System.out.println(sysDate + " to " + daily.get(lastElement).getKey());
        sum += bal * (elapsed/days);
        
        return sum;
    }
    
    public int diffDays(java.util.Date fin, java.util.Date init) {
        long diffInMillies = fin.getTime() - init.getTime();
        return toIntExact(TimeUnit.DAYS.convert(diffInMillies,TimeUnit.MILLISECONDS));
        //return (int)( (fin.getTime() - init.getTime()) / (1000 * 60 * 60 * 24) );
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
        jLabel1 = new javax.swing.JLabel();
        status = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        back.setText("BACK");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        jLabel1.setText("Add Interest");

        status.setText("Status");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(331, Short.MAX_VALUE)
                        .addComponent(back))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(status)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(status)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(addInterest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addInterest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addInterest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addInterest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addInterest().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel status;
    // End of variables declaration//GEN-END:variables
}
