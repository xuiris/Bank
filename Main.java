import java.sql.Connection;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kenkh
 */
public class Main {

    /**
     * @param args the command line arguments
     * 
     */
    
    
    public static void main(String[] args) {
        Bank bank = new Bank();
        Connection conn = bank.getConnection();
        SetUpTables su = new SetUpTables(conn);
        su.destroy();
        su.create();
        su.initData();
        BankForm form = new BankForm();
        form.setVisible(true);
        // TODO code application logic here
    }
    
}
