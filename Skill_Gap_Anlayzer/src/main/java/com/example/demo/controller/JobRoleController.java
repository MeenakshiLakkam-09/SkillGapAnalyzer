package com.example.demo.controller;

import com.example.demo.entity.JobRole;
import com.example.demo.entity.Skill;
import com.example.demo.repository.JobRoleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobroles")
@CrossOrigin
public class JobRoleController 
{

    private final JobRoleRepository jobRoleRepository;

    public JobRoleController(JobRoleRepository jobRoleRepository) 
    {
        this.jobRoleRepository = jobRoleRepository;
    }

    // Add a new job role
    @PostMapping("/add")
    public JobRole addRole(@RequestBody JobRole jobRole)
    {
        return jobRoleRepository.save(jobRole);
    }

    // Get all job roles
    @GetMapping("/all")
    public List<JobRole> getAllRoles()
    {
        return jobRoleRepository.findAll();
    }

    // Get a job role by id
    @GetMapping("/{id}")
    public JobRole getRoleById(@PathVariable Long id)
    {
        return jobRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id " + id));
    }

    // Delete a job role by id
    @DeleteMapping("/delete/{id}")
    public String deleteRole(@PathVariable Long id) {
        jobRoleRepository.deleteById(id);
        return "Role with id " + id + " deleted successfully.";
    }
}
    