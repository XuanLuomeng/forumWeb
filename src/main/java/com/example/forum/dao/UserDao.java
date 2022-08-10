package com.example.forum.dao;

import com.example.forum.tools.User;

public interface UserDao {
    /**
     * 检测用户是否存在
     * @param user
     * @return
     */
    public boolean isExist(User user) throws Exception;

    /**
     * 查询用户信息
     * @param username
     * @return
     */
    public User findUsername(String username);

    /**
     * 保存用户数据
     */
    public void save();
}
