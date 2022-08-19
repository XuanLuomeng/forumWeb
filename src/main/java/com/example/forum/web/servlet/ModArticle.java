package com.example.forum.web.servlet;

import com.example.forum.tools.Article;
import com.example.forum.tools.sql.UpdateArticle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 修改文章内容，以aid找到文章数据库并修改主题和内容（发布日期是不会修改的）
 */
@WebServlet("/modArticle")
public class ModArticle extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置参数
        Article article = new Article();
        article.setAid(Integer.parseInt(req.getParameter("aid")));
        article.setTheme(req.getParameter("theme"));
        article.setArticleText(req.getParameter("article"));
        try {
            new UpdateArticle(article);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
