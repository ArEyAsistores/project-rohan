package com.yonduunversity.rohan.repository.pagination;
import com.yonduunversity.rohan.models.Course;

import org.springframework.data.repository.PagingAndSortingRepository;


////////////////
/// UserPaginate
//////////////////
public interface CourseRepoPaginate extends PagingAndSortingRepository<Course, Long> {

}
