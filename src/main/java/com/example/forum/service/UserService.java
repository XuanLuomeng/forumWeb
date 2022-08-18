package com.example.forum.service;

import com.example.forum.tools.User;

import java.sql.SQLException;

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

    /**
     * 查看个人信息
     */
    User seeUserInfo(String userid) throws SQLException;

    /**
     * 修改个人信息
     */
    public boolean update(User user) throws Exception;

    /**
     * 修改密码
     */
    public void reKey(String userid,String newPassword) throws SQLException;
}
