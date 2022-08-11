package com.example.forum.dao.impl;

import com.example.forum.dao.UserDao;
import com.example.forum.tools.JDBCUtils;
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
        //定义sql
        String sql = "select username from tab_user where username = ? or userid = ?;";
        //获取pstmt对象
        PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
        //设置参数
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getUserid());
        //执行sql
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            JDBCUtils.close(JDBCUtils.getConnection());
            return true;
        }else {
            JDBCUtils.close(JDBCUtils.getConnection());
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
