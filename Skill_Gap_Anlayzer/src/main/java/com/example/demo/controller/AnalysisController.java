package com.example.demo.controller;

import com.example.demo.repository.JobRoleRepository;
import com.example.demo.service.ATSService;
import com.example.demo.service.SkillGapService;
import com.example.demo.service.RecommendationService;

import org.apache.tika.Tika;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/analyze")
@CrossOrigin
public class AnalysisController {

    private final ATSService atsService;
    private final SkillGapService skillGapService;
    private final RecommendationService recommendationService;
    private final JobRoleRepository jobRoleRepository;

    // ✅ Constructor injection for all final fields
    public AnalysisController(ATSService atsService,
                              SkillGapService skillGapService,
                              RecommendationService recommendationService,
                              JobRoleRepository jobRoleRepository) {
        this.atsService = atsService;
        this.skillGapService = skillGapService;
        this.recommendationService = recommendationService;
        this.jobRoleRepository = jobRoleRepository;
    }

    // ============================
    // 🔹 Analyze resume API
    // ============================
    @PostMapping(consumes = "multipart/form-data")
    public Map<String, Object> analyze(
            @RequestParam("file") MultipartFile file,
            @RequestParam("role") String role) {

        Map<String, Object> res = new HashMap<>();

        try {
            // Extract text from resume using Apache Tika
            Tika tika = new Tika();
            String text = tika.parseToString(file.getInputStream());

            // Fetch job role from repository
            var job = jobRoleRepository.findByRoleName(role)
                    .orElseThrow(() -> new RuntimeException("Role not found"));

            // Get required skills for the role
            List<String> skills = job.getRequiredSkills()
                    .stream()
                    .map(s -> s.getName())
                    .toList();

            // Calculate ATS score
            int score = atsService.calculateATSScore(text, skills);

            // Analyze skill gap
            Map<String, Object> gap = skillGapService.analyzeSkills(text, skills);
            List<String> missing = (List<String>) gap.get("missingSkills");

            // Get recommended courses for missing skills
            Map<String, List<String>> courses = recommendationService.getRecommendations(missing);

            // Prepare response
            res.put("ATS Score", score);
            res.put("Skill Gap", gap);
            res.put("Courses", courses);

        } catch (Exception e) {
            // Proper exception handling
            throw new RuntimeException("Error analyzing resume: " + e.getMessage(), e);
        }

        return res;
    }
}