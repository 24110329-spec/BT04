package com.quanao.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import com.quanao.model.Clothing;
import com.quanao.service.CategoryService;
import com.quanao.service.ClothingService;
import com.quanao.service.impl.CategoryServiceImpl;
import com.quanao.service.impl.ClothingServiceImpl;
import com.quanao.util.Constant;

@WebServlet(urlPatterns = { "/admin/clothing/add" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class ClothingAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".webp");

    private ClothingService clothingService = new ClothingServiceImpl();
    private CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", cateService.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/add-clothing.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String clothingName = req.getParameter("clothingName");
        String cateIdStr = req.getParameter("cateId");
        String size = req.getParameter("size");
        String color = req.getParameter("color");
        String quantityStr = req.getParameter("quantity");
        String priceStr = req.getParameter("price");
        String description = req.getParameter("description");

        if (clothingName != null) clothingName = clothingName.trim();
        if (size != null) size = size.trim();
        if (color != null) color = color.trim();
        if (description != null) description = description.trim();

        StringBuilder errorBuilder = new StringBuilder();

        if (clothingName == null || clothingName.isEmpty()) {
            errorBuilder.append("Tên quần áo không được để trống.<br>");
        }

        int cateId = 0;
        try {
            cateId = Integer.parseInt(cateIdStr);
            if (cateId <= 0) errorBuilder.append("Vui lòng chọn danh mục hợp lệ.<br>");
        } catch (Exception e) {
            errorBuilder.append("Vui lòng chọn danh mục hợp lệ.<br>");
        }

        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity < 0) errorBuilder.append("Số lượng sản phẩm không được nhỏ hơn 0.<br>");
        } catch (Exception e) {
            errorBuilder.append("Số lượng không hợp lệ (phải là số nguyên >= 0).<br>");
        }

        double price = 0.0;
        try {
            price = Double.parseDouble(priceStr);
            if (price <= 0) errorBuilder.append("Giá sản phẩm phải lớn hơn 0.<br>");
        } catch (Exception e) {
            errorBuilder.append("Giá sản phẩm không hợp lệ (phải là số > 0).<br>");
        }

        Part filePart = null;
        try {
            filePart = req.getPart("image");
        } catch (Exception e) {
            errorBuilder.append("Kích thước file ảnh vượt quá giới hạn 10MB.<br>");
        }

        String imagePath = null;
        if (filePart != null && filePart.getSize() > 0) {
            String submittedFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            int index = submittedFileName.lastIndexOf(".");
            String ext = index >= 0 ? submittedFileName.substring(index).toLowerCase() : ".jpg";

            if (!ALLOWED_EXTENSIONS.contains(ext)) {
                errorBuilder.append("Định dạng ảnh không hỗ trợ (chỉ chấp nhận: .jpg, .jpeg, .png, .gif, .webp).<br>");
            } else {
                String fileName = System.currentTimeMillis() + ext;
                File uploadDir = new File(Constant.DIR + "/clothing");
                if (!uploadDir.exists()) uploadDir.mkdirs();
                filePart.write(Constant.DIR + "/clothing/" + fileName);

                try {
                    String contextUploadPath = getServletContext().getRealPath("") + File.separator + "uploads" + File.separator + "clothing";
                    File contextDir = new File(contextUploadPath);
                    if (!contextDir.exists()) contextDir.mkdirs();
                    org.apache.commons.io.FileUtils.copyFile(new File(Constant.DIR + "/clothing/" + fileName), new File(contextUploadPath + File.separator + fileName));
                } catch (Exception ignored) {}

                imagePath = "clothing/" + fileName;
            }
        }

        if (errorBuilder.length() > 0) {
            req.setAttribute("categories", cateService.getAll());
            req.setAttribute("errorMsg", errorBuilder.toString());
            req.setAttribute("clothingName", clothingName);
            req.setAttribute("cateId", cateIdStr);
            req.setAttribute("size", size);
            req.setAttribute("color", color);
            req.setAttribute("quantity", quantityStr);
            req.setAttribute("price", priceStr);
            req.setAttribute("description", description);
            req.getRequestDispatcher("/views/admin/add-clothing.jsp").forward(req, resp);
            return;
        }

        Clothing c = new Clothing();
        c.setClothingName(clothingName);
        c.setCateId(cateId);
        c.setSize(size);
        c.setColor(color);
        c.setQuantity(quantity);
        c.setPrice(price);
        c.setDescription(description);
        c.setImage(imagePath);

        clothingService.insert(c);
        resp.sendRedirect(req.getContextPath() + "/admin/clothing/list");
    }
}