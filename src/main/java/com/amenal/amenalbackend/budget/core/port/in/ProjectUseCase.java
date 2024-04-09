package com.amenal.amenalbackend.budget.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.Project;
import com.amenal.amenalbackend.budget.core.port.out.ProjectDao;

public class ProjectUseCase {
	
	private ProjectDao projectDao;
	
	public ProjectUseCase(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public Project findProjectById(Integer id) {
	    return projectDao.findProjectById(id);
	}

	public List<Project> findAllProjects() {
		return projectDao.findAllProjects();
	}
	
	public Project saveProject(Project project) {
		return projectDao.saveProject(project);
	}
	
	public Project updateProject(Project project) {
		return projectDao.updateProject(project);
	}
	
	public void deleteProject(Integer id) {
		projectDao.deleteProject(id);
	}
	

}
