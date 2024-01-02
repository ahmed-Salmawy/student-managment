package com.mywhoosh.studentresultManagment.security.repoadapter;

import com.mywhoosh.studentresultManagment.base.BaseRepoAdapter;
import com.mywhoosh.studentresultManagment.security.dto.TokenDto;

import java.util.List;
import java.util.Optional;

public interface UserTokenRepoAdapter extends BaseRepoAdapter<TokenDto> {
    
   List<TokenDto> findAllValidTokenByUser(String userId);

    void saveAll(List<TokenDto> validUserTokens);

    Optional<TokenDto> findByToken(String token);
}
