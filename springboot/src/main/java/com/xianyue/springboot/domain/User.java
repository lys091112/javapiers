package com.xianyue.springboot.domain;

import com.xianyue.springboot.util.PageInfo;
import lombok.*;
import org.apache.ibatis.type.Alias;

/**
 * @author Xianyue
 */
@Alias("User")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User extends PageInfo{
    private int    userId;
    private String userName;
    private String password;
}
