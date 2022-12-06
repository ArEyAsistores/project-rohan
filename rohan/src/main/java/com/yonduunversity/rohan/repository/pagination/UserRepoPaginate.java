package com.yonduunversity.rohan.repository.pagination;

import com.yonduunversity.rohan.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;

////////////////
/// UserPaginate
//////////////////
public interface UserRepoPaginate extends PagingAndSortingRepository<User, Long> {

}
