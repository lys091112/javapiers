package com.xianyue.springboot.mapper;

import com.xianyue.springboot.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

/**
 * @author Xianyue
 */
public interface UserMapper {

    @Insert("insert into user (username,password) values (#{username},#{password})")
    public int addUser(User user);

    @Update("update user set username = #{username}, password = #{password} where userid = #{userId}")
    public int updateUser(User user);
}
