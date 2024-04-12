package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.achat.infrastructure.dto.BesoinDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.amenal.amenalbackend.achat.core.domain.Besoin;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.BesoinEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.BesoinRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class BesoinDaoAdapterTest {

    @Mock
    private BesoinRepository besoinRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private BesoinDaoAdapter besoinDaoAdapter;

    @Test
    void testFindBesoinById() {
        BesoinEntity entity = new BesoinEntity();
        entity.setId(1);

        when(besoinRepository.findById(1)).thenReturn(Optional.of(entity));

        BesoinDto result = besoinDaoAdapter.findBesoinById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllBesoins() {
        when(besoinRepository.findAll()).thenReturn(Collections.emptyList());

        List<BesoinDto> result = besoinDaoAdapter.findAllBesoins();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveBesoin() {
        Besoin besoin = new Besoin();
        besoin.setId(1);
        BesoinEntity entity = new BesoinEntity();
        entity.setId(1);

        when(besoinRepository.save(any(BesoinEntity.class))).thenReturn(entity);

        Besoin result = besoinDaoAdapter.saveBesoin(besoin);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateBesoin() {
        Besoin besoin = new Besoin();
        besoin.setId(1);
        BesoinEntity entity = new BesoinEntity();
        entity.setId(1);
        when(besoinRepository.findById(1)).thenReturn(Optional.of(entity));
        when(besoinRepository.save(any(BesoinEntity.class))).thenReturn(entity);

        Besoin result = besoinDaoAdapter.updateBesoin(besoin);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteBesoin() {
        BesoinEntity entity = new BesoinEntity();
        entity.setId(1);
        when(besoinRepository.findById(1)).thenReturn(Optional.of(entity));

        besoinDaoAdapter.deleteBesoin(1);

        verify(besoinRepository, times(1)).delete(entity);
    }
}
