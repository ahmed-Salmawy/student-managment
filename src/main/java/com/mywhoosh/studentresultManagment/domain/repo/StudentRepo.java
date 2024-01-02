package com.mywhoosh.studentresultManagment.domain.repo;

import com.mywhoosh.studentresultManagment.base.BaseRepository;
import com.mywhoosh.studentresultManagment.domain.dto.StudentsResultsDto;
import com.mywhoosh.studentresultManagment.presistance.entity.StudentEntity;

import java.util.List;

public interface StudentRepo extends BaseRepository<StudentEntity,String> {
    StudentEntity findByRollNumber(Integer rollNumber);

    List<StudentsResultsDto> getActiveStudentsWithResults();
}
