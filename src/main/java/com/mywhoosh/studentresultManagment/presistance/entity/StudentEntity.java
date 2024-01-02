package com.mywhoosh.studentresultManagment.presistance.entity;

import com.mywhoosh.studentresultManagment.base.AbstractBaseEntity;
import com.mywhoosh.studentresultManagment.domain.enums.StatusEnum;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document("students")
@FieldNameConstants
public class StudentEntity extends AbstractBaseEntity {


    private String name;
    private Integer rollNumber;
    private String fatherName;
    private Integer grade;
    private StatusEnum status;


    @Override
    public String getId() {
        return null;
    }
}
