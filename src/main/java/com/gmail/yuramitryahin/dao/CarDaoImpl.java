package com.gmail.yuramitryahin.dao;

import com.gmail.yuramitryahin.db.Storage;
import com.gmail.yuramitryahin.lib.Dao;
import com.gmail.yuramitryahin.modal.Car;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Dao
public class CarDaoImpl implements CarDao {
    @Override
    public Car create(Car car) {
        Storage.addCar(car);
        return car;
    }

    @Override
    public Optional<Car> get(Long id) {
        return Storage.cars.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Car> getAll() {
        return Storage.cars;
    }

    @Override
    public Car update(Car car) {
        Storage.cars.set(findCarIndex(car), car);
        return car;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.cars.removeIf(c -> c.getId().equals(id));
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        return Storage.cars.stream()
                .filter(c -> containsDriver(c, driverId))
                .collect(Collectors.toList());
    }

    private boolean containsDriver(Car car, Long driverId) {
        return car.getDrivers().stream()
                .filter(d -> Objects.equals(d.getId(), driverId))
                .findFirst()
                .isPresent();
    }

    private int findCarIndex(Car car) {
        return IntStream.range(0, Storage.cars.size())
                .filter(i -> Objects.equals(Storage.cars.get(i).getId(), car.getId()))
                .findFirst()
                .getAsInt();
    }
}
