package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.budget.core.domain.Banque;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.BanqueEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.BanqueRepository;
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
public class BanqueDaoAdapterTest {

    @Mock
    private BanqueRepository banqueRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private BanqueDaoAdapter banqueDaoAdapter;

    @Test
    void testFindBanqueById() {
        BanqueEntity entity = new BanqueEntity();
        entity.setId(1);

        when(banqueRepository.findById(1)).thenReturn(Optional.of(entity));

        Banque result = banqueDaoAdapter.findBanqueById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllBanques() {
        when(banqueRepository.findAll()).thenReturn(Collections.emptyList());

        List<Banque> result = banqueDaoAdapter.findAllBanques();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveBanque() {
        Banque banque = new Banque();
        banque.setId(1);
        BanqueEntity entity = new BanqueEntity();
        entity.setId(1);

        when(banqueRepository.save(any(BanqueEntity.class))).thenReturn(entity);

        Banque result = banqueDaoAdapter.saveBanque(banque);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateBanque() {
        Banque banque = new Banque();
        banque.setId(1);
        BanqueEntity entity = new BanqueEntity();
        entity.setId(1);
        when(banqueRepository.findById(1)).thenReturn(Optional.of(entity));
        when(banqueRepository.save(any(BanqueEntity.class))).thenReturn(entity);

        Banque result = banqueDaoAdapter.updateBanque(banque);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteBanque() {
        BanqueEntity entity = new BanqueEntity();
        entity.setId(1);
        when(banqueRepository.findById(1)).thenReturn(Optional.of(entity));

        banqueDaoAdapter.deleteBanque(1);

        verify(banqueRepository, times(1)).delete(entity);
    }
}
