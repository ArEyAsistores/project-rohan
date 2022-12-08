package com.yonduunversity.rohan.repository;

import com.yonduunversity.rohan.models.ClassBatch;
import com.yonduunversity.rohan.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClassBatchRepo extends JpaRepository<ClassBatch,Long> {
//       ClassBatch findClassBatchByBatch(long batch);
//    ClassBatch findClassBatchByStudents(Student student);
//@Query("SELECT p FROM ClassBatch p WHERE p.batch = %?1% AND p.code = LIKE %?1%")

    ClassBatch findClassBatchByCourseCodeAndBatch(String code, long batch);


//    @Query("SELECT p FROM Course p WHERE cast(p.courseCode as string ) LIKE %?1%"
//            + " OR p.title LIKE %?1%"
//            + " OR p.description LIKE %?1%")
//    List<Course> findAllByKeyword(String keyword);
}