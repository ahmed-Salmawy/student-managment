package com.mywhoosh.studentresultManagment.domain.repoadapter.impl;

import com.mywhoosh.studentresultManagment.base.AbstractBaseRepoAdapter;
import com.mywhoosh.studentresultManagment.domain.dto.DeleteStudentRequestDto;
import com.mywhoosh.studentresultManagment.domain.dto.StudentDto;
import com.mywhoosh.studentresultManagment.domain.dto.StudentsResultsDto;
import com.mywhoosh.studentresultManagment.domain.mapper.StudentMapper;
import com.mywhoosh.studentresultManagment.domain.repo.StudentRepo;
import com.mywhoosh.studentresultManagment.domain.repoadapter.StudentRepoAdapter;
import com.mywhoosh.studentresultManagment.presistance.entity.StudentEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class StudentRepoAdapterImpl extends AbstractBaseRepoAdapter
        <StudentEntity, StudentDto, StudentMapper, StudentRepo> implements StudentRepoAdapter {
    protected StudentRepoAdapterImpl(StudentMapper mapper, StudentRepo repository) {
        super(mapper, repository);
    }

    @Override
    public void delete(StudentDto dto) {


    }


    @Override
    public StudentDto findByRollNumber(Integer rollNumber) {
        Map<String, Object> paramters = new HashMap();
        paramters.put(StudentEntity.Fields.rollNumber, rollNumber);
        return Optional.ofNullable(repository.findOneByMap(paramters)).map(mapper::toDto).orElse(null);
    }

    @Override
    public List<StudentsResultsDto> getActiveStudentsWithResults() {
        return repository.getActiveStudentsWithResults();
    }

    @Override
    public Optional<StudentDto> findStudentByRollNumberAndGrade(DeleteStudentRequestDto deleteStudentRequestDto) {
        Map<String, Object> parameters = new HashMap();
        parameters.put(StudentEntity.Fields.rollNumber, deleteStudentRequestDto.getRollNumber());
        parameters.put(StudentEntity.Fields.grade, deleteStudentRequestDto.getGrade());
        return Optional.ofNullable(repository.findOneByMap(parameters)).map(mapper::toDto);
    }

    @Override
    public void updateStudent(StudentDto studentDto) {
        repository.update(mapper.toEntity(studentDto));
    }
}
