package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.budget.core.domain.DetailProduitAttente;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailProduitAttenteEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailProduitAttenteRepository;
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
public class DetailProduitAttenteDaoAdapterTest {

    @Mock
    private DetailProduitAttenteRepository detailProduitAttenteRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private DetailProduitAttenteDaoAdapter detailProduitAttenteDaoAdapter;

    @Test
    void testFindDetailProduitAttenteById() {
        DetailProduitAttenteEntity entity = new DetailProduitAttenteEntity();
        entity.setId(1);

        when(detailProduitAttenteRepository.findById(1)).thenReturn(Optional.of(entity));

        DetailProduitAttente result = detailProduitAttenteDaoAdapter.findDetailProduitAttenteById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllDetailProduitAttentes() {
        when(detailProduitAttenteRepository.findAll()).thenReturn(Collections.emptyList());

        List<DetailProduitAttente> result = detailProduitAttenteDaoAdapter.findAllDetailProduitAttentes();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveDetailProduitAttente() {
        DetailProduitAttente detailProduitAttente = new DetailProduitAttente();
        detailProduitAttente.setId(1);
        DetailProduitAttenteEntity entity = new DetailProduitAttenteEntity();
        entity.setId(1);

        when(detailProduitAttenteRepository.save(any(DetailProduitAttenteEntity.class))).thenReturn(entity);

        DetailProduitAttente result = detailProduitAttenteDaoAdapter.saveDetailProduitAttente(detailProduitAttente);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateDetailProduitAttente() {
        DetailProduitAttente detailProduitAttente = new DetailProduitAttente();
        detailProduitAttente.setId(1);
        DetailProduitAttenteEntity entity = new DetailProduitAttenteEntity();
        entity.setId(1);
        when(detailProduitAttenteRepository.findById(1)).thenReturn(Optional.of(entity));
        when(detailProduitAttenteRepository.save(any(DetailProduitAttenteEntity.class))).thenReturn(entity);

        DetailProduitAttente result = detailProduitAttenteDaoAdapter.updateDetailProduitAttente(detailProduitAttente);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteDetailProduitAttente() {
        DetailProduitAttenteEntity entity = new DetailProduitAttenteEntity();
        entity.setId(1);
        when(detailProduitAttenteRepository.findById(1)).thenReturn(Optional.of(entity));

        detailProduitAttenteDaoAdapter.deleteDetailProduitAttente(1);

        verify(detailProduitAttenteRepository, times(1)).delete(entity);
    }
}
