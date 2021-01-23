package com.gmail.yuramitryahin.controller;

import com.gmail.yuramitryahin.lib.Injector;
import com.gmail.yuramitryahin.modal.Manufacturer;
import com.gmail.yuramitryahin.service.ManufacturerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationManufacturerController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.gmail.yuramitryahin");
    private ManufacturerService manufacturerService = (ManufacturerService) injector
            .getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/addManufacturer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String country = req.getParameter("country");
        manufacturerService.create(new Manufacturer(name, country));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
