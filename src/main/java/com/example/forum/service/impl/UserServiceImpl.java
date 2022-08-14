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

    /**
     * 登录账号
     * @param userid
     * @param password
     * @return
     * @throws Exception
     */
    @Override
    public boolean login(String userid, String password) throws Exception{
        UserDao userDao = new UserDaoImpl();
        //检测用户是否存在且密码正确，若都正确则返回true登录成功，否则返回false登陆失败
        if(userDao.findByUsernameAndPassword(userid,password)){
            return true;
        }else {
            return false;
        }
    }
}
