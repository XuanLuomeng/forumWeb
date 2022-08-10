package com.example.forum.service;

import com.example.forum.tools.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean regist(User user) throws Exception;
}
