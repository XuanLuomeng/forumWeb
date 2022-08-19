package com.example.forum.tools;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    /**
     * 账号
     * 密码
     * 用户名
     * 邮箱
     * 性别
     * 电话号码
     * 注册日期
     */
    private String userid;
    private String password;
    private String username;
    private String email;
    private String sex;
    private String telephone;
    private String regdate;
}
