package com.mywhoosh.studentresultManagment.base;



import java.util.Optional;

public abstract class AbstractBaseRepoAdapter<E extends AbstractBaseEntity, D extends AbstractBaseDto
        , M extends BaseMapper<E, D>, R extends BaseRepository>
        implements BaseRepoAdapter<D> {

    protected final M mapper;
    protected final R repository;

    protected AbstractBaseRepoAdapter(M mapper, R repository) {
        this.mapper = mapper;
        this.repository = repository;

    }


    @Override
    public String create(D dto) {
        E entity = mapper.toEntity(dto);
        return repository.save(entity).getId();
    }

    @Override
    public void update(D dto) {
        E entity = mapper.toEntity(dto);
        repository.save(entity);
    }


    @Override
    public Optional<D> retrieve(String id) {
        return Optional.ofNullable(repository.findById(id)).map(e -> mapper.toDto((E) e));

    }


}
