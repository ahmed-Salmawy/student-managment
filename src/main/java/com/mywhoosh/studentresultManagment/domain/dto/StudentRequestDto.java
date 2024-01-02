package com.mywhoosh.studentresultManagment.domain.dto;


import com.mywhoosh.studentresultManagment.base.AbstractBaseDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequestDto extends AbstractBaseDto {
    @NotEmpty(message = "name shouldn't be blank or empty")
    private String name;

    @Max(value = 100, message = "rollNumber should be less than 100")
    @Min(value = 1, message = "rollNumber should be greater than Zero")
    @NotNull(message = "roll number shouldn't be blank or empty")
    private Integer rollNumber;

    @NotEmpty(message = "father name shouldn't be blank or empty")
    private String fatherName;

    @Max(value = 100, message = "grade should be less than 100")
    @Min(value = 1, message = "grade should be greater than Zero")
    @NotNull(message = "grade  shouldn't be blank or empty")
    private Integer grade;
}
