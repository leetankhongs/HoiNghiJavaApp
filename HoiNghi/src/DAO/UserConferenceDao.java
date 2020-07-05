/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJO.UserConference;
import POJO.UserConferenceId;
import Util.NewHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ADMIN
 */
public class UserConferenceDao {
    public static List<UserConference> getAllUserConference(){
        List<UserConference> list = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        try{
            String hql = "select UC from UserConference UC";
            Query query = session.createQuery(hql);
            list = query.list();
        }finally{
            session.close();
        }
        
        return list;
    }
    
    public static UserConference getUserConferenceInformation(UserConferenceId idUserConference){
        UserConference userConference = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        try{
            userConference = (UserConference) session.get(UserConference.class, idUserConference);
        }finally{
            session.close();
        }
        
        return userConference;
    }
    
    public static String insertNewUserConference(UserConference userConference){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        if(UserConferenceDao.getUserConferenceInformation(userConference.getId()) != null)
            return null;
        
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            session.save(userConference);
            transaction.commit();
        }finally{
            session.close();
        }
        
        return userConference.getId().toString();
    }
    
    public static boolean updateUserConferenceInformation(UserConference userConference){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        if(UserConferenceDao.getUserConferenceInformation(userConference.getId()) == null)
            return false;
        
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            session.update(userConference);
            transaction.commit();
        }finally{
            session.close();
        }
        
        return true;
    }
}
