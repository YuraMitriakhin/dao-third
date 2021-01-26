package com.gmail.yuramitryahin.security;

import com.gmail.yuramitryahin.exception.AuthenticationException;
import com.gmail.yuramitryahin.lib.Injector;
import com.gmail.yuramitryahin.lib.Service;
import com.gmail.yuramitryahin.modal.Driver;
import com.gmail.yuramitryahin.service.DriverService;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Injector injector = Injector.getInstance("com.gmail.yuramitryahin");
    private DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    public Driver login(String login, String password)
            throws AuthenticationException {
        Optional<Driver> driver = driverService.findByLogin(login);
        if (driver.isPresent() && driver.get().getPassword().equals(password)) {
            return driver.get();
        }
        throw new AuthenticationException("Incorrect username or password");
    }
}
