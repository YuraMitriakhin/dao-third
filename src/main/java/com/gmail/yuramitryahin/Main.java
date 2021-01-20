package com.gmail.yuramitryahin;

import com.gmail.yuramitryahin.lib.Injector;
import com.gmail.yuramitryahin.modal.Manufacturer;
import com.gmail.yuramitryahin.service.ManufacturerService;

public class Main {
    private static Injector injector = Injector.getInstance("com.gmail.yuramitryahin");

    public static void main(String[] args) {
        Manufacturer tesla = new Manufacturer("Tesla", "USA");
        Manufacturer reno = new Manufacturer("Reno", "French");

        ManufacturerService manufacturerService = (ManufacturerService) injector
                .getInstance(ManufacturerService.class);

        System.out.println(manufacturerService.update(new Manufacturer(3L, "LALA", "BB")));
    }
}
