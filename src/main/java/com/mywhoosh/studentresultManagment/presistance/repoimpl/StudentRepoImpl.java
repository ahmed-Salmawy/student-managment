package com.mywhoosh.studentresultManagment.presistance.repoimpl;

import com.mywhoosh.studentresultManagment.domain.dto.StudentsResultsDto;
import com.mywhoosh.studentresultManagment.domain.repo.StudentRepo;
import com.mywhoosh.studentresultManagment.presistance.entity.StudentEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepoImpl extends AbstractBaseRepo<StudentEntity, String> implements StudentRepo {
    protected StudentRepoImpl(MongoTemplate mongoTemplate) {
        super(StudentEntity.class, mongoTemplate);
    }


    @Override
    public StudentEntity findByRollNumber(Integer rollNumber) {
        Query query = new Query(Criteria.where("rollNumber").is(rollNumber));
        return mongoTemplate.findOne(query, StudentEntity.class);
    }

    @Override
    public List<StudentsResultsDto> getActiveStudentsWithResults() {
        MatchOperation matchOperation = Aggregation.match(Criteria.where("status").is("ACTIVE"));

        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("results")
                .localField("rollNumber")
                .foreignField("rollNumber")
                .as("students_results");

        UnwindOperation unwindOperation = Aggregation.unwind("students_results");

        SortOperation sortOperation = Aggregation.sort(Sort.Direction.DESC, "students_results.obtainedMarks");

        ProjectionOperation projectionOperation = Aggregation.project()
                .and("name").as("name")
                .and("rollNumber").as("rollNumber")
                .and("fatherName").as("fatherName")
                .and("grade").as("grade")
                .and("status").as("status")
                .and("students_results.remarks").as("remarks")
                .and("students_results.totalMarks").as("totalMarks")
                .and("students_results.obtainedMarks").as("obtainedMarks");

        Aggregation aggregation = Aggregation.newAggregation(
                matchOperation,
                lookupOperation,
                unwindOperation,
                sortOperation,
                projectionOperation
        );

        return mongoTemplate.aggregate(aggregation, "students", StudentsResultsDto.class).getMappedResults();
    }
}
