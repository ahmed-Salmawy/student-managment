package com.mywhoosh.studentresultManagment.security.dto;

import com.mywhoosh.studentresultManagment.base.AbstractBaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TokenDto extends AbstractBaseDto {
    private UserDto user;
    private String token;
    private boolean expired;
    private boolean revoked;
}
