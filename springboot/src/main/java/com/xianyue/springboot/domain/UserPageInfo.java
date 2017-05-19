package com.xianyue.springboot.domain;

import com.xianyue.springboot.util.PageInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Xianyue
 */
@Setter
@Getter
public class UserPageInfo extends PageInfo {
    private List<User> users;
}
