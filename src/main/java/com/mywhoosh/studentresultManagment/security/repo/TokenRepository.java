package com.mywhoosh.studentresultManagment.security.repo;

import com.mywhoosh.studentresultManagment.base.BaseRepository;
import com.mywhoosh.studentresultManagment.presistance.entity.TokenEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends BaseRepository<TokenEntity,String>{

    Optional<TokenEntity> findByToken(String token);
}
