/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import DAO.AccountDao;
import DAO.UserDao;
import POJO.Account;
import POJO.User;
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
        for (int i = 1; i < list.size() + 1; i++) {
            if (list.get(i - 1).getId().compareTo(String.format(format, i)) != 0) {
                account.setId(String.format(format, i));
                break;
            }
        }

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

    public static Account getAccountByUserName(String userName) {
        return AccountDao.getAccountByUserName(userName);
    }

    public static int checkAccount(String userName, String password) {
        Account account = AccountDao.getAccountByUserName(userName);

        if (account == null) {
            return -1;
        }

        if (account.getPassword().compareTo(cryptWithMD5(password)) != 0) {
            return 0;
        }

        Object[] objects = account.getUsers().toArray();

        User user = (User) objects[0];

        if (user.getIsDelete() == 1) {
            return -2;
        }

        return 1;

    }

    public static boolean changePassword(String userName, String password) {
        Account account = AccountDao.getAccountByUserName(userName);
        account.setPassword(cryptWithMD5(password));
        return AccountDao.updateAccountInformation(account);
    }
}
