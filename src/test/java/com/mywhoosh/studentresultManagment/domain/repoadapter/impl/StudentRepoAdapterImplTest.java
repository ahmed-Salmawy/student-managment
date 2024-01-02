package com.mywhoosh.studentresultManagment.domain.repoadapter.impl;

import com.mywhoosh.studentresultManagment.domain.dto.DeleteStudentRequestDto;
import com.mywhoosh.studentresultManagment.domain.dto.StudentDto;
import com.mywhoosh.studentresultManagment.domain.dto.StudentsResultsDto;
import com.mywhoosh.studentresultManagment.domain.mapper.StudentMapper;
import com.mywhoosh.studentresultManagment.domain.repo.StudentRepo;
import com.mywhoosh.studentresultManagment.presistance.entity.StudentEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentRepoAdapterImplTest {

    @Mock
    private StudentMapper studentMapper;

    @Mock
    private StudentRepo studentRepo;

    @InjectMocks
    private StudentRepoAdapterImpl studentRepoAdapter;


    @Test
    void findByRollNumber_ShouldReturnStudentDto() {
        int rollNumber = 123;
        StudentEntity studentEntity = new StudentEntity();
        StudentDto studentDto = new StudentDto();
        when(studentRepo.findOneByMap(anyMap())).thenReturn(studentEntity);
        when(studentMapper.toDto(studentEntity)).thenReturn(studentDto);
        StudentDto result = studentRepoAdapter.findByRollNumber(rollNumber);
        assertEquals(studentDto, result);
    }

    @Test
    void findByRollNumber_ShouldReturnNull() {
        int rollNumber = 456;
        when(studentRepo.findOneByMap(any(Map.class))).thenReturn(null);
        StudentDto result = studentRepoAdapter.findByRollNumber(rollNumber);
        assertEquals(null, result);
    }

    @Test
    void getActiveStudentsWithResults_ShouldReturnStudentsResultsDtoList() {
        List<StudentsResultsDto> studentsResults = Collections.singletonList(new StudentsResultsDto());
        when(studentRepo.getActiveStudentsWithResults()).thenReturn(studentsResults);
        List<StudentsResultsDto> result = studentRepoAdapter.getActiveStudentsWithResults();
        assertEquals(studentsResults, result);
    }

    @Test
    void findStudentByRollNumberAndGrade_ShouldReturnStudentDto() {
        DeleteStudentRequestDto deleteStudentRequestDto = DeleteStudentRequestDto.builder().rollNumber(13).grade(5).build();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(StudentEntity.Fields.rollNumber, deleteStudentRequestDto.getRollNumber());
        parameters.put(StudentEntity.Fields.grade, deleteStudentRequestDto.getGrade());

        StudentEntity studentEntity = new StudentEntity();
        StudentDto studentDto = new StudentDto();
        when(studentRepo.findOneByMap(parameters)).thenReturn(studentEntity);
        when(studentMapper.toDto(studentEntity)).thenReturn(studentDto);
        Optional<StudentDto> result = studentRepoAdapter.findStudentByRollNumberAndGrade(deleteStudentRequestDto);

        assertEquals(Optional.of(studentDto), result);
    }

    @Test
    void findStudentByRollNumberAndGrade_ShouldReturnEmptyOptional() {
        DeleteStudentRequestDto deleteStudentRequestDto = DeleteStudentRequestDto.builder().rollNumber(13).grade(5).build();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(StudentEntity.Fields.rollNumber, deleteStudentRequestDto.getRollNumber());
        parameters.put(StudentEntity.Fields.grade, deleteStudentRequestDto.getGrade());
        when(studentRepo.findOneByMap(parameters)).thenReturn(null);

        Optional<StudentDto> result = studentRepoAdapter.findStudentByRollNumberAndGrade(deleteStudentRequestDto);

        assertEquals(Optional.empty(), result);
    }

    @Test
    void updateStudent_ShouldUpdateStudent() {
        StudentDto studentDto = new StudentDto();
        StudentEntity studentEntity = new StudentEntity();
        when(studentMapper.toEntity(studentDto)).thenReturn(studentEntity);

        studentRepoAdapter.updateStudent(studentDto);

        verify(studentRepo).update(studentEntity);
    }

}
