package com.yonduunversity.rohan.repository.pagination;

import com.yonduunversity.rohan.models.ClassBatch;
import com.yonduunversity.rohan.models.User;
import com.yonduunversity.rohan.models.student.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

////////////////
/// UserPaginate
//////////////////
public interface StudentRepoPaginate extends PagingAndSortingRepository<Student, Long> {
    @Query("SELECT p FROM Student p WHERE p.email LIKE %?1%"
            + " OR p.firstname LIKE %?1%"
            + " OR p.lastname LIKE %?1%")
    List<Student> findAllByKeyword(String keyword, Pageable pageable);

    List<Student> findStudentByClassBatches(ClassBatch classBatch, Pageable pageable);
}
