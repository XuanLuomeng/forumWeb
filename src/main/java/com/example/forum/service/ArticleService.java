package com.example.forum.service;


import com.example.forum.tools.Article;
import com.example.forum.tools.Page;

import java.sql.SQLException;

public interface ArticleService {
    public Page<Article> pageQuery(int currentPage, int pageSize,String theme,String authorid) throws SQLException;
    public Article articleTest(int aid) throws SQLException;
}
