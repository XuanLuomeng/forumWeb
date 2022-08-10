package com.example.forum.tools.sql;

import com.example.forum.tools.JDBCUtils;
import com.example.forum.tools.User;

import java.sql.PreparedStatement;

public class InsertUser {
    public InsertUser(User user) throws Exception {
        //定义sql
        String sql = "INSERT INTO tab_user(userid,`password`,username,regdate, sex,telephone,email) VALUES\n" +
                "(?,?,?,?,?,?,?);";
        //获取pstmt对象
        PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
        //设置参数
        pstmt.setString(1, user.getUserid());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, user.getUsername());
        pstmt.setString(4, user.getRegdate());
        pstmt.setString(5, user.getSex());
        pstmt.setString(6, user.getTelephone());
        pstmt.setString(7, user.getEmail());
        //执行sql
        pstmt.executeUpdate();
        //释放资源
        pstmt.close();
        JDBCUtils.close(JDBCUtils.getConnection());
    }
}
