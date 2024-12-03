package com.project.tracker.service;

import com.project.tracker.model.Project;
import com.project.tracker.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project project) {
        Project existingProject = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
        existingProject.setName(project.getName());
        existingProject.setDescription(project.getDescription());
        existingProject.setStartDate(project.getStartDate());
        existingProject.setEndDate(project.getEndDate());
        existingProject.setStatus(project.getStatus());
        return projectRepository.save(existingProject);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}

