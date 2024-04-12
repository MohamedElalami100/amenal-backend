package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.budget.core.domain.DetailProduit;
import com.amenal.amenalbackend.budget.core.domain.Tache;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailProduitEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailProduitRepository;
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
public class DetailProduitDaoAdapterTest {

    @Mock
    private DetailProduitRepository detailProduitRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private DetailProduitDaoAdapter detailProduitDaoAdapter;

    @Test
    void testFindDetailProduitById() {
        DetailProduitEntity entity = new DetailProduitEntity();
        entity.setId(1);

        when(detailProduitRepository.findById(1)).thenReturn(Optional.of(entity));

        DetailProduit result = detailProduitDaoAdapter.findDetailProduitById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllDetailProduits() {
        when(detailProduitRepository.findAll()).thenReturn(Collections.emptyList());

        List<DetailProduit> result = detailProduitDaoAdapter.findAllDetailProduits();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveDetailProduitWhenDuplicateExists() {
        DetailProduit detailProduit = new DetailProduit();
        detailProduit.setId(1);
        Tache tache = new Tache();
        tache.setId(1);
        detailProduit.setTache(tache);
        detailProduit.setReference("test");
        DetailProduitEntity entity = new DetailProduitEntity();
        entity.setId(1);
        DetailProduitEntity entity2 = new DetailProduitEntity();
        entity2.setId(2);
        List<DetailProduitEntity> sameEntities = new ArrayList<>();
        sameEntities.add(entity2);

        when(detailProduitRepository.getDetailProduitsByTacheIdAndDesignation(anyInt(), anyString())).thenReturn(sameEntities);

        assertThrows(DuplicateElementException.class, () -> {
            detailProduitDaoAdapter.saveDetailProduit(detailProduit);
        });

    }

    @Test
    void testSaveDetailProduitWhenDuplicateNotExists() throws DuplicateElementException{

        DetailProduit detailProduit = new DetailProduit();
        detailProduit.setId(1);
        Tache tache = new Tache();
        tache.setId(1);
        detailProduit.setTache(tache);
        detailProduit.setReference("test");
        DetailProduitEntity entity = new DetailProduitEntity();
        entity.setId(1);
        List<DetailProduitEntity> sameEntities = new ArrayList<>();

        when(detailProduitRepository.getDetailProduitsByTacheIdAndDesignation(anyInt(), anyString())).thenReturn(sameEntities);
        when(detailProduitRepository.save(any(DetailProduitEntity.class))).thenReturn(entity);

        DetailProduit result = detailProduitDaoAdapter.saveDetailProduit(detailProduit);
        assertNotNull(result);
        assertEquals(1, result.getId());

    }
    @Test
    void testUpdateDetailProduitWhenDuplicateNotExists() throws DuplicateElementException{
        DetailProduit detailProduit = new DetailProduit();
        detailProduit.setId(1);
        detailProduit.setReference("test");
        DetailProduitEntity existingEntity = new DetailProduitEntity();
        detailProduit.setId(1);
        existingEntity.setReference("test");

        when(detailProduitRepository.findById(1)).thenReturn(Optional.of(existingEntity));
        when(detailProduitRepository.save(any(DetailProduitEntity.class))).thenReturn(existingEntity);

        DetailProduit result = detailProduitDaoAdapter.updateDetailProduit(detailProduit);
        assertNotNull(result);
        assertEquals(1, result.getId());

    }

    @Test
    void testupdateDetailProduitWhenDuplicateExists() {
        DetailProduit detailProduit = new DetailProduit();
        detailProduit.setId(1);
        Tache tache = new Tache();
        tache.setId(1);
        detailProduit.setTache(tache);
        detailProduit.setReference("test");
        DetailProduitEntity entity = new DetailProduitEntity();
        entity.setId(1);
        entity.setReference("test2");
        DetailProduitEntity entity2 = new DetailProduitEntity();
        entity2.setId(2);
        List<DetailProduitEntity> sameEntities = new ArrayList<>();
        sameEntities.add(entity2);

        when(detailProduitRepository.getDetailProduitsByTacheIdAndDesignation(anyInt(), anyString())).thenReturn(sameEntities);
        when(detailProduitRepository.findById(1)).thenReturn(Optional.of(entity));

        assertThrows(DuplicateElementException.class, () -> {
            detailProduitDaoAdapter.updateDetailProduit(detailProduit);
        });

    }

    @Test
    void testSavDetailProduitWhenDuplicateNotExists() throws DuplicateElementException{
        DetailProduit detailProduit = new DetailProduit();
        detailProduit.setId(1);
        detailProduit.setReference("test");
        DetailProduitEntity existingEntity = new DetailProduitEntity();
        detailProduit.setId(1);
        existingEntity.setReference("test");

        when(detailProduitRepository.findById(1)).thenReturn(Optional.of(existingEntity));
        when(detailProduitRepository.save(any(DetailProduitEntity.class))).thenReturn(existingEntity);

        DetailProduit result = detailProduitDaoAdapter.updateDetailProduit(detailProduit);
        assertNotNull(result);
        assertEquals(1, result.getId());

    }
    @Test
    void testUpdateDetailProduit()  throws DuplicateElementException{
        DetailProduit detailProduit = new DetailProduit();
        detailProduit.setId(1);
        DetailProduitEntity entity = new DetailProduitEntity();
        entity.setId(1);
        when(detailProduitRepository.findById(1)).thenReturn(Optional.of(entity));
        when(detailProduitRepository.save(any(DetailProduitEntity.class))).thenReturn(entity);

        DetailProduit result = detailProduitDaoAdapter.updateDetailProduit(detailProduit);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteDetailProduit() {
        DetailProduitEntity entity = new DetailProduitEntity();
        entity.setId(1);
        when(detailProduitRepository.findById(1)).thenReturn(Optional.of(entity));

        detailProduitDaoAdapter.deleteDetailProduit(1);

        verify(detailProduitRepository, times(1)).delete(entity);
    }
}
