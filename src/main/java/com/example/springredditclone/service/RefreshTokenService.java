package com.example.springredditclone.service;

import com.example.springredditclone.exception.SpringRedditException;
import com.example.springredditclone.model.RefreshToken;
import com.example.springredditclone.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;
@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {
    private final RefreshTokenRepository refeshTokenRepository;

    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());

        return refeshTokenRepository.save(refreshToken);
    }

    void validateRefreshToken(String token) throws SpringRedditException {
        refeshTokenRepository.findByToken(token)
                .orElseThrow(() -> new SpringRedditException("Invalid refresh Token"));
    }

    public void deleteRefreshToken(String token) {
        refeshTokenRepository.deleteByToken(token);
    }




}
