package com.amenal.amenalbackend.budget.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.adapter.out.postgres.entities.ProjectEntity;
import com.amenal.amenalbackend.budget.adapter.out.postgres.repositories.ProjectRepository;
import com.amenal.amenalbackend.budget.application.domain.Project;
import com.amenal.amenalbackend.budget.application.port.out.ProjectDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ProjectDaoAdapter implements ProjectDao {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Project findProjectById(Integer id) {
		ProjectEntity projectEntity = projectRepository.findById(id).get();
		Project project = modelMapper.map(projectEntity, Project.class);
		return project;
	}

	@Override
	public List<Project> findAllProjects() {
		List<ProjectEntity> projectEntities = projectRepository.findAll();
		return projectEntities.stream().map(projectEntity -> modelMapper.map(projectEntity, Project.class))
				.collect(Collectors.toList());
	}

	@Override
	public Project saveProject(Project project) {
		ProjectEntity projectEntity = modelMapper.map(project, ProjectEntity.class);
		ProjectEntity savedEntity = projectRepository.save(projectEntity);
		return modelMapper.map(savedEntity, Project.class);
	}

	@Override
	public Project updateProject(Project project) {
		ProjectEntity existingEntity = projectRepository.findById(project.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from Project to existingEntity
		modelMapper.map(project, existingEntity);

		ProjectEntity updatedEntity = projectRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, Project.class);
	}

	@Override
	public void deleteProject(Integer id) {
		// Check if Project with the given ID exists
		ProjectEntity projectEntity = projectRepository.findById(id).orElseThrow();

		// Delete the entity
		projectRepository.delete(projectEntity);
	}

}
