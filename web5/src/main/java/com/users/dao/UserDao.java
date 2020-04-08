package com.users.dao;

import com.users.exception.DBException;
import com.users.model.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    public List<User> getAllUser() throws SQLException, DBException;

    public void addUser(User user) throws SQLException, DBException;

    public void deleteUser(User user) throws SQLException, DBException;

    public void updateUser(User user) throws SQLException, DBException;

}
