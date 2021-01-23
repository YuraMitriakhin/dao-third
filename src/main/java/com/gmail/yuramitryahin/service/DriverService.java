package com.gmail.yuramitryahin.service;

import com.gmail.yuramitryahin.modal.Driver;
import java.util.Optional;

public interface DriverService extends GenericService<Driver, Long> {
    Optional<Driver> findByLogin(String login);
}
