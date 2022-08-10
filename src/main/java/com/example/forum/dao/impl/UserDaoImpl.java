package com.example.forum.dao.impl;

import com.example.forum.dao.UserDao;
import com.example.forum.tools.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {

    /**
     * 检测用户名或者账号是否已经存在，如已存在则返回true，否则返回false
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public boolean isExist(User user) throws Exception {
        //加载配置文件,获取连接池对象,获取数据库连接
        Allocation a = new Allocation();
        //定义sql
        String sql = "select username from tab_user where username = ? or userid = ?;";
        //获取pstmt对象
        PreparedStatement pstmt = a.getConn().prepareStatement(sql);
        //设置参数
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getUserid());
        //执行sql
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User findUsername(String username) {
        return null;
    }

    @Override
    public void save() {

    }
}
