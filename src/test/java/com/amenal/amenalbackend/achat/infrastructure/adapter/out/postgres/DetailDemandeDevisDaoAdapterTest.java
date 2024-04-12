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

import com.amenal.amenalbackend.achat.core.domain.DetailDemandeDevis;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.DetailDemandeDevisEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.DetailDemandeDevisRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class DetailDemandeDevisDaoAdapterTest {

    @Mock
    private DetailDemandeDevisRepository detailDemandeDevisRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private DetailDemandeDevisDaoAdapter detailDemandeDevisDaoAdapter;

    @Test
    void testFindDetailDemandeDevisById() {
        DetailDemandeDevisEntity entity = new DetailDemandeDevisEntity();
        entity.setId(1);

        when(detailDemandeDevisRepository.findById(1)).thenReturn(Optional.of(entity));

        DetailDemandeDevis result = detailDemandeDevisDaoAdapter.findDetailDemandeDevisById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllDetailDemandeDeviss() {
        when(detailDemandeDevisRepository.findAll()).thenReturn(Collections.emptyList());

        List<DetailDemandeDevis> result = detailDemandeDevisDaoAdapter.findAllDetailDemandeDeviss();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveDetailDemandeDevis() {
        DetailDemandeDevis detailDemandeDevis = new DetailDemandeDevis();
        detailDemandeDevis.setId(1);
        DetailDemandeDevisEntity entity = new DetailDemandeDevisEntity();
        entity.setId(1);

        when(detailDemandeDevisRepository.save(any(DetailDemandeDevisEntity.class))).thenReturn(entity);

        DetailDemandeDevis result = detailDemandeDevisDaoAdapter.saveDetailDemandeDevis(detailDemandeDevis);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateDetailDemandeDevis() {
        DetailDemandeDevis detailDemandeDevis = new DetailDemandeDevis();
        detailDemandeDevis.setId(1);
        DetailDemandeDevisEntity entity = new DetailDemandeDevisEntity();
        entity.setId(1);
        when(detailDemandeDevisRepository.findById(1)).thenReturn(Optional.of(entity));
        when(detailDemandeDevisRepository.save(any(DetailDemandeDevisEntity.class))).thenReturn(entity);

        DetailDemandeDevis result = detailDemandeDevisDaoAdapter.updateDetailDemandeDevis(detailDemandeDevis);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteDetailDemandeDevis() {
        DetailDemandeDevisEntity entity = new DetailDemandeDevisEntity();
        entity.setId(1);
        when(detailDemandeDevisRepository.findById(1)).thenReturn(Optional.of(entity));

        detailDemandeDevisDaoAdapter.deleteDetailDemandeDevis(1);

        verify(detailDemandeDevisRepository, times(1)).delete(entity);
    }
}
