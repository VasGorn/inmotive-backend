package com.vesmer.inmotive.service;


import com.vesmer.inmotive.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }
}
