package com.mywhoosh.studentresultManagment.domain.dto;

import com.mywhoosh.studentresultManagment.base.AbstractBaseDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResultRequestDto extends AbstractBaseDto {
    @Max(value = 100, message = "total marks should be less than 100")
    @Min(value = 1, message = "total marks should be greater than Zero")
    @NotNull(message = "total marks shouldn't be blank or empty")
    private Double totalMarks;
    @Max(value = 100, message = "obtained marks should be less than 100")
    @Min(value = 1, message = "obtained marks should be greater than Zero")
    @NotNull(message = "obtained marks shouldn't be blank or empty")
    private Double obtainedMarks;

    @Max(value = 100, message = "rollNumber should be less than 100")
    @Min(value = 1, message = "rollNumber should be greater than Zero")
    @NotNull(message = "roll number shouldn't be blank or empty")
    private Integer rollNumber;

    
}