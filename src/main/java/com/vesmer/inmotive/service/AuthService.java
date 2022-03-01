package com.vesmer.inmotive.service;

import com.vesmer.inmotive.dto.RegisterRequest;
import com.vesmer.inmotive.exception.InmotiveException;
import com.vesmer.inmotive.model.User;
import com.vesmer.inmotive.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void signup(RegisterRequest registerRequest) {
        if (userRepository.existsUserByUsername(registerRequest.getUsername())) {
            throw new InmotiveException(
                    String.format("Username '%s' is not unique", registerRequest.getUsername())
            );
        }
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);
    }
}
