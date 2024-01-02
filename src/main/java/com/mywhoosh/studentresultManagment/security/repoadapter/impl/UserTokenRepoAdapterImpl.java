package com.mywhoosh.studentresultManagment.security.repoadapter.impl;

import com.mywhoosh.studentresultManagment.base.AbstractBaseRepoAdapter;
import com.mywhoosh.studentresultManagment.presistance.entity.TokenEntity;
import com.mywhoosh.studentresultManagment.security.dto.TokenDto;
import com.mywhoosh.studentresultManagment.security.mapper.TokenMapper;
import com.mywhoosh.studentresultManagment.security.repo.TokenRepository;
import com.mywhoosh.studentresultManagment.security.repoadapter.UserTokenRepoAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class UserTokenRepoAdapterImpl extends
        AbstractBaseRepoAdapter<TokenEntity, TokenDto, TokenMapper, TokenRepository> implements
        UserTokenRepoAdapter {
    protected UserTokenRepoAdapterImpl(TokenMapper mapper, TokenRepository repository) {
        super(mapper, repository);
    }

    @Override
    public String create(TokenDto dto) {
        TokenEntity tokenEntity = mapper.toEntity(dto);
        repository.save(tokenEntity);
        return repository.save(tokenEntity).getId();
    }


    @Override
    public void delete(TokenDto dto) {

    }


    @Override
    public List<TokenDto> findAllValidTokenByUser(String userId) {
        Map<String, Object> parameters = new HashMap();
        parameters.put(TokenEntity.Fields.user, userId);
        parameters.put(TokenEntity.Fields.expired, false);
        return Optional.ofNullable(repository.findManyByMap(parameters))
                .map(tokens ->
                        tokens.stream().map(mapper::toDto).collect(Collectors.toList()))
                .orElse(new LinkedList<>());
    }

    @Override
    public void saveAll(List<TokenDto> validUserTokens) {
        repository.update(validUserTokens.stream().map(mapper::toEntity).collect(Collectors.toList()));
    }

    @Override
    public Optional<TokenDto> findByToken(String token) {
        return repository.findByToken(token).map(mapper::toDto);
    }
}
