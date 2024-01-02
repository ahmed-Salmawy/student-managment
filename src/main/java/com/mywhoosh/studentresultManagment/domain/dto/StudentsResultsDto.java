package com.mywhoosh.studentresultManagment.domain.dto;

import com.mywhoosh.studentresultManagment.base.AbstractBaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentsResultsDto extends AbstractBaseDto {

    private String name;
    private Integer rollNumber;
    private String fatherName;
    private Integer grade;
    private String status;
    private String remarks;
    private int totalMarks;
    private int obtainedMarks;
    private int positionInClass;

}