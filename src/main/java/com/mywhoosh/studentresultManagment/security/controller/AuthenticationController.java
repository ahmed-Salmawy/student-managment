package com.mywhoosh.studentresultManagment.security.controller;

import com.mywhoosh.studentresultManagment.base.AbstractBaseController;
import com.mywhoosh.studentresultManagment.security.dto.AuthenticationRequest;
import com.mywhoosh.studentresultManagment.security.dto.AuthenticationResponse;
import com.mywhoosh.studentresultManagment.security.dto.RegisterRequest;
import com.mywhoosh.studentresultManagment.security.service.AuthenticationService;
import com.mywhoosh.studentresultManagment.security.service.UserRegistrationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController extends AbstractBaseController<AuthenticationService> {

    private final UserRegistrationService registrationService;

    public AuthenticationController(AuthenticationService service, UserRegistrationService registrationService) {
        super(service);
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public AuthenticationResponse register(
            @RequestBody RegisterRequest request
    ) {
        return registrationService.register(request);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return service.authenticate(request);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }


}
