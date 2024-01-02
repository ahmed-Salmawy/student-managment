package com.mywhoosh.studentresultManagment.security.mapper;

import com.mywhoosh.studentresultManagment.base.BaseMapper;
import com.mywhoosh.studentresultManagment.presistance.entity.TokenEntity;
import com.mywhoosh.studentresultManagment.security.dto.TokenDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TokenMapper implements BaseMapper<TokenEntity, TokenDto> {

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public TokenDto toDto(TokenEntity entity) {
        return mapper.map(entity, TokenDto.class);
    }

    @Override
    public TokenEntity toEntity(TokenDto dto) {
        return mapper.map(dto, TokenEntity.class);
    }
}
