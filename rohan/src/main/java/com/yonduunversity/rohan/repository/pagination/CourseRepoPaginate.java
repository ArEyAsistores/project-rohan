package com.yonduunversity.rohan.repository.pagination;

import com.yonduunversity.rohan.models.Course;
import com.yonduunversity.rohan.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;

////////////////
/// UserPaginate
//////////////////
public interface CourseRepoPaginate extends PagingAndSortingRepository<Course, Long> {

}
