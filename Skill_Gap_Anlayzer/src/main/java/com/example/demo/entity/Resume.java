package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Lob
    private byte[] fileData;  // store actual file bytes

    @Lob
    private String extractedText;

    @ManyToOne
    @JoinColumn(name = "job_role_id")
    private JobRole jobRole;  // link resume to a job role

    public Resume() {}

    // getters and setters
    public Long getId() { return id; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public byte[] getFileData() { return fileData; }
    public void setFileData(byte[] fileData) { this.fileData = fileData; }

    public String getExtractedText() { return extractedText; }
    public void setExtractedText(String extractedText) { this.extractedText = extractedText; }

    public JobRole getJobRole() { return jobRole; }
    public void setJobRole(JobRole jobRole) { this.jobRole = jobRole; }
}