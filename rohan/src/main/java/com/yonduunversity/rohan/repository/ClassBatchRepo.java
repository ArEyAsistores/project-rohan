package com.yonduunversity.rohan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yonduunversity.rohan.models.ClassBatch;

public interface ClassBatchRepo extends JpaRepository<ClassBatch, Long> {

    ClassBatch findClassBatchByCourseCodeAndId(String code, long batch);

}
