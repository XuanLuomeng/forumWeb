package com.example.forum.web.servlet;

import com.example.forum.service.ArticleService;
import com.example.forum.service.impl.ArticleServiceImpl;
import com.example.forum.tools.Article;
import com.example.forum.tools.Page;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/articleServlet")
public class ArticleServlet extends HttpServlet {
    private ArticleService articleService = new ArticleServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 获取请求参数
         */
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");
        String theme = "%"+req.getParameter("theme")+"%";

        /**
         * 处理参数
         */
        int currentPage = 0;
        if(currentPageStr!=null&&currentPageStr.length()>0){
            currentPage = Integer.parseInt(currentPageStr);
        }else {
            currentPage = 1;
        }
        int pageSize = 0;
        if(pageSizeStr!=null&&pageSizeStr.length()>0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else {
            pageSize = 10;
        }

        /**
         * 查询Page对象
         */
        Page<Article> page = null;
        try {
            page = articleService.pageQuery(currentPage,pageSize,theme);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /**
         * 将page序列化为json返回给客户端
         */
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(page);
        //设置content-type防止乱码问题
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
