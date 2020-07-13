/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import DAO.UserDao;
import POJO.User;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class UserBus {
    public static List<User> getAllUser(){
        return UserDao.getAllUser();
    }
    
    public static User getUserInformation(String idUser){
        return UserDao.getUserInformation(idUser);
    }
    
    public static String insertNewUser(User user){
        List<User> list = UserDao.getAllUser();
        String format = "%1$010d";
        user.setId(String.format(format, list.size()+1));
        return UserDao.insertNewUser(user);
    }
    
    public static boolean updateUserInformation(User user){
        return UserDao.updateUserInformation(user);
    }
    
    public static boolean blockUser(User user){
        user.setIsDelete((Byte.valueOf("1")));
        return UserDao.updateUserInformation(user);
    }
    
    public static boolean unlockUser(User user){
        user.setIsDelete((Byte.valueOf("0")));
        return UserDao.updateUserInformation(user);
    }
        
}
