package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class JobRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

    @ManyToMany
    private List<Skill> requiredSkills;

    public JobRole() {}

    public Long getId() { return id; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    public List<Skill> getRequiredSkills() { return requiredSkills; }
    public void setRequiredSkills(List<Skill> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }
}