package com.quanao.controller.admin;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.quanao.service.ProductService;
import com.quanao.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = "/admin/product/delete")
public class AdminProductDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            productService.delete(id);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/products");
    }
}
