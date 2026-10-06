package com.quanao.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.quanao.service.UserService;
import com.quanao.service.impl.UserServiceImpl;
import com.quanao.util.Constant;

@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            resp.sendRedirect(req.getContextPath() + "/waiting");
            return;
        }
        req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");

        if (username != null) username = username.trim();
        if (password != null) password = password.trim();
        if (email != null) email = email.trim();
        if (fullname != null) fullname = fullname.trim();
        if (phone != null) phone = phone.trim();

        // Server-side validation
        StringBuilder errorBuilder = new StringBuilder();

        if (username == null || username.isEmpty()) {
            errorBuilder.append("Tên đăng nhập không được để trống.<br>");
        } else if (username.length() < 3) {
            errorBuilder.append("Tên đăng nhập phải có ít nhất 3 ký tự.<br>");
        } else if (!username.matches("^[a-zA-Z0-9_]+$")) {
            errorBuilder.append("Tên đăng nhập chỉ chứa chữ cái, chữ số và dấu gạch dưới.<br>");
        } else if (service.checkExistUsername(username)) {
            errorBuilder.append("Tài khoản '").append(username).append("' đã tồn tại.<br>");
        }

        if (fullname == null || fullname.isEmpty()) {
            errorBuilder.append("Họ và tên không được để trống.<br>");
        }

        if (email == null || email.isEmpty()) {
            errorBuilder.append("Email không được để trống.<br>");
        } else if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            errorBuilder.append("Định dạng email không hợp lệ.<br>");
        } else if (service.checkExistEmail(email)) {
            errorBuilder.append("Email '").append(email).append("' đã tồn tại.<br>");
        }

        if (phone != null && !phone.isEmpty()) {
            if (!phone.matches("^0\\d{9,10}$")) {
                errorBuilder.append("Số điện thoại không hợp lệ (10-11 chữ số bắt đầu bằng 0).<br>");
            } else if (service.checkExistPhone(phone)) {
                errorBuilder.append("Số điện thoại '").append(phone).append("' đã tồn tại.<br>");
            }
        }

        if (password == null || password.isEmpty()) {
            errorBuilder.append("Mật khẩu không được để trống.<br>");
        } else if (password.length() < 6) {
            errorBuilder.append("Mật khẩu phải có ít nhất 6 ký tự.<br>");
        }

        if (errorBuilder.length() > 0) {
            req.setAttribute("alert", errorBuilder.toString());
            req.setAttribute("username", username);
            req.setAttribute("fullname", fullname);
            req.setAttribute("email", email);
            req.setAttribute("phone", phone);
            req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
            return;
        }

        String otp = service.registerWithOTP(username, password, email, fullname, phone);
        if (otp != null) {
            req.getSession().setAttribute("verifyEmail", email);
            req.getSession().setAttribute("otpNotice", "Mã OTP đã được gửi đến email " + email + ". Vui lòng nhập mã OTP để kích hoạt tài khoản!");
            resp.sendRedirect(req.getContextPath() + "/verify-otp");
        } else {
            req.setAttribute("alert", "Lỗi hệ thống trong quá trình đăng ký!");
            req.setAttribute("username", username);
            req.setAttribute("fullname", fullname);
            req.setAttribute("email", email);
            req.setAttribute("phone", phone);
            req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
        }
    }
}