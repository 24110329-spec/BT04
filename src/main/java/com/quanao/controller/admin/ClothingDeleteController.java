package com.quanao.controller.admin;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.quanao.service.ClothingService;
import com.quanao.service.impl.ClothingServiceImpl;

@WebServlet(urlPatterns = { "/admin/clothing/delete" })
public class ClothingDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClothingService clothingService = new ClothingServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            clothingService.delete(id);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/clothing/list");
    }
}