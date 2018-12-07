/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author axlen
 */
public class SystemTime {
    
    private static SystemTime singleton = null;
    private static String date;
    
    private SystemTime() {date = "12-07-2018";}
    
    public static String getSystemDate() {
        if (singleton == null) {
            singleton = new SystemTime();
        }
        return singleton.date;
    }
    
    public static void setSystemDate(String d) {
        date = d;
    }
}
