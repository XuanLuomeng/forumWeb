package com.example.forum.service.impl;

import com.example.forum.dao.UserDao;
import com.example.forum.dao.impl.UserDaoImpl;
import com.example.forum.service.UserService;
import com.example.forum.tools.User;
import com.example.forum.tools.sql.InsertUser;

public class UserServiceImpl implements UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) throws Exception {
        UserDao userDao = new UserDaoImpl();
        //检测用户是否存在，若存在，则返回false代表注册失败，否则返回true代表注册成功并进行用户注册
        if(userDao.isExist(user)){
            return false;
        }else {
            InsertUser insertUser = new InsertUser(user);
            return true;
        }
    }
}
