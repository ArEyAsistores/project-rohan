package com.yonduunversity.rohan.repository.pagination;

import com.yonduunversity.rohan.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
////////////////
/// UserPaginate
//////////////////
public interface UserRepoPagingate extends PagingAndSortingRepository<User, Long> {

}
