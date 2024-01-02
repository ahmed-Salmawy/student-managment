package com.mywhoosh.studentresultManagment.domain.controller;

import com.mywhoosh.studentresultManagment.base.AbstractBaseController;
import com.mywhoosh.studentresultManagment.domain.dto.DeleteStudentRequestDto;
import com.mywhoosh.studentresultManagment.domain.dto.MessageResponseDto;
import com.mywhoosh.studentresultManagment.domain.dto.StudentRequestDto;
import com.mywhoosh.studentresultManagment.domain.dto.StudentResultRequestDto;
import com.mywhoosh.studentresultManagment.domain.service.StudentResultManagementService;
import com.mywhoosh.studentresultManagment.security.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class StudentSocketController extends AbstractBaseController<StudentResultManagementService> {
    public StudentSocketController(StudentResultManagementService service) {
        super(service);
    }

    @Autowired
    private AuthenticationService authenticationService;


    @MessageMapping("/student")
    @SendTo("/topic/students")
    public MessageResponseDto addStudent(@Valid StudentRequestDto requestDto) throws Exception {
        String result = service.addNewStudent(requestDto);
        return new MessageResponseDto(result);
    }



    @MessageMapping("/result")
    @SendTo("/topic/students")
    public MessageResponseDto saveResult(StudentResultRequestDto resultRequestDto) throws Exception {

        return new MessageResponseDto(service.saveStudentResult(resultRequestDto));
    }


    @MessageMapping("/delete-student")
    @SendTo("/topic/students")
    public MessageResponseDto deleteStudent(DeleteStudentRequestDto deleteStudentRequestDto) throws Exception {

        return new MessageResponseDto(service.deleteStudent(deleteStudentRequestDto));
    }


}
