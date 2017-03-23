package com.xianyue.springboot.service;

import com.xianyue.springboot.dao.UserDao;
import com.xianyue.springboot.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserDao userDao;


    public User addUser(String userName, String password) {
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        userDao.addUser(user);
        return user;
    }

    public int updateUser(User user) {
        return userDao.updateUser(user);
    }


    /**
     *  当testTransaction失败产生异常时，虽然数据没有被写入到数据库，但是数据库user表的自增id增加了，
     *  说明插入动作引起了数据库唯一id的改变，事务一场被还原后id没有还原
     */
    @Transactional
    public int testTransaction(User user) {
        userDao.addUser(user);
        logger.info("test transaction. continue...");
        userDao.testTransaction(user);
        return 1;
    }
}
