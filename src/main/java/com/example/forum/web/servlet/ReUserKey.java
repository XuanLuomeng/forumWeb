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

@WebServlet("/reUserKey")
public class ReUserKey extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        Object user = httpSession.getAttribute("userInfo");
        User us = (User) user;
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword2");
        /**
         * 若旧密码正确且新密码与旧密码相同，则修改失败并提示用户新旧密码不能一致
         */
        if(us.getPassword().equals(oldPassword)){
            if(us.getPassword().equals(newPassword)) {
                new InfoResponse(resp, false, "修改失败，新旧密码不能一致！");
            }else {
                UserService service = new UserServiceImpl();
                try {
                    service.reKey(us.getUserid(),newPassword);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                new InfoResponse(resp,true,"");
            }
        }else {
            new InfoResponse(resp, false, "修改失败，旧密码不正确！");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
