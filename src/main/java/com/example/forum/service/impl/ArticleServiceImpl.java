package com.example.forum.service.impl;

import com.example.forum.dao.ArticleDao;
import com.example.forum.dao.impl.ArticleDaoImpl;
import com.example.forum.service.ArticleService;
import com.example.forum.tools.Article;
import com.example.forum.tools.Page;

import java.sql.SQLException;
import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    private ArticleDao articleDao = new ArticleDaoImpl();
    @Override
    public Page<Article> pageQuery(int currentPage, int pageSize,String theme,String authorid) throws SQLException {
        if(authorid==null) {
            //封装page
            Page<Article> page = new Page<Article>();
            //设置当前页码
            page.setCurrentPage(currentPage);
            //设置每页显示条数
            page.setPageSize(pageSize);
            //设置总记录数
            int totalCount = articleDao.findTotalCount(theme);
            page.setTotalCount(totalCount);
            //设置当前页显示的数据集合
            int start = (currentPage - 1) * pageSize;
            List<Article> list = articleDao.findByPage(start, pageSize, theme);
            page.setList(list);
            //设置总页数
            int totalPage = totalCount % pageSize == 0 ? (totalCount / pageSize) : (totalCount / pageSize + 1);
            page.setTotalPage(totalPage);
            return page;
        }else {
            //封装page
            Page<Article> page = new Page<Article>();
            //设置当前页码
            page.setCurrentPage(currentPage);
            //设置每页显示条数
            page.setPageSize(pageSize);
            //设置总记录数
            int totalCount = articleDao.findUserTotalCount(theme,authorid);
            page.setTotalCount(totalCount);
            //设置当前页显示的数据集合
            int start = (currentPage - 1) * pageSize;
            List<Article> list = articleDao.findUserByPage(start, pageSize, theme,authorid);
            page.setList(list);
            //设置总页数
            int totalPage = totalCount % pageSize == 0 ? (totalCount / pageSize) : (totalCount / pageSize + 1);
            page.setTotalPage(totalPage);
            return page;
        }
    }

    @Override
    public Article articleTest(int aid) throws SQLException {
        //封装article
        Article article = articleDao.findArticle(aid);
        return article;
    }
}
