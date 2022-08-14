package com.example.forum.dao.impl;

import com.example.forum.dao.ArticleDao;
import com.example.forum.tools.Article;
import com.example.forum.tools.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoImpl implements ArticleDao {
    @Override
    public int findTotalCount() throws SQLException {
        //定义sql
        String sql = "select count(aid) from tab_article;";
        //获取statement对象
        Statement stmt = JDBCUtils.getConnection().createStatement();
        //执行sql
        ResultSet rs = stmt.executeQuery(sql);
        int pageCount = 0;
        while(rs.next()){
            pageCount = rs.getInt(1);
        }
        return pageCount;
    }

    @Override
    public List<Article> findByPage(int start, int pageSize) throws SQLException {
        //定义sql
        String sql = "select username,theme,article,time from tab_user,tab_article WHERE uid=authorid limit ? , ?;";
        //获取pstmt对象
        PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
        //设置参数
        pstmt.setInt(1, start);
        pstmt.setInt(2, pageSize);
        //执行sql
        ResultSet rs = pstmt.executeQuery();
        //封装数据
        List<Article> articles = new ArrayList<>();
        while (rs.next()){
            Article article = new Article();
            article.setAuthor(rs.getString(1));
            article.setTheme(rs.getString(2));
            article.setArticleText(rs.getString(3));
            article.setTime(rs.getString(4));
            articles.add(article);
        }
        return articles;
    }
}
