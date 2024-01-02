package com.mywhoosh.studentresultManagment.domain.repoadapter.impl;

import com.mywhoosh.studentresultManagment.domain.dto.StudentResultDto;
import com.mywhoosh.studentresultManagment.domain.repo.StudentResultRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentResultRepoAdapterImplTest {


    @Mock
    private StudentResultRepo studentResultRepo;

    @InjectMocks
    private StudentResultRepoAdapterImpl studentResultRepoAdapter;



    @Test
    void delete_ShouldInvokeRepositoryDelete() {
        StudentResultDto studentResultDto = new StudentResultDto();
        studentResultDto.setId("resultId");
        studentResultRepoAdapter.delete(studentResultDto);
        verify(studentResultRepo).delete("resultId");
    }

}
