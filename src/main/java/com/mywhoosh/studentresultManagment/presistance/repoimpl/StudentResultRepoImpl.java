package com.mywhoosh.studentresultManagment.presistance.repoimpl;

import com.mywhoosh.studentresultManagment.domain.repo.StudentResultRepo;
import com.mywhoosh.studentresultManagment.presistance.entity.StudentResultEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentResultRepoImpl extends AbstractBaseRepo<StudentResultEntity, String> implements StudentResultRepo {
    protected StudentResultRepoImpl(MongoTemplate mongoTemplate) {
        super(StudentResultEntity.class, mongoTemplate);
    }
}
