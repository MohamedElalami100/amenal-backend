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

import com.amenal.amenalbackend.achat.core.domain.Fournisseur;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.FournisseurEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.FournisseurRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class FournisseurDaoAdapterTest {

    @Mock
    private FournisseurRepository fournisseurRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private FournisseurDaoAdapter fournisseurDaoAdapter;

    @Test
    void testFindFournisseurById() {
        FournisseurEntity entity = new FournisseurEntity();
        entity.setId(1);

        when(fournisseurRepository.findById(1)).thenReturn(Optional.of(entity));

        Fournisseur result = fournisseurDaoAdapter.findFournisseurById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllFournisseurs() {
        when(fournisseurRepository.findAll()).thenReturn(Collections.emptyList());

        List<Fournisseur> result = fournisseurDaoAdapter.findAllFournisseurs();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveFournisseur() {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(1);
        FournisseurEntity entity = new FournisseurEntity();
        entity.setId(1);

        when(fournisseurRepository.save(any(FournisseurEntity.class))).thenReturn(entity);

        Fournisseur result = fournisseurDaoAdapter.saveFournisseur(fournisseur);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateFournisseur() {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(1);
        FournisseurEntity entity = new FournisseurEntity();
        entity.setId(1);
        when(fournisseurRepository.findById(1)).thenReturn(Optional.of(entity));
        when(fournisseurRepository.save(any(FournisseurEntity.class))).thenReturn(entity);

        Fournisseur result = fournisseurDaoAdapter.updateFournisseur(fournisseur);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteFournisseur() {
        FournisseurEntity entity = new FournisseurEntity();
        entity.setId(1);
        when(fournisseurRepository.findById(1)).thenReturn(Optional.of(entity));

        fournisseurDaoAdapter.deleteFournisseur(1);

        verify(fournisseurRepository, times(1)).delete(entity);
    }
}
