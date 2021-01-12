package com.gmail.yuramitryahin.service;

import com.gmail.yuramitryahin.modal.Driver;
import java.util.List;

public interface DriverService {
    Driver create(Driver driver);

    Driver get(Long id);

    List<Driver> getAll();

    Driver update(Driver driver);

    boolean delete(Long id);
}
