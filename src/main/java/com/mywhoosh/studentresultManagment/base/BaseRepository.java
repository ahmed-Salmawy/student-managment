package com.mywhoosh.studentresultManagment.base;

import java.util.List;
import java.util.Map;

public interface BaseRepository<E extends AbstractBaseEntity, ID> {
    E save(E E);

    List<E> saveAll(List<E> personEntities);

    List<E> findAll();

    List<E> findAll(List<ID> ids);

    E findById(ID id);

    List<E>  findManyByMap(Map<String, Object> parameters);

    long count();

    long delete(ID id);

    long delete(List<ID> ids);

    long deleteAll();

    E update(E E);

    long update(List<E> personEntities);

    E findOneByMap(Map<String, Object> parameters);


}
