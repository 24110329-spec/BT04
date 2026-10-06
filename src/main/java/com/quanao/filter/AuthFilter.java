package com.quanao.filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.quanao.model.User;

@WebFilter(urlPatterns = {"/admin/*", "/manager/*"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("account") : null;

        String path = req.getRequestURI().substring(req.getContextPath().length());

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Kiem tra quyen Admin
        if (path.startsWith("/admin")) {
            if (user.getRoleid() != 1) {
                // Khong phai Admin -> chuyen ve /waiting de dieu huong dung vai tro
                resp.sendRedirect(req.getContextPath() + "/waiting");
                return;
            }
        }

        // Kiem tra quyen Manager
        if (path.startsWith("/manager")) {
            if (user.getRoleid() != 1 && user.getRoleid() != 2) {
                // Khong phai Admin hoac Manager -> chuyen ve trang nguoi dung
                resp.sendRedirect(req.getContextPath() + "/waiting");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}