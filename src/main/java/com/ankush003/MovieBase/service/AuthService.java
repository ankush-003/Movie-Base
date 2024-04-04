package com.ankush003.MovieBase.service;

import com.ankush003.MovieBase.config.jwtConfig.JwtTokenGenerator;
import com.ankush003.MovieBase.model.dto.AuthResponseDto;
import com.ankush003.MovieBase.model.entities.UserEntity;
import com.ankush003.MovieBase.repository.UserRepository;
import com.ankush003.MovieBase.model.dto.TokenType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final JwtTokenGenerator jwtTokenGenerator;

    public AuthResponseDto getJwtTokenAfterAuthentication(Authentication authentication) {
        try {
            UserEntity userEntity = userRepository.findByEmail(authentication.getName())
                    .orElseThrow(() -> {
                        log.error("User not found");
                        return new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");

                    });
            String accessToken = jwtTokenGenerator.generateToken(authentication);
            return AuthResponseDto.builder()
                    .accessToken(accessToken)
                    .accessTokenExpiry(15 * 60)
                    .tokenType(TokenType.Bearer)
                    .userName(userEntity.getEmail())
                    .build();
        }
        catch (Exception e) {
            log.error("Error while generating token", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while generating token");
        }
    }
}
