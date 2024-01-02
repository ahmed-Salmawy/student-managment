package com.mywhoosh.studentresultManagment.domain.controller;

import com.mywhoosh.studentresultManagment.base.AbstractBaseController;
import com.mywhoosh.studentresultManagment.domain.dto.StudentsResultsDto;
import com.mywhoosh.studentresultManagment.domain.service.StudentResultManagementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentResultController extends AbstractBaseController<StudentResultManagementService> {
    public StudentResultController(StudentResultManagementService service) {
        super(service);
    }

    @GetMapping
    public List<StudentsResultsDto> getStudents() {
        return service.getStudentsResults();
    }
    @GetMapping("/result/{rollNumber}")
    public StudentsResultsDto getStudentResult(@PathVariable("rollNumber") Integer rollNumber) {
        return service.getStudentResults(rollNumber);
    }







}
