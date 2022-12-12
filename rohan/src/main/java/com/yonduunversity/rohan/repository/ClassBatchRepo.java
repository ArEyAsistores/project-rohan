package com.yonduunversity.rohan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yonduunversity.rohan.models.ClassBatch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClassBatchRepo extends JpaRepository<ClassBatch, Long> {

    ClassBatch findClassBatchByCourseCodeAndId(String code, long batch);

    @Query("SELECT COUNT(u) FROM ClassBatch u WHERE u.course.code =:code")
    long findClassBatchByCourseCode(@Param("code") String code);

    @Query("SELECT u FROM ClassBatch u WHERE u.course.code =:code AND u.batch =:batch ")
    ClassBatch findClassBatchByCourseCodeAndBatch(@Param("code") String code, @Param("batch") Long batch);

}
