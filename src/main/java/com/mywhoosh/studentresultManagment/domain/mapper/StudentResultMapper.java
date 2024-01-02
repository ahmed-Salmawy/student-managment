package com.mywhoosh.studentresultManagment.domain.mapper;

import com.mywhoosh.studentresultManagment.base.BaseMapper;
import com.mywhoosh.studentresultManagment.domain.dto.StudentResultDto;
import com.mywhoosh.studentresultManagment.presistance.entity.StudentResultEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StudentResultMapper implements BaseMapper<StudentResultEntity, StudentResultDto> {

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public StudentResultDto toDto(StudentResultEntity entity) {
        return mapper.map(entity, StudentResultDto.class);

    }
    @Override
    public StudentResultEntity toEntity(StudentResultDto dto) {
        return mapper.map(dto, StudentResultEntity.class);

    }
}
