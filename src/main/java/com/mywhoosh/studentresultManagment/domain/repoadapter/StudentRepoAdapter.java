package com.mywhoosh.studentresultManagment.domain.repoadapter;

import com.mywhoosh.studentresultManagment.base.BaseRepoAdapter;
import com.mywhoosh.studentresultManagment.domain.dto.DeleteStudentRequestDto;
import com.mywhoosh.studentresultManagment.domain.dto.StudentDto;
import com.mywhoosh.studentresultManagment.domain.dto.StudentsResultsDto;

import java.util.List;
import java.util.Optional;

public interface StudentRepoAdapter extends BaseRepoAdapter<StudentDto> {
    StudentDto findByRollNumber(Integer rollNumber);
    public List<StudentsResultsDto> getActiveStudentsWithResults() ;


    Optional<StudentDto> findStudentByRollNumberAndGrade(DeleteStudentRequestDto deleteStudentRequestDto);

    void updateStudent(StudentDto studentDto);
}
