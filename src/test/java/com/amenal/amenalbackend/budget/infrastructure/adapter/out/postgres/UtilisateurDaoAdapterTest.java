package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.budget.core.domain.Utilisateur;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.UtilisateurEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.UtilisateurRepository;
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
public class UtilisateurDaoAdapterTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private UtilisateurDaoAdapter utilisateurDaoAdapter;

    @Test
    void testFindUtilisateurById() {
        UtilisateurEntity entity = new UtilisateurEntity();
        entity.setId(1);

        when(utilisateurRepository.findById(1)).thenReturn(Optional.of(entity));

        Utilisateur result = utilisateurDaoAdapter.findUtilisateurById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllUtilisateurs() {
        when(utilisateurRepository.findAll()).thenReturn(Collections.emptyList());

        List<Utilisateur> result = utilisateurDaoAdapter.findAllUtilisateurs();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1);
        UtilisateurEntity entity = new UtilisateurEntity();
        entity.setId(1);

        when(utilisateurRepository.save(any(UtilisateurEntity.class))).thenReturn(entity);

        Utilisateur result = utilisateurDaoAdapter.saveUtilisateur(utilisateur);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1);
        UtilisateurEntity entity = new UtilisateurEntity();
        entity.setId(1);
        when(utilisateurRepository.findById(1)).thenReturn(Optional.of(entity));
        when(utilisateurRepository.save(any(UtilisateurEntity.class))).thenReturn(entity);

        Utilisateur result = utilisateurDaoAdapter.updateUtilisateur(utilisateur);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteUtilisateur() {
        UtilisateurEntity entity = new UtilisateurEntity();
        entity.setId(1);
        when(utilisateurRepository.findById(1)).thenReturn(Optional.of(entity));

        utilisateurDaoAdapter.deleteUtilisateur(1);

        verify(utilisateurRepository, times(1)).delete(entity);
    }
}
