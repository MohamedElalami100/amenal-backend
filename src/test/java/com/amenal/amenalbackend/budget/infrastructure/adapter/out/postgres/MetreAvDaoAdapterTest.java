package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.budget.core.domain.MetreAv;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.MetreAvEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.MetreAvRepository;
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
public class MetreAvDaoAdapterTest {

    @Mock
    private MetreAvRepository metreAvRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private MetreAvDaoAdapter metreAvDaoAdapter;

    @Test
    void testFindMetreAvById() {
        MetreAvEntity entity = new MetreAvEntity();
        entity.setId(1);

        when(metreAvRepository.findById(1)).thenReturn(Optional.of(entity));

        MetreAv result = metreAvDaoAdapter.findMetreAvById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllMetreAvs() {
        when(metreAvRepository.findAll()).thenReturn(Collections.emptyList());

        List<MetreAv> result = metreAvDaoAdapter.findAllMetreAvs();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveMetreAv() {
        MetreAv metreAv = new MetreAv();
        metreAv.setId(1);
        MetreAvEntity entity = new MetreAvEntity();
        entity.setId(1);

        when(metreAvRepository.save(any(MetreAvEntity.class))).thenReturn(entity);

        MetreAv result = metreAvDaoAdapter.saveMetreAv(metreAv);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateMetreAv() {
        MetreAv metreAv = new MetreAv();
        metreAv.setId(1);
        MetreAvEntity entity = new MetreAvEntity();
        entity.setId(1);
        when(metreAvRepository.findById(1)).thenReturn(Optional.of(entity));
        when(metreAvRepository.save(any(MetreAvEntity.class))).thenReturn(entity);

        MetreAv result = metreAvDaoAdapter.updateMetreAv(metreAv);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteMetreAv() {
        MetreAvEntity entity = new MetreAvEntity();
        entity.setId(1);
        when(metreAvRepository.findById(1)).thenReturn(Optional.of(entity));

        metreAvDaoAdapter.deleteMetreAv(1);

        verify(metreAvRepository, times(1)).delete(entity);
    }
}
