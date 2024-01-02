package com.mywhoosh.studentresultManagment.presistance.repoimpl;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mywhoosh.studentresultManagment.base.AbstractBaseEntity;
import com.mywhoosh.studentresultManagment.base.BaseRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.FindAndReplaceOptions.options;
import static org.springframework.data.mongodb.core.query.Query.query;

public abstract class AbstractBaseRepo<E extends AbstractBaseEntity, ID> implements BaseRepository<E, ID> {

    private final Class<E> entityTypeClass;
    protected static final TransactionOptions txnOptions = TransactionOptions.builder()
            .readPreference(ReadPreference.primary())
            .readConcern(ReadConcern.MAJORITY)
            .writeConcern(WriteConcern.MAJORITY)
            .build();


    protected final MongoTemplate mongoTemplate;


    protected AbstractBaseRepo(Class<E> entityTypeClass, MongoTemplate mongoTemplate) {
        this.entityTypeClass = entityTypeClass;
        this.mongoTemplate = mongoTemplate;
    }


    public E save(E entity) {
        mongoTemplate.save(entity);
        return entity;
    }

    @Override
    @Transactional
    public List<E> saveAll(List<E> entities) {
        mongoTemplate.insertAll(entities);
        return entities;
    }


    @Override
    public List<E> findAll() {
        return mongoTemplate.findAll(entityTypeClass);
    }

    @Override
    public List<E> findAll(List<ID> ids) {
        Query query = new Query(Criteria.where("_id").in(ids));
        return mongoTemplate.find(query, entityTypeClass);
    }

    @Override
    public E findById(ID id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, entityTypeClass);
    }

    @Override
    public E findOneByMap(Map<String, Object> parameters) {
        Query query = new Query();
        parameters.forEach((key, value) -> query.addCriteria(Criteria.where(key).is(value)));
        return mongoTemplate.findOne(query, entityTypeClass);
    }

    @Override
    public List<E> findManyByMap(Map<String, Object> parameters) {
        Query query = new Query();
        parameters.forEach((key, value) -> query.addCriteria(Criteria.where(key).is(value)));
        return mongoTemplate.find(query, entityTypeClass);
    }

    @Override
    public long count() {
        return mongoTemplate.count(new Query(), entityTypeClass);
    }

    @Override
    public long delete(ID id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.remove(query, entityTypeClass).getDeletedCount();
    }

    @Override
    @Transactional
    public long delete(List<ID> ids) {

        return mongoTemplate.remove(query(Criteria.where("_id").in(ids)), entityTypeClass).getDeletedCount();
    }

    @Override
    public long deleteAll() {
        return mongoTemplate.remove(new Query(), entityTypeClass).getDeletedCount();
    }

    @Override
    public E update(E entity) {

        return mongoTemplate.findAndReplace(query(Criteria.where("_id").is(entity.getId())), entity, options().returnNew());
    }

    @Override
    public long update(List<E> entities) {
        return 0L;
    }

}
