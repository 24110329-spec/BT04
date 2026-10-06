package com.quanao.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.quanao.model.Clothing;
import com.quanao.service.ClothingService;
import com.quanao.service.impl.ClothingServiceImpl;

@WebServlet("/clothing")
public class ClothingController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ClothingService service =
            new ClothingServiceImpl();


    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action =
                request.getParameter("action");


        if (action == null) {

            action = "list";
        }


        // =========================
        // HIỂN THỊ DANH SÁCH
        // =========================

        if (action.equals("list")) {

            request.setAttribute(
                    "list",
                    service.getAll());

            request.getRequestDispatcher(
                    "/views/clothing/list.jsp")
                    .forward(request, response);
        }


        // =========================
        // XÓA
        // =========================

        else if (action.equals("delete")) {

            int id = Integer.parseInt(
                    request.getParameter("id"));

            service.delete(id);

            response.sendRedirect(
                    request.getContextPath()
                    + "/clothing");
        }


        // =========================
        // HIỂN THỊ FORM SỬA
        // =========================

        else if (action.equals("edit")) {

            int id = Integer.parseInt(
                    request.getParameter("id"));

            Clothing clothing =
                    service.getById(id);

            request.setAttribute(
                    "clothing",
                    clothing);

            request.getRequestDispatcher(
                    "/views/clothing/edit.jsp")
                    .forward(request, response);
        }
    }


    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");


        String action =
                request.getParameter("action");


        // =========================
        // THÊM
        // =========================

        if ("add".equals(action)) {

            Clothing c =
                    new Clothing();


            c.setClothingName(
                    request.getParameter(
                            "clothingName"));


            c.setCateId(
                    Integer.parseInt(
                            request.getParameter(
                                    "cateId")));


            c.setSize(
                    request.getParameter("size"));


            c.setColor(
                    request.getParameter("color"));


            c.setQuantity(
                    Integer.parseInt(
                            request.getParameter(
                                    "quantity")));


            c.setPrice(
                    Double.parseDouble(
                            request.getParameter(
                                    "price")));


            c.setImage(
                    request.getParameter("image"));


            c.setDescription(
                    request.getParameter(
                            "description"));


            service.insert(c);
        }


        // =========================
        // CẬP NHẬT
        // =========================

        else if ("update".equals(action)) {

            Clothing c =
                    new Clothing();


            c.setClothingId(
                    Integer.parseInt(
                            request.getParameter(
                                    "clothingId")));


            c.setClothingName(
                    request.getParameter(
                            "clothingName"));


            c.setCateId(
                    Integer.parseInt(
                            request.getParameter(
                                    "cateId")));


            c.setSize(
                    request.getParameter("size"));


            c.setColor(
                    request.getParameter("color"));


            c.setQuantity(
                    Integer.parseInt(
                            request.getParameter(
                                    "quantity")));


            c.setPrice(
                    Double.parseDouble(
                            request.getParameter(
                                    "price")));


            c.setImage(
                    request.getParameter("image"));


            c.setDescription(
                    request.getParameter(
                            "description"));


            service.update(c);
        }


        response.sendRedirect(
                request.getContextPath()
                + "/clothing");
    }
}