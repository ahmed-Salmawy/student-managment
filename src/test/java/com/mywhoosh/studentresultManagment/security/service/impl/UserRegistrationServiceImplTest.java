package com.mywhoosh.studentresultManagment.security.service.impl;

import com.mywhoosh.studentresultManagment.security.dto.AuthenticationResponse;
import com.mywhoosh.studentresultManagment.security.dto.RegisterRequest;
import com.mywhoosh.studentresultManagment.security.dto.UserDto;
import com.mywhoosh.studentresultManagment.security.repoadapter.UserRepoAdapter;
import com.mywhoosh.studentresultManagment.security.repoadapter.UserTokenRepoAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRegistrationServiceImplTest {
    @Mock
    private UserRepoAdapter repoAdapter;

    @Mock
    private UserTokenRepoAdapter tokenRepoAdapter;

    @Mock
    private JwtServiceImpl jwtServiceImpl;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserRegistrationServiceImpl service;



    @Test
    void register_ShouldReturnAuthenticationResponse() {

        RegisterRequest registerRequest = new RegisterRequest("username", "password");
        UserDto savedUser = UserDto.builder().id("userId").username("username").password("encodedPassword").build();
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        when(repoAdapter.create(any())).thenReturn("userId");
        when(tokenRepoAdapter.create(any())).thenReturn("tokenId");

        when(jwtServiceImpl.generateToken(any())).thenReturn("accessToken");
        when(jwtServiceImpl.generateRefreshToken(any())).thenReturn("refreshToken");

        AuthenticationResponse result = service.register(registerRequest);

        verify(passwordEncoder).encode("password");
        verify(repoAdapter).create(any());
        verify(jwtServiceImpl).generateToken(any());
        verify(jwtServiceImpl).generateRefreshToken(any());

        assertNotNull(result);
        assertEquals("accessToken", result.getAccessToken());
        assertEquals("refreshToken", result.getRefreshToken());
    }
}

