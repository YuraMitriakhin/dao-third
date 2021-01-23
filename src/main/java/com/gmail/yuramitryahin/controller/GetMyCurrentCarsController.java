package com.gmail.yuramitryahin.controller;

import com.gmail.yuramitryahin.lib.Injector;
import com.gmail.yuramitryahin.service.CarService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetMyCurrentCarsController extends HttpServlet {
    private static final String DRIVER_ID = "driver_id";
    private static final Injector injector = Injector.getInstance("com.gmail.yuramitryahin");
    private CarService carService = (CarService) injector.getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long driverID = (Long) session.getAttribute(DRIVER_ID);
        req.setAttribute("cars", carService.getAllByDriver(driverID));
        req.getRequestDispatcher("/WEB-INF/views/carsPage.jsp").forward(req, resp);
    }
}
