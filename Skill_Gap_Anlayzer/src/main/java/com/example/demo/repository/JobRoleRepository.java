package com.example.demo.repository;

import com.example.demo.entity.JobRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JobRoleRepository extends JpaRepository<JobRole, Long> {
    Optional<JobRole> findByRoleName(String roleName);
}