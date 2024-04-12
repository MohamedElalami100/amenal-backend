package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.budget.core.domain.DetailChargeAttente;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailChargeAttenteEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailChargeAttenteRepository;
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
public class DetailChargeAttenteDaoAdapterTest {

    @Mock
    private DetailChargeAttenteRepository detailChargeAttenteRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private DetailChargeAttenteDaoAdapter detailChargeAttenteDaoAdapter;

    @Test
    void testFindDetailChargeAttenteById() {
        DetailChargeAttenteEntity entity = new DetailChargeAttenteEntity();
        entity.setId(1);

        when(detailChargeAttenteRepository.findById(1)).thenReturn(Optional.of(entity));

        DetailChargeAttente result = detailChargeAttenteDaoAdapter.findDetailChargeAttenteById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllDetailChargeAttentes() {
        when(detailChargeAttenteRepository.findAll()).thenReturn(Collections.emptyList());

        List<DetailChargeAttente> result = detailChargeAttenteDaoAdapter.findAllDetailChargeAttentes();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveDetailChargeAttente() {
        DetailChargeAttente detailChargeAttente = new DetailChargeAttente();
        detailChargeAttente.setId(1);
        DetailChargeAttenteEntity entity = new DetailChargeAttenteEntity();
        entity.setId(1);

        when(detailChargeAttenteRepository.save(any(DetailChargeAttenteEntity.class))).thenReturn(entity);

        DetailChargeAttente result = detailChargeAttenteDaoAdapter.saveDetailChargeAttente(detailChargeAttente);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateDetailChargeAttente() {
        DetailChargeAttente detailChargeAttente = new DetailChargeAttente();
        detailChargeAttente.setId(1);
        DetailChargeAttenteEntity entity = new DetailChargeAttenteEntity();
        entity.setId(1);
        when(detailChargeAttenteRepository.findById(1)).thenReturn(Optional.of(entity));
        when(detailChargeAttenteRepository.save(any(DetailChargeAttenteEntity.class))).thenReturn(entity);

        DetailChargeAttente result = detailChargeAttenteDaoAdapter.updateDetailChargeAttente(detailChargeAttente);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteDetailChargeAttente() {
        DetailChargeAttenteEntity entity = new DetailChargeAttenteEntity();
        entity.setId(1);
        when(detailChargeAttenteRepository.findById(1)).thenReturn(Optional.of(entity));

        detailChargeAttenteDaoAdapter.deleteDetailChargeAttente(1);

        verify(detailChargeAttenteRepository, times(1)).delete(entity);
    }
}
