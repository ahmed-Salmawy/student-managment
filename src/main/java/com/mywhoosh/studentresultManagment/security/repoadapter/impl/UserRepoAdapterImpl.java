package com.mywhoosh.studentresultManagment.security.repoadapter.impl;


import com.mywhoosh.studentresultManagment.base.AbstractBaseRepoAdapter;
import com.mywhoosh.studentresultManagment.presistance.entity.UserEntity;
import com.mywhoosh.studentresultManagment.security.dto.UserDto;
import com.mywhoosh.studentresultManagment.security.mapper.UserMapper;
import com.mywhoosh.studentresultManagment.security.repo.UserRepository;
import com.mywhoosh.studentresultManagment.security.repoadapter.UserRepoAdapter;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepoAdapterImpl
        extends AbstractBaseRepoAdapter<UserEntity, UserDto, UserMapper, UserRepository> implements
        UserRepoAdapter {

    protected UserRepoAdapterImpl(UserMapper mapper, UserRepository repository) {
        super(mapper, repository);

    }

/*
    @Override
    public String create(UserDto userDto) {
        UserEntity user = mapper.toEntity(userDto);
        return repository.save(user).getId();
    }
*/



    @Override
    public void delete(UserDto dto) {

    }

  /*  public Optional<UserDto> retrieve(String id) {
        return Optional.of(repository.findById(id).map(mapper::toDto).orElse(new UserDto()));
    }*/

    @Override
    public UserDto getUser(String username) {
        return repository.findByUsername(username).map(mapper::toDto).orElse(null);
    }
}
