package com.gmail.yuramitryahin.controllers;

import com.gmail.yuramitryahin.lib.Injector;
import com.gmail.yuramitryahin.modal.Driver;
import com.gmail.yuramitryahin.service.DriverService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationDriverController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.gmail.yuramitryahin");
    private DriverService driverService = (DriverService) injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/addDriver.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("driver_name");
        String licenseNumber = req.getParameter("license number");
        driverService.create(new Driver(name, licenseNumber));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
