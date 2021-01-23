package com.gmail.yuramitryahin.controller;

import com.gmail.yuramitryahin.lib.Injector;
import com.gmail.yuramitryahin.modal.Car;
import com.gmail.yuramitryahin.modal.Manufacturer;
import com.gmail.yuramitryahin.service.CarService;
import com.gmail.yuramitryahin.service.ManufacturerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.gmail.yuramitryahin");
    private CarService carService = (CarService) injector.getInstance(CarService.class);
    private ManufacturerService manufacturerService = (ManufacturerService) injector
            .getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/addCar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String model = req.getParameter("model");
        Long idManufacturer = Long.parseLong(req.getParameter("id_manufacturer"));
        Manufacturer manufacturer = manufacturerService.get(idManufacturer);
        carService.create(new Car(model, manufacturer));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
