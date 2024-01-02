package com.mywhoosh.studentresultManagment.domain.dto;

import com.mywhoosh.studentresultManagment.base.AbstractBaseDto;
import com.mywhoosh.studentresultManagment.domain.enums.RemarkEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResultDto extends AbstractBaseDto {
    private String Id;
    private Double totalMarks;
    private Double obtainedMarks;
    private Integer rollNumber;
    private RemarkEnum remarks;

}
