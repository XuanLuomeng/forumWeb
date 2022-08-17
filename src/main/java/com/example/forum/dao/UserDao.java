package com.example.forum.dao;

import com.example.forum.tools.User;

import java.sql.SQLException;

public interface UserDao {
    /**
     * 检测用户是否存在
     * @param user
     * @return
     */
    public boolean isExist(User user) throws Exception;

    /**
     * 修改用户数据
     */
    public boolean save(User user) throws Exception;

    /**
     * 对照数据库是否存在此账号与密码是否对应
     * @param userid
     * @param password
     * @return
     */
    public boolean findByUsernameAndPassword(String userid, String password) throws SQLException;

    /**
     * 返回用户信息，用于存入session
     */
    public User userInfo(String userid) throws SQLException;
}
