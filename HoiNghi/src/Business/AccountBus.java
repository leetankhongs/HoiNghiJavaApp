/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import DAO.AccountDao;
import DAO.UserDao;
import POJO.Account;
import Util.NewHibernateUtil;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class AccountBus {

    private static MessageDigest md;

    public static List<Account> getAllAccount() {
        return AccountDao.getAllAccount();
    }

    public static Account getAccountInformation(String idAccount) {
        return AccountDao.getAccountInformation(idAccount);
    }

    public static String insertNewAccount(String userName, String password) {
        List<Account> list = AccountDao.getAllAccount();
        String format = "%1$010d";
        Account account = new Account(userName, cryptWithMD5(password));
        account.setId(String.format(format, list.size() + 1));
        return AccountDao.insertNewAccount(account);
    }

    public static boolean updateAccountInformation(Account account) {
        return AccountDao.updateAccountInformation(account);
    }

    public static String cryptWithMD5(String pass) {
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digested.length; i++) {
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
        }
        return null;
    }
    
    public static Account getAccountByUserName(String userName){
        return AccountDao.getAccountByUserName(userName);
    }
    
    public static int checkAccount(String userName, String password){
        Account account = AccountDao.getAccountByUserName(userName);
        
        if(account == null)
            return -1;

        if(account.getPassword().compareTo(cryptWithMD5(password)) != 0)
            return 0;
        
        return 1;
        
    }
}
