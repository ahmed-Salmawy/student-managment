package com.mywhoosh.studentresultManagment.security.service;

import com.mywhoosh.studentresultManagment.base.BaseService;
import com.mywhoosh.studentresultManagment.security.dto.AuthenticationResponse;
import com.mywhoosh.studentresultManagment.security.dto.RegisterRequest;

public interface UserRegistrationService extends BaseService {

    AuthenticationResponse register(RegisterRequest request);
}
