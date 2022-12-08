package com.yonduunversity.rohan.repository;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yonduunversity.rohan.models.ClassBatch;

public interface ClassBatchRepo extends JpaRepository<ClassBatch, Long> {

}
