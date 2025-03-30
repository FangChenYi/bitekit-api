package com.bitekit.bitekit.api.service.user;

import com.bitekit.bitekit.api.dto.request.RegisterRequest;
import com.bitekit.bitekit.api.dto.response.RegisterResponse;

public interface IUserService {
    RegisterResponse register(RegisterRequest request);
}
