package com.quanao.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.quanao.service.UserService;
import com.quanao.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = "/verify-forgot-password")
public class VerifyForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String) req.getSession().getAttribute("resetEmail");
        if (email != null) {
            req.setAttribute("email", email);
        }
        req.getRequestDispatcher("/views/verify-forgot-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        String otp = req.getParameter("otp");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");

        if (email == null || email.trim().isEmpty() || otp == null || otp.trim().isEmpty()
                || newPassword == null || newPassword.trim().isEmpty()) {
            req.setAttribute("alert", "Vui lòng nhập đầy đủ thông tin!");
            req.setAttribute("email", email);
            req.getRequestDispatcher("/views/verify-forgot-password.jsp").forward(req, resp);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            req.setAttribute("alert", "Mật khẩu xác nhận không khớp!");
            req.setAttribute("email", email);
            req.getRequestDispatcher("/views/verify-forgot-password.jsp").forward(req, resp);
            return;
        }

        boolean resetSuccess = userService.resetPassword(email.trim(), otp.trim(), newPassword.trim());
        if (resetSuccess) {
            req.getSession().removeAttribute("resetEmail");
            req.getSession().removeAttribute("otpNotice");
            req.getSession().setAttribute("registerSuccess", "Đổi mật khẩu thành công! Bạn có thể đăng nhập bằng mật khẩu mới.");
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.setAttribute("alert", "Mã OTP không chính xác hoặc đã hết hạn!");
            req.setAttribute("email", email);
            req.getRequestDispatcher("/views/verify-forgot-password.jsp").forward(req, resp);
        }
    }
}
