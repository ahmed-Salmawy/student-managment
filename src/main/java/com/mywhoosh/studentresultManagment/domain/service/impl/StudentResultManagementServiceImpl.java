package com.mywhoosh.studentresultManagment.domain.service.impl;

import com.mywhoosh.studentresultManagment.base.AbstractBaseService;
import com.mywhoosh.studentresultManagment.domain.dto.*;
import com.mywhoosh.studentresultManagment.domain.enums.RemarkEnum;
import com.mywhoosh.studentresultManagment.domain.enums.StatusEnum;
import com.mywhoosh.studentresultManagment.domain.repoadapter.StudentRepoAdapter;
import com.mywhoosh.studentresultManagment.domain.repoadapter.StudentResultRepoAdapter;
import com.mywhoosh.studentresultManagment.domain.service.StudentResultManagementService;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentResultManagementServiceImpl extends AbstractBaseService<StudentDto, StudentRepoAdapter> implements StudentResultManagementService {
    private final StudentResultRepoAdapter resultRepoAdapter;

    protected StudentResultManagementServiceImpl(StudentRepoAdapter repoAdapter, StudentResultRepoAdapter resultRepoAdapter) {
        super(repoAdapter);
        this.resultRepoAdapter = resultRepoAdapter;
    }


    @Override
    public String addNewStudent(StudentRequestDto studentRequest) {
        try {
            StudentDto studentDto = StudentDto.builder().grade(studentRequest.getGrade())
                    .name(studentRequest.getName())
                    .fatherName(studentRequest.getFatherName())
                    .rollNumber(studentRequest.getRollNumber())
                    .status(StatusEnum.ACTIVE).build();
            repoAdapter.create(studentDto);
            return "Student added successfully :) ";
        } catch (DuplicateKeyException e) {
            return " Student registered before";
        }

    }

    @Override
    public String saveStudentResult(StudentResultRequestDto resultRequestDto) {
        log.info("request received {}", resultRequestDto);
        StudentDto studentDto = repoAdapter.findByRollNumber(resultRequestDto.getRollNumber());
        if (!ObjectUtils.isEmpty(studentDto)) {
            StudentResultDto studentResultDto = StudentResultDto.builder()
                    .rollNumber(resultRequestDto.getRollNumber())
                    .obtainedMarks(resultRequestDto.getObtainedMarks())
                    .remarks(resultRequestDto.getObtainedMarks() >= 50 ? RemarkEnum.PASSED : RemarkEnum.FAILED)
                    .totalMarks(resultRequestDto.getTotalMarks())
                    .build();
            log.info("building up result dto {}", studentResultDto);
            log.info("saving result to db");
            resultRepoAdapter.create(studentResultDto);
            log.info(" result is saved successfully db");
            return "Result is saved Successfully :)";
        } else {
            log.info("student wasn't found in db {} ", resultRequestDto);
            return "Sorry!,Student is not Registered :(";
        }
    }

    @Override
    public List<StudentsResultsDto> getStudentsResults() {
        List<StudentsResultsDto> sortedResults = repoAdapter.getActiveStudentsWithResults();
        for (int i = 0; i < sortedResults.size(); i++) {
            sortedResults.get(i).setPositionInClass(i + 1);
        }
        return sortedResults;
    }

    @Override
    public String deleteStudent(DeleteStudentRequestDto deleteStudentRequestDto) {
        Optional<StudentDto> studentDto = repoAdapter.findStudentByRollNumberAndGrade(deleteStudentRequestDto);
        studentDto.ifPresentOrElse(s -> {
            s.setStatus(StatusEnum.INACTIVE);
            repoAdapter.update(s);
            log.info("student deleted successfully");
        }, () -> log.info("student not Found :("));


        return studentDto.isPresent() ? "student deleted successfully" : "student not Found :(";
    }

    @Override
    public StudentsResultsDto getStudentResults(@NotEmpty Integer rollNumber) {

        return repoAdapter.getActiveStudentsWithResults().stream().filter(r -> r.getRollNumber().equals(rollNumber)).findAny().orElse(new StudentsResultsDto());
    }


}
