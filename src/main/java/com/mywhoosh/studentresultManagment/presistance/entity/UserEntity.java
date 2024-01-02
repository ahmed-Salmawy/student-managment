package com.mywhoosh.studentresultManagment.presistance.entity;

import com.mywhoosh.studentresultManagment.base.AbstractBaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("users")
@Data
public class UserEntity extends AbstractBaseEntity {

    private String username;
    private String password;


}
