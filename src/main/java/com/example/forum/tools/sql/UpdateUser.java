package com.example.forum.tools.sql;

import com.example.forum.tools.JDBCUtils;
import com.example.forum.tools.User;

import java.sql.PreparedStatement;

public class UpdateUser {
    public UpdateUser(User user) throws Exception {
        //定义sql
        String sql = "UPDATE tab_user SET username = ? ,sex = ? ,telephone = ?,email = ? WHERE userid = ?;";
        //获取pstmt对象
        PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
        //设置参数
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getSex());
        pstmt.setString(3, user.getTelephone());
        pstmt.setString(4, user.getEmail());
        pstmt.setString(5,user.getUserid());
        //执行sql
        pstmt.executeUpdate();
        //释放资源
        pstmt.close();
        JDBCUtils.close(JDBCUtils.getConnection());
    }
}
