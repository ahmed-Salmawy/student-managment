package com.mywhoosh.studentresultManagment.domain.service;

import com.mywhoosh.studentresultManagment.base.BaseService;
import com.mywhoosh.studentresultManagment.domain.dto.DeleteStudentRequestDto;
import com.mywhoosh.studentresultManagment.domain.dto.StudentRequestDto;
import com.mywhoosh.studentresultManagment.domain.dto.StudentResultRequestDto;
import com.mywhoosh.studentresultManagment.domain.dto.StudentsResultsDto;

import java.util.List;

public interface StudentResultManagementService extends BaseService {

   String addNewStudent(StudentRequestDto studentRequest);
   String saveStudentResult(StudentResultRequestDto resultRequestDto);

   List<StudentsResultsDto> getStudentsResults();

   String deleteStudent(DeleteStudentRequestDto deleteStudentRequestDto);

   StudentsResultsDto getStudentResults(Integer rollNumber);
}
