package com.mywhoosh.studentresultManagment.domain.mapper;

import com.mywhoosh.studentresultManagment.base.BaseMapper;
import com.mywhoosh.studentresultManagment.domain.dto.StudentDto;
import com.mywhoosh.studentresultManagment.presistance.entity.StudentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper implements BaseMapper<StudentEntity, StudentDto> {

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public StudentDto toDto(StudentEntity entity) {
        return mapper.map(entity, StudentDto.class);
    }

    @Override
    public StudentEntity toEntity(StudentDto dto) {
        return mapper.map(dto, StudentEntity.class);
    }
}
