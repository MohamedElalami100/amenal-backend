package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.achat.infrastructure.dto.DetailFactureDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.amenal.amenalbackend.achat.core.domain.DetailFacture;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.DetailFactureEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.DetailFactureRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class DetailFactureDaoAdapterTest {

    @Mock
    private DetailFactureRepository detailFactureRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private DetailFactureDaoAdapter detailFactureDaoAdapter;

    @Test
    void testFindDetailFactureById() {
        DetailFactureEntity entity = new DetailFactureEntity();
        entity.setId(1);

        when(detailFactureRepository.findById(1)).thenReturn(Optional.of(entity));

        DetailFactureDto result = detailFactureDaoAdapter.findDetailFactureById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllDetailFactures() {
        when(detailFactureRepository.findAll()).thenReturn(Collections.emptyList());

        List<DetailFactureDto> result = detailFactureDaoAdapter.findAllDetailFactures();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveDetailFacture() {
        DetailFacture detailFacture = new DetailFacture();
        detailFacture.setId(1);
        DetailFactureEntity entity = new DetailFactureEntity();
        entity.setId(1);

        when(detailFactureRepository.save(any(DetailFactureEntity.class))).thenReturn(entity);

        DetailFacture result = detailFactureDaoAdapter.saveDetailFacture(detailFacture);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateDetailFacture() {
        DetailFacture detailFacture = new DetailFacture();
        detailFacture.setId(1);
        DetailFactureEntity entity = new DetailFactureEntity();
        entity.setId(1);
        when(detailFactureRepository.findById(1)).thenReturn(Optional.of(entity));
        when(detailFactureRepository.save(any(DetailFactureEntity.class))).thenReturn(entity);

        DetailFacture result = detailFactureDaoAdapter.updateDetailFacture(detailFacture);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteDetailFacture() {
        DetailFactureEntity entity = new DetailFactureEntity();
        entity.setId(1);
        when(detailFactureRepository.findById(1)).thenReturn(Optional.of(entity));

        detailFactureDaoAdapter.deleteDetailFacture(1);

        verify(detailFactureRepository, times(1)).delete(entity);
    }
}
