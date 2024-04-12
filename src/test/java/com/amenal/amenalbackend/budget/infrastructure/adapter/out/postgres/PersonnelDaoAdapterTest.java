package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.budget.core.domain.Personnel;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.PersonnelEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.PersonnelRepository;
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
public class PersonnelDaoAdapterTest {

    @Mock
    private PersonnelRepository personnelRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private PersonnelDaoAdapter personnelDaoAdapter;

    @Test
    void testFindPersonnelById() {
        PersonnelEntity entity = new PersonnelEntity();
        entity.setMatricule(1);

        when(personnelRepository.findById(1)).thenReturn(Optional.of(entity));

        Personnel result = personnelDaoAdapter.findPersonnelById(1);
        assertNotNull(result);
        assertEquals(1, result.getMatricule());
    }

    @Test
    void testFindAllPersonnels() {
        when(personnelRepository.findAll()).thenReturn(Collections.emptyList());

        List<Personnel> result = personnelDaoAdapter.findAllPersonnels();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSavePersonnel() {
        Personnel personnel = new Personnel();
        personnel.setMatricule(1);
        PersonnelEntity entity = new PersonnelEntity();
        entity.setMatricule(1);

        when(personnelRepository.save(any(PersonnelEntity.class))).thenReturn(entity);

        Personnel result = personnelDaoAdapter.savePersonnel(personnel);
        assertNotNull(result);
        assertEquals(1, result.getMatricule());
    }

    @Test
    void testUpdatePersonnel() {
        Personnel personnel = new Personnel();
        personnel.setMatricule(1);
        PersonnelEntity entity = new PersonnelEntity();
        entity.setMatricule(1);
        when(personnelRepository.findById(1)).thenReturn(Optional.of(entity));
        when(personnelRepository.save(any(PersonnelEntity.class))).thenReturn(entity);

        Personnel result = personnelDaoAdapter.updatePersonnel(personnel);
        assertNotNull(result);
        assertEquals(1, result.getMatricule());
    }

    @Test
    void testDeletePersonnel() {
        PersonnelEntity entity = new PersonnelEntity();
        entity.setMatricule(1);
        when(personnelRepository.findById(1)).thenReturn(Optional.of(entity));

        personnelDaoAdapter.deletePersonnel(1);

        verify(personnelRepository, times(1)).delete(entity);
    }
}
