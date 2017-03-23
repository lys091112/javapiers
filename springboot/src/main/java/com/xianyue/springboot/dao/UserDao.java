package com.xianyue.springboot.dao;

import com.xianyue.springboot.domain.User;
import com.xianyue.springboot.exception.TransException;
import com.xianyue.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Xianyue
 */

@Repository
public class UserDao {
    @Autowired
    private UserMapper userMapper;

    public void addUser(User user) {
        userMapper.addUser(user);
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public int testTransaction(User user) {
        if (user.getUsername().startsWith("trans")) {
            throw new TransException("this is a transaction test");
        }
        return 1;
    }
}
