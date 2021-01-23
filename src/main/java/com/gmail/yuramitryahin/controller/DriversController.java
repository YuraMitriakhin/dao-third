package com.gmail.yuramitryahin.controller;

import com.gmail.yuramitryahin.lib.Injector;
import com.gmail.yuramitryahin.service.DriverService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DriversController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.gmail.yuramitryahin");
    private DriverService driverService = (DriverService) injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("drivers", driverService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/driversPage.jsp").forward(req, resp);
    }

}
