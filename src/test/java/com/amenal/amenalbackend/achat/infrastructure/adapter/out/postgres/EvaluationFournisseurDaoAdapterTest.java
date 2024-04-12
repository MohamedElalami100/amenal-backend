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

import com.amenal.amenalbackend.achat.core.domain.EvaluationFournisseur;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.EvaluationFournisseurEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.EvaluationFournisseurRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class EvaluationFournisseurDaoAdapterTest {

    @Mock
    private EvaluationFournisseurRepository evaluationFournisseurRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private EvaluationFournisseurDaoAdapter evaluationFournisseurDaoAdapter;

    @Test
    void testFindEvaluationFournisseurById() {
        EvaluationFournisseurEntity entity = new EvaluationFournisseurEntity();
        entity.setId(1);

        when(evaluationFournisseurRepository.findById(1)).thenReturn(Optional.of(entity));

        EvaluationFournisseur result = evaluationFournisseurDaoAdapter.findEvaluationFournisseurById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllEvaluationFournisseurs() {
        when(evaluationFournisseurRepository.findAll()).thenReturn(Collections.emptyList());

        List<EvaluationFournisseur> result = evaluationFournisseurDaoAdapter.findAllEvaluationFournisseurs();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveEvaluationFournisseur() {
        EvaluationFournisseur evaluationFournisseur = new EvaluationFournisseur();
        evaluationFournisseur.setId(1);
        EvaluationFournisseurEntity entity = new EvaluationFournisseurEntity();
        entity.setId(1);

        when(evaluationFournisseurRepository.save(any(EvaluationFournisseurEntity.class))).thenReturn(entity);

        EvaluationFournisseur result = evaluationFournisseurDaoAdapter.saveEvaluationFournisseur(evaluationFournisseur);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateEvaluationFournisseur() {
        EvaluationFournisseur evaluationFournisseur = new EvaluationFournisseur();
        evaluationFournisseur.setId(1);
        EvaluationFournisseurEntity entity = new EvaluationFournisseurEntity();
        entity.setId(1);
        when(evaluationFournisseurRepository.findById(1)).thenReturn(Optional.of(entity));
        when(evaluationFournisseurRepository.save(any(EvaluationFournisseurEntity.class))).thenReturn(entity);

        EvaluationFournisseur result = evaluationFournisseurDaoAdapter.updateEvaluationFournisseur(evaluationFournisseur);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteEvaluationFournisseur() {
        EvaluationFournisseurEntity entity = new EvaluationFournisseurEntity();
        entity.setId(1);
        when(evaluationFournisseurRepository.findById(1)).thenReturn(Optional.of(entity));

        evaluationFournisseurDaoAdapter.deleteEvaluationFournisseur(1);

        verify(evaluationFournisseurRepository, times(1)).delete(entity);
    }
}
