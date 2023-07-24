package com.example.demo.repository;

import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {
    @Query("select u from Users u " + "where u.userId = :userId")
    Users findById(@Param("userId") int userId);

    @Query("select u from Users u " + "where u.email = :email")
    Users findByEmail(@Param("email") String email);
}
