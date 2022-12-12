package com.yonduunversity.rohan.repository.pagination;

import com.yonduunversity.rohan.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

////////////////
/// UserPaginate
//////////////////
public interface UserRepoPaginate extends PagingAndSortingRepository<User, Long> {
    @Query("SELECT p FROM User p WHERE p.email LIKE %?1%"
            + " OR p.firstname LIKE %?1%"
            + " OR p.lastname LIKE %?1%")
    List<User> findAllByKeyword(String keyword, Pageable pageable);
}
