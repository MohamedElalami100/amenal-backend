package com.amenal.amenalbackend.budget.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.application.domain.Project;

public interface ProjectDao {
	Project findProjectById(Integer id);
	
	List<Project> findAllProjects();
	
	Project saveProject(Project project);
	
	Project updateProject(Project project);
	
	void deleteProject(Integer id);
	
}
