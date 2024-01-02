package com.mywhoosh.studentresultManagment.security.service;

import com.mywhoosh.studentresultManagment.security.dto.TokenDto;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public interface JwtService {
    String extractUsername(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String generateToken(UserDetails userDetails);

    String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    );

    String generateRefreshToken(
            UserDetails userDetails
    );

    boolean isTokenValid(String token, UserDetails userDetails);

    Optional<TokenDto> findByToken(String jwt);
}
