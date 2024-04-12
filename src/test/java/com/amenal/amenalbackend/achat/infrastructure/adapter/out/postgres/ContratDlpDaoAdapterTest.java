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

import com.amenal.amenalbackend.achat.core.domain.ContratDlp;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.ContratDlpEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.ContratDlpRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ContratDlpDaoAdapterTest {

    @Mock
    private ContratDlpRepository contratDlpRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private ContratDlpDaoAdapter contratDlpDaoAdapter;

    @Test
    void testFindContratDlpById() {
        ContratDlpEntity entity = new ContratDlpEntity();
        entity.setId(1);

        when(contratDlpRepository.findById(1)).thenReturn(Optional.of(entity));

        ContratDlp result = contratDlpDaoAdapter.findContratDlpById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllContratDlps() {
        when(contratDlpRepository.findAll()).thenReturn(Collections.emptyList());

        List<ContratDlp> result = contratDlpDaoAdapter.findAllContratDlps();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveContratDlp() {
        ContratDlp contratDlp = new ContratDlp();
        contratDlp.setId(1);
        ContratDlpEntity entity = new ContratDlpEntity();
        entity.setId(1);

        when(contratDlpRepository.save(any(ContratDlpEntity.class))).thenReturn(entity);

        ContratDlp result = contratDlpDaoAdapter.saveContratDlp(contratDlp);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateContratDlp() {
        ContratDlp contratDlp = new ContratDlp();
        contratDlp.setId(1);
        ContratDlpEntity entity = new ContratDlpEntity();
        entity.setId(1);
        when(contratDlpRepository.findById(1)).thenReturn(Optional.of(entity));
        when(contratDlpRepository.save(any(ContratDlpEntity.class))).thenReturn(entity);

        ContratDlp result = contratDlpDaoAdapter.updateContratDlp(contratDlp);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteContratDlp() {
        ContratDlpEntity entity = new ContratDlpEntity();
        entity.setId(1);
        when(contratDlpRepository.findById(1)).thenReturn(Optional.of(entity));

        contratDlpDaoAdapter.deleteContratDlp(1);

        verify(contratDlpRepository, times(1)).delete(entity);
    }
}
