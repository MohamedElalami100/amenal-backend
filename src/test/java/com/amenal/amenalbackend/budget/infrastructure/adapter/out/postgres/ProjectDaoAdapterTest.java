package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.budget.core.domain.Project;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.ProjectEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ProjectDaoAdapterTest {

    @Mock
    private ProjectRepository projectRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private ProjectDaoAdapter projectDaoAdapter;

    @Test
    void testFindProjectById() {
        ProjectEntity entity = new ProjectEntity();
        entity.setId(1);

        when(projectRepository.findById(1)).thenReturn(Optional.of(entity));

        Project result = projectDaoAdapter.findProjectById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllProjects() {
        when(projectRepository.findAll()).thenReturn(Collections.emptyList());

        List<Project> result = projectDaoAdapter.findAllProjects();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveProject() {
        Project project = new Project();
        project.setId(1);
        ProjectEntity entity = new ProjectEntity();
        entity.setId(1);

        when(projectRepository.save(any(ProjectEntity.class))).thenReturn(entity);

        Project result = projectDaoAdapter.saveProject(project);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateProject() {
        Project project = new Project();
        project.setId(1);
        ProjectEntity entity = new ProjectEntity();
        entity.setId(1);
        when(projectRepository.findById(1)).thenReturn(Optional.of(entity));
        when(projectRepository.save(any(ProjectEntity.class))).thenReturn(entity);

        Project result = projectDaoAdapter.updateProject(project);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteProject() {
        ProjectEntity entity = new ProjectEntity();
        entity.setId(1);
        when(projectRepository.findById(1)).thenReturn(Optional.of(entity));

        projectDaoAdapter.deleteProject(1);

        verify(projectRepository, times(1)).delete(entity);
    }
}
