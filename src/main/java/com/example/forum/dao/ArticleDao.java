package com.example.forum.dao;

import com.example.forum.tools.Article;

import java.sql.SQLException;
import java.util.List;

public interface ArticleDao {
    /**
     * 查询总记录数
     */
    public int findTotalCount(String theme) throws SQLException;
    /**
     * 根据start,pageSize查询当前页的数据集合
     */
    List<Article> findByPage(int start, int pageSize, String theme) throws SQLException;

    /**
     * 查询个人总记录数
     */
    public int findUserTotalCount(String theme,String authorid) throws SQLException;
    /**
     * 根据start,pageSize查询个人当前页的数据集合
     */
    List<Article> findUserByPage(int start, int pageSize, String theme,String authorid) throws SQLException;

    public Article findArticle(int aid) throws SQLException;
}
