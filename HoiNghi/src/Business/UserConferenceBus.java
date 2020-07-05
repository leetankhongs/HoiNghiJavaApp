/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import POJO.UserConference;
import java.util.List;
import DAO.UserConferenceDao;
import POJO.UserConferenceId;

/**
 *
 * @author ADMIN
 */
public class UserConferenceBus {
    public static List<UserConference> getAllUserConference(){
        return UserConferenceDao.getAllUserConference();
    }
    
    public static UserConference getUserConferenceInformation(UserConferenceId idUserConference){
        return UserConferenceDao.getUserConferenceInformation(idUserConference);
    }
    
    public static String insertNewUserConference(UserConference userConference){
        return UserConferenceDao.insertNewUserConference(userConference);
    }
    
    public static boolean updateUserConferenceInformation(UserConference userConference){
        return UserConferenceDao.updateUserConferenceInformation(userConference);
    }
}
