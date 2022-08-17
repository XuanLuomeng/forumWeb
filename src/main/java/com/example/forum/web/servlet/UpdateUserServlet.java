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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/updateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取并封装注册用户信息
        User user = new User();
        user.setUserid(req.getParameter("userid"));
        user.setEmail(req.getParameter("email"));
        user.setUsername(req.getParameter("username"));
        user.setSex(req.getParameter("sex"));
        user.setTelephone(req.getParameter("telephone"));
        //调用service进行数据库处理修改
        UserService service = new UserServiceImpl();
        boolean flag = false;
        try {
            flag = service.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //若修改成功，则刷新session内容
        if(flag){
            req.getSession().invalidate();
            HttpSession httpSession = req.getSession();
            try {
                httpSession.setAttribute("userInfo",service.seeUserInfo(user.getUserid()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //响应是否修改成功
        new InfoResponse(resp,flag,"修改失败！用户名已存在！");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
