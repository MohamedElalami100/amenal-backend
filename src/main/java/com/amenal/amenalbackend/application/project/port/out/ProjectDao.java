package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.Project;

public interface ProjectDao {
	Project findProjectById(Integer id);
	
	List<Project> findAllProjects();
	
	Project saveProject(Project project);
	
	Project updateProject(Project project);
	
	void deleteProject(Integer id);
	
}
