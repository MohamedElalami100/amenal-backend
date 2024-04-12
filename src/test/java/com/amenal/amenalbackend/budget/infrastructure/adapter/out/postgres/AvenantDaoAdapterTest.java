package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.budget.core.domain.Avenant;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.AvenantEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.AvenantRepository;
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
public class AvenantDaoAdapterTest {

    @Mock
    private AvenantRepository avenantRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private AvenantDaoAdapter avenantDaoAdapter;

    @Test
    void testFindAvenantById() {
        AvenantEntity entity = new AvenantEntity();
        entity.setId(1);

        when(avenantRepository.findById(1)).thenReturn(Optional.of(entity));

        Avenant result = avenantDaoAdapter.findAvenantById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllAvenants() {
        when(avenantRepository.findAll()).thenReturn(Collections.emptyList());

        List<Avenant> result = avenantDaoAdapter.findAllAvenants();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveAvenant() {
        Avenant avenant = new Avenant();
        avenant.setId(1);
        AvenantEntity entity = new AvenantEntity();
        entity.setId(1);

        when(avenantRepository.save(any(AvenantEntity.class))).thenReturn(entity);

        Avenant result = avenantDaoAdapter.saveAvenant(avenant);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateAvenant() {
        Avenant avenant = new Avenant();
        avenant.setId(1);
        AvenantEntity entity = new AvenantEntity();
        entity.setId(1);
        when(avenantRepository.findById(1)).thenReturn(Optional.of(entity));
        when(avenantRepository.save(any(AvenantEntity.class))).thenReturn(entity);

        Avenant result = avenantDaoAdapter.updateAvenant(avenant);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteAvenant() {
        AvenantEntity entity = new AvenantEntity();
        entity.setId(1);
        when(avenantRepository.findById(1)).thenReturn(Optional.of(entity));

        avenantDaoAdapter.deleteAvenant(1);

        verify(avenantRepository, times(1)).delete(entity);
    }
}
