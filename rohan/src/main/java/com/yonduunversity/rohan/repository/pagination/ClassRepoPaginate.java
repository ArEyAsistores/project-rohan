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
public interface ClassRepoPaginate extends PagingAndSortingRepository<ClassBatch, Long> {
    @Query("SELECT p FROM ClassBatch p WHERE p.course.code LIKE %?1%"
            + " OR  p.course.title LIKE %?1%"
            + " OR  cast(p.startDate as string) LIKE %?1%"
            + " OR cast(p.endDate as string)  LIKE %?1%"
            + " OR  p.course.description LIKE %?1%")
    List<ClassBatch> findAllByKeyword(String keyword, Pageable pageable);
    List<ClassBatch> findClassBatchByStudents(Student student, Pageable pageable);
}
