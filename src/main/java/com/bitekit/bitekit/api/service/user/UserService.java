package com.bitekit.bitekit.api.service.user;

import com.bitekit.bitekit.api.dto.request.RegisterRequest;
import com.bitekit.bitekit.api.dto.response.RegisterResponse;
import com.bitekit.bitekit.api.entity.User;
import com.bitekit.bitekit.api.repository.UserRepository;
import com.bitekit.bitekit.api.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 可改成 @Bean

    @Override
    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("使用者名稱已存在");
        }

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setEmail(request.getEmail());
        user.setTel(request.getTel());
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        String token = jwtUtil.generateToken(user);


        return new RegisterResponse("註冊成功", user.getUsername(), token);
    }
}
