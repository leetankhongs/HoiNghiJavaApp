/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJO.Conference;
import POJO.Place;
import Util.NewHibernateUtil;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ADMIN
 */
public class ConferenceDao {
    
    public static List<Conference> getAllConference(){
        List<Conference> list = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
       
        try{
            String hql = "select cf from Conference cf";
            Query query = session.createQuery(hql);
            list = query.list();
            
        }finally{
            session.close();
        }
        
        return list;
    }
    
    public static Conference getConferenceInformation(String idConference){
        Conference conference = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        try{
            conference = (Conference) session.get(Conference.class, idConference);
        }finally{
            session.close();
        }
        
        return conference;
    }
    
    public static String insertNewConference(Conference conference){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        if(ConferenceDao.getConferenceInformation(conference.getId()) != null)
            return null;
        
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            session.save(conference);
            transaction.commit();
        }finally{
            session.close();
        }
        
        return conference.getId();
    }
    
    public static boolean updateConferenceformation(Conference conference){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        if(ConferenceDao.getConferenceInformation(conference.getId()) == null)
            return false;
        
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            session.update(conference);
            transaction.commit();
        }finally{
            session.close();
        }
        
        return true;
    }
}
