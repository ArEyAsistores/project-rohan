package com.yonduunversity.rohan.repository.pagination;

import com.yonduunversity.rohan.models.ClassBatch;
import com.yonduunversity.rohan.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

////////////////
/// UserPaginate
//////////////////
public interface ClassRepoPaginate extends PagingAndSortingRepository<ClassBatch, Long> {

}
