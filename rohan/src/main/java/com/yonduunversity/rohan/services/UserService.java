package com.yonduunversity.rohan.services;

import com.yonduunversity.rohan.models.*;

import java.util.List;
import com.yonduunversity.rohan.models.dto.StudentDTO;
import com.yonduunversity.rohan.models.dto.UserAccountDTO;
import com.yonduunversity.rohan.models.dto.UserDTO;
import com.yonduunversity.rohan.models.student.Student;

public interface UserService {


    UserAccountDTO saveUser(UserAccountDTO user, String whoAdded) throws Exception;
    UserAccountDTO defaultUsers(UserAccountDTO user) ;
    User saveUser(User user);
    ClassBatch enrollStudent(String email, String code,long batchNumber);
    ClassBatch unEnrollStudent(String email,  String code, long batchNumber);
    ClassBatch deactivateClass(String code, long batchNumber);
    List<ClassBatch> findStudentCourse();
    Role saveRole(Role role);
    void assignRole(String email, String roleName);
    void saveUser(Student student);
    List<User>getUsers();//Default getAllUser
    List<ClassBatch>getAllClassBatch();
    List<User>getUsers(int pageNumber, int pageSize);

    User getUser(String email);
    List<UserDTO>getUserByKeyword(String keyword, int pageNumber, int pageSize);
    List<StudentDTO>getStudentsByKeyword(String keyword, int pageNumber, int pageSize);
    StudentDTO getStudent(String email);
    User deactivateUser(String email);


}
