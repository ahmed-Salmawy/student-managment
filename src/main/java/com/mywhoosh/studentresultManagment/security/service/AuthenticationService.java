package com.mywhoosh.studentresultManagment.security.service;

import com.mywhoosh.studentresultManagment.base.BaseService;
import com.mywhoosh.studentresultManagment.security.dto.AuthenticationRequest;
import com.mywhoosh.studentresultManagment.security.dto.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService extends BaseService{


    public AuthenticationResponse authenticate(AuthenticationRequest request);


    void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException;

 }
