package com.mywhoosh.studentresultManagment.base;

public abstract class AbstractBaseService <D extends AbstractBaseDto,R extends BaseRepoAdapter<D>>
implements BaseService{
    protected final R repoAdapter;
    protected AbstractBaseService(R repoAdapter) {
        this.repoAdapter = repoAdapter;
    }
}
