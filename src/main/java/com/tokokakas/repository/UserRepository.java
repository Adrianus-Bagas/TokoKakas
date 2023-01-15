package com.tokokakas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tokokakas.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	@Query("select count(*) from User where user_name=:user_name and password=:password")
	String checkUser(@Param("user_name") String user_name, @Param("password") String password);
}
