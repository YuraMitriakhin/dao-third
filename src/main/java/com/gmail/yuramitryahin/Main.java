package com.gmail.yuramitryahin;

import com.gmail.yuramitryahin.lib.Injector;
import com.gmail.yuramitryahin.modal.Car;
import com.gmail.yuramitryahin.modal.Driver;
import com.gmail.yuramitryahin.modal.Manufacturer;
import com.gmail.yuramitryahin.service.CarService;

import java.util.List;

public class Main {
    private static Injector injector = Injector.getInstance("com.gmail.yuramitryahin");

    public static void main(String[] args) {
        Manufacturer tesla = new Manufacturer(1L, "Tesla", "USA");
        Manufacturer reno = new Manufacturer(2L, "Reno", "French");

        CarService carService = (CarService) injector
                .getInstance(CarService.class);
        Driver kolia = new Driver("Kolia", "1000234");
        Driver john = new Driver("John", "1009653");
        Car modelX = new Car("ModelX", tesla);
        modelX.setDrivers(List.of(john, kolia));

        carService.create(modelX);
        System.out.println(carService.getAll());
    }
}
