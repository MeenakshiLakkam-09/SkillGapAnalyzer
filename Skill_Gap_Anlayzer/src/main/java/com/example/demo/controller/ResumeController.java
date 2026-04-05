package com.example.demo.controller;

import com.example.demo.entity.JobRole;
import com.example.demo.entity.Resume;
import com.example.demo.repository.JobRoleRepository;
import com.example.demo.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/resumes")
@CrossOrigin
public class ResumeController {

    private final ResumeRepository resumeRepository;
    private final JobRoleRepository jobRoleRepository;

    @Autowired
    public ResumeController(ResumeRepository resumeRepository, JobRoleRepository jobRoleRepository) {
        this.resumeRepository = resumeRepository;
        this.jobRoleRepository = jobRoleRepository;
    }

    // Upload resume and associate with a job role
    @PostMapping("/upload")
    public String uploadResume(@RequestParam("file") MultipartFile file,
                               @RequestParam("jobRoleId") Long jobRoleId) throws IOException {

        JobRole jobRole = jobRoleRepository.findById(jobRoleId)
                .orElseThrow(() -> new RuntimeException("Job role not found"));

        Resume resume = new Resume();
        resume.setFileName(file.getOriginalFilename());
        resume.setFileData(file.getBytes()); // store actual file data
        resume.setExtractedText("Extracted text here"); // optional: parse text from file
        resume.setJobRole(jobRole);

        resumeRepository.save(resume);

        return "Resume uploaded successfully for role: " + jobRole.getRoleName();
    }

    // Get all resumes
    @GetMapping("/all")
    public Iterable<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }

    // Get resume by id
    @GetMapping("/{id}")
    public Resume getResumeById(@PathVariable Long id) {
        return resumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resume not found with id " + id));
    }

    // Delete resume by id
    @DeleteMapping("/delete/{id}")
    public String deleteResume(@PathVariable Long id) {
        resumeRepository.deleteById(id);
        return "Resume with id " + id + " deleted successfully.";
    }
}