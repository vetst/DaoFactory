package com.users.service;

import com.users.exception.DBException;
import com.users.model.User;

import java.util.List;

public interface UserService {
    public boolean addUser(String name, String surName) throws DBException;

    public boolean updateUser(Long id, String name, String surName) throws DBException;

    public boolean deleteUser(Long id) throws DBException;

    public List<User> getAllUser() throws DBException;
}
