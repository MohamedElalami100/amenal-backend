package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.achat.infrastructure.dto.DetailDevisDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.amenal.amenalbackend.achat.core.domain.DetailDevis;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.DetailDevisEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.DetailDevisRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class DetailDevisDaoAdapterTest {

    @Mock
    private DetailDevisRepository detailDevisRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private DetailDevisDaoAdapter detailDevisDaoAdapter;

    @Test
    void testFindDetailDevisById() {
        DetailDevisEntity entity = new DetailDevisEntity();
        entity.setId(1);

        when(detailDevisRepository.findById(1)).thenReturn(Optional.of(entity));

        DetailDevisDto result = detailDevisDaoAdapter.findDetailDevisById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllDetailDeviss() {
        when(detailDevisRepository.findAll()).thenReturn(Collections.emptyList());

        List<DetailDevisDto> result = detailDevisDaoAdapter.findAllDetailDeviss();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveDetailDevis() {
        DetailDevis detailDevis = new DetailDevis();
        detailDevis.setId(1);
        DetailDevisEntity entity = new DetailDevisEntity();
        entity.setId(1);

        when(detailDevisRepository.save(any(DetailDevisEntity.class))).thenReturn(entity);

        DetailDevis result = detailDevisDaoAdapter.saveDetailDevis(detailDevis);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateDetailDevis() {
        DetailDevis detailDevis = new DetailDevis();
        detailDevis.setId(1);
        DetailDevisEntity entity = new DetailDevisEntity();
        entity.setId(1);
        when(detailDevisRepository.findById(1)).thenReturn(Optional.of(entity));
        when(detailDevisRepository.save(any(DetailDevisEntity.class))).thenReturn(entity);

        DetailDevis result = detailDevisDaoAdapter.updateDetailDevis(detailDevis);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteDetailDevis() {
        DetailDevisEntity entity = new DetailDevisEntity();
        entity.setId(1);
        when(detailDevisRepository.findById(1)).thenReturn(Optional.of(entity));

        detailDevisDaoAdapter.deleteDetailDevis(1);

        verify(detailDevisRepository, times(1)).delete(entity);
    }
}
