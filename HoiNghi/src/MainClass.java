
import Class.Conference11;
import POJO.Conference;
import Util.NewHibernateUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

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
        private static List<Conference> listSinhVien;

    public static void main(String[] args) {

        org.hibernate.SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();

            listSinhVien = session.createQuery("select sv from Conference sv").list();

            for (int i = 0; i < listSinhVien.size(); i++) {
                System.out.println(listSinhVien.get(i).toString());
            }
                session.getTransaction().commit();
            
        } finally {
            session.close();
            sessionFactory.close();
        }
        
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//        
//            public void run() {
//                try {
//                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//                    
//                    
//                    
//                    Date date1 = sdf.parse("11-11-2020");
//                    Date date2 = sdf.parse("11-11-2020");
//                    Date date3 = sdf.parse("13-11-2020");
//                    Date date4 = sdf.parse("14-11-2020");
//                    System.out.println(date2.compareTo(date1));
//                } catch (ParseException ex) {
//                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
    }   
}
