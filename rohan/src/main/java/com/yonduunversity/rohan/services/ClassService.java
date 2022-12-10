package com.yonduunversity.rohan.services;

import com.yonduunversity.rohan.models.ClassBatch;
import com.yonduunversity.rohan.models.Course;
import com.yonduunversity.rohan.models.Role;
import com.yonduunversity.rohan.models.User;
import com.yonduunversity.rohan.models.student.Student;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ClassService {
    ClassBatch saveClass(ClassBatch classBatch, String whoAdded) throws Exception;
    ClassBatch enrollStudent(String email, String code,long batchNumber);
    ClassBatch unEnrollStudent(String email, String code, long batchNumber);
    ClassBatch deactivateClass(String code, long batchNumber);
    List<ClassBatch> findStudentClass(String email);
    List<ClassBatch>getAllClassBatch();
    ClassBatch getClassStudents(String code, long batchNumber);

}
