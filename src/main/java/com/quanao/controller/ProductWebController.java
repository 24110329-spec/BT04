package com.quanao.controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.quanao.model.Product;
import com.quanao.service.CategoryService;
import com.quanao.service.ProductService;
import com.quanao.service.impl.CategoryServiceImpl;
import com.quanao.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = "/product")
public class ProductWebController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int PAGE_SIZE = 6;

    private ProductService productService = new ProductServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        int currentPage = 1;
        if (pageStr != null && !pageStr.trim().isEmpty()) {
            try {
                currentPage = Integer.parseInt(pageStr.trim());
                if (currentPage < 1) currentPage = 1;
            } catch (NumberFormatException ignored) {}
        }

        List<Product> products = productService.getPaginated(currentPage, PAGE_SIZE);
        int totalPages = productService.getTotalPages(PAGE_SIZE);
        int totalProducts = productService.countTotalProducts();

        req.setAttribute("productList", products);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("totalProducts", totalProducts);
        req.setAttribute("categories", categoryService.getAll());

        req.getRequestDispatcher("/views/web/product-list.jsp").forward(req, resp);
    }
}
