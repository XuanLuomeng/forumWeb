package com.example.forum.dao.impl;

import com.example.forum.dao.UserDao;
import com.example.forum.tools.JDBCUtils;
import com.example.forum.tools.User;
import com.example.forum.tools.sql.UpdateUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    /**
     * 修改保存用户信息(首先判断username是否以及存在，其次判断该username是否是自己正在使用的)
     * @param user
     * @return
     * @throws SQLException
     */
    @Override
    public boolean save(User user) throws Exception {
        //定义sql
        String sql = "select username from tab_user where username = ?;";
        //获取pstmt对象
        PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
        //设置参数
        pstmt.setString(1, user.getUsername());
        //执行sql
        ResultSet rs = pstmt.executeQuery();
        //判断是否用户名已存在，若不存在，则可以直接修改，若存在，则判断是否是目前自己正在使用的，若是自己目前正在使用，则可以修改成功，否则修改失败
        if(rs.next()){
            //定义sql
            String sql1 = "select username from tab_user where userid = ?;";
            //获取pstmt对象
            PreparedStatement pstmt1 = JDBCUtils.getConnection().prepareStatement(sql1);
            //设置参数
            pstmt1.setString(1, user.getUserid());
            //执行sql
            ResultSet rs1 = pstmt.executeQuery();
            if (rs1.next()){
                if(rs1.getString(1).equals(user.getUsername())){
                    new UpdateUser(user);
                }else {
                    JDBCUtils.close(JDBCUtils.getConnection());
                    return false;
                }
            }
            JDBCUtils.close(JDBCUtils.getConnection());
            return true;
        }else {
            new UpdateUser(user);
            JDBCUtils.close(JDBCUtils.getConnection());
            return true;
        }
    }

    /**
     * 根据用户名和密码查询的方法
     * @param userid
     * @param password
     * @return
     */
    @Override
    public boolean findByUsernameAndPassword(String userid, String password) throws SQLException {
        //定义sql
        String sql = "select username from tab_user where userid = ? and password = ?;";
        //获取pstmt对象
        PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
        //设置参数
        pstmt.setString(1, userid);
        pstmt.setString(2, password);
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

    /**
     * 返回用户信息，用于存入session
     */
    @Override
    public User userInfo(String userid) throws SQLException {
        User user = new User();
        //定义sql
        String sql = "select * from tab_user where userid = ?;";
        //获取pstmt对象
        PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
        //设置参数
        pstmt.setString(1, userid);
        //执行sql
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            user.setUserid(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setUsername(rs.getString(4));
            user.setRegdate(rs.getString(5));
            user.setSex(rs.getString(6));
            user.setTelephone(rs.getString(7));
            user.setEmail(rs.getString(8));
        }
        return user;
    }
}
