package com.mywhoosh.studentresultManagment;


import com.mywhoosh.studentresultManagment.domain.dto.StudentRequestDto;
import com.mywhoosh.studentresultManagment.domain.service.StudentResultManagementService;
import com.mywhoosh.studentresultManagment.security.dto.RegisterRequest;
import com.mywhoosh.studentresultManagment.security.dto.UserDto;
import com.mywhoosh.studentresultManagment.security.service.UserRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Slf4j
public class StudentResultManagementApplication implements CommandLineRunner {

    @Value("${app.user}")
    String appUser;
    @Value("${app.password}")
    String password;
    @Autowired
    StudentResultManagementService studentResultManagementService;
  @Autowired
  UserRegistrationService userRegistrationService;

    public static void main(String[] args) {
        SpringApplication.run(StudentResultManagementApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        RegisterRequest registerRequest=RegisterRequest.builder().username(appUser).password(password).build();
        userRegistrationService.register(registerRequest);
        log.info("user registered successfully {}",registerRequest);

        getSampleStudents().stream().forEach(s ->
                studentResultManagementService.addNewStudent(s));
        log.info("sample students registered successfully{}",registerRequest);
    }

    private List<StudentRequestDto> getSampleStudents() {
        StudentRequestDto student1 = StudentRequestDto.builder()
                .name("John Doe")
                .rollNumber(1)
                .fatherName("Mr. Doe")
                .grade(5)
                .build();
        StudentRequestDto student2 = StudentRequestDto.builder()
                .name("Jane Smith")
                .rollNumber(2)
                .fatherName("Mr. Smith")
                .grade(5)
                .build();
        StudentRequestDto student3 = StudentRequestDto.builder()
                .name("Min Student")
                .rollNumber(3)
                .fatherName("Min Father")
                .grade(1)
                .build();
        StudentRequestDto student4 = StudentRequestDto.builder()
                .name("Max Student")
                .rollNumber(4)
                .fatherName("Max Father")
                .grade(5)
                .build();
        StudentRequestDto student5 = StudentRequestDto.builder()
                .name("lili")
                .rollNumber(5)
                .fatherName("Elsalmawy")
                .grade(5)
                .build();
        StudentRequestDto student6 = StudentRequestDto.builder()
                .name("ahmed")
                .rollNumber(6)
                .fatherName("elsalmawy")
                .grade(5)
                .build();
        return Arrays.asList(student1, student2, student3, student4, student5, student6);

    }
}
