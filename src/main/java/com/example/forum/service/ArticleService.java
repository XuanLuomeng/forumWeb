package com.example.forum.service;


import com.example.forum.tools.Article;
import com.example.forum.tools.Page;

import java.sql.SQLException;

public interface ArticleService {
    public Page<Article> pageQuery(int currentPage, int pageSize) throws SQLException;
}
