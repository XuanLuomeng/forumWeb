package com.example.forum.web.servlet;

import com.example.forum.tools.Article;
import com.example.forum.tools.User;
import com.example.forum.tools.sql.FindAuthorid;
import com.example.forum.tools.sql.InsertArticle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 发布文章
 */
@WebServlet("/pushArticle")
public class PushArticle extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 获取参数，并获取当前时间
         */
        String theme = req.getParameter("theme");
        String article = req.getParameter("article");
        Date pushdate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        /**
         * 获取当前用户uid信息,并用该uid去上传文章
         */
        HttpSession httpSession = req.getSession();
        Object user = httpSession.getAttribute("userInfo");
        User us = (User) user;
        Article articlePush = new Article();
        try {
            FindAuthorid findAuthorid = new FindAuthorid(us.getUserid());
            String authorid = findAuthorid.getUid();
            articlePush.setArticleText(article);
            articlePush.setTheme(theme);
            articlePush.setTime(simpleDateFormat.format(pushdate));
            articlePush.setUid(authorid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            new InsertArticle(articlePush);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
