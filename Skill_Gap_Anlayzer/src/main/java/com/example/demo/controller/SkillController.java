package com.example.demo.controller;

import com.example.demo.entity.Skill;
import com.example.demo.repository.SkillRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
@CrossOrigin
public class SkillController {

    private final SkillRepository skillRepository;

    public SkillController(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    // Add a new skill
    @PostMapping("/add")
    public Skill addSkill(@RequestBody Skill skill) {
        return skillRepository.save(skill);
    }

    // Get all skills
    @GetMapping("/all")
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    // Get a skill by id
    @GetMapping("/{id}")
    public Skill getSkillById(@PathVariable Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found with id " + id));
    }

    // Delete a skill by id
    @DeleteMapping("/delete/{id}")
    public String deleteSkill(@PathVariable Long id) {
        skillRepository.deleteById(id);
        return "Skill with id " + id + " deleted successfully.";
    }
}