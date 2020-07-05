/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJO.Place;
import Util.NewHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ADMIN
 */
public class PlaceDao {
    public static List<Place> getAllPlace(){
        List<Place> list = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
       
        try{
            String hql = "select pl from Place pl";
            Query query = session.createQuery(hql);
            list = query.list();
        }finally{
            session.close();
        }
        
        return list;
    }
    
    public static Place getPlaceInformation(String idPlace){
        Place place = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        try{
            place = (Place) session.get(Place.class, idPlace);
        }finally{
            session.close();
        }
        
        return place;
    }
    
    public static String insertNewPlace(Place place){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        if(PlaceDao.getPlaceInformation(place.getId()) != null)
            return null;
        
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            session.save(place);
            transaction.commit();
        }finally{
            session.close();
        }
        
        return place.getId();
    }
    
    public static boolean updatePlaceInformation(Place place){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        if(PlaceDao.getPlaceInformation(place.getId()) == null)
            return false;
        
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            session.update(place);
            transaction.commit();
        }finally{
            session.close();
        }
        
        return true;
    }
}
