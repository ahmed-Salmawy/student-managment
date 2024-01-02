package com.mywhoosh.studentresultManagment.domain.repoadapter.impl;


import com.mywhoosh.studentresultManagment.base.AbstractBaseRepoAdapter;
import com.mywhoosh.studentresultManagment.domain.dto.StudentResultDto;
import com.mywhoosh.studentresultManagment.domain.mapper.StudentResultMapper;
import com.mywhoosh.studentresultManagment.domain.repo.StudentResultRepo;
import com.mywhoosh.studentresultManagment.domain.repoadapter.StudentResultRepoAdapter;
import com.mywhoosh.studentresultManagment.presistance.entity.StudentResultEntity;
import org.springframework.stereotype.Repository;

@Repository
public class StudentResultRepoAdapterImpl
        extends AbstractBaseRepoAdapter<StudentResultEntity, StudentResultDto, StudentResultMapper, StudentResultRepo>
        implements StudentResultRepoAdapter {


    protected StudentResultRepoAdapterImpl(StudentResultMapper mapper, StudentResultRepo repository) {
        super(mapper, repository);
    }

    @Override
    public void delete(StudentResultDto dto) {
        repository.delete(dto.getId());
    }


}
