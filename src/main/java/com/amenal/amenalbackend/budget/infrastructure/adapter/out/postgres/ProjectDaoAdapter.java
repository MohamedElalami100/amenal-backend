package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import com.amenal.amenalbackend.budget.core.domain.Project;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.ProjectEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.core.domain.Project;
import com.amenal.amenalbackend.budget.core.port.out.ProjectDao;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.ProjectEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.ProjectRepository;

import lombok.RequiredArgsConstructor;

import static com.amenal.amenalbackend.utils.infrastructure.Methods.Copy.copyNonNullProperties;

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
		ProjectEntity newEntity = modelMapper.map(project, ProjectEntity.class);

		copyNonNullProperties(newEntity, existingEntity);

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
