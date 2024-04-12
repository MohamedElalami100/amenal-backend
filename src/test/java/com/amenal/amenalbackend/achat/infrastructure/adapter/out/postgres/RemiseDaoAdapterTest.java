package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.amenal.amenalbackend.achat.core.domain.Remise;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.RemiseEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.RemiseRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class RemiseDaoAdapterTest {

    @Mock
    private RemiseRepository remiseRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private RemiseDaoAdapter remiseDaoAdapter;

    @Test
    void testFindRemiseById() {
        RemiseEntity entity = new RemiseEntity();
        entity.setId(1);

        when(remiseRepository.findById(1)).thenReturn(Optional.of(entity));

        Remise result = remiseDaoAdapter.findRemiseById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllRemises() {
        when(remiseRepository.findAll()).thenReturn(Collections.emptyList());

        List<Remise> result = remiseDaoAdapter.findAllRemises();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveRemise() {
        Remise remise = new Remise();
        remise.setId(1);
        RemiseEntity entity = new RemiseEntity();
        entity.setId(1);

        when(remiseRepository.save(any(RemiseEntity.class))).thenReturn(entity);

        Remise result = remiseDaoAdapter.saveRemise(remise);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateRemise() {
        Remise remise = new Remise();
        remise.setId(1);
        RemiseEntity entity = new RemiseEntity();
        entity.setId(1);
        when(remiseRepository.findById(1)).thenReturn(Optional.of(entity));
        when(remiseRepository.save(any(RemiseEntity.class))).thenReturn(entity);

        Remise result = remiseDaoAdapter.updateRemise(remise);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteRemise() {
        RemiseEntity entity = new RemiseEntity();
        entity.setId(1);
        when(remiseRepository.findById(1)).thenReturn(Optional.of(entity));

        remiseDaoAdapter.deleteRemise(1);

        verify(remiseRepository, times(1)).delete(entity);
    }
}
