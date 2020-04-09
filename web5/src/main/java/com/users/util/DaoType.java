package com.users.util;

import com.users.exception.DBException;
import com.users.service.UserServiceImpl;

import java.io.InputStream;
import java.util.Properties;

public class DaoType {

    private static DaoType INSTANCE;

    private DaoType() {

    }

    public static DaoType getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DaoType();
        }
        return INSTANCE;
    }

    public String getProperties() throws DBException {
        String db = null;
        Properties properties = new Properties();
        try (InputStream fis = UserServiceImpl.class.getResourceAsStream("/config.properties")) {
            properties.load(fis);
            db = properties.getProperty("daotype");
        } catch (Exception e) {
            throw new DBException("Файл свойств не найден", e);
        }
        return db;
    }
}
