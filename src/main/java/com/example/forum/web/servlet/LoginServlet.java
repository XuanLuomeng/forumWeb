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

/**
 * 完成对传入服务器的账号与密码封装并对照数据库确认账号与密码是否正确且返回处理结果操作
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取并封装注册用户信息
        User user = new User();
        user.setUserid(req.getParameter("userid"));
        user.setPassword(req.getParameter("password"));
        //调用service进行数据库处理注册
        UserService service = new UserServiceImpl();
        boolean flag = false;
        try {
            flag = service.login(user.getUserid(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //响应是否登陆成功
        new InfoResponse(resp,flag,"登录失败！账号或密码错误！");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
