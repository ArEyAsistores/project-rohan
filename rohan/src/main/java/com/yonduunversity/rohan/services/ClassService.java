package com.yonduunversity.rohan.services;

import com.yonduunversity.rohan.models.*;
import com.yonduunversity.rohan.models.dto.ClassCourseDTO;
import com.yonduunversity.rohan.models.dto.StudentDTO;

import java.util.List;

public interface ClassService {
    ClassBatch saveClass(ClassBatch classBatch, String whoAdded) throws Exception;
    ClassBatch enrollStudent(String email, String code,long batchNumber) throws Exception;
    ClassBatch unEnrollStudent(String email, String code, long batchNumber) throws Exception;
    ClassBatch deactivateClass(String code, long batchNumber) throws Exception;
    List<ClassBatch> findStudentClass(String email);
    List<ClassBatch>getAllClassBatch();
    List<StudentDTO> getClassStudents(String code, long batchNumber, int pageNumber, int pageSize);
    List<ClassBatch> getAllClassBatch(int pageNumber, int pageSize);
    List<ClassCourseDTO>getClassByKeyword(String keyword, int pageNumber, int pageSize);

}
