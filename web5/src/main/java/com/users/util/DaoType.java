package com.users.util;

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

    public static String getUserDaoType(String key) {
        return properties.getProperty(key);
    }
}
