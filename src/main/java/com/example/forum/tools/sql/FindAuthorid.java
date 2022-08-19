package com.example.forum.tools.sql;

import com.example.forum.tools.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 根据账号查询uid，用于获取个人文章信息
 */
public class FindAuthorid {
    private String uid;
    public FindAuthorid(String userid) throws SQLException {
        //定义sql
        String sql = "SELECT uid FROM  tab_user WHERE userid = ?";
        //获取pstmt对象
        PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
        //设置参数
        pstmt.setString(1, userid);
        //执行sql
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            uid = rs.getString(1);
        }
        //释放资源
        pstmt.close();
        JDBCUtils.close(JDBCUtils.getConnection());
    }
    public String getUid(){
        return uid;
    }
}
