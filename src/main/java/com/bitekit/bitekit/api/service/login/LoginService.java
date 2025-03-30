package com.bitekit.bitekit.api.service.login;

import com.bitekit.bitekit.api.dto.request.LoginRequest;
import com.bitekit.bitekit.api.dto.response.LoginResponse;
import com.bitekit.bitekit.api.entity.User;
import com.bitekit.bitekit.api.repository.UserRepository;
import com.bitekit.bitekit.api.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class LoginService implements ILoginService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("帳號不存在"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("密碼錯誤");
        }

        String token = jwtUtil.generateToken(user);
        return new LoginResponse(token);
    }
}
