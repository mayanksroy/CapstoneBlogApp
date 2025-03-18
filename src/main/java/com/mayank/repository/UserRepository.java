package com.mayank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mayank.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	public Optional<User> findByEmail(String email);
}
