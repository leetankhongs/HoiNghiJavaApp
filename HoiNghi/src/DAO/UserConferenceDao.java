/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJO.Conference;
import POJO.User;
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

    public static List<UserConference> getAllUserConference() {
        List<UserConference> list = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        try {
            String hql = "select UC from UserConference UC";
            Query query = session.createQuery(hql);
            list = query.list();
        } finally {
            session.close();
        }

        return list;
    }

    public static UserConference getUserConferenceInformation(UserConferenceId idUserConference) {
        UserConference userConference = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        try {
            userConference = (UserConference) session.get(UserConference.class, idUserConference);
        } finally {
            session.close();
        }

        return userConference;
    }

    public static String insertNewUserConference(UserConference userConference) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (UserConferenceDao.getUserConferenceInformation(userConference.getId()) != null) {
            return null;
        }

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(userConference);
            transaction.commit();
        } finally {
            session.close();
        }

        return userConference.getId().toString();
    }

    public static boolean updateUserConferenceInformation(UserConference userConference) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        if (UserConferenceDao.getUserConferenceInformation(userConference.getId()) == null) {
            return false;
        }

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(userConference);
            transaction.commit();
        } finally {
            session.close();
        }

        return true;
    }

    public static List<UserConference> getListUserConferenceByUser(User user) {
        List<UserConference> list = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        try {
            String hql = "select UC from UserConference UC where UC.user = :user";
            Query query = session.createQuery(hql);
            query.setParameter("user", user);
            list = query.list();

        } finally {
            session.close();
        }

        return list;
    }

    public static List<UserConference> getListUserConferenceIsAcceptedByUser(User user) {
        List<UserConference> list = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        try {
            String hql = "select UC from UserConference UC where UC.user = :user and isAccepted = 1";
            Query query = session.createQuery(hql);
            query.setParameter("user", user);
            list = query.list();

        } finally {
            session.close();
        }

        return list;
    }
    
    public static List<UserConference> getListUserConferenceIsAcceptedByConference(Conference conference) {
        List<UserConference> list = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        try {
            String hql = "select UC from UserConference UC where UC.conference = :conference and isAccepted = 1";
            Query query = session.createQuery(hql);
            query.setParameter("conference", conference);
            list = query.list();

        } finally {
            session.close();
        }

        return list;
    }

    public static int deleteRegistration(UserConferenceId userConferenceId) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        int result = 0;
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            String hql = "delete  from UserConference UC where UC.id = :userConferenceId";
            Query query = session.createQuery(hql);
            query.setParameter("userConferenceId", userConferenceId);
            result = query.executeUpdate();
            transaction.commit();

        } finally {
            session.close();
        }
        return result;
    }

    public static UserConference getUserConference(Conference conference, User user) {
        List<UserConference> list = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select UC from UserConference UC where UC.user = :user and UC.conference = :conference";
            Query query = session.createQuery(hql);
            query.setParameter("user", user);
            query.setParameter("conference", conference);
            list = query.list();

        } finally {
            session.close();
        }

        return list.get(0);
    }

    public static List<UserConference> getNewRequests(Conference conference) {
        List<UserConference> list = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        try {
            String hql = "select UC from UserConference UC where UC.isAccepted = 0 and UC.conference = :conference";
            Query query = session.createQuery(hql);
            query.setParameter("conference", conference);
            list = query.list();

        } finally {
            session.close();
        }

        return list;
    }

    public static int getTheNumberOfUserIsAccepted(Conference conference) {
        List<UserConference> list = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        try {
            String hql = "select UC from UserConference UC where UC.isAccepted = 1 and UC.conference = :conference";
            Query query = session.createQuery(hql);
            query.setParameter("conference", conference);
            list = query.list();

        } finally {
            session.close();
        }

        return list.size();
    }

    public static int getTheNumberOfUserIsNotDeclined(Conference conference) {
        List<UserConference> list = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();

        try {
            String hql = "select UC from UserConference UC where UC.isAccepted != 2 and UC.conference = :conference";
            Query query = session.createQuery(hql);
            query.setParameter("conference", conference);
            list = query.list();

        } finally {
            session.close();
        }

        return list.size();
    }
}
