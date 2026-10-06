package com.quanao.controller.admin;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.quanao.service.UserService;
import com.quanao.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = { "/admin/user/list" })
public class UserListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("userList", userService.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/list-user.jsp");
        dispatcher.forward(req, resp);
    }
}