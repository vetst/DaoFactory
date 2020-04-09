package com.users.util;

import com.users.dao.MySqlUserDao;
import com.users.dao.UserDao;
import com.users.dao.UserHibernateDAO;
import com.users.exception.DBException;

public class UserDaoFactory {

    private static UserDaoFactory INSTANCE;

    private UserDaoFactory() {

    }

    public static UserDaoFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserDaoFactory();
        }
        return INSTANCE;
    }

    public static UserDao getUserDAO(String type) throws DBException {
        if (type.equalsIgnoreCase("DaoJDBC")) {
            return new MySqlUserDao();
        } else {
            return new UserHibernateDAO();
        }
    }
}
