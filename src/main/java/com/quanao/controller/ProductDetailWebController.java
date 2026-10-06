package com.quanao.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.quanao.model.Product;
import com.quanao.service.ProductService;
import com.quanao.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = { "/product/detail", "/product-detail" })
public class ProductDetailWebController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr.trim());
                Product product = productService.getById(id);
                if (product != null) {
                    req.setAttribute("product", product);
                    req.getRequestDispatcher("/views/web/product-detail.jsp").forward(req, resp);
                    return;
                }
            } catch (NumberFormatException ignored) {}
        }
        resp.sendRedirect(req.getContextPath() + "/product");
    }
}
