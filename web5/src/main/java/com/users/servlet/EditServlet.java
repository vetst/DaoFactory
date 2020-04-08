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

@WebServlet("/edit")
public class EditServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String name = req.getParameter("name");
        String surName = req.getParameter("surName");
        req.setAttribute("userId", userId);
        req.setAttribute("name", name);
        req.setAttribute("surName", surName);
        getServletContext().getRequestDispatcher("/edit-user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String userId = req.getParameter("userId");
        String name = req.getParameter("name");
        String surName = req.getParameter("surName");
        try {
            Long id = Long.parseLong(userId);
            userService.updateUser(id, name, surName);
            resp.sendRedirect(req.getContextPath() + "/main");
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
