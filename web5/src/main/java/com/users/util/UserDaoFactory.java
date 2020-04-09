package com.users.util;

import com.users.dao.MySqlUserDao;
import com.users.dao.UserDao;
import com.users.dao.UserHibernateDAO;
import com.users.exception.DBException;

public class UserDaoFactory {

    public static UserDao getUserDAO(String type) throws DBException {
        if (type.equalsIgnoreCase("DaoJDBC")) {
            return new MySqlUserDao();
        } else {
            return new UserHibernateDAO();
        }
    }
}
