package com.gmail.yuramitryahin.security;

import com.gmail.yuramitryahin.exception.AuthenticationException;
import com.gmail.yuramitryahin.modal.Driver;

public interface AuthenticationService {
    Driver login(String login, String password) throws AuthenticationException;
}
