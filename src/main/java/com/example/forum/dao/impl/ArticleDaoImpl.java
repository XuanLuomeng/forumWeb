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
    /**
     * 返回页码总数(包含搜索功能)
     * @param theme
     * @return
     * @throws SQLException
     */
    @Override
    public int findTotalCount(String theme) throws SQLException {
        //定义sql
        String sql;
        ResultSet rs;
        if(theme!=null&&theme.length()>0){
            /**
             * 查询功能的文章总数
             */
            sql = "select count(aid) from tab_article where theme like ?;";
            //获取pstmt对象
            PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
            //设置参数
            pstmt.setString(1, theme);
            //执行sql
            rs = pstmt.executeQuery();
        }else {
            /**
             * 所有文章总数
             */
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

    /**
     * 分页查询获取分页信息(包含搜索功能)
     * @param start
     * @param pageSize
     * @param theme
     * @return
     * @throws SQLException
     */
    @Override
    public List<Article> findByPage(int start, int pageSize,String theme) throws SQLException {
        //定义sql
        String sql;
        ResultSet rs;
        if(theme!=null&&theme.length()>0){
            /**
             * 查询功能的所有文章的分页功能
             */
            sql = "select username,theme,article,time,aid from tab_user,tab_article WHERE uid=authorid and theme like ? limit ? , ?;";
            //获取pstmt对象
            PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
            //设置参数
            pstmt.setString(1,theme);
            pstmt.setInt(2, start);
            pstmt.setInt(3, pageSize);
            //执行sql
            rs = pstmt.executeQuery();
        }else {
            /**
             * 所有文章的分页功能
             */
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

    /**
     * 个人文章总数等信息(包含搜索功能)
     * @param theme
     * @param authorid
     * @return
     * @throws SQLException
     */
    @Override
    public int findUserTotalCount(String theme, String authorid) throws SQLException {
        //定义sql
        String sql;
        ResultSet rs;
        if(theme!=null&&theme.length()>0){
            /**
             * 查询功能的个人总文章数
             */
            sql = "select count(aid) from tab_article where authorid = ? and theme like ?;";
            //获取pstmt对象
            PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
            //设置参数
            pstmt.setString(2, theme);
            pstmt.setString(1,authorid);
            //执行sql
            rs = pstmt.executeQuery();
        }else {
            /**
             * 所有的个人总文章数
             */
            sql = "select count(aid) from tab_article where authorid = ?;";
            //获取pstmt对象
            PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
            //设置参数
            pstmt.setString(1,authorid);
            //执行sql
            rs = pstmt.executeQuery();
        }
        int pageCount = 0;
        while(rs.next()){
            pageCount = rs.getInt(1);
        }
        return pageCount;
    }

    /**
     * 分页查询个人分页信息（包含搜索功能）
     * @param start
     * @param pageSize
     * @param theme
     * @param authorid
     * @return
     * @throws SQLException
     */
    @Override
    public List<Article> findUserByPage(int start, int pageSize, String theme, String authorid) throws SQLException {
        //定义sql
        String sql;
        ResultSet rs;
        if(theme!=null&&theme.length()>0){
            /**
             * 查询功能的个人文章分页
             */
            sql = "select username,theme,article,time,aid from tab_user,tab_article WHERE uid=authorid and authorid = ? and theme like ? limit ? , ?;";
            //获取pstmt对象
            PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
            //设置参数
            pstmt.setString(1,authorid);
            pstmt.setString(2,theme);
            pstmt.setInt(3, start);
            pstmt.setInt(4, pageSize);
            //执行sql
            rs = pstmt.executeQuery();
        }else {
            /**
             * 所有个人文章分页
             */
            sql = "select username,theme,article,time,aid from tab_user,tab_article WHERE uid=authorid and authorid = ? limit ? , ?;";
            //获取pstmt对象
            PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
            //设置参数
            pstmt.setString(1,authorid);
            pstmt.setInt(2, start);
            pstmt.setInt(3, pageSize);
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

    /**
     * 以aid查询文章信息
     * @param aid
     * @return
     * @throws SQLException
     */
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
