package org.example.checkmate.user.service;

import lombok.RequiredArgsConstructor;

import org.example.checkmate.user.domain.RefreshToken;
import org.example.checkmate.user.domain.User;
import org.example.checkmate.user.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public RefreshToken addOrUpdateRefreshToken(User user, String newRefreshTokenValue) {
        Optional<RefreshToken> existingToken = refreshTokenRepository.findByUser(user);

        if (existingToken.isPresent()) {
            RefreshToken token = existingToken.get();
            token.setValue(newRefreshTokenValue);
            return refreshTokenRepository.save(token);
        } else {
            RefreshToken newToken = new RefreshToken();
            newToken.setUser(user);
            newToken.setValue(newRefreshTokenValue);
            return refreshTokenRepository.save(newToken);
        }
    }

    @Transactional(readOnly = true)
    public Optional<RefreshToken> findRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByValue(refreshToken);
    }

    public void deleteRefreshToken(String refreshToken) {
        refreshTokenRepository.findByValue(refreshToken).ifPresent(refreshTokenRepository::delete);
    }
}