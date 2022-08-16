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
    public int findTotalCount(String theme) throws SQLException {
        //定义sql
        String sql;
        ResultSet rs;
        if(theme!=null&&theme.length()>0){
            sql = "select count(aid) from tab_article where theme like ?;";
            //获取pstmt对象
            PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
            //设置参数
            pstmt.setString(1, theme);
            //执行sql
            rs = pstmt.executeQuery();
        }else {
            sql = "select count(aid) from tab_article;";
            //获取statement对象
            Statement stmt = JDBCUtils.getConnection().createStatement();
            //执行sql
            rs = stmt.executeQuery(sql);
        }
        int pageCount = 0;
        while(rs.next()){
            pageCount = rs.getInt(1);
        }
        return pageCount;
    }

    @Override
    public List<Article> findByPage(int start, int pageSize,String theme) throws SQLException {
        //定义sql
        String sql;
        ResultSet rs;
        if(theme!=null&&theme.length()>0){
            sql = "select username,theme,article,time from tab_user,tab_article WHERE uid=authorid and theme like ? limit ? , ?;";
            //获取pstmt对象
            PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
            //设置参数
            pstmt.setString(1,theme);
            pstmt.setInt(2, start);
            pstmt.setInt(3, pageSize);
            //执行sql
            rs = pstmt.executeQuery();
        }else {
            sql = "select username,theme,article,time,aid from tab_user,tab_article WHERE uid=authorid limit ? , ?;";
            //获取pstmt对象
            PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
            //设置参数
            pstmt.setInt(1, start);
            pstmt.setInt(2, pageSize);
            //执行sql
            rs = pstmt.executeQuery();
        }
        //封装数据
        List<Article> articles = new ArrayList<>();
        while (rs.next()){
            Article article = new Article();
            article.setAuthor(rs.getString(1));
            article.setTheme(rs.getString(2));
            article.setArticleText(rs.getString(3));
            article.setTime(rs.getString(4));
            article.setAid(rs.getInt(5));
            articles.add(article);
        }
        return articles;
    }

    @Override
    public Article findArticle(int aid) throws SQLException {
        //定义sql
        String sql = "select username,theme,article,time from tab_user,tab_article WHERE uid=authorid and aid=?;";
        //获取pstmt对象
        PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
        //设置参数
        pstmt.setInt(1, aid);
        //执行sql
        ResultSet rs = pstmt.executeQuery();
        Article article = new Article();
        if(rs.next()){
            article.setAuthor(rs.getString(1));
            article.setTheme(rs.getString(2));
            article.setArticleText(rs.getString(3));
            article.setTime(rs.getString(4));
        }
        return article;
    }
}
