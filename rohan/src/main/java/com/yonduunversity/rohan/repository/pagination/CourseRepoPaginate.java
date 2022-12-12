package com.yonduunversity.rohan.repository.pagination;
import com.yonduunversity.rohan.models.Course;

import com.yonduunversity.rohan.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


////////////////
/// UserPaginate
//////////////////
public interface CourseRepoPaginate extends PagingAndSortingRepository<Course, Long> {
    @Query("SELECT p FROM Course p WHERE p.code LIKE %?1%"
            + " OR p.title LIKE %?1%"
            + " OR p.description LIKE %?1%")
    List<Course> findAllByKeyword(String keyword, Pageable pageable);
}
