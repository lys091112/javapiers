package com.xianyue.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @author Xianyue
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("User")
public class User {
    private int    userId;
    private String userName;
    private String password;
}
