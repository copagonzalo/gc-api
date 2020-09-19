package com.gc.api.dondevoy.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gc.api.dondevoy.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	User findByUsername(String username);
}
