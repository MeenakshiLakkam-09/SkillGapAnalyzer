package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ATSService {

    public int calculateATSScore(String resumeText, List<String> requiredSkills) {

        int matched = 0;

        for (String skill : requiredSkills) {
            if (resumeText.toLowerCase().contains(skill.toLowerCase())) {
                matched++;
            }
        }

        return (matched * 100) / requiredSkills.size();
    }
}