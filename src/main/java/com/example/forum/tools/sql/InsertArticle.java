package com.example.forum.tools.sql;

import com.example.forum.tools.Article;
import com.example.forum.tools.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 发布文章
 */
public class InsertArticle {
    public InsertArticle(Article pushArticle) throws SQLException {
        //定义sql
        String sql = "INSERT INTO tab_article(authorid,theme,article,time) VALUES (?,?,?,?);";
        //获取pstmt对象
        PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
        //设置参数
        pstmt.setInt(1, Integer.parseInt(pushArticle.getUid()));
        pstmt.setString(2, pushArticle.getTheme());
        pstmt.setString(3, pushArticle.getArticleText());
        pstmt.setString(4, pushArticle.getTime());
        //执行sql
        pstmt.executeUpdate();
        //释放资源
        pstmt.close();
        JDBCUtils.close(JDBCUtils.getConnection());
    }
}
