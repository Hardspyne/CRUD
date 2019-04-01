package com.dao;

import com.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public int addUser(User user) {
        String createUserQuery = "insert into users(firstName,lastName,birthdayDate,email) values (?, ?, ?, ? )";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createUserQuery, Statement.RETURN_GENERATED_KEYS);
            addUserParametersToPreparedStatement(preparedStatement, user);

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void deleteUser(int id) {
        String deleteUserQuery = "delete from users where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteUserQuery);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void updateUser(User user) {
        String updateUserQuery = "update users set firstName=?, lastName=?, birthdayDate=?, email=?" +
                "where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateUserQuery);
            addUserParametersToPreparedStatement(preparedStatement, user);
            preparedStatement.setInt(5, user.getUserId());

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        String selectAllQuery = "select * from users";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectAllQuery);

            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"), resultSet.getString("firstName"),
                        resultSet.getString("lastName"), resultSet.getDate("birthdayDate"), resultSet.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getUserById(int id) {
        String selectUserByIdQuery = "select * from users where id=?";
        User user = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectUserByIdQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new User(resultSet.getInt("id"), resultSet.getString("firstName"),
                        resultSet.getString("lastName"), resultSet.getDate("birthdayDate"), resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private void addUserParametersToPreparedStatement(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setDate(3, new java.sql.Date(user.getBirthdayDate().getTime()));
        preparedStatement.setString(4, user.getEmail());
    }
}
