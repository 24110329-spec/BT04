package com.quanao.controller.admin;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.quanao.service.CategoryService;
import com.quanao.service.ClothingService;
import com.quanao.service.UserService;
import com.quanao.service.impl.CategoryServiceImpl;
import com.quanao.service.impl.ClothingServiceImpl;
import com.quanao.service.impl.UserServiceImpl;
import com.quanao.util.Constant;

@WebServlet(urlPatterns = { "/admin/home" })
public class AdminHomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService cateService = new CategoryServiceImpl();
    private ClothingService clothingService = new ClothingServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categoryCount", cateService.getAll().size());
        req.setAttribute("clothingCount", clothingService.getAll().size());
        req.setAttribute("userCount", userService.getAll().size());

        RequestDispatcher dispatcher = req.getRequestDispatcher(Constant.Path.ADMIN_HOME);
        dispatcher.forward(req, resp);
    }
}