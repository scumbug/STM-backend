package com.wongc.stm.repository;

import com.wongc.stm.model.User;

import com.wongc.stm.model.enums.UserType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User findByUsername(String username);
	List<User> findByType(UserType type);
    
}
