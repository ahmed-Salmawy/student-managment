package com.mywhoosh.studentresultManagment.security.repoadapter;


import com.mywhoosh.studentresultManagment.base.BaseRepoAdapter;
import com.mywhoosh.studentresultManagment.security.dto.UserDto;

public interface UserRepoAdapter extends BaseRepoAdapter<UserDto> {

    UserDto getUser(String username);

}
