package com.quanao.controller.manager;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.quanao.service.CategoryService;
import com.quanao.service.ClothingService;
import com.quanao.service.impl.CategoryServiceImpl;
import com.quanao.service.impl.ClothingServiceImpl;
import com.quanao.util.Constant;

@WebServlet(urlPatterns = { "/manager/home" })
public class ManagerHomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService cateService = new CategoryServiceImpl();
    private ClothingService clothingService = new ClothingServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categoryList", cateService.getAll());
        req.setAttribute("clothingList", clothingService.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher(Constant.Path.MANAGER_HOME);
        dispatcher.forward(req, resp);
    }
}