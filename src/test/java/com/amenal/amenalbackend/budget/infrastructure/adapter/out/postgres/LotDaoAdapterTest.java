package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.budget.core.domain.Lot;
import com.amenal.amenalbackend.budget.core.domain.Project;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.LotEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.LotRepository;
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
public class LotDaoAdapterTest {

    @Mock
    private LotRepository lotRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private LotDaoAdapter lotDaoAdapter;

    @Test
    void testFindLotById() {
        LotEntity entity = new LotEntity();
        entity.setId(1);

        when(lotRepository.findById(1)).thenReturn(Optional.of(entity));

        Lot result = lotDaoAdapter.findLotById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllLots() {
        when(lotRepository.findAll()).thenReturn(Collections.emptyList());

        List<Lot> result = lotDaoAdapter.findAllLots();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveLotWhenDuplicateExists() {
        Lot lot = new Lot();
        lot.setId(1);
        Project project = new Project();
        project.setId(1);
        lot.setProject(project);
        lot.setDesignation("test");
        LotEntity entity = new LotEntity();
        entity.setId(1);
        LotEntity entity2 = new LotEntity();
        entity2.setId(2);
        List<LotEntity> sameEntities = new ArrayList<>();
        sameEntities.add(entity2);

        when(lotRepository.getLotsByProjectIdAndDesignation(anyInt(), anyString())).thenReturn(sameEntities);

        Lot result = lotDaoAdapter.saveLot(lot);
        assertNotNull(result);
        assertEquals(2, result.getId());

    }

    @Test
    void testSaveLotWhenDuplicateNotExists() throws DuplicateElementException{

        Lot lot = new Lot();
        lot.setId(1);
        Project project = new Project();
        project.setId(1);
        lot.setProject(project);
        lot.setDesignation("test");
        LotEntity entity = new LotEntity();
        entity.setId(1);
        List<LotEntity> sameEntities = new ArrayList<>();

        when(lotRepository.getLotsByProjectIdAndDesignation(anyInt(), anyString())).thenReturn(sameEntities);
        when(lotRepository.save(any(LotEntity.class))).thenReturn(entity);

        Lot result = lotDaoAdapter.saveLot(lot);
        assertNotNull(result);
        assertEquals(1, result.getId());

    }
    @Test
    void testUpdateLotWhenDuplicateNotExists() throws DuplicateElementException{
        Lot lot = new Lot();
        lot.setId(1);
        lot.setDesignation("test");
        LotEntity existingEntity = new LotEntity();
        lot.setId(1);
        existingEntity.setDesignation("test");

        when(lotRepository.findById(1)).thenReturn(Optional.of(existingEntity));
        when(lotRepository.save(any(LotEntity.class))).thenReturn(existingEntity);

        Lot result = lotDaoAdapter.updateLot(lot);
        assertNotNull(result);
        assertEquals(1, result.getId());

    }

    @Test
    void testupdateLotWhenDuplicateExists() {
        Lot lot = new Lot();
        lot.setId(1);
        Project project = new Project();
        project.setId(1);
        lot.setProject(project);
        lot.setDesignation("test");
        LotEntity entity = new LotEntity();
        entity.setId(1);
        entity.setDesignation("test2");
        LotEntity entity2 = new LotEntity();
        entity2.setId(2);
        List<LotEntity> sameEntities = new ArrayList<>();
        sameEntities.add(entity2);

        when(lotRepository.getLotsByProjectIdAndDesignation(anyInt(), anyString())).thenReturn(sameEntities);
        when(lotRepository.findById(1)).thenReturn(Optional.of(entity));

        assertThrows(DuplicateElementException.class, () -> {
            lotDaoAdapter.updateLot(lot);
        });

    }

    @Test
    void testSavLotWhenDuplicateNotExists() throws DuplicateElementException{
        Lot lot = new Lot();
        lot.setId(1);
        lot.setDesignation("test");
        LotEntity existingEntity = new LotEntity();
        lot.setId(1);
        existingEntity.setDesignation("test");

        when(lotRepository.findById(1)).thenReturn(Optional.of(existingEntity));
        when(lotRepository.save(any(LotEntity.class))).thenReturn(existingEntity);

        Lot result = lotDaoAdapter.updateLot(lot);
        assertNotNull(result);
        assertEquals(1, result.getId());

    }
    @Test
    void testUpdateLot()  throws DuplicateElementException{
        Lot lot = new Lot();
        lot.setId(1);
        LotEntity entity = new LotEntity();
        entity.setId(1);
        when(lotRepository.findById(1)).thenReturn(Optional.of(entity));
        when(lotRepository.save(any(LotEntity.class))).thenReturn(entity);

        Lot result = lotDaoAdapter.updateLot(lot);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteLot() {
        LotEntity entity = new LotEntity();
        entity.setId(1);
        when(lotRepository.findById(1)).thenReturn(Optional.of(entity));

        lotDaoAdapter.deleteLot(1);

        verify(lotRepository, times(1)).delete(entity);
    }
}
