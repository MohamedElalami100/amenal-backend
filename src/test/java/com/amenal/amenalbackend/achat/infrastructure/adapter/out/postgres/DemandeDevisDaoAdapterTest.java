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

import com.amenal.amenalbackend.achat.core.domain.DemandeDevis;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.DemandeDevisEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.DemandeDevisRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class DemandeDevisDaoAdapterTest {

    @Mock
    private DemandeDevisRepository demandeDevisRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private DemandeDevisDaoAdapter demandeDevisDaoAdapter;

    @Test
    void testFindDemandeDevisById() {
        DemandeDevisEntity entity = new DemandeDevisEntity();
        entity.setId(1);

        when(demandeDevisRepository.findById(1)).thenReturn(Optional.of(entity));

        DemandeDevis result = demandeDevisDaoAdapter.findDemandeDevisById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllDemandeDeviss() {
        when(demandeDevisRepository.findAll()).thenReturn(Collections.emptyList());

        List<DemandeDevis> result = demandeDevisDaoAdapter.findAllDemandeDeviss();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveDemandeDevis() {
        DemandeDevis demandeDevis = new DemandeDevis();
        demandeDevis.setId(1);
        DemandeDevisEntity entity = new DemandeDevisEntity();
        entity.setId(1);

        when(demandeDevisRepository.save(any(DemandeDevisEntity.class))).thenReturn(entity);

        DemandeDevis result = demandeDevisDaoAdapter.saveDemandeDevis(demandeDevis);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateDemandeDevis() {
        DemandeDevis demandeDevis = new DemandeDevis();
        demandeDevis.setId(1);
        DemandeDevisEntity entity = new DemandeDevisEntity();
        entity.setId(1);
        when(demandeDevisRepository.findById(1)).thenReturn(Optional.of(entity));
        when(demandeDevisRepository.save(any(DemandeDevisEntity.class))).thenReturn(entity);

        DemandeDevis result = demandeDevisDaoAdapter.updateDemandeDevis(demandeDevis);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteDemandeDevis() {
        DemandeDevisEntity entity = new DemandeDevisEntity();
        entity.setId(1);
        when(demandeDevisRepository.findById(1)).thenReturn(Optional.of(entity));

        demandeDevisDaoAdapter.deleteDemandeDevis(1);

        verify(demandeDevisRepository, times(1)).delete(entity);
    }
}
