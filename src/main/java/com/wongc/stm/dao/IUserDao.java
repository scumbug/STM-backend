package com.wongc.stm.dao;

import java.util.List;

import com.wongc.stm.model.User;

public interface IUserDao {
    List<User> getAllUsers();
    User getUserById(int userId);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
}
