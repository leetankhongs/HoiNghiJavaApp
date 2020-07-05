/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJO.Place;
import POJO.User;
import Util.NewHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ADMIN
 */
public class UserDao {
    public static List<User> getAllUser(){
        List<User> list = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
       
        try{
            String hql = "select user from User user";
            Query query = session.createQuery(hql);
            list = query.list();
        }finally{
            session.close();
        }
        
        return list;
    }
    
    public static User getUserInformation(String idUser){
        User user = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        try{
            user = (User) session.get(User.class, idUser);
        }finally{
            session.close();
        }
        
        return user;
    }
    
    public static String insertNewUser(User user){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        if(UserDao.getUserInformation(user.getId()) != null)
            return null;
        
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }finally{
            session.close();
        }
        
        return user.getId();
    }
    
    public static boolean updateUserInformation(User user){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        if(UserDao.getUserInformation(user.getId()) == null)
            return false;
        
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }finally{
            session.close();
        }
        
        return true;
    }
    
//    public static User getUserByAccount(String userName){
//        
//    }
}
