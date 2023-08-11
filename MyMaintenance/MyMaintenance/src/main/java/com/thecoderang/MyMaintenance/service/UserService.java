package com.thecoderang.MyMaintenance.service;

import com.thecoderang.MyMaintenance.entity.User;
import com.thecoderang.MyMaintenance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void createEngineer(User user) {
        user.setCreated_at(Calendar.getInstance());
        userRepository.save(user);
    }

    public void createAdmin(User user) {
        user.setCreated_at(Calendar.getInstance());
        userRepository.save(user);
    }

    public boolean verifyAdmin(int userID) {
        int count = userRepository.verifyAdmin(userID);
        return count == 1;
    }

    public boolean verifyEngineer(int userID) {
        int count = userRepository.verifyEngineer(userID);
        return count == 1;
    }
}
