package com.example.forum.web.servlet;

import com.example.forum.service.ArticleService;
import com.example.forum.service.impl.ArticleServiceImpl;
import com.example.forum.tools.Article;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 文章内容展示
 */
@WebServlet("/articleTestServlet")
public class ArticleTestServlet extends HttpServlet {

    private ArticleService articleService = new ArticleServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 获取参数
         */
        String aid = req.getParameter("aid");
        /**
         * 利用参数查询文章相关信息
         */
        Article article = new Article();
        try {
            article = articleService.articleTest(Integer.parseInt(aid));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /**
         * 将article序列化为json返回给客户端
         */
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(article);
        //设置content-type防止乱码问题
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
