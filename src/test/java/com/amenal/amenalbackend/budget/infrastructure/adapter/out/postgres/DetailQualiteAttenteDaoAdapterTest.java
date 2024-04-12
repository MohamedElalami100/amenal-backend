package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.budget.core.domain.DetailQualiteAttente;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailQualiteAttenteEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailQualiteAttenteRepository;
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
public class DetailQualiteAttenteDaoAdapterTest {

    @Mock
    private DetailQualiteAttenteRepository detailQualiteAttenteRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private DetailQualiteAttenteDaoAdapter detailQualiteAttenteDaoAdapter;

    @Test
    void testFindDetailQualiteAttenteById() {
        DetailQualiteAttenteEntity entity = new DetailQualiteAttenteEntity();
        entity.setId(1);

        when(detailQualiteAttenteRepository.findById(1)).thenReturn(Optional.of(entity));

        DetailQualiteAttente result = detailQualiteAttenteDaoAdapter.findDetailQualiteAttenteById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllDetailQualiteAttentes() {
        when(detailQualiteAttenteRepository.findAll()).thenReturn(Collections.emptyList());

        List<DetailQualiteAttente> result = detailQualiteAttenteDaoAdapter.findAllDetailQualiteAttentes();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveDetailQualiteAttente() {
        DetailQualiteAttente detailQualiteAttente = new DetailQualiteAttente();
        detailQualiteAttente.setId(1);
        DetailQualiteAttenteEntity entity = new DetailQualiteAttenteEntity();
        entity.setId(1);

        when(detailQualiteAttenteRepository.save(any(DetailQualiteAttenteEntity.class))).thenReturn(entity);

        DetailQualiteAttente result = detailQualiteAttenteDaoAdapter.saveDetailQualiteAttente(detailQualiteAttente);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateDetailQualiteAttente() {
        DetailQualiteAttente detailQualiteAttente = new DetailQualiteAttente();
        detailQualiteAttente.setId(1);
        DetailQualiteAttenteEntity entity = new DetailQualiteAttenteEntity();
        entity.setId(1);
        when(detailQualiteAttenteRepository.findById(1)).thenReturn(Optional.of(entity));
        when(detailQualiteAttenteRepository.save(any(DetailQualiteAttenteEntity.class))).thenReturn(entity);

        DetailQualiteAttente result = detailQualiteAttenteDaoAdapter.updateDetailQualiteAttente(detailQualiteAttente);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteDetailQualiteAttente() {
        DetailQualiteAttenteEntity entity = new DetailQualiteAttenteEntity();
        entity.setId(1);
        when(detailQualiteAttenteRepository.findById(1)).thenReturn(Optional.of(entity));

        detailQualiteAttenteDaoAdapter.deleteDetailQualiteAttente(1);

        verify(detailQualiteAttenteRepository, times(1)).delete(entity);
    }
}
