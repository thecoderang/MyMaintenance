package com.thecoderang.MyMaintenance.service;

import com.thecoderang.MyMaintenance.entity.Issue;
import com.thecoderang.MyMaintenance.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class IssueService {
    @Autowired
    private IssueRepository issueRepository;

    public List<Issue> getIssues() {
        return issueRepository.findAll();
    }

    public List<Issue> getIssuesByUserID(int userID) {
        return issueRepository.getIssuesByID(userID);
    }

    public void createIssue(Issue issue) {
        issue.setCreated_at(Calendar.getInstance());
        issueRepository.save(issue);
    }

    public boolean acknowledgeIssue(int issueID, int userID) {
        Issue existingIssue = issueRepository.findById(issueID).orElse(null);
        if (existingIssue != null) {
            if (existingIssue.getAssigned_user_id() != userID) {
                return false;
            }
            existingIssue.setStatus("In Progress");
            existingIssue.setUpdated_at(Calendar.getInstance());
            issueRepository.save(existingIssue);
            return true;
        }
        return false;
    }

    public boolean completeIssue(int issueID, int userID) {
        Issue existingIssue = issueRepository.findById(issueID).orElse(null);
        if (existingIssue != null) {
            if (existingIssue.getAssigned_user_id() != userID) {
                return false;
            }
            existingIssue.setStatus("Completed");
            existingIssue.setUpdated_at(Calendar.getInstance());
            issueRepository.save(existingIssue);
            return true;
        }
        return false;
    }
}
