package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.budget.core.domain.DetailCharge;
import com.amenal.amenalbackend.budget.core.domain.Tache;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailChargeEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailChargeRepository;
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
public class DetailChargeDaoAdapterTest {

    @Mock
    private DetailChargeRepository detailChargeRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private DetailChargeDaoAdapter detailChargeDaoAdapter;

    @Test
    void testFindDetailChargeById() {
        DetailChargeEntity entity = new DetailChargeEntity();
        entity.setId(1);

        when(detailChargeRepository.findById(1)).thenReturn(Optional.of(entity));

        DetailCharge result = detailChargeDaoAdapter.findDetailChargeById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllDetailCharges() {
        when(detailChargeRepository.findAll()).thenReturn(Collections.emptyList());

        List<DetailCharge> result = detailChargeDaoAdapter.findAllDetailCharges();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveDetailChargeWhenDuplicateExists() {
        DetailCharge detailCharge = new DetailCharge();
        detailCharge.setId(1);
        Tache tache = new Tache();
        tache.setId(1);
        detailCharge.setTache(tache);
        detailCharge.setDesignation("test");
        DetailChargeEntity entity = new DetailChargeEntity();
        entity.setId(1);
        DetailChargeEntity entity2 = new DetailChargeEntity();
        entity2.setId(2);
        List<DetailChargeEntity> sameEntities = new ArrayList<>();
        sameEntities.add(entity2);

        when(detailChargeRepository.getDetailChargesByTacheIdAndDesignation(anyInt(), anyString())).thenReturn(sameEntities);

        assertThrows(DuplicateElementException.class, () -> {
            detailChargeDaoAdapter.saveDetailCharge(detailCharge);
        });

    }

    @Test
    void testSaveDetailChargeWhenDuplicateNotExists() throws DuplicateElementException{

        DetailCharge detailCharge = new DetailCharge();
        detailCharge.setId(1);
        Tache tache = new Tache();
        tache.setId(1);
        detailCharge.setTache(tache);
        detailCharge.setDesignation("test");
        DetailChargeEntity entity = new DetailChargeEntity();
        entity.setId(1);
        List<DetailChargeEntity> sameEntities = new ArrayList<>();

        when(detailChargeRepository.getDetailChargesByTacheIdAndDesignation(anyInt(), anyString())).thenReturn(sameEntities);
        when(detailChargeRepository.save(any(DetailChargeEntity.class))).thenReturn(entity);

        DetailCharge result = detailChargeDaoAdapter.saveDetailCharge(detailCharge);
        assertNotNull(result);
        assertEquals(1, result.getId());

    }
    @Test
    void testUpdateDetailChargeWhenDuplicateNotExists() throws DuplicateElementException{
        DetailCharge detailCharge = new DetailCharge();
        detailCharge.setId(1);
        detailCharge.setDesignation("test");
        DetailChargeEntity existingEntity = new DetailChargeEntity();
        existingEntity.setId(1);
        existingEntity.setDesignation("test");

        when(detailChargeRepository.findById(1)).thenReturn(Optional.of(existingEntity));
        when(detailChargeRepository.save(any(DetailChargeEntity.class))).thenReturn(existingEntity);

        DetailCharge result = detailChargeDaoAdapter.updateDetailCharge(detailCharge);
        assertNotNull(result);
        assertEquals(1, result.getId());

    }

    @Test
    void testupdateDetailChargeWhenDuplicateExists() {
        DetailCharge detailCharge = new DetailCharge();
        detailCharge.setId(1);
        Tache tache = new Tache();
        tache.setId(1);
        detailCharge.setTache(tache);
        detailCharge.setDesignation("test");
        DetailChargeEntity entity = new DetailChargeEntity();
        entity.setId(1);
        entity.setDesignation("test2");
        DetailChargeEntity entity2 = new DetailChargeEntity();
        entity2.setId(2);
        List<DetailChargeEntity> sameEntities = new ArrayList<>();
        sameEntities.add(entity2);

        when(detailChargeRepository.getDetailChargesByTacheIdAndDesignation(anyInt(), anyString())).thenReturn(sameEntities);
        when(detailChargeRepository.findById(1)).thenReturn(Optional.of(entity));

        assertThrows(DuplicateElementException.class, () -> {
            detailChargeDaoAdapter.updateDetailCharge(detailCharge);
        });

    }

    @Test
    void testSavDetailChargeWhenDuplicateNotExists() throws DuplicateElementException{
        DetailCharge detailCharge = new DetailCharge();
        detailCharge.setId(1);
        detailCharge.setDesignation("test");
        DetailChargeEntity existingEntity = new DetailChargeEntity();
        existingEntity.setId(1);
        existingEntity.setDesignation("test");

        when(detailChargeRepository.findById(1)).thenReturn(Optional.of(existingEntity));
        when(detailChargeRepository.save(any(DetailChargeEntity.class))).thenReturn(existingEntity);

        DetailCharge result = detailChargeDaoAdapter.updateDetailCharge(detailCharge);
        assertNotNull(result);
        assertEquals(1, result.getId());

    }
    @Test
    void testUpdateDetailCharge()  throws DuplicateElementException{
        DetailCharge detailCharge = new DetailCharge();
        detailCharge.setId(1);
        DetailChargeEntity entity = new DetailChargeEntity();
        entity.setId(1);
        when(detailChargeRepository.findById(1)).thenReturn(Optional.of(entity));
        when(detailChargeRepository.save(any(DetailChargeEntity.class))).thenReturn(entity);

        DetailCharge result = detailChargeDaoAdapter.updateDetailCharge(detailCharge);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteDetailCharge() {
        DetailChargeEntity entity = new DetailChargeEntity();
        entity.setId(1);
        when(detailChargeRepository.findById(1)).thenReturn(Optional.of(entity));

        detailChargeDaoAdapter.deleteDetailCharge(1);

        verify(detailChargeRepository, times(1)).delete(entity);
    }
}
