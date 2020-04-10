package com.users.util;

import com.users.dao.MySqlUserDao;
import com.users.dao.UserDao;
import com.users.dao.UserHibernateDAO;
import com.users.exception.DBException;

import java.io.InputStream;
import java.util.Properties;

public class DaoType {

    private static Properties properties = new Properties();
    static {
        try (InputStream fis = DaoType.class.getResourceAsStream("/config.properties")) {
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserDao getUserDaoType(String key) throws DBException {
       String db = properties.getProperty(key);
        if (db.equalsIgnoreCase("DaoJdbc")) {
            return new MySqlUserDao();
        } else {
            return new UserHibernateDAO();
        }
    }
}
