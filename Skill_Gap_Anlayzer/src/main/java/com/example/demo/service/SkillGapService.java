package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SkillGapService {

    public Map<String, Object> analyzeSkills(String resumeText, List<String> requiredSkills) {

        List<String> matched = new ArrayList<>();
        List<String> missing = new ArrayList<>();

        for (String skill : requiredSkills) {
            if (resumeText.toLowerCase().contains(skill.toLowerCase())) {
                matched.add(skill);
            } else {
                missing.add(skill);
            }
        }

        int percent = (matched.size() * 100) / requiredSkills.size();

        Map<String, Object> result = new HashMap<>();
        result.put("matchedSkills", matched);
        result.put("missingSkills", missing);
        result.put("matchPercentage", percent);

        return result;
    }
}