package com.mywhoosh.studentresultManagment.presistance.entity;


import com.mywhoosh.studentresultManagment.base.AbstractBaseEntity;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


@Document("user_tokens")
@Data
@FieldNameConstants
public class TokenEntity extends AbstractBaseEntity {


    private String token;
    private String username;
    boolean expired;
    public boolean revoked;

    @DocumentReference(lazy = true)
    public UserEntity user;
}
