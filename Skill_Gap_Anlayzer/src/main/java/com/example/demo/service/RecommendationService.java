package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RecommendationService {

    public Map<String, List<String>> getRecommendations(List<String> missingSkills) {

        Map<String, List<String>> map = new HashMap<>();

        for (String skill : missingSkills) {

            List<String> links = new ArrayList<>();
            links.add("https://www.coursera.org/search?query=" + skill);
            links.add("https://www.udemy.com/courses/search/?q=" + skill);
            links.add("https://www.edx.org/search?q=" + skill);

            map.put(skill, links);
        }

        return map;
    }
}