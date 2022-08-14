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
     * 保存用户数据
     */
    public void save();

    /**
     * 对照数据库是否存在此账号与密码是否对应
     * @param userid
     * @param password
     * @return
     */
    public boolean findByUsernameAndPassword(String userid, String password) throws SQLException;
}
