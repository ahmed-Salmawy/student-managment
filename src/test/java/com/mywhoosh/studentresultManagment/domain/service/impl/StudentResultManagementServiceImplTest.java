package com.mywhoosh.studentresultManagment.domain.service.impl;

import com.mywhoosh.studentresultManagment.domain.dto.*;
import com.mywhoosh.studentresultManagment.domain.enums.RemarkEnum;
import com.mywhoosh.studentresultManagment.domain.repoadapter.StudentRepoAdapter;
import com.mywhoosh.studentresultManagment.domain.repoadapter.StudentResultRepoAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentResultManagementServiceImplTest {

    @Mock
    private StudentRepoAdapter studentRepoAdapter;

    @Mock
    private StudentResultRepoAdapter studentResultRepoAdapter;

    @InjectMocks
    private StudentResultManagementServiceImpl studentResultManagementService;


    @Test
    void addNewStudent_ShouldReturnSuccessMessage() {
        StudentRequestDto studentRequestDto = StudentRequestDto.builder()
                .name("John")
                .fatherName("Doe")
                .rollNumber(13)
                .grade(4).build();
        when(studentRepoAdapter.create(any(StudentDto.class))).thenReturn("studentId");
        String result = studentResultManagementService.addNewStudent(studentRequestDto);
        verify(studentRepoAdapter).create(any(StudentDto.class));
        assertEquals("Student added successfully :) ", result);
    }

    @Test
    void addNewStudent_ShouldReturnDuplicateMessage() {

        StudentRequestDto studentRequestDto = StudentRequestDto.builder()
                .name("John")
                .fatherName("Doe")
                .rollNumber(13)
                .grade(4).build();
        when(studentRepoAdapter.create(any(StudentDto.class))).thenThrow(DuplicateKeyException.class);
        String result = studentResultManagementService.addNewStudent(studentRequestDto);
        verify(studentRepoAdapter).create(any(StudentDto.class));
        assertEquals(" Student registered before", result);
    }

    @Test
    void saveStudentResult_ShouldSaveResultSuccessfully() {

        StudentResultRequestDto resultRequestDto = StudentResultRequestDto.builder()
                .rollNumber(13)
                .obtainedMarks(80D)
                .totalMarks(100D).build();
        when(studentRepoAdapter.findByRollNumber(13)).thenReturn(new StudentDto());
        when(studentResultRepoAdapter.create(any(StudentResultDto.class))).thenReturn("resultId");
        String result = studentResultManagementService.saveStudentResult(resultRequestDto);
        verify(studentRepoAdapter).findByRollNumber(13);
        verify(studentResultRepoAdapter).create(any(StudentResultDto.class));
        assertEquals("Result is saved Successfully :)", result);
    }

    @Test
    void saveStudentResult_ShouldReturnStudentNotFound() {
        StudentResultRequestDto resultRequestDto = StudentResultRequestDto
                .builder().totalMarks(100D).obtainedMarks(80D).rollNumber(13).build();
        when(studentRepoAdapter.findByRollNumber(anyInt())).thenReturn(null);
        String result = studentResultManagementService.saveStudentResult(resultRequestDto);
        verify(studentRepoAdapter).findByRollNumber(13);
        assertEquals("Sorry!,Student is not Registered :(", result);
    }

    @Test
    void getStudentsResults_ShouldReturnSortedResults() {
        List<StudentsResultsDto> sortedResults = Collections.singletonList(new StudentsResultsDto());
        when(studentRepoAdapter.getActiveStudentsWithResults()).thenReturn(sortedResults);
        List<StudentsResultsDto> result = studentResultManagementService.getStudentsResults();
        verify(studentRepoAdapter).getActiveStudentsWithResults();
        assertEquals(sortedResults, result);
    }

    @Test
    void deleteStudent_ShouldReturnSuccessMessage() {
        DeleteStudentRequestDto deleteStudentRequestDto = DeleteStudentRequestDto.builder().rollNumber(13).grade(5).build();
        StudentDto studentDto = StudentDto.builder().rollNumber(13).name("ahmed").fatherName("elsalmawy").grade(5).build();
        when(studentRepoAdapter.findStudentByRollNumberAndGrade(deleteStudentRequestDto)).thenReturn(Optional.of(studentDto));
        String result = studentResultManagementService.deleteStudent(deleteStudentRequestDto);
        verify(studentRepoAdapter).findStudentByRollNumberAndGrade(deleteStudentRequestDto);
        verify(studentRepoAdapter).update(studentDto);
        assertEquals("student deleted successfully", result);
    }

    @Test
    void deleteStudent_ShouldReturnNotFoundMessage() {
        DeleteStudentRequestDto deleteStudentRequestDto = DeleteStudentRequestDto.builder().rollNumber(13).grade(5).build();
        when(studentRepoAdapter.findStudentByRollNumberAndGrade(deleteStudentRequestDto)).thenReturn(Optional.empty());
        String result = studentResultManagementService.deleteStudent(deleteStudentRequestDto);
        verify(studentRepoAdapter).findStudentByRollNumberAndGrade(deleteStudentRequestDto);
        verify(studentRepoAdapter, never()).update(any(StudentDto.class));
        assertEquals("student not Found :(", result);
    }



    @Test
    void getStudentResults_ShouldReturnStudentResults() {
        int rollNumber = 13;
        List<StudentsResultsDto> studentResults = Collections.singletonList(
                StudentsResultsDto.builder().name("ahmed").fatherName("elsalmaway").obtainedMarks(80).totalMarks(100).rollNumber(rollNumber).remarks(RemarkEnum.PASSED.name()).build());
        when(studentRepoAdapter.getActiveStudentsWithResults()).thenReturn(studentResults);
        StudentsResultsDto result = studentResultManagementService.getStudentResults(rollNumber);
        assertEquals(studentResults.get(0), result);
    }

    @Test
    void getStudentResults_ShouldReturnEmptyResult() {
        int rollNumber = 456;
        when(studentRepoAdapter.getActiveStudentsWithResults()).thenReturn(Collections.emptyList());
        StudentsResultsDto result = studentResultManagementService.getStudentResults(rollNumber);
        assertEquals(new StudentsResultsDto(), result);
    }

}
