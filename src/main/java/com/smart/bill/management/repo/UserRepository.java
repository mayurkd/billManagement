package com.smart.bill.management.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.bill.management.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);
}
