package com.wongc.stm.dao;

import java.util.List;

import com.wongc.stm.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UserDao implements IUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getUserById(int userId) {
        return null;
    }

    @Override
    public void addUser(User user) {
        // Add user
        String stmt = "INSERT INTO users (userId,username,password,type,name,primaryContact,primaryEmail) values (?,?,?,?,?,?,?)";
        jdbcTemplate.update(stmt,user.getUserId(),user.getUsername(),user.getPassword(),user.getType(),user.getName(),user.getPrimaryContact(),user.getPrimaryEmail());

        // Get user ID
        stmt = "SELECT userId FROM users WHERE username = ? and password = ?";
        int userId = jdbcTemplate.queryForObject(stmt, Integer.class, user.getUsername(),user.getPassword());

        // Set user ID
        user.setUserId(userId);
    }

    @Override
    public void deleteUser(int userId) {
        String stmt = "DELETE FROM users WHERE userId = ?";
        jdbcTemplate.update(stmt,userId);
    }

    @Override
    public List<User> getAllUsers() {
        // TODO
        return null;
    }

    @Override
    public void updateUser(User user) {
        // TODO

    }
}
