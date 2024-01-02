package com.mywhoosh.studentresultManagment.security.repo;


import com.mywhoosh.studentresultManagment.base.BaseRepository;
import com.mywhoosh.studentresultManagment.presistance.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends BaseRepository<UserEntity,String> {

    Optional<UserEntity> findByUsername(String username);

}
