/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import DAO.ConferenceDao;
import POJO.Conference;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ConferenceBus {
    public static List<Conference> getAllConference(){
        return ConferenceDao.getAllConference();
    }
    
    public static Conference getConferenceInformation(String idConference){
       return ConferenceDao.getConferenceInformation(idConference);
    }
    
    public static String insertNewConference(Conference conference){
        List<Conference> list = ConferenceDao.getAllConference();
        String format = "%1$04d";
        boolean isExist = false;
        for(int i = 1 ;; i++)
        {
            isExist = false;
            for(int j = 0; j < list.size(); j++){
                if (list.get(j).getId().compareTo(String.format(format, i)) == 0) {
                    isExist = true;
                    break;
                }
            }
            
            if(isExist == false){
                conference.setId(String.format(format, i));
                break;
            }
        }

        return ConferenceDao.insertNewConference(conference);
    }
    
    public static boolean updateConfereneInformation(Conference conference){ 
        return ConferenceDao.updateConferenceformation(conference);
    }
    
    public static boolean deleteConference(Conference conference){
        conference.setIsDelete(Byte.valueOf("1"));
        return ConferenceDao.updateConferenceformation(conference);
    }
}
