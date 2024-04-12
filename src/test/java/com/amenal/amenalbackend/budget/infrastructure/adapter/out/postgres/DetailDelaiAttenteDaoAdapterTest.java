package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.budget.core.domain.DetailDelaiAttente;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailDelaiAttenteEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailDelaiAttenteRepository;
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
public class DetailDelaiAttenteDaoAdapterTest {

    @Mock
    private DetailDelaiAttenteRepository detailDelaiAttenteRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private DetailDelaiAttenteDaoAdapter detailDelaiAttenteDaoAdapter;

    @Test
    void testFindDetailDelaiAttenteById() {
        DetailDelaiAttenteEntity entity = new DetailDelaiAttenteEntity();
        entity.setId(1);

        when(detailDelaiAttenteRepository.findById(1)).thenReturn(Optional.of(entity));

        DetailDelaiAttente result = detailDelaiAttenteDaoAdapter.findDetailDelaiAttenteById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllDetailDelaiAttentes() {
        when(detailDelaiAttenteRepository.findAll()).thenReturn(Collections.emptyList());

        List<DetailDelaiAttente> result = detailDelaiAttenteDaoAdapter.findAllDetailDelaiAttentes();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveDetailDelaiAttente() {
        DetailDelaiAttente detailDelaiAttente = new DetailDelaiAttente();
        detailDelaiAttente.setId(1);
        DetailDelaiAttenteEntity entity = new DetailDelaiAttenteEntity();
        entity.setId(1);

        when(detailDelaiAttenteRepository.save(any(DetailDelaiAttenteEntity.class))).thenReturn(entity);

        DetailDelaiAttente result = detailDelaiAttenteDaoAdapter.saveDetailDelaiAttente(detailDelaiAttente);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateDetailDelaiAttente() {
        DetailDelaiAttente detailDelaiAttente = new DetailDelaiAttente();
        detailDelaiAttente.setId(1);
        DetailDelaiAttenteEntity entity = new DetailDelaiAttenteEntity();
        entity.setId(1);
        when(detailDelaiAttenteRepository.findById(1)).thenReturn(Optional.of(entity));
        when(detailDelaiAttenteRepository.save(any(DetailDelaiAttenteEntity.class))).thenReturn(entity);

        DetailDelaiAttente result = detailDelaiAttenteDaoAdapter.updateDetailDelaiAttente(detailDelaiAttente);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteDetailDelaiAttente() {
        DetailDelaiAttenteEntity entity = new DetailDelaiAttenteEntity();
        entity.setId(1);
        when(detailDelaiAttenteRepository.findById(1)).thenReturn(Optional.of(entity));

        detailDelaiAttenteDaoAdapter.deleteDetailDelaiAttente(1);

        verify(detailDelaiAttenteRepository, times(1)).delete(entity);
    }
}
