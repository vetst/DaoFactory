package com.users.util;

import com.users.dao.MySqlUserDao;
import com.users.dao.UserDao;
import com.users.dao.UserHibernateDAO;
import com.users.exception.DBException;

import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {

    public static UserDao getUserDAO() throws DBException {
        String db = null;
        Properties properties = new Properties();
        try (InputStream fis = UserDaoFactory.class.getResourceAsStream("/config.properties")) {
            properties.load(fis);
            db = properties.getProperty("daotype");
            if (db.equalsIgnoreCase("DaoJDBC")) {
                return new MySqlUserDao();
            } else {
                return new UserHibernateDAO();
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
    }
}
