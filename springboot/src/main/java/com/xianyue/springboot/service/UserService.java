package com.xianyue.springboot.service;

import com.xianyue.springboot.config.UserDemoValue;
import com.xianyue.springboot.dao.UserDao;
import com.xianyue.springboot.domain.User;
import com.xianyue.springboot.test.AwareBean;
import com.xianyue.springboot.test.CommonTestAutoConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 *
 */
@Service
@AutoConfigureAfter(CommonTestAutoConfig.class)
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserDao userDao;

    @Autowired
    UserDemoValue userDemoValue;
    /**
     * 通过这个实例证明@Value的初始化在PostConstruct之前
     */
    @PostConstruct
    public void init() {
//        System.out.println("user.id----------------- = "+userId);
//        userId = 9L;
//        System.out.println("user.id2---------------- = "+ userId);
    }

    public User addUser(String userName, String password) {
        User user = new User();
        user.setUserName(userName);
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

    public List<User> getUsersByPage(int pageIndex) {
        User user = new User();
        user.setPageIndex(pageIndex);
        return userDao.getUsersByPage(user);
    }
}
