package com.mywhoosh.studentresultManagment.security.service.impl;

import com.mywhoosh.studentresultManagment.security.dto.AuthenticationRequest;
import com.mywhoosh.studentresultManagment.security.dto.AuthenticationResponse;
import com.mywhoosh.studentresultManagment.security.dto.TokenDto;
import com.mywhoosh.studentresultManagment.security.dto.UserDto;
import com.mywhoosh.studentresultManagment.security.repoadapter.UserRepoAdapter;
import com.mywhoosh.studentresultManagment.security.repoadapter.UserTokenRepoAdapter;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {


    @Mock
    private JwtServiceImpl jwtServiceImpl;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepoAdapter userRepoAdapter;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private UserTokenRepoAdapter tokenRepository;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

 /*   @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }*/

    @Test
    void authenticate_ShouldReturnAuthenticationResponse() {

        AuthenticationRequest authenticationRequest = new AuthenticationRequest("username", "password");
        UserDto user = UserDto.builder().id("userId").username("username").password("encodedPassword").build();
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userRepoAdapter.getUser(anyString())).thenReturn(user);
        when(jwtServiceImpl.generateToken(any(UserDto.class))).thenReturn("accessToken");
        when(jwtServiceImpl.generateRefreshToken(any(UserDto.class))).thenReturn("refreshToken");
        when(tokenRepository.findAllValidTokenByUser(anyString())).thenReturn(Collections.emptyList());

        AuthenticationResponse result = authenticationService.authenticate(authenticationRequest);

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepoAdapter).getUser("username");
        verify(jwtServiceImpl).generateToken(user);
        verify(jwtServiceImpl).generateRefreshToken(user);
        verify(tokenRepository).findAllValidTokenByUser("userId");
        verify(tokenRepository).create(any(TokenDto.class));


        assertNotNull(result);
        assertEquals("accessToken", result.getAccessToken());
        assertEquals("refreshToken", result.getRefreshToken());
    }

    @Test
    void refreshToken_ShouldRefreshTokenAndWriteToResponse() throws IOException {

        MockHttpServletRequest request = mock(MockHttpServletRequest.class);
        MockHttpServletResponse response = mock(MockHttpServletResponse.class);
        when(request.getHeader(eq(HttpHeaders.AUTHORIZATION))).thenReturn("Bearer refreshToken");
       // when(response.getOutputStream()).thenReturn(response.getOutputStream());

        when(jwtServiceImpl.extractUsername("refreshToken")).thenReturn("username");
        UserDto user = UserDto.builder().id("userId").username("username").password("encodedPassword").build();
        when(userRepoAdapter.getUser(anyString())).thenReturn(user);
        when(jwtServiceImpl.isTokenValid(anyString(), any(UserDto.class))).thenReturn(true);
        when(jwtServiceImpl.generateToken(any(UserDto.class))).thenReturn("newAccessToken");
        when(tokenRepository.findAllValidTokenByUser(anyString())).thenReturn(Collections.emptyList());


        authenticationService.refreshToken(request, response);


        verify(jwtServiceImpl).extractUsername("refreshToken");
        verify(userRepoAdapter).getUser("username");
        verify(jwtServiceImpl).isTokenValid("refreshToken", user);
        verify(jwtServiceImpl).generateToken(user);
        verify(tokenRepository).findAllValidTokenByUser("userId");
        verify(tokenRepository).create(any(TokenDto.class));
    }

}