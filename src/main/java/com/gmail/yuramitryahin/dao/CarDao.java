package com.gmail.yuramitryahin.dao;

import com.gmail.yuramitryahin.modal.Car;
import java.util.List;

public interface CarDao extends GenericDao<Car, Long> {

    List<Car> getAllByDriver(Long driverId);
}
