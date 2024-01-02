package com.mywhoosh.studentresultManagment.base;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Document
public abstract class AbstractBaseEntity implements Serializable {
    @Id
    private String id;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private String updatedBy;
    private String createdBy;






}
