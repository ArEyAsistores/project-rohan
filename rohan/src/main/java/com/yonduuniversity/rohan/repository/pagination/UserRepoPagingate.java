package com.yonduuniversity.rohan.repository.pagination;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yonduuniversity.rohan.models.User;

import java.util.List;

////////////////
/// UserPaginate
//////////////////
public interface UserRepoPagingate extends PagingAndSortingRepository<User, Long> {

}
