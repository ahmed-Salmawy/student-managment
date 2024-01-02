package com.mywhoosh.studentresultManagment.base;

public interface BaseMapper<E extends AbstractBaseEntity, D extends AbstractBaseDto> {

    public  D toDto(E entity);

    public  E toEntity(D dto);

}
