package com.users.service;

import com.users.dao.UserDao;
import com.users.exception.DBException;
import com.users.model.User;
import com.users.util.UserDaoFactory;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = UserDaoFactory.getUserDAO();
    private static UserServiceImpl INSTANCE;

    private UserServiceImpl() throws DBException {

    }

    public static UserServiceImpl getInstance() {
        if (INSTANCE == null) {
            try {
                INSTANCE = new UserServiceImpl();
            } catch (DBException e) {
                e.printStackTrace();
            }
        }
        return INSTANCE;
    }

    public boolean addUser(String name, String surName) throws DBException {
        try {
            User user = new User();
            user.setName(name);
            user.setSurName(surName);
            userDao.addUser(user);
            return true;
        } catch (Exception e) {
            throw new DBException("Не могу добавить пользователя", e);
        }
    }

    public boolean updateUser(Long id, String name, String surName) throws DBException {
        try {
            if (id != null && name != null && surName != null) {
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setSurName(surName);
                userDao.updateUser(user);
                return true;
            }
        } catch (Exception e) {
            throw new DBException("Не могу обновить пользователя", e);
        }
        return false;
    }

    public boolean deleteUser(Long id) throws DBException {
        try {
            if (id != null) {
                User user = new User();
                user.setId(id);
                userDao.deleteUser(user);
                return true;
            }
        } catch (Exception e) {
            throw new DBException("Не могу удалить пользователя", e);
        }
        return false;
    }

    public List<User> getAllUser() throws DBException {
        try {
            return userDao.getAllUser();
        } catch (Exception e) {
            throw new DBException("Не могу получить список пользователей", e);
        }
    }
}
