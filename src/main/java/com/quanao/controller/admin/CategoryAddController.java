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

import com.quanao.model.Category;
import com.quanao.service.CategoryService;
import com.quanao.service.impl.CategoryServiceImpl;
import com.quanao.util.Constant;

@WebServlet(urlPatterns = { "/admin/category/add" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class CategoryAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".webp");
    private CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/add-category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        if (name != null) name = name.trim();

        StringBuilder errorBuilder = new StringBuilder();
        if (name == null || name.isEmpty()) {
            errorBuilder.append("Tên danh mục không được để trống.<br>");
        } else if (name.length() > 100) {
            errorBuilder.append("Tên danh mục không được vượt quá 100 ký tự.<br>");
        }

        Part filePart = null;
        try {
            filePart = req.getPart("icon");
        } catch (Exception e) {
            errorBuilder.append("File ảnh vượt quá dung lượng cho phép (tối đa 10MB).<br>");
        }

        String iconPath = null;
        if (filePart != null && filePart.getSize() > 0) {
            String submittedFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            int index = submittedFileName.lastIndexOf(".");
            String ext = index >= 0 ? submittedFileName.substring(index).toLowerCase() : ".jpg";

            if (!ALLOWED_EXTENSIONS.contains(ext)) {
                errorBuilder.append("Định dạng file không hợp lệ (chỉ chấp nhận: .jpg, .jpeg, .png, .gif, .webp).<br>");
            } else {
                String fileName = System.currentTimeMillis() + ext;
                File uploadDir = new File(Constant.DIR + "/category");
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                filePart.write(Constant.DIR + "/category/" + fileName);

                // Save to web context uploads directory as well
                try {
                    String contextUploadPath = getServletContext().getRealPath("") + File.separator + "uploads" + File.separator + "category";
                    File contextDir = new File(contextUploadPath);
                    if (!contextDir.exists()) contextDir.mkdirs();
                    org.apache.commons.io.FileUtils.copyFile(new File(Constant.DIR + "/category/" + fileName), new File(contextUploadPath + File.separator + fileName));
                } catch (Exception ignored) {}

                iconPath = "category/" + fileName;
            }
        }

        if (errorBuilder.length() > 0) {
            req.setAttribute("errorMsg", errorBuilder.toString());
            req.setAttribute("name", name);
            req.getRequestDispatcher("/views/admin/add-category.jsp").forward(req, resp);
            return;
        }

        Category category = new Category();
        category.setName(name);
        category.setIcon(iconPath);

        cateService.insert(category);
        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
    }
}