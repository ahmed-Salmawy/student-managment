package com.mywhoosh.studentresultManagment.domain.dto;

import com.mywhoosh.studentresultManagment.base.AbstractBaseDto;
import com.mywhoosh.studentresultManagment.domain.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto extends AbstractBaseDto {

    private String id;
    private String name;
    private Integer rollNumber;
    private String fatherName;
    private Integer grade;
    private StatusEnum status=StatusEnum.ACTIVE;
    private int positionInClass;


}