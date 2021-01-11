package com.gmail.yuramitryahin;

import com.gmail.yuramitryahin.lib.Injector;
import com.gmail.yuramitryahin.modal.Manufacturer;
import com.gmail.yuramitryahin.service.ManufacturerService;

public class Main {
    private static Injector injector = Injector.getInstance("com.gmail.yuramitryahin");

    public static void main(String[] args) {
        ManufacturerService manufacturerService = (ManufacturerService) injector
                .getInstance(ManufacturerService.class);
        Manufacturer tesla = new Manufacturer("Tesla", "USA");
        Manufacturer reno = new Manufacturer("Reno", "Germany");
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
