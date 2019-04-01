package com.dao;

import com.model.User;

import java.util.List;

public interface Dao {
    int addUser(User user);

    void deleteUser(int id);

    void updateUser(User user);

    List<User> getAllUsers();

    User getUserById(int userId);

}
