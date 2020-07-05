/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJO.Account;
import Util.NewHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author ADMIN
 */
public class AccountDao {
    public static List<Account> getAllAccount(){
        List<Account> list = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        try{
            String hql = "select acc from Account acc";
            Query query = session.createQuery(hql);
            list = query.list();
        }finally{
            session.close();
        }
        
        return list;
    }
    
    public static Account getAccountInformation(String idAccount){
        Account account = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        try{
            account = (Account) session.get(Account.class, idAccount);
        }finally{
            session.close();
        }
        
        return account;
    }
    
    public static String insertNewAccount(Account account){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        if(UserDao.getUserInformation(account.getId()) != null)
            return null;
        
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
        }finally{
            session.close();
        }
        
        return account.getId();
    }
    
    public static boolean updateAccountInformation(Account account){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        if(UserDao.getUserInformation(account.getId()) == null)
            return false;
        
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            session.update(account);
            transaction.commit();
        }finally{
            session.close();
        }
        
        return true;
    }
    
    public static Account getAccountByUserName(String userName){
        List<Account> list = null;
        Account account = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        try{
            String hql = "select acc from Account acc where acc.userName = :UserName";
            Query query = session.createQuery(hql);
            query.setParameter("UserName", userName);     
            list = query.list();
            
            if(list.size() != 0)
                account = list.get(0);
            
        }finally{
            session.close();
        }
        
        return account;
    }
}
