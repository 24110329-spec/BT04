package com.quanao.controller;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.quanao.model.Clothing;
import com.quanao.service.CategoryService;
import com.quanao.service.ClothingService;
import com.quanao.service.impl.CategoryServiceImpl;
import com.quanao.service.impl.ClothingServiceImpl;

@WebServlet(urlPatterns = { "/clothing/detail" })
public class ClothingDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClothingService clothingService = new ClothingServiceImpl();
    private CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            Clothing clothing = clothingService.get(id);
            req.setAttribute("clothing", clothing);
            if (clothing != null) {
                req.setAttribute("relatedClothing", clothingService.getByCategory(clothing.getCateId()));
            }
        }
        req.setAttribute("categories", cateService.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/web/clothing-detail.jsp");
        dispatcher.forward(req, resp);
    }
}