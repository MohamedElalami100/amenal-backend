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

import com.amenal.amenalbackend.achat.core.domain.ContratPlafond;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.ContratPlafondEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.ContratPlafondRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ContratPlafondDaoAdapterTest {

    @Mock
    private ContratPlafondRepository contratPlafondRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private ContratPlafondDaoAdapter contratPlafondDaoAdapter;

    @Test
    void testFindContratPlafondById() {
        ContratPlafondEntity entity = new ContratPlafondEntity();
        entity.setId(1);

        when(contratPlafondRepository.findById(1)).thenReturn(Optional.of(entity));

        ContratPlafond result = contratPlafondDaoAdapter.findContratPlafondById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllContratPlafonds() {
        when(contratPlafondRepository.findAll()).thenReturn(Collections.emptyList());

        List<ContratPlafond> result = contratPlafondDaoAdapter.findAllContratPlafonds();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveContratPlafond() {
        ContratPlafond contratPlafond = new ContratPlafond();
        contratPlafond.setId(1);
        ContratPlafondEntity entity = new ContratPlafondEntity();
        entity.setId(1);

        when(contratPlafondRepository.save(any(ContratPlafondEntity.class))).thenReturn(entity);

        ContratPlafond result = contratPlafondDaoAdapter.saveContratPlafond(contratPlafond);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateContratPlafond() {
        ContratPlafond contratPlafond = new ContratPlafond();
        contratPlafond.setId(1);
        ContratPlafondEntity entity = new ContratPlafondEntity();
        entity.setId(1);
        when(contratPlafondRepository.findById(1)).thenReturn(Optional.of(entity));
        when(contratPlafondRepository.save(any(ContratPlafondEntity.class))).thenReturn(entity);

        ContratPlafond result = contratPlafondDaoAdapter.updateContratPlafond(contratPlafond);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteContratPlafond() {
        ContratPlafondEntity entity = new ContratPlafondEntity();
        entity.setId(1);
        when(contratPlafondRepository.findById(1)).thenReturn(Optional.of(entity));

        contratPlafondDaoAdapter.deleteContratPlafond(1);

        verify(contratPlafondRepository, times(1)).delete(entity);
    }
}
