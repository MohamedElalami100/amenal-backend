package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.budget.core.domain.BudgetAchatAv;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.BudgetAchatAvEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.BudgetAchatAvRepository;
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
public class BudgetAchatAvDaoAdapterTest {

    @Mock
    private BudgetAchatAvRepository budgetAchatAvRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private BudgetAchatAvDaoAdapter budgetAchatAvDaoAdapter;

    @Test
    void testFindBudgetAchatAvById() {
        BudgetAchatAvEntity entity = new BudgetAchatAvEntity();
        entity.setId(1);

        when(budgetAchatAvRepository.findById(1)).thenReturn(Optional.of(entity));

        BudgetAchatAv result = budgetAchatAvDaoAdapter.findBudgetAchatAvById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllBudgetAchatAvs() {
        when(budgetAchatAvRepository.findAll()).thenReturn(Collections.emptyList());

        List<BudgetAchatAv> result = budgetAchatAvDaoAdapter.findAllBudgetAchatAvs();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveBudgetAchatAv() {
        BudgetAchatAv budgetAchatAv = new BudgetAchatAv();
        budgetAchatAv.setId(1);
        BudgetAchatAvEntity entity = new BudgetAchatAvEntity();
        entity.setId(1);

        when(budgetAchatAvRepository.save(any(BudgetAchatAvEntity.class))).thenReturn(entity);

        BudgetAchatAv result = budgetAchatAvDaoAdapter.saveBudgetAchatAv(budgetAchatAv);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateBudgetAchatAv() {
        BudgetAchatAv budgetAchatAv = new BudgetAchatAv();
        budgetAchatAv.setId(1);
        BudgetAchatAvEntity entity = new BudgetAchatAvEntity();
        entity.setId(1);
        when(budgetAchatAvRepository.findById(1)).thenReturn(Optional.of(entity));
        when(budgetAchatAvRepository.save(any(BudgetAchatAvEntity.class))).thenReturn(entity);

        BudgetAchatAv result = budgetAchatAvDaoAdapter.updateBudgetAchatAv(budgetAchatAv);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteBudgetAchatAv() {
        BudgetAchatAvEntity entity = new BudgetAchatAvEntity();
        entity.setId(1);
        when(budgetAchatAvRepository.findById(1)).thenReturn(Optional.of(entity));

        budgetAchatAvDaoAdapter.deleteBudgetAchatAv(1);

        verify(budgetAchatAvRepository, times(1)).delete(entity);
    }
}
