package com.ahinfotech.secureapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahinfotech.secureapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
