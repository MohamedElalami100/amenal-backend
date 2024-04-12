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

import com.amenal.amenalbackend.achat.core.domain.DetailBesoin;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.DetailBesoinEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.DetailBesoinRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class DetailBesoinDaoAdapterTest {

    @Mock
    private DetailBesoinRepository detailBesoinRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private DetailBesoinDaoAdapter detailBesoinDaoAdapter;

    @Test
    void testFindDetailBesoinById() {
        DetailBesoinEntity entity = new DetailBesoinEntity();
        entity.setId(1);

        when(detailBesoinRepository.findById(1)).thenReturn(Optional.of(entity));

        DetailBesoin result = detailBesoinDaoAdapter.findDetailBesoinById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllDetailBesoins() {
        when(detailBesoinRepository.findAll()).thenReturn(Collections.emptyList());

        List<DetailBesoin> result = detailBesoinDaoAdapter.findAllDetailBesoins();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveDetailBesoin() {
        DetailBesoin detailBesoin = new DetailBesoin();
        detailBesoin.setId(1);
        DetailBesoinEntity entity = new DetailBesoinEntity();
        entity.setId(1);

        when(detailBesoinRepository.save(any(DetailBesoinEntity.class))).thenReturn(entity);

        DetailBesoin result = detailBesoinDaoAdapter.saveDetailBesoin(detailBesoin);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateDetailBesoin() {
        DetailBesoin detailBesoin = new DetailBesoin();
        detailBesoin.setId(1);
        DetailBesoinEntity entity = new DetailBesoinEntity();
        entity.setId(1);
        when(detailBesoinRepository.findById(1)).thenReturn(Optional.of(entity));
        when(detailBesoinRepository.save(any(DetailBesoinEntity.class))).thenReturn(entity);

        DetailBesoin result = detailBesoinDaoAdapter.updateDetailBesoin(detailBesoin);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteDetailBesoin() {
        DetailBesoinEntity entity = new DetailBesoinEntity();
        entity.setId(1);
        when(detailBesoinRepository.findById(1)).thenReturn(Optional.of(entity));

        detailBesoinDaoAdapter.deleteDetailBesoin(1);

        verify(detailBesoinRepository, times(1)).delete(entity);
    }
}
