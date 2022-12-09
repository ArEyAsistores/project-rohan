package com.yonduunversity.rohan.repository;

import com.yonduunversity.rohan.models.ClassBatch;
import com.yonduunversity.rohan.models.Course;
import com.yonduunversity.rohan.models.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ClassBatchRepo extends JpaRepository<ClassBatch,Long> {

//    @Query("SELECT p.batch as ITLOG, p.course, p.sme FROM ClassBatch p")
    ClassBatch findClassBatchByCourseCodeAndBatch(String code, long batch);
//    ClassBatch findClassBatchByStudentsIn(Collection<Student> student);


}