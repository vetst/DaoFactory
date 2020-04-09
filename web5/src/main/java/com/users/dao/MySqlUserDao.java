package com.users.dao;

import com.users.exception.DBException;
import com.users.model.User;
import com.users.util.DBHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao implements UserDao {

    public MySqlUserDao() {

    }

    public List<User> getAllUser() throws SQLException, DBException {
        List<User> resultList = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = DBHelper.getInstance().getMysqlConnection();
            stmt = connection.createStatement();
            stmt.execute("select * from users");
            rs = stmt.getResultSet();
            while (rs.next()) {
                resultList.add(new User(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
        } catch (Exception e) {
            throw new DBException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {

            }
        }
        return resultList;
    }

    public void addUser(User user) throws SQLException, DBException {
        String addQuery = "insert into users (name, surname) values (?, ?)";
        try (Connection connection = DBHelper.getInstance().getMysqlConnection();
             PreparedStatement statement = connection.prepareStatement(addQuery)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurName());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public void deleteUser(User user) throws SQLException, DBException {
        String deleteQuery = "DELETE FROM users WHERE id =?";
        try (Connection connection = DBHelper.getInstance().getMysqlConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setLong(1, user.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public void updateUser(User user) throws SQLException, DBException {
        String updateQuery = "UPDATE users SET name =?, surname =? WHERE id =?";
        try (Connection connection = DBHelper.getInstance().getMysqlConnection();
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurName());
            statement.setLong(3, user.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DBException(e);
        }
    }
}
