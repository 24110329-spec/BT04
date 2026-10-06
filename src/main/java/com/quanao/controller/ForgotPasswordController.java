package com.quanao.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.quanao.service.UserService;
import com.quanao.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = "/forgot-password")
public class ForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        if (email == null || email.trim().isEmpty()) {
            req.setAttribute("alert", "Vui lòng nhập Email của bạn!");
            req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
            return;
        }

        boolean sent = userService.sendForgotPasswordOTP(email.trim());
        if (sent) {
            req.getSession().setAttribute("resetEmail", email.trim());
            req.getSession().setAttribute("otpNotice", "Mã OTP khôi phục mật khẩu đã gửi đến email " + email + ". Vui lòng kiểm tra và nhập mã!");
            resp.sendRedirect(req.getContextPath() + "/verify-forgot-password");
        } else {
            req.setAttribute("alert", "Email không tồn tại trên hệ thống!");
            req.setAttribute("email", email);
            req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
        }
    }
}
