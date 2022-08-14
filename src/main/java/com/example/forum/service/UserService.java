package com.example.forum.service;

import com.example.forum.tools.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean regist(User user) throws Exception;

    /**
     * 登录账号
     * @param userid
     * @param password
     * @return
     * @throws Exception
     */
    boolean login(String userid,String password) throws Exception;
}
