package com.quanao.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.quanao.model.User;
import com.quanao.service.UserService;
import com.quanao.service.impl.UserServiceImpl;
import com.quanao.util.Constant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet(urlPatterns = "/profile")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 5 * 1024 * 1024,      // 5MB
        maxRequestSize = 10 * 1024 * 1024   // 10MB
)
public class ProfileController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".webp");

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User account = (session != null) ? (User) session.getAttribute("account") : null;

        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        User user = userService.getById(account.getId());
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setAttribute("user", user);
        request.getRequestDispatcher("/views/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        User account = (session != null) ? (User) session.getAttribute("account") : null;

        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int userId = account.getId();
        User user = userService.getById(userId);

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phone");

        if (fullName != null) fullName = fullName.trim();
        if (phone != null) phone = phone.trim();

        // Server-side Validation
        StringBuilder errorBuilder = new StringBuilder();

        if (fullName == null || fullName.isEmpty()) {
            errorBuilder.append("Họ và tên không được để trống.<br>");
        } else if (fullName.length() > 100) {
            errorBuilder.append("Họ và tên không được vượt quá 100 ký tự.<br>");
        }

        if (phone == null || phone.isEmpty()) {
            errorBuilder.append("Số điện thoại không được để trống.<br>");
        } else if (!phone.matches("^0\\d{9,10}$")) {
            errorBuilder.append("Số điện thoại không hợp lệ (phải gồm 10-11 chữ số bắt đầu bằng 0).<br>");
        } else if (userService.checkExistPhoneExceptUser(phone, userId)) {
            errorBuilder.append("Số điện thoại đã được đăng ký bởi tài khoản khác.<br>");
        }

        Part imagePart = null;
        try {
            imagePart = request.getPart("image");
        } catch (Exception e) {
            errorBuilder.append("Kích thước file ảnh vượt quá giới hạn 5MB.<br>");
        }

        String newAvatarPath = null;
        if (imagePart != null && imagePart.getSize() > 0) {
            String submittedFileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
            String ext = "";
            int lastDot = submittedFileName.lastIndexOf(".");
            if (lastDot >= 0) {
                ext = submittedFileName.substring(lastDot).toLowerCase();
            }

            if (!ALLOWED_EXTENSIONS.contains(ext)) {
                errorBuilder.append("Định dạng file ảnh không hỗ trợ (chấp nhận: .jpg, .jpeg, .png, .gif, .webp).<br>");
            } else {
                String newFileName = "user_" + userId + "_" + System.currentTimeMillis() + ext;
                
                // Save to webapp real path uploads
                String contextUploadPath = getServletContext().getRealPath("") + File.separator + "uploads" + File.separator + "users";
                File contextDir = new File(contextUploadPath);
                if (!contextDir.exists()) {
                    contextDir.mkdirs();
                }
                String contextFilePath = contextUploadPath + File.separator + newFileName;
                imagePart.write(contextFilePath);

                // Optionally save to Constant.DIR as backup
                try {
                    File extDir = new File(Constant.DIR + File.separator + "users");
                    if (!extDir.exists()) {
                        extDir.mkdirs();
                    }
                    org.apache.commons.io.FileUtils.copyFile(new File(contextFilePath), new File(extDir + File.separator + newFileName));
                } catch (Exception ignored) {}

                newAvatarPath = "uploads/users/" + newFileName;
            }
        }

        if (errorBuilder.length() > 0) {
            // Put transient input values back for re-rendering form
            user.setFullName(fullName);
            user.setPhone(phone);
            request.setAttribute("user", user);
            request.setAttribute("errorMsg", errorBuilder.toString());
            request.getRequestDispatcher("/views/profile.jsp").forward(request, response);
            return;
        }

        // Apply updates via JPA
        user.setFullName(fullName);
        user.setPhone(phone);
        if (newAvatarPath != null) {
            user.setAvatar(newAvatarPath);
        }

        userService.update(user);

        // Refresh session
        session.setAttribute("account", user);

        response.sendRedirect(request.getContextPath() + "/profile?success=1");
    }
}