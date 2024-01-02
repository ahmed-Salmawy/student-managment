package com.mywhoosh.studentresultManagment.security.service.impl;

import com.mywhoosh.studentresultManagment.base.AbstractBaseService;
import com.mywhoosh.studentresultManagment.security.dto.AuthenticationResponse;
import com.mywhoosh.studentresultManagment.security.dto.RegisterRequest;
import com.mywhoosh.studentresultManagment.security.dto.TokenDto;
import com.mywhoosh.studentresultManagment.security.dto.UserDto;
import com.mywhoosh.studentresultManagment.security.repoadapter.UserRepoAdapter;
import com.mywhoosh.studentresultManagment.security.repoadapter.UserTokenRepoAdapter;
import com.mywhoosh.studentresultManagment.security.service.UserRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserRegistrationServiceImpl  extends AbstractBaseService<UserDto, UserRepoAdapter> implements UserRegistrationService {
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtServiceImpl;
    private final UserTokenRepoAdapter tokenRepoAdapter;

    @Autowired
    protected UserRegistrationServiceImpl(UserRepoAdapter repoAdapter, PasswordEncoder passwordEncoder, JwtServiceImpl jwtServiceImpl, UserTokenRepoAdapter tokenRepoAdapter) {
        super(repoAdapter);
        this.passwordEncoder = passwordEncoder;
        this.jwtServiceImpl = jwtServiceImpl;
        this.tokenRepoAdapter = tokenRepoAdapter;
    }


    @Override
    public AuthenticationResponse register(RegisterRequest request) {

        UserDto user = UserDto.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        String id = repoAdapter.create(user);
        user.setId(id);

        var jwtToken = jwtServiceImpl.generateToken(user);
        var refreshToken = jwtServiceImpl.generateRefreshToken(user);

        saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }



    private void saveUserToken(UserDto user, String jwtToken) {
        TokenDto token = TokenDto.builder()
                .user(user)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepoAdapter.create(token);

    }
}
