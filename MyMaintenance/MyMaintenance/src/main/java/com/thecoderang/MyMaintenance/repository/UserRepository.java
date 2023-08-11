package com.thecoderang.MyMaintenance.repository;

import com.thecoderang.MyMaintenance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM USERS WHERE USER_ID = :userID AND USER_ROLE = 'Admin'")
    int verifyAdmin(@Param(value = "userID") int userID);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM USERS WHERE USER_ID = :userID AND USER_ROLE = 'Engineer'")
    int verifyEngineer(@Param(value = "userID") int userID);
}
