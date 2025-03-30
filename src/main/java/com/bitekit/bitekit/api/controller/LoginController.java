package com.bitekit.bitekit.api.controller;

import com.bitekit.bitekit.api.dto.response.LoginResponse;
import com.bitekit.bitekit.api.dto.request.LoginRequest;
import com.bitekit.bitekit.api.entity.User;
import com.bitekit.bitekit.api.repository.UserRepository;
import com.bitekit.bitekit.api.service.login.ILoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 暫時手動加

    private final ILoginService loginService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }


        return loginService.login(request);
    }

}
