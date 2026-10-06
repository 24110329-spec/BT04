package com.quanao.controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.quanao.model.Clothing;
import com.quanao.model.Product;
import com.quanao.service.CategoryService;
import com.quanao.service.ClothingService;
import com.quanao.service.ProductService;
import com.quanao.service.impl.CategoryServiceImpl;
import com.quanao.service.impl.ClothingServiceImpl;
import com.quanao.service.impl.ProductServiceImpl;
import com.quanao.util.Constant;

@WebServlet(urlPatterns = { "/home" })
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService cateService = new CategoryServiceImpl();
    private ClothingService clothingService = new ClothingServiceImpl();
    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        String cateIdStr = req.getParameter("cateId");
        List<Clothing> clothingList;
        List<Product> top10Products;

        if (keyword != null && !keyword.trim().isEmpty()) {
            clothingList = clothingService.search(keyword.trim());
            top10Products = productService.search(keyword.trim());
            req.setAttribute("keyword", keyword.trim());
        } else if (cateIdStr != null && !cateIdStr.trim().isEmpty()) {
            int cateId = Integer.parseInt(cateIdStr);
            clothingList = clothingService.getByCategory(cateId);
            top10Products = productService.getByCategory(cateId);
            req.setAttribute("selectedCateId", cateId);
        } else {
            clothingList = clothingService.getAll();
            top10Products = productService.getTop10Newest();
        }

        req.setAttribute("categories", cateService.getAll());
        req.setAttribute("clothingList", clothingList);
        req.setAttribute("top10Products", top10Products);

        RequestDispatcher dispatcher = req.getRequestDispatcher(Constant.Path.HOME);
        dispatcher.forward(req, resp);
    }
}