package com.mywhoosh.studentresultManagment.security.dto;

import com.mywhoosh.studentresultManagment.base.AbstractBaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest extends AbstractBaseDto {

    private String username;
    private String password;
}
