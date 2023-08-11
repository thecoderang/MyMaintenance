package com.thecoderang.MyMaintenance.controller;

import com.thecoderang.MyMaintenance.entity.Issue;
import com.thecoderang.MyMaintenance.service.IssueService;
import com.thecoderang.MyMaintenance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueController {
    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public ResponseEntity<List<Issue>> getAllIssues(@RequestHeader(value = "user_id") int userID) {
        boolean verified = userService.verifyAdmin(userID);
        if (verified) {
            return new ResponseEntity<>(issueService.getIssues(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{userID}")
    public List<Issue> getIssuesByID(@PathVariable int userID) {
        return issueService.getIssuesByUserID(userID);
    }

    @PostMapping("/createIssue")
    public ResponseEntity<String> createIssue(@RequestHeader(value = "user_id") int userID, @RequestBody Issue issue) {
        boolean verified = userService.verifyAdmin(userID);
        if (verified) {
            issue.setCreator_user_id(userID);
            issueService.createIssue(issue);
            return new ResponseEntity<>("Issue created and assigned!", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Not an admin!", HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/acknowledgeIssue/{issueID}")
    public ResponseEntity<String> acknowledgeIssue(@RequestHeader(value = "user_id") int userID, @PathVariable int issueID) {
        boolean verified = userService.verifyEngineer(userID);
        if (verified) {
            boolean updated = issueService.acknowledgeIssue(issueID, userID);
            if (updated) {
                return new ResponseEntity<>("Issue acknowledged!", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Unable to find issue", HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return new ResponseEntity<>("Not an engineer!", HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/completeIssue/{issueID}")
    public ResponseEntity<String> completeIssue(@RequestHeader(value = "user_id") int userID, @PathVariable int issueID) {
        boolean verified = userService.verifyEngineer(userID);
        if (verified) {
            boolean updated = issueService.completeIssue(issueID, userID);
            if (updated) {
                return new ResponseEntity<>("Issue fixed!", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Unable to find issue", HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return new ResponseEntity<>("Not an engineer!", HttpStatus.FORBIDDEN);
        }
    }
}
