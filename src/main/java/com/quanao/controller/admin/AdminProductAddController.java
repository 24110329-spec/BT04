package com.quanao.controller.admin;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.quanao.model.Category;
import com.quanao.model.Product;
import com.quanao.service.CategoryService;
import com.quanao.service.ProductService;
import com.quanao.service.impl.CategoryServiceImpl;
import com.quanao.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = "/admin/product/add")
public class AdminProductAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService = new ProductServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryService.getAll();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/views/admin/add-product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String productName = req.getParameter("productName");
        String cateIdStr = req.getParameter("cateId");
        String priceStr = req.getParameter("price");
        String quantityStr = req.getParameter("quantity");
        String image = req.getParameter("image");
        String description = req.getParameter("description");

        if (productName != null) productName = productName.trim();
        if (image != null) image = image.trim();
        if (description != null) description = description.trim();

        StringBuilder errorBuilder = new StringBuilder();

        if (productName == null || productName.isEmpty()) {
            errorBuilder.append("Tên sản phẩm không được để trống.<br>");
        }

        int cateId = 0;
        Category cat = null;
        try {
            cateId = Integer.parseInt(cateIdStr);
            cat = categoryService.get(cateId);
            if (cat == null) errorBuilder.append("Danh mục được chọn không tồn tại.<br>");
        } catch (Exception e) {
            errorBuilder.append("Vui lòng chọn danh mục hợp lệ.<br>");
        }

        double price = 0.0;
        try {
            price = Double.parseDouble(priceStr);
            if (price <= 0) errorBuilder.append("Giá sản phẩm phải lớn hơn 0.<br>");
        } catch (Exception e) {
            errorBuilder.append("Giá sản phẩm không hợp lệ (phải là số > 0).<br>");
        }

        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity < 0) errorBuilder.append("Số lượng sản phẩm không được nhỏ hơn 0.<br>");
        } catch (Exception e) {
            errorBuilder.append("Số lượng không hợp lệ (phải là số nguyên >= 0).<br>");
        }

        if (errorBuilder.length() > 0) {
            req.setAttribute("categories", categoryService.getAll());
            req.setAttribute("errorMsg", errorBuilder.toString());
            req.setAttribute("productName", productName);
            req.setAttribute("cateId", cateIdStr);
            req.setAttribute("price", priceStr);
            req.setAttribute("quantity", quantityStr);
            req.setAttribute("image", image);
            req.setAttribute("description", description);
            req.getRequestDispatcher("/views/admin/add-product.jsp").forward(req, resp);
            return;
        }

        Product product = new Product();
        product.setProductName(productName);
        product.setCategory(cat);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setImage(image != null ? image : "");
        product.setDescription(description);
        product.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        productService.insert(product);
        resp.sendRedirect(req.getContextPath() + "/admin/products");
    }
}
