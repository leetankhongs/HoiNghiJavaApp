/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import POJO.UserConference;
import java.util.List;
import DAO.UserConferenceDao;
import POJO.Conference;
import POJO.User;
import POJO.UserConferenceId;
import Util.NewHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ADMIN
 */
public class UserConferenceBus {

    public static List<UserConference> getAllUserConference() {
        return UserConferenceDao.getAllUserConference();
    }

    public static UserConference getUserConferenceInformation(UserConferenceId idUserConference) {
        return UserConferenceDao.getUserConferenceInformation(idUserConference);
    }

    public static String insertNewUserConference(UserConference userConference) {
        return UserConferenceDao.insertNewUserConference(userConference);
    }

    public static boolean updateUserConferenceInformation(UserConference userConference) {
        return UserConferenceDao.updateUserConferenceInformation(userConference);
    }

    public static List<UserConference> getListUserConferenceByUser(User user) {
        return UserConferenceDao.getListUserConferenceByUser(user);
    }
    
    public static List<UserConference> getListUserConferenceIsAcceptedByUser(User user) {
        return UserConferenceDao.getListUserConferenceIsAcceptedByUser(user);
    }
    
    public static List<UserConference> getListUserConferenceIsAcceptedByConference(Conference conference) {
        return UserConferenceDao.getListUserConferenceIsAcceptedByConference(conference);
    }

    public static int deleteRegistration(Conference conference, User user) {
        return UserConferenceDao.deleteRegistration(UserConferenceDao.getUserConference(conference, user).getId());
    }
    
    public static int deleteRegistration(UserConference userConference) {
        return UserConferenceDao.deleteRegistration(UserConferenceDao.getUserConference(userConference.getConference(), userConference.getUser()).getId());
    }

    public static List<UserConference> getNewRequests(Conference conference) {
        return UserConferenceDao.getNewRequests(conference);
    }

    public static boolean acceptRequest(UserConferenceId id) {
        UserConference userConference = UserConferenceDao.getUserConferenceInformation(id);
        userConference.setIsAccepted(1);
        return UserConferenceDao.updateUserConferenceInformation(userConference);
    }

    public static boolean declineRequest(UserConferenceId id) {
        UserConference userConference = UserConferenceDao.getUserConferenceInformation(id);
        userConference.setIsAccepted(2);
        return UserConferenceDao.updateUserConferenceInformation(userConference);
    }
    
    public static int getTheNumberOfUserIsAccepted(Conference conference){
        return UserConferenceDao.getTheNumberOfUserIsAccepted(conference);
    }

    public static int getTheNumberOfUserIsNotDeclined(Conference conference) {
        return UserConferenceDao.getTheNumberOfUserIsNotDeclined(conference);
    }
}
