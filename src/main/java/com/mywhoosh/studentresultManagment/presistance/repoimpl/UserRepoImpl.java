package com.mywhoosh.studentresultManagment.presistance.repoimpl;

import com.mywhoosh.studentresultManagment.presistance.entity.UserEntity;
import com.mywhoosh.studentresultManagment.security.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepoImpl extends AbstractBaseRepo<UserEntity, String> implements UserRepository {


    @Autowired
    protected UserRepoImpl( MongoTemplate mongoTemplate) {
        super(UserEntity.class, mongoTemplate);
    }




    @Override
    public Optional<UserEntity> findByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return Optional.ofNullable(mongoTemplate.findOne(query,UserEntity.class));
    }
}
