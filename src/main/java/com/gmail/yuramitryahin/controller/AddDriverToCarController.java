package com.gmail.yuramitryahin.controller;

import com.gmail.yuramitryahin.lib.Injector;
import com.gmail.yuramitryahin.modal.Car;
import com.gmail.yuramitryahin.modal.Driver;
import com.gmail.yuramitryahin.service.CarService;
import com.gmail.yuramitryahin.service.DriverService;

import java.io.IOException;
import java.util.NoSuchElementException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddDriverToCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.gmail.yuramitryahin");
    private final CarService carService = (CarService) injector.getInstance(CarService.class);
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/addDriverToCar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long driverId = Long.valueOf(req.getParameter("driver_id"));
        Long carId = Long.valueOf(req.getParameter("car_id"));
        Car car = carService.get(carId);
        Driver driver = driverService.get(driverId);
        car.getDrivers().add(driver);
        carService.update(car);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
