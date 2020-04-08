package com.users.servlet;

import com.users.exception.DBException;
import com.users.service.UserService;
import com.users.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/main")
public class GeneralServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List userList = userService.getAllUser();
            req.setAttribute("userList", userList);
            getServletContext().getRequestDispatcher("/main.jsp").forward(req, resp);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        String surName = req.getParameter("surName");
        try {
            userService.addUser(name, surName);
            resp.sendRedirect(req.getContextPath() + "/main");
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
