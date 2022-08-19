package com.example.forum.tools.sql;

import com.example.forum.tools.Article;
import com.example.forum.tools.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 修改文章内容
 */
public class UpdateArticle {
    public UpdateArticle(Article article) throws SQLException {
        //定义sql
        String sql = "UPDATE tab_article SET theme = ?, article = ? WHERE aid = ?;";
        //获取pstmt对象
        PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
        //设置参数
        pstmt.setString(1, article.getTheme());
        pstmt.setString(2, article.getArticleText());
        pstmt.setInt(3, article.getAid());
        //执行sql
        pstmt.executeUpdate();
        //释放资源
        pstmt.close();
        JDBCUtils.close(JDBCUtils.getConnection());
    }
}
