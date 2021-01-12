package com.gmail.yuramitryahin;

import com.gmail.yuramitryahin.lib.Injector;
import com.gmail.yuramitryahin.modal.Car;
import com.gmail.yuramitryahin.modal.Driver;
import com.gmail.yuramitryahin.modal.Manufacturer;
import com.gmail.yuramitryahin.service.CarService;
import com.gmail.yuramitryahin.service.DriverService;
import com.gmail.yuramitryahin.service.ManufacturerService;

public class Main {
    private static Injector injector = Injector.getInstance("com.gmail.yuramitryahin");
    private static Driver driverNikolai;
    private static Driver driverMark;
    private static Driver driverTom;
    private static Manufacturer tesla;
    private static Manufacturer reno;
    private static Car modelX;
    private static Car logan;

    public static void main(String[] args) {
        driverNikolai = new Driver("Nikolai", "142453345");
        driverMark = new Driver("Mark", "216483948");
        driverTom = new Driver("Tom", "925738163");
        tesla = new Manufacturer("Tesla", "USA");
        reno = new Manufacturer("Reno", "Germany");
        modelX = new Car("Model X", tesla);
        logan = new Car("Logan", reno);

        testDriverService();
        testCarService();
    }

    public static void testCarService() {
        CarService carService = (CarService) injector
                .getInstance(CarService.class);
        carService.create(modelX);
        carService.create(logan);
        System.out.println(carService.getAll());
        carService.addDriverToCar(driverNikolai, modelX);
        carService.addDriverToCar(driverMark, logan);
        System.out.println(carService.getAll());
        System.out.println(carService.getAllByDriver(1L));
        carService.removeDriverFromCar(driverNikolai, modelX);
        carService.delete(2L);
        System.out.println(carService.getAll());
    }

    public static void testDriverService() {
        DriverService driverService = (DriverService) injector
                .getInstance(DriverService.class);
        driverService.create(driverNikolai);
        driverService.create(driverMark);
        driverService.create(driverTom);
        System.out.println(driverService.getAll());
        driverMark.setLicenseNumber("111111112");
        driverService.update(driverMark);
        driverService.delete(3L);
        System.out.println(driverService.getAll());
    }

    public static void testManufacturerService() {
        ManufacturerService manufacturerService = (ManufacturerService) injector
                .getInstance(ManufacturerService.class);
        manufacturerService.create(tesla);
        manufacturerService.create(reno);
        System.out.println(manufacturerService.getAll());
        reno.setCountry("France");
        manufacturerService.update(reno);
        System.out.println(manufacturerService.getAll());
        manufacturerService.delete(2L);
        System.out.println(manufacturerService.getAll());
    }
}
