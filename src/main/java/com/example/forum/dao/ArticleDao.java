package com.example.forum.dao;

import com.example.forum.tools.Article;

import java.sql.SQLException;
import java.util.List;

public interface ArticleDao {
    /**
     * 查询总记录数
     */
    public int findTotalCount() throws SQLException;
    /**
     * 根据start,pageSize查询当前页的数据集合
     */
    List<Article> findByPage(int start, int pageSize) throws SQLException;
}
