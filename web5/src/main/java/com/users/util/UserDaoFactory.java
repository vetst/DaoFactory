package com.users.util;

import com.users.dao.MySqlUserDao;
import com.users.dao.UserDao;
import com.users.dao.UserHibernateDAO;
import com.users.exception.DBException;

public class UserDaoFactory {

    private static String db = DaoType.getUserDaoType("daotype");

    public static UserDao getUserDAO() throws DBException {
        if (db.equalsIgnoreCase("DaoJDBC")) {
            return new MySqlUserDao();
        } else if (db.equalsIgnoreCase("Hiber")) {
            return new UserHibernateDAO();
        }
        return null;
    }
}
