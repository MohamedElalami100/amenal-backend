package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.budget.core.domain.*;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.LotEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.ProduitEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.TacheEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.TacheRepository;
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
public class TacheDaoAdapterTest {

    @Mock
    private TacheRepository tacheRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private TacheDaoAdapter tacheDaoAdapter;

    @Test
    void testFindTacheById() {
        TacheEntity entity = new TacheEntity();
        entity.setId(1);

        when(tacheRepository.findById(1)).thenReturn(Optional.of(entity));

        Tache result = tacheDaoAdapter.findTacheById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllTaches() {
        when(tacheRepository.findAll()).thenReturn(Collections.emptyList());

        List<Tache> result = tacheDaoAdapter.findAllTaches();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveTacheWhenDuplicateExists() {
        Tache tache = new Tache();
        tache.setId(1);
        Produit produit = new Produit();
        produit.setId(1);
        MetreAv metre = new MetreAv();
        metre.setId(1);
        Avenant avenant = new Avenant();
        avenant.setId(1);
        metre.setAvenant(avenant);
        produit.setMetre(metre);
        tache.setProduit(produit);
        Lot lot = new Lot();
        lot.setId(1);
        lot.setDesignation("test");
        tache.setLot(lot);
        tache.setTitreActivite("test");
        TacheEntity entity = new TacheEntity();
        entity.setId(1);
        TacheEntity existingEntity = new TacheEntity();
        existingEntity.setId(2);
        List<TacheEntity> sameEntities = new ArrayList<>();
        sameEntities.add(existingEntity);

        when(tacheRepository.getTachesInOtherAvenants(anyInt())).thenReturn(new ArrayList<>());
        when(tacheRepository.getTachesByAvenantIdAndLotAndActivite(anyInt(), anyString(), anyString())).thenReturn(sameEntities);

        Tache result = tacheDaoAdapter.saveTache(tache);
        assertNotNull(result);
        assertEquals(2, result.getId());

    }

    @Test
    void testSaveTacheWhenDuplicateNotExists(){
        Tache tache = new Tache();
        tache.setId(1);
        Produit produit = new Produit();
        produit.setId(1);
        MetreAv metre = new MetreAv();
        metre.setId(1);
        Avenant avenant = new Avenant();
        avenant.setId(1);
        metre.setAvenant(avenant);
        produit.setMetre(metre);
        produit.setMetre(metre);
        tache.setProduit(produit);
        Lot lot = new Lot();
        lot.setId(1);
        lot.setDesignation("test");
        tache.setLot(lot);
        tache.setTitreActivite("test");
        TacheEntity entity = new TacheEntity();
        entity.setId(1);
        List<TacheEntity> sameEntities = new ArrayList<>();

        when(tacheRepository.getTachesInOtherAvenants(anyInt())).thenReturn(new ArrayList<>());
        when(tacheRepository.getTachesByAvenantIdAndLotAndActivite(anyInt(), anyString(), anyString())).thenReturn(sameEntities);
        when(tacheRepository.save(any(TacheEntity.class))).thenReturn(entity);

        Tache result = tacheDaoAdapter.saveTache(tache);

        assertNotNull(result);
        assertEquals(1, result.getId());

    }
    @Test
    void testUpdateTacheWhenDuplicateNotExists() throws DuplicateElementException{
        Tache tache = new Tache();
        tache.setId(1);
        tache.setTitreActivite("test");
        TacheEntity existingEntity = new TacheEntity();
        existingEntity.setId(1);
        existingEntity.setTitreActivite("test");

        when(tacheRepository.findById(1)).thenReturn(Optional.of(existingEntity));
        when(tacheRepository.save(any(TacheEntity.class))).thenReturn(existingEntity);

        Tache result = tacheDaoAdapter.updateTache(tache);
        assertNotNull(result);
        assertEquals(1, result.getId());

    }

    @Test
    void testupdateTacheWhenDuplicateExists() {
        Tache tache = new Tache();
        tache.setId(1);
        Produit produit = new Produit();
        produit.setId(1);
        MetreAv metre = new MetreAv();
        metre.setId(1);
        Avenant avenant = new Avenant();
        avenant.setId(1);
        metre.setAvenant(avenant);
        produit.setMetre(metre);
        tache.setProduit(produit);
        Lot lot = new Lot();
        lot.setId(1);
        lot.setDesignation("test");
        tache.setLot(lot);
        tache.setTitreActivite("test");
        tache.setId(1);
        TacheEntity entity = new TacheEntity();
        ProduitEntity produitEntity = new ProduitEntity();
        produit.setId(1);
        entity.setProduit(produitEntity);
        LotEntity lotEntity = new LotEntity();
        lotEntity.setId(1);
        lotEntity.setDesignation("test");
        entity.setLot(lotEntity);
        entity.setId(1);
        entity.setTitreActivite("test2");
        TacheEntity entity2 = new TacheEntity();
        entity2.setId(2);
        List<TacheEntity> sameEntities = new ArrayList<>();
        sameEntities.add(entity2);

        when(tacheRepository.getTachesInOtherAvenants(anyInt())).thenReturn(new ArrayList<>());
        when(tacheRepository.getTachesByAvenantIdAndLotAndActivite(anyInt(), anyString(), anyString())).thenReturn(sameEntities);
        when(tacheRepository.findById(1)).thenReturn(Optional.of(entity));

        assertThrows(DuplicateElementException.class, () -> {
            tacheDaoAdapter.updateTache(tache);
        });

    }

    @Test
    void testUpdateTacheWithTitreDefinedAndDuplicateNotExists() throws DuplicateElementException{
        Tache tache = new Tache();
        tache.setId(1);
        tache.setTitreActivite("test");
        TacheEntity existingEntity = new TacheEntity();
        existingEntity.setId(1);
        existingEntity.setTitreActivite("test");

        when(tacheRepository.findById(1)).thenReturn(Optional.of(existingEntity));
        when(tacheRepository.save(any(TacheEntity.class))).thenReturn(existingEntity);

        Tache result = tacheDaoAdapter.updateTache(tache);
        assertNotNull(result);
        assertEquals(1, result.getId());

    }
    @Test
    void testUpdateTache()  throws DuplicateElementException{
        Tache tache = new Tache();
        tache.setId(1);
        TacheEntity entity = new TacheEntity();
        entity.setId(1);
        when(tacheRepository.findById(1)).thenReturn(Optional.of(entity));
        when(tacheRepository.save(any(TacheEntity.class))).thenReturn(entity);

        Tache result = tacheDaoAdapter.updateTache(tache);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteTache() {
        TacheEntity entity = new TacheEntity();
        entity.setId(1);
        when(tacheRepository.findById(1)).thenReturn(Optional.of(entity));

        tacheDaoAdapter.deleteTache(1);

        verify(tacheRepository, times(1)).delete(entity);
    }
}
