package com.example.forum.tools.sql;

import com.example.forum.tools.JDBCUtils;
import com.example.forum.tools.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * sql查询
 * */
public class SelectUser {
    private User user;

    public User getUser() {
        return user;
    }

    public SelectUser(String username) throws Exception {
        //定义sql
        String sql = "select userid,`password`,username,regdate, sex,telephone,email from tab_user where username = ?;";
        //获取pstmt对象
        PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
        //设置参数
        pstmt.setString(1, username);
        //执行sql
        ResultSet rs = pstmt.executeQuery();
        //处理结束
        rs.next();
        User user = new User();
        user.setUserid(rs.getString("userid"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("username"));
        user.setRegdate(rs.getString("regdate"));
        user.setSex(rs.getString("sex"));
        user.setTelephone(rs.getString("telephone"));
        user.setEmail(rs.getString("email"));
        this.user = user;
        //释放资源
        rs.close();
        pstmt.close();
        JDBCUtils.close(JDBCUtils.getConnection());
    }
}
