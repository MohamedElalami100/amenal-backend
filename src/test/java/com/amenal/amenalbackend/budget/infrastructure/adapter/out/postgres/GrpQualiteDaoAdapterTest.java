package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.budget.core.domain.GrpQualite;
import com.amenal.amenalbackend.budget.core.domain.Tache;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.GrpQualiteEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.GrpQualiteRepository;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;
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
public class GrpQualiteDaoAdapterTest {

    @Mock
    private GrpQualiteRepository grpQualiteRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private GrpQualiteDaoAdapter grpQualiteDaoAdapter;

    @Test
    void testFindGrpQualiteById() {
        GrpQualiteEntity entity = new GrpQualiteEntity();
        entity.setId(1);

        when(grpQualiteRepository.findById(1)).thenReturn(Optional.of(entity));

        GrpQualite result = grpQualiteDaoAdapter.findGrpQualiteById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllGrpQualites() {
        when(grpQualiteRepository.findAll()).thenReturn(Collections.emptyList());

        List<GrpQualite> result = grpQualiteDaoAdapter.findAllGrpQualites();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveGrpQualiteWhenDuplicateExists() {
        GrpQualite grpQualite = new GrpQualite();
        grpQualite.setId(1);
        Tache tache = new Tache();
        tache.setId(1);
        grpQualite.setTache(tache);
        grpQualite.setTitre("test");
        GrpQualiteEntity entity = new GrpQualiteEntity();
        entity.setId(1);
        GrpQualiteEntity entity2 = new GrpQualiteEntity();
        entity2.setId(2);
        List<GrpQualiteEntity> sameEntities = new ArrayList<>();
        sameEntities.add(entity2);

        when(grpQualiteRepository.getGrpQualitesByTacheIdAndTitre(anyInt(), anyString())).thenReturn(sameEntities);

        assertThrows(DuplicateElementException.class, () -> {
            grpQualiteDaoAdapter.saveGrpQualite(grpQualite);
        });

    }

    @Test
    void testSaveGrpQualiteWhenDuplicateNotExists() throws DuplicateElementException{

        GrpQualite grpQualite = new GrpQualite();
        grpQualite.setId(1);
        Tache tache = new Tache();
        tache.setId(1);
        grpQualite.setTache(tache);
        grpQualite.setTitre("test");
        GrpQualiteEntity entity = new GrpQualiteEntity();
        entity.setId(1);
        List<GrpQualiteEntity> sameEntities = new ArrayList<>();

        when(grpQualiteRepository.getGrpQualitesByTacheIdAndTitre(anyInt(), anyString())).thenReturn(sameEntities);
        when(grpQualiteRepository.save(any(GrpQualiteEntity.class))).thenReturn(entity);

        GrpQualite result = grpQualiteDaoAdapter.saveGrpQualite(grpQualite);
        assertNotNull(result);
        assertEquals(1, result.getId());

    }
    @Test
    void testUpdateGrpQualiteWhenDuplicateNotExists() throws DuplicateElementException{
        GrpQualite grpQualite = new GrpQualite();
        grpQualite.setId(1);
        grpQualite.setTitre("test");
        GrpQualiteEntity existingEntity = new GrpQualiteEntity();
        existingEntity.setId(1);
        existingEntity.setTitre("test");

        when(grpQualiteRepository.findById(1)).thenReturn(Optional.of(existingEntity));
        when(grpQualiteRepository.save(any(GrpQualiteEntity.class))).thenReturn(existingEntity);

        GrpQualite result = grpQualiteDaoAdapter.updateGrpQualite(grpQualite);
        assertNotNull(result);
        assertEquals(1, result.getId());

    }

    @Test
    void testupdateGrpQualiteWhenDuplicateExists() {
        GrpQualite grpQualite = new GrpQualite();
        grpQualite.setId(1);
        Tache tache = new Tache();
        tache.setId(1);
        grpQualite.setTache(tache);
        grpQualite.setTitre("test");
        GrpQualiteEntity entity = new GrpQualiteEntity();
        entity.setId(1);
        entity.setTitre("test2");
        GrpQualiteEntity entity2 = new GrpQualiteEntity();
        entity2.setId(2);
        List<GrpQualiteEntity> sameEntities = new ArrayList<>();
        sameEntities.add(entity2);

        when(grpQualiteRepository.getGrpQualitesByTacheIdAndTitre(anyInt(), anyString())).thenReturn(sameEntities);
        when(grpQualiteRepository.findById(1)).thenReturn(Optional.of(entity));

        assertThrows(DuplicateElementException.class, () -> {
            grpQualiteDaoAdapter.updateGrpQualite(grpQualite);
        });

    }

    @Test
    void testSavGrpQualiteWhenDuplicateNotExists() throws DuplicateElementException{
        GrpQualite grpQualite = new GrpQualite();
        grpQualite.setId(1);
        grpQualite.setTitre("test");
        GrpQualiteEntity existingEntity = new GrpQualiteEntity();
        existingEntity.setId(1);
        existingEntity.setTitre("test");

        when(grpQualiteRepository.findById(1)).thenReturn(Optional.of(existingEntity));
        when(grpQualiteRepository.save(any(GrpQualiteEntity.class))).thenReturn(existingEntity);

        GrpQualite result = grpQualiteDaoAdapter.updateGrpQualite(grpQualite);
        assertNotNull(result);
        assertEquals(1, result.getId());

    }
    @Test
    void testUpdateGrpQualite()  throws DuplicateElementException{
        GrpQualite grpQualite = new GrpQualite();
        grpQualite.setId(1);
        GrpQualiteEntity entity = new GrpQualiteEntity();
        entity.setId(1);
        when(grpQualiteRepository.findById(1)).thenReturn(Optional.of(entity));
        when(grpQualiteRepository.save(any(GrpQualiteEntity.class))).thenReturn(entity);

        GrpQualite result = grpQualiteDaoAdapter.updateGrpQualite(grpQualite);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteGrpQualite() {
        GrpQualiteEntity entity = new GrpQualiteEntity();
        entity.setId(1);
        when(grpQualiteRepository.findById(1)).thenReturn(Optional.of(entity));

        grpQualiteDaoAdapter.deleteGrpQualite(1);

        verify(grpQualiteRepository, times(1)).delete(entity);
    }
}
