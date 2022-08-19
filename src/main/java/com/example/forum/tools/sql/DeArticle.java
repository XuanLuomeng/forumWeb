package com.example.forum.tools.sql;

import com.example.forum.tools.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 根据aid删除文章
 */
public class DeArticle {
    public DeArticle(String aid) throws SQLException {
        //定义sql
        String sql = "DELETE FROM tab_article WHERE aid = ?;";
        //获取pstmt对象
        PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
        //设置参数
        pstmt.setInt(1, Integer.parseInt(aid));
        //执行sql
        int count = pstmt.executeUpdate();
        //释放资源
        pstmt.close();
        JDBCUtils.close(JDBCUtils.getConnection());
    }
}
