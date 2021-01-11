package com.gmail.yuramitryahin.service;

import com.gmail.yuramitryahin.modal.Manufacturer;
import java.util.List;

public interface ManufacturerService {
    Manufacturer create(Manufacturer manufacturer);

    Manufacturer get(Long id);

    List<Manufacturer> getAll();

    Manufacturer update(Manufacturer manufacturer);

    boolean delete(Long id);
}
