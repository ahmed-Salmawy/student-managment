package com.mywhoosh.studentresultManagment.security.mapper;

import com.mywhoosh.studentresultManagment.base.BaseMapper;
import com.mywhoosh.studentresultManagment.presistance.entity.UserEntity;
import com.mywhoosh.studentresultManagment.security.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements BaseMapper<UserEntity, UserDto> {
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public UserDto toDto(UserEntity entity) {
        return mapper.map(entity, UserDto.class);
    }

    @Override
    public UserEntity toEntity(UserDto dto) {
        return mapper.map(dto, UserEntity.class);
    }
}
