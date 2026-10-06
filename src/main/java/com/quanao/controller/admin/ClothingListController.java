package com.quanao.controller.admin;

import java.io.IOException;
import java.util.List;
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

@WebServlet(urlPatterns = { "/admin/clothing/list" })
public class ClothingListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClothingService clothingService = new ClothingServiceImpl();
    private CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        String cateIdStr = req.getParameter("cateId");
        List<Clothing> clothingList;

        if (keyword != null && !keyword.trim().isEmpty()) {
            clothingList = clothingService.search(keyword.trim());
            req.setAttribute("keyword", keyword.trim());
        } else if (cateIdStr != null && !cateIdStr.trim().isEmpty()) {
            int cateId = Integer.parseInt(cateIdStr);
            clothingList = clothingService.getByCategory(cateId);
            req.setAttribute("selectedCateId", cateId);
        } else {
            clothingList = clothingService.getAll();
        }

        req.setAttribute("clothingList", clothingList);
        req.setAttribute("categories", cateService.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/list-clothing.jsp");
        dispatcher.forward(req, resp);
    }
}