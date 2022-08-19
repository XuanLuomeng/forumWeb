package com.example.forum.web.servlet;

import com.example.forum.service.UserService;
import com.example.forum.service.impl.UserServiceImpl;
import com.example.forum.tools.InfoResponse;
import com.example.forum.tools.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 完成注册的基本数据封装与返回数据库操作信息封装
 */
@WebServlet("/registUserServlet")
public class RegistUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取并封装注册日期
        Date regdate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //获取并封装注册用户信息
        User user = new User();
        user.setUserid(req.getParameter("userid"));
        user.setPassword(req.getParameter("password2"));
        user.setEmail(req.getParameter("email"));
        user.setUsername(req.getParameter("username"));
        user.setSex(req.getParameter("sex"));
        user.setTelephone(req.getParameter("telephone"));
        user.setRegdate(simpleDateFormat.format(regdate));
        //调用service进行数据库处理注册
        UserService service = new UserServiceImpl();
        boolean flag = false;
        try {
            flag = service.regist(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //响应是否注册成功
        new InfoResponse(resp,flag,"注册失败！账号/用户名已存在！");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
