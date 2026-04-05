package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // optional: if you want bidirectional
    @ManyToMany(mappedBy = "requiredSkills")
    private Set<JobRole> jobRoles;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<JobRole> getJobRoles() { return jobRoles; }
    public void setJobRoles(Set<JobRole> jobRoles) { this.jobRoles = jobRoles; }
}