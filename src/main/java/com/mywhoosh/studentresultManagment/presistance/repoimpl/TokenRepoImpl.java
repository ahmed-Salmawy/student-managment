package com.mywhoosh.studentresultManagment.presistance.repoimpl;

import com.mywhoosh.studentresultManagment.presistance.entity.TokenEntity;
import com.mywhoosh.studentresultManagment.security.repo.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TokenRepoImpl extends AbstractBaseRepo<TokenEntity, String> implements TokenRepository {


    @Autowired
    protected TokenRepoImpl(MongoTemplate mongoTemplate) {
        super(TokenEntity.class, mongoTemplate);
    }

    @Override
    public Optional<TokenEntity> findByToken(String token) {
        Query query = new Query(Criteria.where("token").is(token));
        return Optional.ofNullable(mongoTemplate.findOne(query, TokenEntity.class));

    }
}