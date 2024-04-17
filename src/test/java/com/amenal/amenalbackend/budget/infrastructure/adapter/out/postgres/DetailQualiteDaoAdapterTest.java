package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.budget.core.domain.DetailQualite;
import com.amenal.amenalbackend.budget.core.domain.GrpQualite;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailQualiteEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailQualiteRepository;
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
public class DetailQualiteDaoAdapterTest {

    @Mock
    private DetailQualiteRepository detailQualiteRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private DetailQualiteDaoAdapter detailQualiteDaoAdapter;

    @Test
    void testFindDetailQualiteById() {
        DetailQualiteEntity entity = new DetailQualiteEntity();
        entity.setId(1);

        when(detailQualiteRepository.findById(1)).thenReturn(Optional.of(entity));

        DetailQualite result = detailQualiteDaoAdapter.findDetailQualiteById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllDetailQualites() {
        when(detailQualiteRepository.findAll()).thenReturn(Collections.emptyList());

        List<DetailQualite> result = detailQualiteDaoAdapter.findAllDetailQualites();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveDetailQualiteWhenDuplicateExists() {
        DetailQualite detailQualite = new DetailQualite();
        detailQualite.setId(1);
        GrpQualite grp = new GrpQualite();
        grp.setId(1);
        detailQualite.setGroupe(grp);
        detailQualite.setPointDeControle("test");
        DetailQualiteEntity entity = new DetailQualiteEntity();
        entity.setId(1);
        DetailQualiteEntity entity2 = new DetailQualiteEntity();
        entity2.setId(2);
        List<DetailQualiteEntity> sameEntities = new ArrayList<>();
        sameEntities.add(entity2);

        when(detailQualiteRepository.getDetailQualitesByGrpQualiteIdAndAffaire(anyInt(), anyString())).thenReturn(sameEntities);

        assertThrows(DuplicateElementException.class, () -> {
            detailQualiteDaoAdapter.saveDetailQualite(detailQualite);
        });

    }

    @Test
    void testSaveDetailQualiteWhenDuplicateNotExists() throws DuplicateElementException{

    	DetailQualite detailQualite = new DetailQualite();
        detailQualite.setId(1);
        GrpQualite grp = new GrpQualite();
        grp.setId(1);
        detailQualite.setGroupe(grp);
        detailQualite.setPointDeControle("test");
        DetailQualiteEntity entity = new DetailQualiteEntity();
        entity.setId(1);
        List<DetailQualiteEntity> sameEntities = new ArrayList<>();

        when(detailQualiteRepository.getDetailQualitesByGrpQualiteIdAndAffaire(anyInt(), anyString())).thenReturn(sameEntities);
        when(detailQualiteRepository.save(any(DetailQualiteEntity.class))).thenReturn(entity);

        DetailQualite result = detailQualiteDaoAdapter.saveDetailQualite(detailQualite);
        assertNotNull(result);
        assertEquals(1, result.getId());

    }
    @Test
    void testUpdateDetailQualiteWhenDuplicateNotExists() throws DuplicateElementException{
        DetailQualite detailQualite = new DetailQualite();
        detailQualite.setId(1);
        detailQualite.setPointDeControle("test");
        DetailQualiteEntity existingEntity = new DetailQualiteEntity();
        detailQualite.setId(1);
        existingEntity.setPointDeControle("test");

        when(detailQualiteRepository.findById(1)).thenReturn(Optional.of(existingEntity));
        when(detailQualiteRepository.save(any(DetailQualiteEntity.class))).thenReturn(existingEntity);

        DetailQualite result = detailQualiteDaoAdapter.updateDetailQualite(detailQualite);
        assertNotNull(result);
        assertEquals(1, result.getId());

    }

    @Test
    void testupdateDetailQualiteWhenDuplicateExists() {
        DetailQualite detailQualite = new DetailQualite();
        detailQualite.setId(1);
        GrpQualite grp = new GrpQualite();
        grp.setId(1);
        detailQualite.setGroupe(grp);
        detailQualite.setPointDeControle("test");
        DetailQualiteEntity entity = new DetailQualiteEntity();
        entity.setId(1);
        entity.setPointDeControle("test2");
        DetailQualiteEntity entity2 = new DetailQualiteEntity();
        entity2.setId(2);
        List<DetailQualiteEntity> sameEntities = new ArrayList<>();
        sameEntities.add(entity2);

        when(detailQualiteRepository.getDetailQualitesByGrpQualiteIdAndAffaire(anyInt(), anyString())).thenReturn(sameEntities);
        when(detailQualiteRepository.findById(1)).thenReturn(Optional.of(entity));

        assertThrows(DuplicateElementException.class, () -> {
            detailQualiteDaoAdapter.updateDetailQualite(detailQualite);
        });

    }

    @Test
    void testSavDetailQualiteWhenDuplicateNotExists() throws DuplicateElementException{
        DetailQualite detailQualite = new DetailQualite();
        detailQualite.setId(1);
        detailQualite.setPointDeControle("test");
        DetailQualiteEntity existingEntity = new DetailQualiteEntity();
        detailQualite.setId(1);
        existingEntity.setPointDeControle("test");

        when(detailQualiteRepository.findById(1)).thenReturn(Optional.of(existingEntity));
        when(detailQualiteRepository.save(any(DetailQualiteEntity.class))).thenReturn(existingEntity);

        DetailQualite result = detailQualiteDaoAdapter.updateDetailQualite(detailQualite);
        assertNotNull(result);
        assertEquals(1, result.getId());

    }
    @Test
    void testUpdateDetailQualite()  throws DuplicateElementException{
        DetailQualite detailQualite = new DetailQualite();
        detailQualite.setId(1);
        DetailQualiteEntity entity = new DetailQualiteEntity();
        entity.setId(1);
        when(detailQualiteRepository.findById(1)).thenReturn(Optional.of(entity));
        when(detailQualiteRepository.save(any(DetailQualiteEntity.class))).thenReturn(entity);

        DetailQualite result = detailQualiteDaoAdapter.updateDetailQualite(detailQualite);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteDetailQualite() {
        DetailQualiteEntity entity = new DetailQualiteEntity();
        entity.setId(1);
        when(detailQualiteRepository.findById(1)).thenReturn(Optional.of(entity));

        detailQualiteDaoAdapter.deleteDetailQualite(1);

        verify(detailQualiteRepository, times(1)).delete(entity);
    }
}
