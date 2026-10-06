package com.quanao.controller.admin;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.quanao.model.Product;
import com.quanao.service.ProductService;
import com.quanao.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = "/admin/products")
public class AdminProductListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.getAll();
        req.setAttribute("productList", products);
        req.getRequestDispatcher("/views/admin/list-product.jsp").forward(req, resp);
    }
}
