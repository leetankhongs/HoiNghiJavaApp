
import Class.Conference;
import MainScreenUI.Login;
import MainScreenUI.Register;
import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class MainClass {
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    
                    
                    
                    Date date1 = sdf.parse("11-11-2020");
                    Date date2 = sdf.parse("11-11-2020");
                    Date date3 = sdf.parse("13-11-2020");
                    Date date4 = sdf.parse("14-11-2020");
                    System.out.println(date2.compareTo(date1));
                } catch (ParseException ex) {
                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }   
}
