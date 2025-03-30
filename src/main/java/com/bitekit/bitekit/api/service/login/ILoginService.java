package com.bitekit.bitekit.api.service.login;

import com.bitekit.bitekit.api.dto.response.LoginResponse;
import com.bitekit.bitekit.api.dto.request.LoginRequest;

public interface ILoginService {
    LoginResponse login(LoginRequest request);
}
