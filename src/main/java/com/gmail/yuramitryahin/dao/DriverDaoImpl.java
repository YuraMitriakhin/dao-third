package com.gmail.yuramitryahin.dao;

import com.gmail.yuramitryahin.db.Storage;
import com.gmail.yuramitryahin.lib.Dao;
import com.gmail.yuramitryahin.modal.Driver;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class DriverDaoImpl implements DriverDao {
    @Override
    public Driver create(Driver driver) {
        Storage.addDriver(driver);
        return driver;
    }

    @Override
    public Optional<Driver> get(Long id) {
        return Storage.drivers.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Driver> getAll() {
        return Storage.drivers;
    }

    @Override
    public Driver update(Driver driver) {
        int index = IntStream.range(0, Storage.drivers.size())
                .filter(i -> Objects.equals(Storage.drivers.get(i).getId(), driver.getId()))
                .findFirst()
                .getAsInt();
        Storage.drivers.set(index, driver);
        return driver;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.drivers.removeIf(d -> d.getId().equals(id));
    }
}
