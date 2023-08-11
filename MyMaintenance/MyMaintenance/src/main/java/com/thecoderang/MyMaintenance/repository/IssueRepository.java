package com.thecoderang.MyMaintenance.repository;

import com.thecoderang.MyMaintenance.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Integer>{
    @Query(nativeQuery = true, value = "SELECT * FROM ISSUES WHERE ASSIGNED_USER_ID = :assignedID")
    List<Issue> getIssuesByID(@Param(value = "assignedID") int userID);
}
