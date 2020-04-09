package com.users.util;

import com.users.dao.UserDao;
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

    public static UserDao getUserDaoType() throws DBException {
        UserDao userDaoType = new UserDaoFactory().getUserDAO(properties.getProperty("daotype"));
        return userDaoType;
    }
}
